package com.guet.qiusuo.fruittravel.bean.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class FruitRecVO {
    @ApiModelProperty(value = "水果父类id")
    private String fruitId;

    @ApiModelProperty(value = "水果子类id")
    private String childFruitId;

    @ApiModelProperty(value = "水果子类名称")
    private String childFruitName;

    @ApiModelProperty(value = "水果子类最低价格")
    private Integer childFruitLowestPrice;

    @ApiModelProperty(value = "销量")
    private Integer sales;

    @ApiModelProperty(value = "图片url")
    private String imageUrl;
}
