package com.guet.qiusuo.fruittravel.bean.vo;

import com.guet.qiusuo.fruittravel.model.Scenic;
import com.guet.qiusuo.fruittravel.model.Ticket;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class ScenicVO extends Scenic {

    @ApiModelProperty(value = "景区门票Id")
    private String ticketId;

    @ApiModelProperty(value = "景区门票价格")
    private Integer price;

    @ApiModelProperty(value = "景区门票类型")
    private Short ticketType;

    @ApiModelProperty(value = "景区门票描述")
    private String ticketDescription;
}
