package com.guet.qiusuo.fruittravel.bean.vo;

import com.guet.qiusuo.fruittravel.model.Fruit;
import com.guet.qiusuo.fruittravel.model.OrderForm;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class OrderAndProductVO {

    @ApiModelProperty(value = "订单信息")
    private OrderForm orderForm;

    @ApiModelProperty(value = "水果信息")
    private Fruit fruit;

    @ApiModelProperty(value = "景区信息")
    private ScenicVO scenic;

}
