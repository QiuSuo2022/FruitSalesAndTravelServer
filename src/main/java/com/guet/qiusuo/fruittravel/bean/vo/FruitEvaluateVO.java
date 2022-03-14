package com.guet.qiusuo.fruittravel.bean.vo;

import com.guet.qiusuo.fruittravel.model.Evaluate;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class FruitEvaluateVO extends Evaluate {
    @ApiModelProperty(value = "水果追评详细")
    private String fruitReevaluate;
}
