package com.guet.qiusuo.fruittravel.service;

import com.guet.qiusuo.fruittravel.common.SystemConstants;
import com.guet.qiusuo.fruittravel.config.WxPayConfig;
import com.guet.qiusuo.fruittravel.dao.OrderFormMapper;
import com.guet.qiusuo.fruittravel.model.OrderForm;
import com.guet.qiusuo.fruittravel.utils.PayUtil;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import static java.lang.invoke.MethodHandles.lookup;
import static org.slf4j.LoggerFactory.getLogger;

@Service
public class PayService {

    private static final Logger LOG = getLogger(lookup().lookupClass());

    @Resource
    private WxPayConfig wxPayConfig;

    @Resource
    private OrderFormMapper orderFormMapper;


    /**
     * 请求wx下单接口
     * @param request
     * @return
     */
    public JSONObject wxPay(HttpServletRequest request, OrderForm orderForm){
        try {
            //商品名称
            //获取客户端的ip地址
            String spbill_create_ip = getIpAddr(request);
            String product = orderForm.getName();
            String orderFormId = orderForm.getId();
            String openid = request.getParameter("openid");

            String money = orderForm.getFee().toString();
            String nonce_str = getRandomStringByLength(32);

            //组装参数，用户生成统一下单接口的签名
            Map<String, String> packageParams = new HashMap<>();
            packageParams.put("appid",          wxPayConfig.getAppId());
            packageParams.put("mch_id",         wxPayConfig.getMchId());
            packageParams.put("nonce_str",      nonce_str);
            packageParams.put("body",           product);
            packageParams.put("out_trade_no",   orderFormId + "");
            packageParams.put("total_fee",      money + "");
            packageParams.put("spbill_create_ip", spbill_create_ip);
            packageParams.put("notify_url",     wxPayConfig.getNotifyDomain());
            packageParams.put("trade_type",     wxPayConfig.getTradeType());
            packageParams.put("openid", openid + "");

            // 把数组所有元素，按照“参数=参数值”的模式用“&”字符拼接成字符串
            String prestr = PayUtil.createLinkString(packageParams);

            //第一次签名 - 调用统一下单接口
            String mysign = PayUtil.sign(prestr, wxPayConfig.getPrivateKeyString(), "utf-8").toUpperCase();

            //拼接统一下单接口使用的xml数据，要将上一步生成的签名一起拼接进去
            String xml = "<xml>" +
                    "<appid>"               + wxPayConfig.getAppId() +             "</appid>"
                    + "<body><![CDATA["     + product +                    "]]></body>"
                    + "<mch_id>"            + wxPayConfig.getMchId() +            "</mch_id>"
                    + "<nonce_str>"         + nonce_str +                      "</nonce_str>"
                    + "<notify_url>"        + wxPayConfig.getNotifyDomain() + "</notify_url>"
                    + "<openid>"            + openid +                            "</openid>"
                    + "<out_trade_no>"      + orderFormId +                 "</out_trade_no>"
                    + "<spbill_create_ip>"  + spbill_create_ip +        "</spbill_create_ip>"
                    + "<total_fee>"         + money +                          "</total_fee>"
                    + "<trade_type>"        + wxPayConfig.getTradeType() +    "</trade_type>"
                    + "<sign>"              + mysign +                              "</sign>"
                    + "</xml>";

            //调用统一下单接口，并接受返回的结果
            String result = PayUtil.httpRequest(wxPayConfig.getNotifyDomain(), "POST", xml);

            // 想wx请求下单的结果存入map
            Map map = PayUtil.doXMLParse(result);

            //结果状态码
            String return_code = (String) map.get("return_code");
            String result_code = (String) map.get("result_code");

            //返回给小程序端需要的参数
            Map<String, Object> response = new HashMap<String, Object>();
            if (("SUCCESS").equals(result_code)  && return_code.equals(result_code)) {
                LOG.info("获取prepayId成功!");
                //wx预付订单号
                String prepay_id = (String) map.get("prepay_id");
                response.put("nonceStr", nonce_str);
                response.put("package", "prepay_id=" + prepay_id);
                Long timeStamp = System.currentTimeMillis() / 1000;
                response.put("timeStamp", timeStamp + "");
                //拼接签名需要的参数
                String stringSignTemp = "appId=" + wxPayConfig.getAppId() + "&nonceStr=" + nonce_str + "&package=prepay_id=" + prepay_id + "&signType=MD5&timeStamp=" + timeStamp;
                //再次签名，用于小程序端调用wx.requesetPayment方法
                String paySign = PayUtil.sign(stringSignTemp, wxPayConfig.getPrivateKeyString(), "utf-8").toUpperCase();

                response.put("paySign", paySign);
            }

            response.put("appid", wxPayConfig.getAppId());
            LOG.info("请求下单成功!");
            return new JSONObject(response);
        } catch (Exception e) {
            e.printStackTrace();
            LOG.info("请求下单失败!");
        }
        return null;
    }

