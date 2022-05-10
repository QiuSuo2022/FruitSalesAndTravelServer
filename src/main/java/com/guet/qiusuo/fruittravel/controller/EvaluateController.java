package com.guet.qiusuo.fruittravel.controller;

import com.guet.qiusuo.fruittravel.bean.vo.EvaluateVO;
import com.guet.qiusuo.fruittravel.bean.vo.FruitEvaluateVO;
import com.guet.qiusuo.fruittravel.bean.vo.ScenicEvaluateVO;
import com.guet.qiusuo.fruittravel.common.PageList;
import com.guet.qiusuo.fruittravel.common.SystemConstants;
import com.guet.qiusuo.fruittravel.model.Evaluate;
import com.guet.qiusuo.fruittravel.service.EvaluateService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "评价表")
@RestController
@RequestMapping("/evaluate")
public class EvaluateController {
    private EvaluateService evaluateService;
    @Autowired
    public void setFruitEvaluateService(EvaluateService evaluateService){
        this.evaluateService = evaluateService;
    }

    @ApiOperation(value = "增加主评",notes = "0-评价水果 1评价景区 2-对评价的评论")
    @PostMapping("/addEvaluate")
    public void addFruitEvaluate(@RequestBody Evaluate evaluate) {
        evaluateService.addFruitEvaluate(evaluate);
    }

    @ApiOperation(value = "增加追评",notes = "0-评价水果 1评价景区 2-对评价的评论")
    @PostMapping("/addReevaluate")
    public void addFruitReevaluate(@RequestParam String evaluateId) {
        evaluateService.addFruitReevaluate(evaluateId);
    }

    @ApiOperation(value = "删除追评")
    @DeleteMapping("/deleteReevaluate")
    public void deleteRevaluate(@RequestParam String evaluateId) {
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
        evaluateService.updateEvaluate(evaluate);
    }

    @ApiOperation(value = "查询追评")
    @GetMapping("/searchReevaluate")
    public List<EvaluateVO> searchReevaluate(@RequestParam String evaluateId) {
        return evaluateService.searchReevaluate(evaluateId);
    }

    @ApiOperation(value = "查询水果评价")
    @GetMapping("/searchFruitEvaluate")
    public FruitEvaluateVO searchFruitEvaluate(@RequestParam String evaluateId) {
        return evaluateService.searchFruitEvaluate(evaluateId);
    }

    @ApiOperation(value = "查询景区评价")
    @GetMapping("/searchScenicEvaluate")
    public ScenicEvaluateVO searchScenicEvaluate(@RequestParam String evaluateId) {
        return evaluateService.searchScenicEvaluate(evaluateId);
    }

    @ApiOperation("获取水果评价列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "productId", value = "产品Id", dataType = "String", dataTypeClass = String.class, required = true),
            @ApiImplicitParam(name = "page",value = "页数",dataType = "int", dataTypeClass = Integer.class, required = true),
            @ApiImplicitParam(name = "pageSize",value = "每页的数量",dataType = "int", dataTypeClass = Integer.class, required = true)
    })
    @GetMapping("/fruitEvaluateList")
    public PageList<FruitEvaluateVO> getFruitEvaluateVOList(@RequestParam String productId,
                                                            @RequestParam(required = false,defaultValue =
                                                                    SystemConstants.DEFAULT_PAGE) Integer page,
                                                            @RequestParam(required = false,defaultValue =
                                                                    SystemConstants.DEFAULT_PAGE_SIZE) Integer pageSize
                                                            ) {
        return evaluateService.getFruitEvaluateVOList(productId,page,pageSize);
    }

    @ApiOperation("获取景区评价列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "productId", value = "产品Id", dataType = "String", dataTypeClass = String.class, required = true),
            @ApiImplicitParam(name = "page",value = "页数",dataType = "int", dataTypeClass = Integer.class, required = true),
            @ApiImplicitParam(name = "pageSize",value = "每页的数量",dataType = "int", dataTypeClass = Integer.class, required = true)
    })
    @GetMapping("/scenicEvaluateList")
    public PageList<ScenicEvaluateVO> getScenicEvaluateVOList(@RequestParam String productId,
                                                              @RequestParam(required = false,defaultValue =
                                                                      SystemConstants.DEFAULT_PAGE) Integer page,
                                                              @RequestParam(required = false,defaultValue =
                                                                      SystemConstants.DEFAULT_PAGE_SIZE) Integer pageSize) {
        return evaluateService.getScenicEvaluateVOList(productId, page, pageSize);
    }
}
