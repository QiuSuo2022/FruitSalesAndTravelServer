package com.guet.qiusuo.fruittravel.service;

import com.guet.qiusuo.fruittravel.bean.vo.OrderAndProductVO;
import com.guet.qiusuo.fruittravel.bean.vo.WxObject;
import com.guet.qiusuo.fruittravel.common.SystemConstants;
import com.guet.qiusuo.fruittravel.config.ErrorCode;
import com.guet.qiusuo.fruittravel.config.SystemException;
import com.guet.qiusuo.fruittravel.config.UserContextHolder;
import com.guet.qiusuo.fruittravel.dao.ChildFruitMapper;
import com.guet.qiusuo.fruittravel.dao.OrderFormMapper;
import com.guet.qiusuo.fruittravel.model.Cart;
import com.guet.qiusuo.fruittravel.model.ChildFruit;
import com.guet.qiusuo.fruittravel.model.Goods;
import com.guet.qiusuo.fruittravel.model.OrderForm;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static java.lang.invoke.MethodHandles.lookup;
import static org.slf4j.LoggerFactory.getLogger;


@Service
public class OrderFormService {

    private OrderFormMapper orderFormMapper;
    private static final Logger LOG = getLogger(lookup().lookupClass());
    private GoodsService goodsService;
    private PayService payService;

    private ChildFruitMapper childFruitMapper;

    @Autowired
    public void setChildFruitMapper(ChildFruitMapper childFruitMapper) {
        this.childFruitMapper = childFruitMapper;
    }

    @Autowired
    public void setPayService(PayService payService) {
        this.payService = payService;
    }

    @Autowired
    public void setOrderFormMapper(OrderFormMapper orderFormMapper) {
        this.orderFormMapper = orderFormMapper;
    }

    @Autowired
    public void setGoodsService(GoodsService goodsService) { this.goodsService = goodsService; }
    /**
     * 创建订单
     * @param orderForm
     * @return
     */
    public WxObject createOrderForm(HttpServletRequest request, OrderForm orderForm) throws JSONException {
        OrderForm check = orderFormMapper.selectByPrimaryKey(orderForm.getId()).orElse(null);
        if(check != null){
            LOG.info("订单重复创建");
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("hadCreated",true);
            WxObject wxObject = new WxObject();
            wxObject.setJsonObject(jsonObject);
            return wxObject;
        }
        long now = System.currentTimeMillis();
        orderForm.setId(UUID.randomUUID().toString().replace("-", ""));
        orderForm.setCreateTime(now);
        orderForm.setUpdateTime(now);
        orderForm.setCreateUserId(UserContextHolder.getUserId());
        orderForm.setUpdateUserId(UserContextHolder.getUserId());
        orderForm.setPayStatus(SystemConstants.UNPAID);
        orderForm.setStatus(SystemConstants.STATUS_ACTIVE);
        int i = orderFormMapper.insert(orderForm);
        if (i == 0){
            throw new SystemException(ErrorCode.INSERT_ERROR);
        }
        LOG.info("创建系统订单成功");
        //返回需要创建的Object数据
        WxObject wxObject = new WxObject();
        wxObject.setJsonObject(payService.wxPay(request,orderForm));
        return wxObject;
    }

    public OrderForm createFruitOrder(List<Cart> CartList,String address){
        OrderForm fruitOrder = new OrderForm();
        fruitOrder.setId(UUID.randomUUID().toString().replace("-",""));
        fruitOrder.setAddress(address);
        //统计总价
        int fee = 0;
        List<Goods> goodList = null;
        for (Cart cart:CartList) {
            /***循环创建goods表***/
            Goods goods = goodsService.addGood(cart, fruitOrder.getId());
            if (goods != null) {
                goodList.add(goods);
            }

            ChildFruit childFruit = childFruitMapper.selectByPrimaryKey(cart.getChildFruitId()).orElse(null);
            if (childFruit == null){
                continue;
            }
            //单价乘以数量
            fee += childFruit.getFruitPrice() * cart.getQuantity();
        }
        //信息插入数据库
        fruitOrder.setFee(fee);
        fruitOrder.setPayStatus(SystemConstants.UNPAID);
        fruitOrder.setStatus(SystemConstants.STATUS_ACTIVE);
        fruitOrder.setBindEvaluateId(null);
        fruitOrder.setHasEvaluate(SystemConstants.UNEVAL);
        fruitOrder.setExpress(null);
        long now = System.currentTimeMillis();
        fruitOrder.setCreateTime(now);
        fruitOrder.setUpdateTime(now);
        fruitOrder.setCreateUserId(UserContextHolder.getUserId());
        fruitOrder.setUpdateUserId(UserContextHolder.getUserId());

        int i = orderFormMapper.insert(fruitOrder);
        if (i == 0){
            throw new SystemException(ErrorCode.INSERT_ERROR);
        }

        LOG.info("创建id={}的订单以及商品映射成功",fruitOrder.getId());
        OrderAndProductVO vo = new OrderAndProductVO();
        vo.setOrderForm(fruitOrder);
        vo.setGoods(goodList);
        return fruitOrder;
    }

