package com.guet.qiusuo.fruittravel.bean.vo;

import com.guet.qiusuo.fruittravel.model.ChildFruit;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class FruitRecVO extends ChildFruit {
    @ApiModelProperty(value = "销量")
    private Integer sales;
}
