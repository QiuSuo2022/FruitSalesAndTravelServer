package com.guet.qiusuo.fruittravel.controller;

import com.guet.qiusuo.fruittravel.model.Goods;
import com.guet.qiusuo.fruittravel.service.GoodsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "订单商品表")
@RestController
@RequestMapping("/goods")
public class GoodsController {
    private GoodsService goodsService;

    @Autowired
    public void setGoodsService(GoodsService goodsService) { this.goodsService = goodsService; }

    @ApiOperation(value = "查找所有商品")
    @GetMapping
    public List<Goods> searchAllGoods(@RequestParam String orderId) {
        return goodsService.searchAllGoods(orderId);
    }

//    @ApiOperation(value = "添加商品")
//    @PostMapping
//    public void addGoods(@RequestBody Goods goods) {
//        goodsService.addGoods(goods);
//    }

    @ApiOperation(value = "修改商品")
    @PutMapping
    public void updateGoods(@RequestBody Goods goods) {
        goodsService.updateGoods(goods);
    }

    @ApiOperation(value = "删除商品")
    @DeleteMapping
    public void deleteGoods(@RequestParam String orderId) {
        goodsService.deleteGoods(orderId);
    }
}
