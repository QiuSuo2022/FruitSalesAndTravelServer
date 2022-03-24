package com.guet.qiusuo.fruittravel.controller;

import com.guet.qiusuo.fruittravel.bean.vo.FruitEvaluateVO;
import com.guet.qiusuo.fruittravel.model.Evaluate;
import com.guet.qiusuo.fruittravel.service.EvaluateService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "水果评价表")
@RestController
@RequestMapping("/fruitsEvaluate")
public class EvaluateController {
    private EvaluateService evaluateService;
    @Autowired
    public void setFruitEvaluateService(EvaluateService evaluateService){
        this.evaluateService = evaluateService;
    }

    @ApiOperation(value = "增加主评")
    @PostMapping("/addEvaluate")
    public void addFruitEvaluate(@RequestBody Evaluate evaluate) {
        evaluateService.addFruitEvaluate(evaluate);
    }

    @ApiOperation(value = "增加追评")
    @PostMapping("/addReevaluate")
    public void addFruitReevaluate(@RequestParam String evaluateId) {
        evaluateService.addFruitReevaluate(evaluateId);
    }

    @ApiOperation(value = "删除追评")
    @DeleteMapping("/deleteReevaluate")
    public void deleteReevaluate(@RequestParam String evaluateId) {
        evaluateService.deleteFruitReEvaluate(evaluateId);
    }

    @ApiOperation(value = "删除评价")
    @DeleteMapping("/deleteEvaluate")
    public void deleteEvaluate(@RequestParam String UUID) {
        evaluateService.deleteFruitEvaluate(UUID);
    }

    @ApiOperation(value = "修改评价")
    @PutMapping
    public void updateEvaluate(@RequestBody Evaluate evaluate) {
        evaluateService.updateFruitEvaluate(evaluate);
    }

    @ApiOperation(value = "查询追评")
    @GetMapping("/searchReevaluate")
    public List<Evaluate> searchReevaluate(@RequestParam String evaluateId) {
        return evaluateService.searchFruitReevaluate(evaluateId);
    }

    @ApiOperation(value = "查询评价")
    @GetMapping("/searchEvaluate")
    public List<FruitEvaluateVO> searchEvaluate(@RequestParam String evaluateId) {
        return evaluateService.searchFruitEvaluate(evaluateId);
    }
}
