package com.guet.qiusuo.fruittravel.bean.vo;

import com.guet.qiusuo.fruittravel.model.Evaluate;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author user
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class EvaluateVO extends Evaluate {
    @ApiModelProperty(value = "用户名")
    private String userName;
}