    /**
     * 更改订单完成状态
     * @param orderId
     * @param orderStatus
     * @return
     */
    public boolean setOrderStatus(String orderId, Short orderStatus){
        OrderForm order = orderFormMapper.selectByPrimaryKey(orderId).orElse(null);
        if(order != null){
            LOG.info("不存在该订单号!");
            throw new SystemException(ErrorCode.PARAM_ERROR);
        }
        if (orderStatus == null){
            throw new SystemException(ErrorCode.PARAM_NULL_ERROR);
        }
        order.setPayStatus(orderStatus);
        order.setUpdateUserId(UserContextHolder.getUserId());
        order.setUpdateTime(System.currentTimeMillis());
        int i = orderFormMapper.updateByPrimaryKey(order);
        if (i == 0){
            throw new SystemException(ErrorCode.UPDATE_ERROR);
        }
        LOG.info("更改订单状态完成");
        return true;
    }

    /**
     * 完成订单
     * @param orderId
     * @param evalId
     */
    public void finishOrder(String orderId,String evalId){
        OrderForm order = orderFormMapper.selectByPrimaryKey(orderId).orElse(null);
        if(order != null){
            LOG.info("不存在该订单号!");
            throw new SystemException(ErrorCode.PARAM_ERROR);
        }
        order.setPayStatus(SystemConstants.FINISHED);
        order.setHasEvaluate(SystemConstants.EVAL);
        order.setBindEvaluateId(evalId);
        order.setUpdateUserId(UserContextHolder.getUserId());
        order.setUpdateTime(System.currentTimeMillis());
        int i = orderFormMapper.updateByPrimaryKey(order);
        if (i == 0){
            throw new SystemException(ErrorCode.UPDATE_ERROR);
        }
        LOG.info("更改订单状态完成");
    }

    /**
     * 删除订单
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public void deleteOrderForm(String orderFormId){
        Optional<OrderForm> orderFormOptional = orderFormMapper.selectByPrimaryKey(orderFormId);
        OrderForm orderForm = orderFormOptional.orElseThrow(() -> new SystemException(ErrorCode.DELETE_ERROR));
        orderForm.setUpdateTime(System.currentTimeMillis());
        orderForm.setUpdateUserId(UserContextHolder.getUserId());
        orderForm.setStatus(SystemConstants.STATUS_NEGATIVE);
        int i = orderFormMapper.updateByPrimaryKey(orderForm);
        if (i == 0){
            throw new SystemException(ErrorCode.DELETE_ERROR);
        }
        LOG.info("删除订单成功");
    }




    /**
     * 查找订单
     * @param orderFormId
     * @return
     */
    public OrderForm getOrderForm(String orderFormId){
        Optional<OrderForm> orderFormOptional = orderFormMapper.selectByPrimaryKey(orderFormId);
        OrderForm orderForm = orderFormOptional.orElseThrow(()->new SystemException(ErrorCode.NO_FOUND_ORDER_FOMR));
        LOG.info("查找订单{}成功",orderFormId);
        return orderForm;
    }

    /**
     * 获取数据库中订单支付状态
     * @param orderFormId
     * @return
     */
    public short getPaymentStatus(String orderFormId){
        OrderForm orderForm = orderFormMapper.selectByPrimaryKey(orderFormId).orElse(null);
        if (orderForm == null){
            throw new SystemException(ErrorCode.PARAM_NULL_ERROR);
        }
        return orderForm.getPayStatus();
    }

    /**
     * 假支付
     * @param orderId
     */
    public boolean fakePay(String orderId){
        OrderForm orderForm = orderFormMapper.selectByPrimaryKey(orderId).orElse(null);
        if (orderForm == null){
            throw new SystemException(ErrorCode.PARAM_ERROR);
        }
        if (!orderForm.getPayStatus().equals(SystemConstants.UNPAID)){
            LOG.info("订单重复支付!");
            throw new SystemException(ErrorCode.PAY_ERROR);
        }
        long now = System.currentTimeMillis();
        orderForm.setPayStatus(SystemConstants.PAID);
        orderForm.setPayTime(now);
        LOG.info("假支付成功!");

        int i = orderFormMapper.updateByPrimaryKey(orderForm);
        if (i == 0){
            throw new SystemException(ErrorCode.UPDATE_ERROR);
        }
        return true;
    }

    /**
     * 假退款
     * @param orderId
     */
    public boolean fakeRefund(String orderId){
        OrderForm orderForm = orderFormMapper.selectByPrimaryKey(orderId).orElse(null);
        if (orderForm == null){
            throw new SystemException(ErrorCode.PARAM_ERROR);
        }
        if (orderForm.getPayStatus().equals(SystemConstants.UNPAID)){
            throw new SystemException(ErrorCode.UNPAID_ERROR);
        }
        orderForm.setPayStatus(SystemConstants.REFUND);
        int i = orderFormMapper.updateByPrimaryKey(orderForm);
        if (i == 0){
            throw new SystemException(ErrorCode.UPDATE_ERROR);
        }
        LOG.info("假退款成功!");
        return true;
    }
}
