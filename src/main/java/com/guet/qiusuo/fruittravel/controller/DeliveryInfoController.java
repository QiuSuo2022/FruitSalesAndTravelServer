package com.guet.qiusuo.fruittravel.controller;


import com.guet.qiusuo.fruittravel.config.UserContextHolder;
import com.guet.qiusuo.fruittravel.model.DeliveryInfo;
import com.guet.qiusuo.fruittravel.service.DeliveryInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author : lu
 */
@Api(tags = "管理收货地址")
@RestController
@RequestMapping("/delivery")
public class DeliveryInfoController {

    DeliveryInfoService deliveryInfoService;

    @Autowired
    public void setDeliveryInfoService(DeliveryInfoService deliveryInfoService) {
        this.deliveryInfoService = deliveryInfoService;
    }

    @ApiOperation(value = "获取默认地址")
    @PostMapping("/getDefault")
    public DeliveryInfo getDefault (){
        return deliveryInfoService.getDefaultDeliInfo(UserContextHolder.getUserId());
    }

    @ApiOperation(value = "获取全部地址")
    @PostMapping("/getAll")
    public List<DeliveryInfo> getAll(){
        return deliveryInfoService.getAllDeliInfo();
    }

    @ApiOperation(value = "逻辑删除地址")
    @DeleteMapping
    public void delete(@RequestParam String deliId){
        deliveryInfoService.deleteDeliInfo(deliId);
    }

    @ApiOperation(value = "添加地址")
    @PutMapping
    public void add(@RequestBody DeliveryInfo deliveryInfo){
        deliveryInfoService.addDeliInfo(deliveryInfo);
    }

    @ApiOperation(value = "更新地址 - 设为默认地址的方式 status = 3")
    @PostMapping("/update")
    public void update(@RequestBody DeliveryInfo deliveryInfo){
        deliveryInfoService.updateDeliInfo(deliveryInfo);
    }
}
