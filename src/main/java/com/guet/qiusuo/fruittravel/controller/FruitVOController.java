package com.guet.qiusuo.fruittravel.controller;

import com.guet.qiusuo.fruittravel.bean.vo.FruitVO;
import com.guet.qiusuo.fruittravel.service.FruitService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@Api(tags = "获取FruitVO")
@RestController
@RequestMapping("/fruitVO")
public class FruitVOController {

    private FruitService fruitService;
    @Autowired
    public void  setFruitService(FruitService fruitService){
        this.fruitService = fruitService;
    }

    @ApiOperation(value = "获取FruitVO")
    @GetMapping
    public FruitVO getFruitVOByFruitId(@RequestParam String fruit_id){
        return fruitService.getFruitVOByFruitId(fruit_id);
    }
}
