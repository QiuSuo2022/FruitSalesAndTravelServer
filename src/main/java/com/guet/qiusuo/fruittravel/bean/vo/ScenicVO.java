package com.guet.qiusuo.fruittravel.bean.vo;

import com.guet.qiusuo.fruittravel.model.Scenic;
import com.guet.qiusuo.fruittravel.model.Ticket;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.Map;

@EqualsAndHashCode(callSuper = true)
@Data
public class ScenicVO extends Scenic {

    private ArrayList<TicketArray> ticketList;

    @ApiModelProperty(value = "水果图片")
    private String imgUrl;

}