    /**
     * 支付回调接口
     * @param request
     * @param response
     * @throws Exception
     */

    public void wxNotify(HttpServletRequest request, HttpServletResponse response)throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));
        String line = null;
        //sb为微信返回的xml
        StringBuilder sb = new StringBuilder();
        while ((line = br.readLine()) != null) {
            sb.append(line);
        }
        br.close();
        String notifyXml = sb.toString();
        String resXml = "";

        //解析xml
        Map map = PayUtil.doXMLParse(notifyXml);

        String returnCode = (String) map.get("return_code");
        String orderFormId = (String) map.get("out_trade_no");
        String total_fee = (String) map.get("total_fee");
        OrderForm orderForm  = orderFormMapper.selectByPrimaryKey(orderFormId).orElse(null);

        if ("SUCCESS".equals(returnCode)) {
            //回调验签时需要去除sign和空值参数
            Map<String, String> validParams = PayUtil.paraFilter(map);
            String prestr = PayUtil.createLinkString(validParams);
            //对回调的参数进行验签 && 对返回的金额与系统订单的金额进行比对等
            if (PayUtil.verify(prestr, (String) map.get("sign"), wxPayConfig.getPrivateKeyString(), "utf-8")
                && orderForm.getFee().toString().equals(total_fee)) {
                if (orderForm.getPayStatus().equals(SystemConstants.PAID)){
                    LOG.info("回调重复,订单已经支付!");
                }else {
                    orderForm.setPayStatus(SystemConstants.PAID);
                }
                resXml = "<xml>" +
                            "<return_code><![CDATA[SUCCESS]]></return_code>" +
                            "<return_msg> <![CDATA[OK]]>     </return_msg>" +
                         "</xml> ";
            }
        } else {
            resXml = "<xml>" +
                        "<return_code><![CDATA[FAIL]]>  </return_code>" +
                        "<return_msg> <![CDATA[报文为空]]></return_msg>" +
                    "</xml> ";
        }

        BufferedOutputStream out = new BufferedOutputStream(response.getOutputStream());
        out.write(resXml.getBytes());
        out.flush();
        out.close();
    }





    /**
     * 获取随机字符串的工具类
     * @param length
     * @return
     */
    private String getRandomStringByLength(int length) {
        String base = "abcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }

    /**
     * 获取ip
     * @param request
     * @return
     */
    private String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");
        if (!ip.isEmpty() && !"unKnown".equalsIgnoreCase(ip)) {
            //多次反向代理后会有多个ip值，第一个ip才是真实ip
            int index = ip.indexOf(",");
            if (index != -1) {
                return ip.substring(0, index);
            } else {
                return ip;
            }
        }
        ip = request.getHeader("X-Real-IP");
        if (!ip.isEmpty() && !"unKnown".equalsIgnoreCase(ip)) {
            return ip;
        }
        return request.getRemoteAddr();
    }
}
