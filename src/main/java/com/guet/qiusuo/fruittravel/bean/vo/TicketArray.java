package com.guet.qiusuo.fruittravel.bean.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode()
@Data
public class TicketArray {
    @ApiModelProperty("景区门票价格")
    private Integer price;

    @ApiModelProperty("景区门票类型")
    private Short ticketType;

    public void setTicketType(Short ticketType) {
        this.ticketType = ticketType;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public TicketArray(){}
}
