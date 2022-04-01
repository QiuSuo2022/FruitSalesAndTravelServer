package com.guet.qiusuo.fruittravel.controller;

import com.guet.qiusuo.fruittravel.model.ChildFruit;
import com.guet.qiusuo.fruittravel.service.ChildFruitService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "水果子项表")
@RestController
@RequestMapping("/childFruit")
public class ChildFruitController {

    private ChildFruitService childFruitService;

    @Autowired
    public void setChildFruitService(ChildFruitService childFruitService){
        this.childFruitService = childFruitService;
    }

    @ApiOperation(value = "添加水果子项")
    @PostMapping
    public boolean addChildFruit(@RequestBody ChildFruit childFruit){
        return childFruitService.addChildFruit(childFruit);
    }

    @ApiOperation(value = "删除水果子项")
    @DeleteMapping
    public boolean deleteChildFruit(@RequestParam String fruitId){
        return childFruitService.deleteChildFruit(fruitId);
    }

    @ApiOperation(value = "修改水果子项")
    @PutMapping
    public boolean updateChildFruit(@RequestBody ChildFruit childFruit){
        return childFruitService.updateChildFruit(childFruit);
    }

    @ApiOperation(value = "查找所有水果子项")
    @GetMapping(value = "/allChildFruit")
    public List<ChildFruit> getAllChildFruit(){
        return childFruitService.getAllChildFruits();
    }

    @ApiOperation(value = "获取水果子项列表")
    @GetMapping
    public List<ChildFruit> getChildFruit(@RequestParam String childFruitId){
        return childFruitService.getChildFruitListByFruitId(childFruitId);
    }

}
