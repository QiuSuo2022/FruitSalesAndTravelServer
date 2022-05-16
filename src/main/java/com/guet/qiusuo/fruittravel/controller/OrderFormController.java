package com.guet.qiusuo.fruittravel.controller;


import com.guet.qiusuo.fruittravel.bean.vo.OrderAndProductVO;
import com.guet.qiusuo.fruittravel.config.ErrorCode;
import com.guet.qiusuo.fruittravel.config.SystemException;
import com.guet.qiusuo.fruittravel.dao.ChildFruitMapper;
import com.guet.qiusuo.fruittravel.model.Cart;
import com.guet.qiusuo.fruittravel.model.OrderForm;
import com.guet.qiusuo.fruittravel.service.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Api(tags = "下单与支付")
@RestController
@RequestMapping("/OrderForm")
public class OrderFormController {

    private OrderFormService orderFormService;

    @Autowired
    public void setOrderFormService(OrderFormService orderFormService) {
        this.orderFormService = orderFormService;
    }


    @ApiOperation(value = "创建订单: order不填则表示是水果订单,反之则是景区订单,两者只需填一种")
    @PostMapping("/createFakeOrder")
    public OrderAndProductVO createFakeOrder (@RequestBody(required = false) OrderForm order,
                                      @RequestBody(required = false) List<Cart> CartList,
                                      @RequestBody(required = false) String address){
        if (order == null && CartList == null){
            throw new SystemException(ErrorCode.PARAM_NULL_ERROR);
        }
        OrderAndProductVO res = new OrderAndProductVO();
        /**如果order为空,表示当前订单为水果订单**/
        if (order == null){
            res =  orderFormService.createFruitOrder(CartList,address);
        }
        /**如果list为空,表示当前订单为景区订单**/
        if (CartList == null){
            res =  orderFormService.createScenicOrder(order);
        }
        return res;
    }

    @ApiOperation(value = "删除订单")
    @DeleteMapping
    public void deleteOrder(@RequestParam String orderFormId){
        orderFormService.deleteOrderForm(orderFormId);
    }

    @ApiOperation(value = "根据订单id获取订单信息")
    @PostMapping("/getOrderInfo")
    public OrderAndProductVO getOrderInfo(@RequestParam String orderFormId){
        return orderFormService.getOrderVOFormById(orderFormId);
    }

    @ApiOperation(value = "根据订单状态获取订单信息")
    @GetMapping("/getInfoByType")
    public List<OrderAndProductVO> getOrderVOsByType_User(@RequestParam Short type){
        return orderFormService.getOrderVOsByType_User(type);
    }
    @ApiOperation(value = "获取当前用户所有订单以及商品信息")
    @PostMapping("/getAllOrder")
    public List<OrderAndProductVO> getAllOrderVO_User(){
        return orderFormService.getAllOrderVO_User();
    }

    @ApiOperation(value = "查询数据库中订单支付状态")
    @GetMapping("/getPayStatus")
    public short getPaymentStatus(@RequestParam String orderFormId){
        return orderFormService.getPaymentStatus(orderFormId);
    }

    @ApiOperation(value = "假支付")
    @PostMapping("/fakePay")
    public boolean fakePay(@RequestParam String orderId){
        return orderFormService.fakePay(orderId);
    }

    @ApiOperation(value = "假退款")
    @PostMapping("/fakeRefund")
    public boolean fakeRefund(@RequestParam String orderId){
        return orderFormService.fakeRefund(orderId);
    }

    @ApiOperation(value = "设置订单状态",notes = "订单状态:待付款-0 待发货-1 待收货-2 待评价-3 售后-4")
    @PostMapping("/updateOrderStatus")
    public boolean UpdateOrderStatus(@RequestParam String orderId,@RequestParam Short orderStatus){
        return orderFormService.setOrderStatus(orderId,orderStatus);
    }

    @ApiOperation(value = "管理员根据状态获取所有订单",notes = "订单状态:待付款-0 待发货-1 待收货-2 待评价-3 已完成-4 售后-5")
    @PostMapping("/getOrdersByAdmin")
    public List<OrderAndProductVO> getOrderVOsByType_Admin(@RequestParam Short orderStatus){
        return orderFormService.getOrderVOsByType_User(orderStatus);
    }

    @ApiOperation(value = "管理员获取全部状态的订单",notes = "订单状态:待付款-0 待发货-1 待收货-2 待评价-3 已完成-4 售后-5")
    @PostMapping("/getAllOrdersByAdmin")
    public List<OrderAndProductVO> getAllOrderVO_Admin(){
        return orderFormService.getAllOrderVO_User();
    }

}
