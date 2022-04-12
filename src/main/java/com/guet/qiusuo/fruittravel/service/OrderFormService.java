package com.guet.qiusuo.fruittravel.service;

import com.guet.qiusuo.fruittravel.common.SystemConstants;
import com.guet.qiusuo.fruittravel.config.ErrorCode;
import com.guet.qiusuo.fruittravel.config.SystemException;
import com.guet.qiusuo.fruittravel.config.UserContextHolder;
import com.guet.qiusuo.fruittravel.dao.OrderFormMapper;
import com.guet.qiusuo.fruittravel.model.OrderForm;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;
import java.util.UUID;

import static java.lang.invoke.MethodHandles.lookup;
import static org.slf4j.LoggerFactory.getLogger;

/**
 * @author : lu
 */
@Service
public class OrderFormService {

    private OrderFormMapper orderFormMapper;
    private static final Logger LOG = getLogger(lookup().lookupClass());

    private PayService payService;

    @Autowired
    public void setPayService(PayService payService) {
        this.payService = payService;
    }

    @Autowired
    public void setOrderFormMapper(OrderFormMapper orderFormMapper) {
        this.orderFormMapper = orderFormMapper;
    }

    /**
     * 创建订单
     * @param orderForm
     * @return
     */
    public JSONObject createOrderForm(HttpServletRequest request, OrderForm orderForm) throws JSONException {
        OrderForm check = orderFormMapper.selectByPrimaryKey(orderForm.getId()).orElse(null);
        if(check != null){
            LOG.info("订单重复创建");
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("hadCreated",true);
            return jsonObject;
        }
        long now = System.currentTimeMillis();
        orderForm.setId(UUID.randomUUID().toString().replace("-", ""));
        orderForm.setCreateTime(now);
        orderForm.setUpdateTime(now);
        orderForm.setCreateUserId(UserContextHolder.getUserId());
        orderForm.setUpdateUserId(UserContextHolder.getUserId());
        orderForm.setPayStatus(SystemConstants.UNPAID);
        orderForm.setStatus(SystemConstants.STATUS_ACTIVE);
        //防止外键约束错误
        if (orderForm.getFruitId() == null){
            orderForm.setFruitId(SystemConstants.nullFlag);
        }else if (orderForm.getScenicId() == null){
            orderForm.setScenicId(SystemConstants.nullFlag);
        }
        int i = orderFormMapper.insert(orderForm);
        if (i == 0){
            throw new SystemException(ErrorCode.INSERT_ERROR);
        }
        LOG.info("创建系统订单成功");
        //返回需要创建的Object数据
        return payService.wxPay(request,orderForm);
    }

    public OrderForm createFakeOrder(OrderForm orderForm){
        OrderForm check = orderFormMapper.selectByPrimaryKey(orderForm.getId()).orElse(null);
        if(check != null){
            LOG.info("订单重复创建");
            throw new SystemException(ErrorCode.FRUIT_ALREADY_EXITS);
        }
        long now = System.currentTimeMillis();
        orderForm.setId(UUID.randomUUID().toString().replace("-", ""));
        orderForm.setCreateTime(now);
        orderForm.setUpdateTime(now);
        orderForm.setCreateUserId(UserContextHolder.getUserId());
        orderForm.setUpdateUserId(UserContextHolder.getUserId());
        orderForm.setPayStatus(SystemConstants.UNPAID);
        orderForm.setStatus(SystemConstants.STATUS_ACTIVE);
        LOG.info(orderForm.getFruitId());
        LOG.info(orderForm.getScenicId());
        //防止外键约束错误
        if (orderForm.getFruitId() == null){
            orderForm.setFruitId(SystemConstants.nullFlag);
        }else if (orderForm.getScenicId() == null){
            orderForm.setScenicId(SystemConstants.nullFlag);
        }
        int i = orderFormMapper.insert(orderForm);
        if (i == 0){
            throw new SystemException(ErrorCode.INSERT_ERROR);
        }
        LOG.info("创建系统假订单成功");
        return orderForm;
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
        order.setPayStatus(orderStatus);
        order.setUpdateUserId(UserContextHolder.getUserId());
        order.setUpdateTime(System.currentTimeMillis());
        int i = orderFormMapper.updateByPrimaryKey(order);
        if (i == 0){
            throw new SystemException(ErrorCode.UPDATE_ERROR);
        }
        return true;
    }

    /**
     * 删除订单
     * @return
     */
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
