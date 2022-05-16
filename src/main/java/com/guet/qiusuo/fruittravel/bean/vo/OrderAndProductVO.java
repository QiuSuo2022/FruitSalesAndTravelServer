package com.guet.qiusuo.fruittravel.bean.vo;

import com.guet.qiusuo.fruittravel.model.Goods;
import com.guet.qiusuo.fruittravel.model.OrderForm;
import com.guet.qiusuo.fruittravel.model.Ticket;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
public class OrderAndProductVO {

    @ApiModelProperty(value = "订单信息")
    private OrderForm orderForm;

    @ApiModelProperty(value = "水果商品信息")
    private List<Goods> goods;

//    @ApiModelProperty(value = "景区信息")
//    private ScenicVO scenicVO;

    @ApiModelProperty(value = "当前门票类型信息")
    private Ticket thisTicket;
}
