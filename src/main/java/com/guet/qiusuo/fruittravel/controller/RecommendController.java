package com.guet.qiusuo.fruittravel.controller;


import com.guet.qiusuo.fruittravel.bean.vo.*;
import com.guet.qiusuo.fruittravel.model.ChildFruit;
import com.guet.qiusuo.fruittravel.service.FruitService;
import com.guet.qiusuo.fruittravel.service.ScenicService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@Api(tags = "获取水果或景区推荐列表")
@RestController
@RequestMapping("/rec")
public class RecommendController {
    ScenicService scenicService;

    @Autowired
    public void setScenicService(ScenicService scenicService) {
        this.scenicService = scenicService;
    }

    FruitService fruitService;

    @Autowired
    public void setFruitService(FruitService fruitService) {
        this.fruitService = fruitService;
    }


    @ApiOperation("获取水果推荐列表")
    @GetMapping("/fruitRec")
    public List<FruitRecVO> getFruitRec(){
        return fruitService.getFruitRec();
    }

    @ApiOperation("获取景区推荐列表")
    @GetMapping("/scenicRec")
    public List<ScenicUrlVO> getScenicRec(){
        return scenicService.getScenicRec();
    }
}
