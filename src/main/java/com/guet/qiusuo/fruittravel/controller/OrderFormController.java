package com.guet.qiusuo.fruittravel.controller;


import com.guet.qiusuo.fruittravel.bean.vo.OrderAndProductVO;
import com.guet.qiusuo.fruittravel.bean.vo.WxObject;
import com.guet.qiusuo.fruittravel.common.SystemConstants;
import com.guet.qiusuo.fruittravel.config.ErrorCode;
import com.guet.qiusuo.fruittravel.config.SystemException;
import com.guet.qiusuo.fruittravel.model.Goods;
import com.guet.qiusuo.fruittravel.model.OrderForm;
import com.guet.qiusuo.fruittravel.service.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author : lu
 */
@Api(tags = "下单与支付")
@RestController
@RequestMapping("/OrderForm")
public class OrderFormController {

    private PayService payService;

    private OrderFormService orderFormService;

    private ScenicService scenicService;

    private FruitService fruitService;

    private GoodsService goodsService;

    @Autowired
    public void setFruitService(FruitService fruitService) {
        this.fruitService = fruitService;
    }

    @Autowired
    public void setScenicService(ScenicService scenicService) { this.scenicService = scenicService; }

    @Autowired
    public void setOrderFormService(OrderFormService orderFormService) {
        this.orderFormService = orderFormService;
    }

    @Autowired
    public void setPayService(PayService payService) {
        this.payService = payService;
    }

    @Autowired
    public void setGoodsService(GoodsService goodsService) { this.goodsService = goodsService; }
    /**
     * 用户未取消支付订单情况下:
     * @param request
     * @param order
     * @return
     * @throws JSONException
     */
    @ApiOperation(value = "创建预付订单,返回请求wx支付的Object")
    @PostMapping("/createOrder")
    public WxObject createOrder (HttpServletRequest request, @RequestBody OrderForm order) throws JSONException {
        if (order == null){
            throw new SystemException(ErrorCode.PARAM_NULL_ERROR);
        }
        return orderFormService.createOrderForm(request,order);
    }

    @ApiOperation(value = "提交假订单: fee - 总付款金额  price - 商品单价 amount - 商品数目")
    @PostMapping("/createFakeOrder")
    public OrderForm createFakeOrder (@RequestBody OrderForm order){
        if (order == null){
            throw new SystemException(ErrorCode.PARAM_NULL_ERROR);
        }
        return orderFormService.createFakeOrder(order);
    }

    @ApiOperation(value = "删除订单")
    @DeleteMapping
    public void deleteOrder(@RequestParam String orderFormId){
        orderFormService.deleteOrderForm(orderFormId);
    }

    @ApiOperation(value = "获取订单信息")
    @PostMapping("/getOrderInfo")
    public OrderForm getOrderInfo(@RequestParam String orderFormId){
        return orderFormService.getOrderForm(orderFormId);
    }

    @ApiOperation(value = "获取订单以及商品信息")
    @PostMapping("/getOrderAndProduct")
    public OrderAndProductVO getOrderAndProduct(@RequestParam String orderFormId) throws JSONException {
        OrderAndProductVO object = new OrderAndProductVO();
        OrderForm orderForm = orderFormService.getOrderForm(orderFormId);
        Goods goods = goodsService.selectGoodsByOrderId(orderFormId);
        object.setOrderForm(orderForm);
        if (goods.getScenicId().equals(SystemConstants.nullFlag)){
            //如果是水果订单
            object.setFruit(fruitService.getFruit(goods.getFruitId()));
            object.setScenic(null);
        }else if (goods.getFruitId().equals(SystemConstants.nullFlag)){
            //如果是景区订单
            object.setScenic(scenicService.getScenicVOByScenicId(goods.getScenicId()));
            object.setFruit(null);
        }
        return object;
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

    @ApiOperation(value = "设置订单完成状态",notes = "订单状态:未支付-0 已支付-1 待发货-2 已发货-3 已完成-4 已退款-5")
    @PostMapping("/setOrderStatus")
    public boolean payStatus(@RequestParam String orderId,@RequestParam Short orderStatus){
        return orderFormService.setOrderStatus(orderId,orderStatus);
    }

    /**
     * wx支付回调接口
     * @param request
     * @param response
     * @throws Exception
     */
    @PostMapping("/notify")
    public void wxNotify(HttpServletRequest request, HttpServletResponse response) throws Exception {
        payService.wxNotify(request,response);
    }
}
