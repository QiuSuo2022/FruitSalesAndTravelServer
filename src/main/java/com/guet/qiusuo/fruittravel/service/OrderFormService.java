package com.guet.qiusuo.fruittravel.service;

import com.guet.qiusuo.fruittravel.common.SystemConstants;
import com.guet.qiusuo.fruittravel.config.ErrorCode;
import com.guet.qiusuo.fruittravel.config.SystemException;
import com.guet.qiusuo.fruittravel.config.UserContextHolder;
import com.guet.qiusuo.fruittravel.dao.OrderFormMapper;
import com.guet.qiusuo.fruittravel.model.OrderForm;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    @Autowired
    public void setOrderFormMapper(OrderFormMapper orderFormMapper) {
        this.orderFormMapper = orderFormMapper;
    }

    /**
     * 创建订单
     * @param orderForm
     * @return
     */
    public OrderForm createOrderForm(OrderForm orderForm){
        long now = System.currentTimeMillis();
        orderForm.setId(UUID.randomUUID().toString().replace("-", ""));
        orderForm.setCreateTime(now);
        orderForm.setUpdateTime(now);
        orderForm.setCreateUserId(UserContextHolder.getUserId());
        orderForm.setUpdateUserId(UserContextHolder.getUserId());
        orderForm.setOrderFormStatus(SystemConstants.UNPAID);
        orderForm.setStatus(SystemConstants.STATUS_ACTIVE);
        int i = orderFormMapper.insert(orderForm);
        if (i == 0){
            throw new SystemException(ErrorCode.INSERT_ERROR);
        }
        return orderForm;
    }

    /**
     * 删除订单
     * @return
     */
    public boolean deleteOrderForm(String orderFormId){
        Optional<OrderForm> orderFormOptional = orderFormMapper.selectByPrimaryKey(orderFormId);
        OrderForm orderForm = orderFormOptional.orElseThrow(() -> new SystemException(ErrorCode.DELETE_ERROR));
        orderForm.setUpdateTime(System.currentTimeMillis());
        orderForm.setUpdateUserId(UserContextHolder.getUserId());
        orderForm.setStatus(SystemConstants.STATUS_NEGATIVE);
        return true;
    }
}
