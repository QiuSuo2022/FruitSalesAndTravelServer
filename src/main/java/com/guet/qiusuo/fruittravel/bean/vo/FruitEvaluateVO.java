package com.guet.qiusuo.fruittravel.bean.vo;

import com.guet.qiusuo.fruittravel.model.Evaluate;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;


@EqualsAndHashCode(callSuper = true)
@Data
public class FruitEvaluateVO extends Evaluate {
    @ApiModelProperty(value = "用户名")
    private String userName;

    @ApiModelProperty(value = "水果追评详细")
    private List<EvaluateVO> fruitReevaluate;

    @ApiModelProperty(value = "评论图片")
    private List<String> EvalImageUrl;
}
