package com.guet.qiusuo.fruittravel.bean.vo;

import com.guet.qiusuo.fruittravel.model.Cart;
import com.guet.qiusuo.fruittravel.model.ChildFruit;
import lombok.Data;

@Data
public class CartVO  {
    private ChildFruit childFruit;
    private Cart cart;
}
