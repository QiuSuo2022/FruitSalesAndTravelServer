package com.guet.qiusuo.fruittravel.controller;

import com.guet.qiusuo.fruittravel.model.ChildFruit;
import com.guet.qiusuo.fruittravel.service.ChildFruitService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public void addChildFruit(@RequestBody ChildFruit childFruit){
        childFruitService.addChildFruit(childFruit);
    }

    @ApiOperation(value = "删除水果子项")
    @DeleteMapping
    public void deleteChildFruit(@RequestParam String fruitId){
        childFruitService.deleteChildFruit(fruitId);
    }

    @ApiOperation(value = "修改水果子项")
    @PutMapping
    public void updateChildFruit(@RequestBody ChildFruit childFruit){
        childFruitService.updateChildFruit(childFruit);
    }

    @ApiOperation(value = "查找水果子项")
    @GetMapping
    public void searchChildFruit(@RequestParam String fruitId){
        childFruitService.searchChildFruit(fruitId);
    }

}
