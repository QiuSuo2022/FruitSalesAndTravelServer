package com.guet.qiusuo.fruittravel.controller;


import com.guet.qiusuo.fruittravel.model.Fruit;
import com.guet.qiusuo.fruittravel.service.FruitService;
import io.swagger.annotations.Api;
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
    @GetMapping("/getFruits")
    public List<Fruit> searchAllFruits(){
        return fruitService.searchAllFruits();
    }
}

