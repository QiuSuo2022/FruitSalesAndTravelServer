package com.guet.qiusuo.fruittravel.controller;

import com.guet.qiusuo.fruittravel.bean.vo.ScenicVO;
import com.guet.qiusuo.fruittravel.common.PageList;
import com.guet.qiusuo.fruittravel.common.SystemConstants;
import com.guet.qiusuo.fruittravel.service.ScenicVOService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

public class SelectScenicController {
    private ScenicVOService scenicVOService;
    @ApiOperation("获取景区门票列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ticketId",value = "景区门票Id",dataType = "String"),
            @ApiImplicitParam(name = "nameLike",value = "景区名称模糊词",dataType = "String"),
            @ApiImplicitParam(name = "price",value = "景区门票价格",dataType = "Integer"),
            @ApiImplicitParam(name = "type",value = "景区门票类型",dataType = "Short"),
            @ApiImplicitParam(name = "description",value = "景区门票描述",dataType = "String"),
            @ApiImplicitParam(name = "ticketName",value = "景区门票名称",dataType = "String"),
            @ApiImplicitParam(name = "page",value = "页数",dataType = "int"),
            @ApiImplicitParam(name = "pageSize",value = "每页的数量",dataType = "int")
    })
    @GetMapping("/list")
        public PageList<ScenicVO> getScenicList(@RequestParam(required = false) String ticketId,
                                                @RequestParam(required = false) Integer price,
                                                @RequestParam(required = false) String nameLike,
                                                @RequestParam(required = false) Short type,
                                                @RequestParam(required = false) String description,
                                                @RequestParam(required = false) String ticketName,
                                                @RequestParam(required = false,defaultValue =
                                                        SystemConstants.DEFAULT_PAGE) Integer page,
                                                @RequestParam(required = false,defaultValue =
                                                        SystemConstants.DEFAULT_PAGE_SIZE) Integer pageSize){

            if(nameLike != null && nameLike.trim().isEmpty()) {
                nameLike = null;
            }
            if(ticketId != null && (ticketId.trim().isEmpty() || ticketId.length() == 0)){
                ticketId = null;
            }
            if(description != null && (description.trim().isEmpty() || description.length() == 0)){
                description = null;
            }
            if(ticketName != null && (ticketName.trim().isEmpty() || ticketName.length() == 0)){
                ticketName = null;
            }
            return scenicVOService.getScenicVOList(ticketId, nameLike, price, type, description, ticketName, page, pageSize);
        }
}
