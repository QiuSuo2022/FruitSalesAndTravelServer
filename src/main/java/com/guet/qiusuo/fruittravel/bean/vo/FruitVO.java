package com.guet.qiusuo.fruittravel.bean.vo;

import com.guet.qiusuo.fruittravel.model.Fruit;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import net.bytebuddy.implementation.bind.annotation.SuperCall;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class FruitVO extends Fruit {

    @ApiModelProperty(value = "水果子项id")
    private String childFruitId;

    @ApiModelProperty(value = "水果库存数量")
    private Integer stock;

    @ApiModelProperty(value = "水果子类名称")
    private String childFruitName;

    @ApiModelProperty(value = "水果子类最低价格")
    private Integer childFruitLowestPrice;

    @ApiModelProperty(value = "销量")
    private Integer sales;

    @ApiModelProperty(value = "水果子项图片url")
    private List<String> childFImageUrl;

    @ApiModelProperty(value = "水果图片url")
    private List<String> fruitImageUrl;
}
