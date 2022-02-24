package com.guet.qiusuo.fruittravel.controller;

import com.guet.qiusuo.fruittravel.common.PageList;
import com.guet.qiusuo.fruittravel.common.SystemConstants;
import com.guet.qiusuo.fruittravel.model.Scenic;
import com.guet.qiusuo.fruittravel.service.ScenicService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

public class SelectScenicController {
    private ScenicService scenicService;
    @ApiOperation("获取景区列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id",value = "景区ID",dataType = "String"),
            @ApiImplicitParam(name = "scenicName",value = "景区名称",dataType = "String"),
            @ApiImplicitParam(name = "nameLike",value = "景区名称模糊词",dataType = "String"),
            @ApiImplicitParam(name = "location",value = "景区地理位置",dataType = "String"),
            @ApiImplicitParam(name = "type",value = "景区类型",dataType = "Short"),
            @ApiImplicitParam(name = "description",value = "景区描述",dataType = "String"),
            @ApiImplicitParam(name = "openingHours",value = "景区开放时间",dataType = "String"),
            @ApiImplicitParam(name = "page",value = "页数",dataType = "int"),
            @ApiImplicitParam(name = "pageSize",value = "每页的数量",dataType = "int")
    })
    @GetMapping("/list")
        public PageList<Scenic> getScenicList(@RequestParam(required = false) String id,
                                              @RequestParam(required = false) String scenicName,
                                              @RequestParam(required = false) String location,
                                              @RequestParam(required = false) String nameLike,
                                              @RequestParam(required = false) Short type,
                                              @RequestParam(required = false) String description,
                                              @RequestParam(required = false) String openingHours,
                                              @RequestParam(required = false,defaultValue =
                                                        SystemConstants.DEFAULT_PAGE) Integer page,
                                              @RequestParam(required = false,defaultValue =
                                                        SystemConstants.DEFAULT_PAGE_SIZE) Integer pageSize){

            if(nameLike != null && nameLike.trim().isEmpty()) {
                nameLike = null;
            }
            if(id != null && (id.trim().isEmpty() || id.length() == 0)){
                id = null;
            }
            if(scenicName != null && (scenicName.trim().isEmpty() || scenicName.length() == 0)){
                scenicName = null;
            }
            if(description != null && (description.trim().isEmpty() || description.length() == 0)){
                description = null;
            }
            if(location != null && (location.trim().isEmpty() || location.length() == 0)){
                location = null;
            }
            if(openingHours != null && (openingHours.trim().isEmpty() || openingHours.length() == 0)){
                openingHours = null;
            }
            return scenicService.getScenicList(id,scenicName, nameLike,location, openingHours, description, type, page, pageSize);
        }
}
