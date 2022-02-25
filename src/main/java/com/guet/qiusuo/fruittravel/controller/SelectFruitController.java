package com.guet.qiusuo.fruittravel.controller;

import com.guet.qiusuo.fruittravel.common.PageList;
import com.guet.qiusuo.fruittravel.common.SystemConstants;
import com.guet.qiusuo.fruittravel.model.Fruit;
import com.guet.qiusuo.fruittravel.service.FruitService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

public class SelectFruitController {
    private FruitService fruitService;
    @ApiOperation("获取水果列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id",value = "水果ID",dataType = "String"),
            @ApiImplicitParam(name = "fruitName",value = "水果名称",dataType = "String"),
            @ApiImplicitParam(name = "nameLike",value = "水果名称模糊词",dataType = "String"),
            @ApiImplicitParam(name = "fruitPrice",value = "水果价格",dataType = "String"),
            @ApiImplicitParam(name = "departurePoint",value = "发货地点",dataType = "String"),
            @ApiImplicitParam(name = "description",value = "水果描述",dataType = "String"),
            @ApiImplicitParam(name = "deliveryCost",value = "快递费",dataType = "Integer"),
            @ApiImplicitParam(name = "page",value = "页数",dataType = "int"),
            @ApiImplicitParam(name = "pageSize",value = "每页的数量",dataType = "int")
    })
    @GetMapping("/fruit-list")
        public PageList<Fruit> getFruitList(@RequestParam(required = false) String id,
                                            @RequestParam(required = false) String fruitName,
                                            @RequestParam(required = false) String fruitPrice,
                                            @RequestParam(required = false) String nameLike,
                                            @RequestParam(required = false) String departurePoint,
                                            @RequestParam(required = false) String description,
                                            @RequestParam(required = false) Integer deliveryCost,
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
        if(fruitName != null && (fruitName.trim().isEmpty() || fruitName.length() == 0)){
            fruitName = null;
        }
        return fruitService.getFruitList(id,fruitName,fruitPrice,nameLike,departurePoint,description,deliveryCost,page,pageSize);
    }
}
