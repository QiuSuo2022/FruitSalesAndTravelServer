package com.guet.qiusuo.fruittravel.controller;


import com.guet.qiusuo.fruittravel.config.ErrorCode;
import com.guet.qiusuo.fruittravel.config.SystemException;
import com.guet.qiusuo.fruittravel.model.OrderForm;
import com.guet.qiusuo.fruittravel.service.OrderFormService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : lu
 */
@Api(tags = "订单")
@RestController
@RequestMapping("/OrderForm")
public class OrderFormController {

    private OrderFormService orderFormService;

    @Autowired
    public void setOrderFormService(OrderFormService orderFormService) {
        this.orderFormService = orderFormService;
    }

    @ApiOperation(value = "创建预付订单")
    @PostMapping("/createOrder")
    public OrderForm createOrder (@RequestBody OrderForm order){
        if (order == null){
            throw new SystemException(ErrorCode.PARAM_NULL_ERROR);
        }
        return orderFormService.createOrderForm(order);
    }

    @ApiOperation(value = "删除订单")
    @RequestMapping("/deleteOrder")
    public boolean deleteOrder(@Parameter String orderId){
        return orderFormService.deleteOrderForm(orderId);
    }

    @ApiOperation(value = "回调接口")
    @RequestMapping("/notify")
    public String orderNotify(){
        return "";
    }
}
