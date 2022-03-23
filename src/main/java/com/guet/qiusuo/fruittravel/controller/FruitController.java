package com.guet.qiusuo.fruittravel.controller;


import com.guet.qiusuo.fruittravel.common.PageList;
import com.guet.qiusuo.fruittravel.common.SystemConstants;
import com.guet.qiusuo.fruittravel.model.Fruit;
import com.guet.qiusuo.fruittravel.service.FruitService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "水果商品表")
@RestController
@RequestMapping("/fruits")
public class FruitController {
    private FruitService fruitService;
    @Autowired
    public void  setFruitService(FruitService fruitService){
        this.fruitService = fruitService;
    }

    @ApiOperation(value = "添加水果")
    @PostMapping
    public void addFruit(@RequestBody Fruit fruit){
        fruitService.addFruit(fruit);
    }

    @ApiOperation(value = "删除水果")
    @DeleteMapping
    public void deleteFruit(@RequestParam String fruitName){
        fruitService.deleteFruit(fruitName);
    }

    @ApiOperation(value = "修改水果")
    @PutMapping("/updateFruit")
    public void updateFruit(@RequestBody Fruit fruit){
        fruitService.updateFruit(fruit);
    }

    @ApiOperation(value = "查找所有水果")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page",value = "页数",dataType = "int", dataTypeClass = Integer.class, required = true),
            @ApiImplicitParam(name = "pageSize",value = "每页的数量",dataType = "int", dataTypeClass = Integer.class, required = true)
    })
    @GetMapping("/getFruits")
    public PageList<Fruit> searchAllFruits(@RequestParam(required = false,defaultValue =
                                                    SystemConstants.DEFAULT_PAGE) Integer page,
                                           @RequestParam(required = false,defaultValue =
                                                   SystemConstants.DEFAULT_PAGE_SIZE) Integer pageSize) {
        return fruitService.searchAllFruits(page, pageSize);
    }

    @ApiOperation("获取水果列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "orderByType",value = "排序方式",dataType = "Short", dataTypeClass = Short.class, required = true),
            @ApiImplicitParam(name = "nameLike",value = "水果名称模糊词",dataType = "String", dataTypeClass = String.class, required = true),
            @ApiImplicitParam(name = "page",value = "页数",dataType = "int", dataTypeClass = Integer.class, required = true),
            @ApiImplicitParam(name = "pageSize",value = "每页的数量",dataType = "int", dataTypeClass = Integer.class, required = true)
    })
    @GetMapping("/fruitList")
    public PageList<Fruit> getFruitList(@RequestParam(required = false) String id,
                                        @RequestParam(required = false) String fruitName,
                                        @RequestParam(required = false) String fruitPrice,
                                        @RequestParam(required = false) String nameLike,
                                        @RequestParam(required = false) String departurePoint,
                                        @RequestParam(required = false) String description,
                                        @RequestParam(required = false) Integer deliveryCost,
                                        @RequestParam(required = false) Short orderByType,
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
        return fruitService.getFruitList(id,fruitName,fruitPrice,nameLike,departurePoint,description,deliveryCost,orderByType,page,pageSize);
    }

    @ApiOperation(value = "获取水果推荐列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "nameLike",value = "水果名称模糊词",dataType = "String", dataTypeClass = String.class, required = true),
            @ApiImplicitParam(name = "page",value = "页数",dataType = "int", dataTypeClass = Integer.class, required = true),
            @ApiImplicitParam(name = "pageSize",value = "每页的数量",dataType = "int", dataTypeClass = Integer.class, required = true)
    })
    @GetMapping("/fruitRecommendList")
    public PageList<Fruit> getFruitRecommendList(@RequestParam(required = false) String nameLike,
                                                 @RequestParam(required = false,defaultValue =
                                                         SystemConstants.DEFAULT_PAGE) Integer page,
                                                 @RequestParam(required = false,defaultValue =
                                                         SystemConstants.DEFAULT_PAGE_SIZE) Integer pageSize) {

        return fruitService.getFruitRecommendList(nameLike, page, pageSize);
    }
}

