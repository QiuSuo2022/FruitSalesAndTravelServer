package com.guet.qiusuo.fruittravel.bean.vo;

import com.guet.qiusuo.fruittravel.model.Cart;
import lombok.Data;

import java.util.List;

@Data
public class FruitOrderVO {
    List<Cart> cartList;
    String address;
}
