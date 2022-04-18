package com.guet.qiusuo.fruittravel.bean.vo;

import com.guet.qiusuo.fruittravel.model.Scenic;
import com.guet.qiusuo.fruittravel.model.Ticket;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Map;

@EqualsAndHashCode(callSuper = true)
@Data
public class ScenicVO extends Scenic {

    private Map<String,Object> ticketMap;

    @ApiModelProperty(value = "景区门票描述")//默认同景区门票描述一致
    private String ticketDescription;
}

