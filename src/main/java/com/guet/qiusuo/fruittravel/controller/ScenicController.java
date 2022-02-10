package com.guet.qiusuo.fruittravel.controller;

import com.guet.qiusuo.fruittravel.model.Scenic;
import com.guet.qiusuo.fruittravel.service.ScenicService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "景点表")
@RestController
@RequestMapping("/Scenic")
public class ScenicController {
    private ScenicService scenicService;
    @Autowired
    public void setScenicService(ScenicService scenicService){
        this.scenicService = scenicService;
    }

    @ApiOperation(value = "添加景点")
    @PostMapping
    public void addScenic(@RequestBody Scenic scenic){scenicService.addScenic(scenic);}

    @ApiOperation(value = "删除景点")
    @DeleteMapping
    public void deleteScenic(@RequestParam String scenicId){scenicService.deleteScenic(scenicId);}

    @ApiOperation(value = "修改景点")
    @PutMapping
    public void updateScenic(@RequestBody Scenic scenic){scenicService.updateScenic(scenic);}

    @ApiOperation(value = "查找景点")
    @GetMapping
    public void searchScenic(@RequestParam String scenicId){scenicService.searchScenic(scenicId);}

    @ApiOperation(value = "查找所有景点")
    @GetMapping("/get_all_scenic")
    public List<Scenic> searchAllScenic() {return scenicService.searchAllScenic();}
}
