package com.guet.qiusuo.fruittravel.bean.vo;

import com.guet.qiusuo.fruittravel.model.ChildFruit;
import com.guet.qiusuo.fruittravel.model.Fruit;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;
@Data
public class FruitAndChildFruitsVO  {

    @ApiModelProperty(value = "水果")
    private Fruit fruit;

    @ApiModelProperty(value = "该水果的子项")
    private List<ChildFruit> childFruits;
}
