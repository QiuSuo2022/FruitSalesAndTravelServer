package com.guet.qiusuo.fruittravel.bean.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode()
@Data
public class TicketArray {
    @ApiModelProperty("景区门票Id")
    private String ticketId;

    @ApiModelProperty("景区门票价格")
    private Integer price;

    @ApiModelProperty("景区门票类型")
    private Short ticketType;

    @ApiModelProperty(value = "景区门票描述")
    private String ticketDescription;

    public void setTicketType(Short ticketType) {
        this.ticketType = ticketType;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public void setTicketDescription(String ticketDescription) {
        this.ticketDescription = ticketDescription;
    }

    public void setTicketId(String ticketId) {
        this.ticketId = ticketId;
    }

    public TicketArray(){}
}
