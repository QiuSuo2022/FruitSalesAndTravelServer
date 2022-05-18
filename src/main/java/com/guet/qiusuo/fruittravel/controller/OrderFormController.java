package com.guet.qiusuo.fruittravel.controller;


import com.guet.qiusuo.fruittravel.bean.vo.FruitOrderVO;
import com.guet.qiusuo.fruittravel.bean.vo.OrderAndProductVO;
import com.guet.qiusuo.fruittravel.bean.vo.OrderVO;
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


    @ApiOperation(value = "创建订单",tags = "景区订单中address表示门票类型(1成人 2儿童 3老人)  express表示数量")
    @PostMapping("/createFakeOrder")
    public OrderAndProductVO createFakeOrder (@RequestBody OrderVO orderVO){
        if (orderVO == null){
            throw new SystemException(ErrorCode.PARAM_NULL_ERROR);
        }
        if (orderVO.getScenicOrder() != null && orderVO.getFruitOrder()!= null){
            throw new SystemException(ErrorCode.PARAM_NULL_ERROR);
        }
        OrderAndProductVO res = new OrderAndProductVO();
        /**如果ScenicOrder为空,表示当前订单为水果订单**/
        if (orderVO.getScenicOrder() == null){
            res =  orderFormService.createFruitOrder(orderVO.getFruitOrder());
        }
        /**如果list为空,表示当前订单为景区订单**/
        if (orderVO.getFruitOrder() == null){
            res =  orderFormService.createScenicOrder(orderVO.getScenicOrder());
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

    @ApiOperation(value = "设置订单状态",notes = "订单状态:待付款-0 待发货-1 待收货-2 待评价-3 已完成-4 售后-5")
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
