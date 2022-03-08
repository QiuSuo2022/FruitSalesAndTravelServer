package com.guet.qiusuo.fruittravel.controller;

import com.guet.qiusuo.fruittravel.common.PageList;
import com.guet.qiusuo.fruittravel.common.SystemConstants;
import com.guet.qiusuo.fruittravel.model.Fruit;
import com.guet.qiusuo.fruittravel.model.Scenic;
import com.guet.qiusuo.fruittravel.service.FruitService;
import com.guet.qiusuo.fruittravel.service.ScenicService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

public class SelectFruitScenicController {
    private ScenicService scenicService;
    private FruitService fruitService;

    @GetMapping("/fruit-scenic-list")
    @ApiOperation("获取水果列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "fruitId",value = "水果ID",dataType = "String"),
            @ApiImplicitParam(name = "nameLike",value = "名称模糊词",dataType = "String"),
            @ApiImplicitParam(name = "page",value = "页数",dataType = "int"),
            @ApiImplicitParam(name = "pageSize",value = "每页的数量",dataType = "int")
    })
        public PageList<Fruit> getFruitScenicList01(@RequestParam(required = false) String fruitId,
                                                    @RequestParam(required = false) String fruitName,
                                                    @RequestParam(required = false) String fruitPrice,
                                                    @RequestParam(required = false) String nameLike,
                                                    @RequestParam(required = false) String departurePoint,
                                                    @RequestParam(required = false) String fruitDescription,
                                                    @RequestParam(required = false) Integer deliveryCost,
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
        if(fruitName != null && (fruitName.trim().isEmpty() || fruitName.length() == 0)){
            fruitName = null;
        }
        return fruitService.getFruitList(fruitId,fruitName,fruitPrice,nameLike,departurePoint,fruitDescription,
                deliveryCost,page,pageSize);
    }

    @ApiOperation("获取景区列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id",value = "景区ID",dataType = "String"),
            @ApiImplicitParam(name = "nameLike",value = "景区名称模糊词",dataType = "String"),
            @ApiImplicitParam(name = "page",value = "页数",dataType = "int"),
            @ApiImplicitParam(name = "pageSize",value = "每页的数量",dataType = "int")
    })
    public PageList<Scenic> getFruitScenicList02(@RequestParam(required = false) String scenicId,
                                                 @RequestParam(required = false) String scenicName,
                                                 @RequestParam(required = false) String location,
                                                 @RequestParam(required = false) String nameLike,
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
        if(scenicId != null && (scenicId.trim().isEmpty() || scenicId.length() == 0)){
            scenicId = null;
        }
        if(scenicName != null && (scenicName.trim().isEmpty() || scenicName.length() == 0)){
            scenicName = null;
        }
        return scenicService.getScenicList(scenicId,scenicName,location,nameLike,type,scenicDescription,openingHours,
                page,pageSize);
    }
}
