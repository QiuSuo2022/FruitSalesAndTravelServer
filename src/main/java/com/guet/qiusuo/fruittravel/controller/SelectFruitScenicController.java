package com.guet.qiusuo.fruittravel.controller;

import com.guet.qiusuo.fruittravel.common.PageList;
import com.guet.qiusuo.fruittravel.common.SystemConstants;
import com.guet.qiusuo.fruittravel.model.FruitScenic;
import com.guet.qiusuo.fruittravel.service.SelectFruitScenicService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

public class SelectFruitScenicController {
    private SelectFruitScenicService selectFruitScenicService;
    @ApiOperation("获取水果和景区列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "fruitId",value = "水果ID",dataType = "String"),
            @ApiImplicitParam(name = "fruitName",value = "水果名称",dataType = "String"),
            @ApiImplicitParam(name = "nameLike",value = "名称模糊词",dataType = "String"),
            @ApiImplicitParam(name = "fruitPrice",value = "水果价格",dataType = "String"),
            @ApiImplicitParam(name = "departurePoint",value = "发货地点",dataType = "String"),
            @ApiImplicitParam(name = "fruitDescription",value = "水果描述",dataType = "String"),
            @ApiImplicitParam(name = "deliveryCost",value = "快递费",dataType = "Integer"),
            @ApiImplicitParam(name = "scenicId",value = "景区ID",dataType = "String"),
            @ApiImplicitParam(name = "scenicName",value = "景区名称",dataType = "String"),
            @ApiImplicitParam(name = "location",value = "景区地理位置",dataType = "String"),
            @ApiImplicitParam(name = "type",value = "景区类型",dataType = "Short"),
            @ApiImplicitParam(name = "scenicDescription",value = "景区描述",dataType = "String"),
            @ApiImplicitParam(name = "openingHours",value = "景区开放时间",dataType = "String"),
            @ApiImplicitParam(name = "page",value = "页数",dataType = "int"),
            @ApiImplicitParam(name = "pageSize",value = "每页的数量",dataType = "int")
    })
    @GetMapping("/fruit-scenic-list")
        public PageList<FruitScenic> getFruitScenicList(@RequestParam(required = false) String fruitId,
                                                        @RequestParam(required = false) String fruitName,
                                                        @RequestParam(required = false) String fruitPrice,
                                                        @RequestParam(required = false) String nameLike,
                                                        @RequestParam(required = false) String departurePoint,
                                                        @RequestParam(required = false) String fruitDescription,
                                                        @RequestParam(required = false) Integer deliveryCost,
                                                        @RequestParam(required = false) String scenicId,
                                                        @RequestParam(required = false) String scenicName,
                                                        @RequestParam(required = false) String location,
                                                        @RequestParam(required = false) Short type,
                                                        @RequestParam(required = false) String scenicDescription,
                                                        @RequestParam(required = false) String openingHours,
                                                        @RequestParam(required = false,defaultValue =
                                                           SystemConstants.DEFAULT_PAGE) Integer page,
                                                        @RequestParam(required = false,defaultValue =
                                                           SystemConstants.DEFAULT_PAGE_SIZE) Integer pageSize) {
        if(nameLike != null && nameLike.trim().isEmpty()) {
            nameLike = null;
        }
        if(fruitId != null && (fruitId.trim().isEmpty() || fruitId.length() == 0)){
            fruitId = null;
        }
        if(scenicId != null && (scenicId.trim().isEmpty() || scenicId.length() == 0)){
            scenicId = null;
        }
        if(scenicName != null && (scenicName.trim().isEmpty() || scenicName.length() == 0)){
            scenicName = null;
        }
        if(fruitName != null && (fruitName.trim().isEmpty() || fruitName.length() == 0)){
            fruitName = null;
        }
        return selectFruitScenicService.getFruitScenicList(fruitId,fruitName,fruitPrice,nameLike,departurePoint,fruitDescription,
                deliveryCost,scenicId,scenicName,location,type,scenicDescription,openingHours,page,pageSize);
    }
}
