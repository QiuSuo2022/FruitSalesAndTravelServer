package com.guet.qiusuo.fruittravel.controller;

import com.guet.qiusuo.fruittravel.model.Cart;
import com.guet.qiusuo.fruittravel.service.CartService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author lu
 */
@Api(tags = "购物车")
@RestController
@RequestMapping("/cart")
public class CartController {

    private CartService cartService;

    @Autowired
    public void  setCartService(CartService cartService){
        this.cartService = cartService;
    }

    @ApiOperation(value = "添加购物车水果项")
    @PostMapping
    public void addCart(@RequestBody Cart cart){
        cartService.addCart(cart);
    }

    @ApiOperation(value = "删除购物车水果项")
    @DeleteMapping
    public void deleteCart(@RequestParam String childFruitId){
        cartService.deleteCart(childFruitId);
    }

    @ApiOperation(value = "修改购物车水果项")
    @PutMapping("/updateCart")
    public void updateCart(@RequestBody Cart cart){
        cartService.updateCart(cart);
    }
    @ApiOperation(value = "获取购物车所有水果项")
    @GetMapping
    public List<Cart> selectCarts(){
        return cartService.selectCarts();
    }
}
