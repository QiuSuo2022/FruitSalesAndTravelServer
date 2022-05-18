package com.guet.qiusuo.fruittravel.bean.vo;

import com.guet.qiusuo.fruittravel.model.Fruit;
import com.guet.qiusuo.fruittravel.model.Scenic;
import lombok.Data;

import java.util.List;

@Data
public class SearchVO {
    List<Fruit> fruit;
    List<Scenic> scenic;
}
