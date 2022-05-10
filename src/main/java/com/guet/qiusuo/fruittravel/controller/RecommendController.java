package com.guet.qiusuo.fruittravel.controller;


import com.guet.qiusuo.fruittravel.bean.vo.FruitRecVO;
import com.guet.qiusuo.fruittravel.bean.vo.ScenicRecVO;
import com.guet.qiusuo.fruittravel.common.PageList;
import com.guet.qiusuo.fruittravel.common.SystemConstants;
import com.guet.qiusuo.fruittravel.service.FruitService;
import com.guet.qiusuo.fruittravel.service.ScenicService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


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
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page",value = "页数",dataType = "int", dataTypeClass = Integer.class, required = true),
            @ApiImplicitParam(name = "pageSize",value = "每页的数量",dataType = "int", dataTypeClass = Integer.class, required = true)
    })
    public PageList<FruitRecVO> getFruitRec(
            @RequestParam(required = false,defaultValue = SystemConstants.DEFAULT_PAGE) Integer page,
            @RequestParam(required = false,defaultValue = SystemConstants.DEFAULT_PAGE_SIZE) Integer pageSize){

        return fruitService.getFruitRec(page,pageSize);
    }

    @ApiOperation("获取景区推荐列表")
    @GetMapping("/scenicRec")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page",value = "页数",dataType = "int", dataTypeClass = Integer.class, required = true),
            @ApiImplicitParam(name = "pageSize",value = "每页的数量",dataType = "int", dataTypeClass = Integer.class, required = true)
    })
    public PageList<ScenicRecVO> getScenicRec(
            @RequestParam(required = false,defaultValue = SystemConstants.DEFAULT_PAGE) Integer page,
            @RequestParam(required = false,defaultValue = SystemConstants.DEFAULT_PAGE_SIZE) Integer pageSize){

        return scenicService.getScenicRec(page,pageSize);
    }
}
