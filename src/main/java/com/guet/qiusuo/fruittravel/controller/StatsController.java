package com.guet.qiusuo.fruittravel.controller;
import com.guet.qiusuo.fruittravel.config.UserContextHolder;
import com.guet.qiusuo.fruittravel.service.StatsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @author : lu
 */
@Api(tags = "统计数据(评价、销量)")
@RestController
@RequestMapping
public class StatsController {

    private StatsService statsService;

    @Autowired
    public void setStatsService(StatsService statsService) {
        this.statsService = statsService;
    }

    /**
     *水果
     */
    @ApiOperation(value = "获取销量最高的水果")
    @GetMapping("/getTopSalesFruit")
    public HashMap<String,Long> getTopSaleFruit(@RequestParam short ago){
        UserContextHolder.validAdmin();
        return statsService.getTopSaleFruit(ago);
    }

    @ApiOperation(value = "获取一种水果一周/月/年的销量")
    @GetMapping("/getOneFruitSales")
    public long getOneFruitSalesByFruitId(@RequestParam String fruitId,@RequestParam short ago){
        UserContextHolder.validAdmin();
        long amount = 0;
        amount = statsService.getSingleSalesByFruitId(fruitId, ago);
        return amount;
    }

    @ApiOperation(value = "获取所有水果一周/月/年的销量的报表数据")
    @GetMapping("/getAllFruitSalesData")
    public List<Map.Entry<String, Long>> getAllFruitSalesData(@RequestParam short ago){
        UserContextHolder.validAdmin();
        return statsService.getAllFruitsSalesByAgo(ago);
    }

    @ApiOperation(value = "获取所有水果一周/月/年的评价的报表")
    @GetMapping("/getAllFruitEvalData")
    public List<Map.Entry<String, Long>> getAllFruitEvalData(@RequestParam short ago, short evaluationType){
        UserContextHolder.validAdmin();
        return statsService.getAllFruitsEvaluationByAgo(ago,evaluationType);
    }

    /**
     *景区
     */
    @ApiOperation(value = "获取销量最高的景区")
    @GetMapping("/getTopSalesScenic")
    public HashMap<String,Long> getTopSaleScenic(@RequestParam short ago){
        UserContextHolder.validAdmin();
        return statsService.getTopSaleScenic(ago);
    }

    @ApiOperation(value = "获取一种景区一周/月/年的销量")
    @GetMapping("/getOneScenicSales")
    public long getSalesAmountByScenicId(@RequestParam String scenicId,@RequestParam short ago){
        UserContextHolder.validAdmin();
        long amount = 0;
        amount = statsService.getSingleSalesByScenicId(scenicId,ago);
        return amount;
    }

    @ApiOperation(value = "获取所有景区一周/月/年的销量的报表")
    @GetMapping("/getAllScenicSalesData")
    public List<Map.Entry<String, Long>> getAllScenicSalesData(@RequestParam short ago){
        UserContextHolder.validAdmin();
        return statsService.getAllScenicSalesByAgo(ago);
    }

    @ApiOperation(value = "获取所有景区一周/月/年的评价的报表")
    @GetMapping("/getAllScenicEvalData")
    public List<Map.Entry<String, Long>> getAllScenicEvalData(@RequestParam short ago, short evaluationType){
        UserContextHolder.validAdmin();
        return statsService.getAllScenicEvaluationByAgo(ago,evaluationType);
    }
}

