package com.guet.qiusuo.fruittravel.bean.vo;

import com.guet.qiusuo.fruittravel.model.Fruit;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class FruitVO extends Fruit {

    @ApiModelProperty(value = "水果库存数量")
    private Integer stock;

    @ApiModelProperty(value = "水果子类名称")
    private String childFruitName;
}
