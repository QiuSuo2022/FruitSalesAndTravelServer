package com.guet.qiusuo.fruittravel.utils;

import com.guet.qiusuo.fruittravel.service.StatsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StatasUtils {
    private StatsService statsService;
    
    @Autowired
    public void setStatsService(StatsService statsService) {
        this.statsService = statsService;
    }
    public  long getSales(String fruitId, short ago){
        return statsService.getSingleSalesByFruitId(fruitId,ago);
    }
}
