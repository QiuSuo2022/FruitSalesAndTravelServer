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


}

