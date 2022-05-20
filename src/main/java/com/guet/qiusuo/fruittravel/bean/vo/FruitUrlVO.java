package com.guet.qiusuo.fruittravel.bean.vo;

import com.guet.qiusuo.fruittravel.model.Fruit;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class FruitUrlVO extends Fruit {

    @ApiModelProperty(value = "水果图片")
    private List<String> imgUrl;
    private Integer Sales;
}
