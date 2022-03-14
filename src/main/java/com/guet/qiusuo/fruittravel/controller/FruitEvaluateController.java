package com.guet.qiusuo.fruittravel.controller;

import com.guet.qiusuo.fruittravel.bean.vo.FruitEvaluateVO;
import com.guet.qiusuo.fruittravel.model.Evaluate;
import com.guet.qiusuo.fruittravel.service.FruitEvaluateService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "水果评价表")
@RestController
@RequestMapping("/fruitsEvaluate")
public class FruitEvaluateController {
    private FruitEvaluateService fruitEvaluateService;
    @Autowired
    public void setFruitEvaluateService(FruitEvaluateService fruitEvaluateService){
        this.fruitEvaluateService = fruitEvaluateService;
    }

    @ApiOperation(value = "增加主评")
    @PostMapping("/addEvaluate")
    public void addFruitEvaluate(@RequestBody Evaluate evaluate) {
        fruitEvaluateService.addFruitEvaluate(evaluate);
    }

    @ApiOperation(value = "增加追评")
    @PostMapping("/addReevaluate")
    public void addFruitReevaluate(@RequestParam String evaluateId) {
        fruitEvaluateService.addFruitReevaluate(evaluateId);
    }

    @ApiOperation(value = "删除追评")
    @DeleteMapping("/deleteReevaluate")
    public void deleteReevaluate(@RequestParam String evaluateId) {
        fruitEvaluateService.deleteFruitReEvaluate(evaluateId);
    }

    @ApiOperation(value = "删除评价")
    @DeleteMapping("/deleteEvaluate")
    public void deleteEvaluate(@RequestParam String UUID) {
        fruitEvaluateService.deleteFruitEvaluate(UUID);
    }

    @ApiOperation(value = "修改评价")
    @PutMapping
    public void updateEvaluate(@RequestBody Evaluate evaluate) {
        fruitEvaluateService.updateFruitEvaluate(evaluate);
    }

    @ApiOperation(value = "查询追评")
    @GetMapping("/searchReevaluate")
    public Evaluate searchReevaluate(@RequestParam String evaluateId) {
        return fruitEvaluateService.searchFruitReevaluate(evaluateId);
    }

    @ApiOperation(value = "查询评价")
    @GetMapping("/searchEvaluate")
    public List<FruitEvaluateVO> searchEvaluate(@RequestParam String evaluateId) {
        return fruitEvaluateService.searchFruitEvaluate(evaluateId);
    }
}
