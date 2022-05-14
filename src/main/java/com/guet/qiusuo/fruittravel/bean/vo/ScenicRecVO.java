package com.guet.qiusuo.fruittravel.bean.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class ScenicRecVO {

    @ApiModelProperty(value = "景区id")
    private String ScenicId;

    @ApiModelProperty(value = "景区名")
    private String scenicName;

    @ApiModelProperty(value = "门票价格")
    private Integer ticketPrice;

    @ApiModelProperty(value = "景区销量")
    private Integer sales;

    @ApiModelProperty(value = "图片url")
    private String imageUrl;
}
