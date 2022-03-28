package com.guet.qiusuo.fruittravel.controller;

import com.github.abel533.echarts.Grid;
import com.github.abel533.echarts.Option;
import com.github.abel533.echarts.axis.CategoryAxis;
import com.github.abel533.echarts.axis.ValueAxis;
import com.github.abel533.echarts.code.Trigger;
import com.github.abel533.echarts.series.Bar;
import com.guet.qiusuo.fruittravel.service.StatsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.lang.invoke.MethodHandles.lookup;
import static org.slf4j.LoggerFactory.getLogger;

/**
 * @author : lu
 */
@Api(tags = "统计数据(评价、销量)")
@RestController
@RequestMapping
public class StatsController {
    private static final short WEEK  = 1;
    private static final short MONTH = 2;
    private static final short YEAH  = 3;
    private static final Logger LOG = getLogger(lookup().lookupClass());

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
        return statsService.getTopSaleFruit(ago);
    }

    @ApiOperation(value = "获取一种水果一周/月/年的销量")
    @GetMapping("/getFruitSalesAmount")
    public long getSalesAmountByFruitId(@RequestParam String fruitId,@RequestParam short ago){
        long amount = 0;
        amount = statsService.getSingleSalesByFruitId(fruitId, ago);
        return amount;
    }

    @ApiOperation(value = "获取所有水果一周/月/年的销量的报表")
    @GetMapping("/getAllFruitSalesReport")
    public Option getSalesReportOfChildFruit(@RequestParam short ago){
        List<Map.Entry<String, Long>> dataList = statsService.getAllFruitsSalesByAgo(ago);
        //构造option
        Option option = new Option();
        option.title("过去一"+getTitle(ago)+"所有水果种类销量统计").tooltip(Trigger.axis);
        //x轴为值轴
        option.xAxis(new ValueAxis().boundaryGap(0d,0.01));
        //y轴为类目轴
        CategoryAxis category = new CategoryAxis();
        //柱状数据
        Bar bar = new Bar("销量");
        //循环数据
        for (int i = 0; i < dataList.size(); i++){
            //设置类目
            category.data(dataList.get(i).getKey());
            //类目对应的柱状图
            bar.data(dataList.get(i).getValue());
        }
        //设置表格格式
        Grid grid = new Grid();
        grid.setLeft(200);
        option.setGrid(grid);
        option.yAxis(category);
        option.series(bar);
        return option;
    }

    @ApiOperation(value = "获取所有水果一周/月/年的评价的报表")
    @GetMapping("/getAllFruitEvalReport")
    public Option getEvaluationReportOfChildFruit(@RequestParam short ago, short evaluationType){
        List<Map.Entry<String, Long>> dataList = statsService.getAllFruitsEvaluationByAgo(ago,evaluationType);
        //构造option
        Option option = new Option();
        option.title("过去一"+getTitle(ago)+"所有水果的"+getEvaluation(evaluationType)+"评数量统计").tooltip(Trigger.axis);
        //x轴为值轴
        option.xAxis(new ValueAxis().boundaryGap(0d,0.01));
        //y轴为类目轴
        CategoryAxis category = new CategoryAxis();
        //柱状数据
        Bar bar = new Bar("销量");
        //循环数据
        for (int i = 0; i < dataList.size(); i++){
            //设置类目
            category.data(dataList.get(i).getKey());
            //类目对应的柱状图
            bar.data(dataList.get(i).getValue());
        }
        //设置表格格式
        Grid grid = new Grid();
        grid.setLeft(200);
        option.setGrid(grid);
        option.yAxis(category);
        option.series(bar);
        return option;
    }

    /**
     *景区
     */
    @ApiOperation(value = "获取销量最高的景区")
    @GetMapping("/getTopSalesScenic")
    public HashMap<String,Long> getTopSaleScenic(@RequestParam short ago){
        return statsService.getTopSaleScenic(ago);
    }

    @ApiOperation(value = "获取一种景区一周/月/年的销量")
    @GetMapping("/getScenicSalesAmount")
    public long getSalesAmountByScenicId(@RequestParam String scenicId,@RequestParam short ago){
        long amount = 0;
        amount = statsService.getSingleSalesByScenicId(scenicId,ago);
        return amount;
    }

    @ApiOperation(value = "获取所有景区一周/月/年的销量的报表")
    @GetMapping("/getAllScenicSalesReport")
    public Option getSalesReportOfScenic(@RequestParam short ago){
        List<Map.Entry<String, Long>> dataList = statsService.getAllScenicSalesByAgo(ago);
        //构造option
        Option option = new Option();
        option.title("过去一"+getTitle(ago)+"所有景区售出门票数量统计").tooltip(Trigger.axis);
        //x轴为值轴
        option.xAxis(new ValueAxis().boundaryGap(0d,0.01));
        //y轴为类目轴
        CategoryAxis category = new CategoryAxis();
        //柱状数据
        Bar bar = new Bar("销量");
        //循环数据
        for (int i = 0; i < dataList.size(); i++){
            //设置类目
            category.data(dataList.get(i).getKey());
            //类目对应的柱状图
            bar.data(dataList.get(i).getValue());
        }
        //设置表格格式
        Grid grid = new Grid();
        grid.setLeft(200);
        option.setGrid(grid);
        option.yAxis(category);
        option.series(bar);
        return option;
    }

    @ApiOperation(value = "获取所有景区一周/月/年的评价的报表")
    @GetMapping("/getAllScenicEvalReport")
    public Option getEvaluationReportOfScenic(@RequestParam short ago, short evaluationType){
        List<Map.Entry<String, Long>> dataList = statsService.getAllScenicEvaluationByAgo(ago,evaluationType);
        //构造option
        Option option = new Option();
        option.title("过去一"+getTitle(ago)+"所有景区的"+getEvaluation(evaluationType)+"评数量统计").tooltip(Trigger.axis);
        //x轴为值轴
        option.xAxis(new ValueAxis().boundaryGap(0d,0.01));
        //y轴为类目轴
        CategoryAxis category = new CategoryAxis();
        //柱状数据
        Bar bar = new Bar("销量");
        //循环数据
        for (int i = 0; i < dataList.size(); i++){
            //设置类目
            category.data(dataList.get(i).getKey());
            //类目对应的柱状图
            bar.data(dataList.get(i).getValue());
        }
        //设置表格格式
        Grid grid = new Grid();
        grid.setLeft(200);
        option.setGrid(grid);
        option.yAxis(category);
        option.series(bar);
        return option;
    }

    private String getTitle(short ago){
        String ans = "";
        switch (ago){
            case WEEK:
                ans = "周";break;
            case MONTH:
                ans = "月";break;
            case YEAH:
                ans = "年";break;
            default:LOG.info("错误参数!");break;
        }
        return ans;
    }
    private String getEvaluation(short type){
        String ans = "";
        switch (type){
            case 1:
                ans = "好";break;
            case 3:
                ans = "差";break;
            default:LOG.info("错误参数!");break;
        }
        return ans;
    }
}

