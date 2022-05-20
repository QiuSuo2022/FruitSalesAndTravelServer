package com.guet.qiusuo.fruittravel.bean.vo;

import com.guet.qiusuo.fruittravel.model.Scenic;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class ScenicUrlVO extends Scenic {

    @ApiModelProperty(value = "景区图片")
    private List<String> imgUrl;
    private Integer Sales;

}
