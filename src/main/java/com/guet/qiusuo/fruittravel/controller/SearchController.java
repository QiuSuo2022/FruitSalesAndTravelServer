package com.guet.qiusuo.fruittravel.controller;

import com.guet.qiusuo.fruittravel.bean.vo.SearchVO;
import com.guet.qiusuo.fruittravel.common.SystemConstants;
import com.guet.qiusuo.fruittravel.dao.FruitDynamicSqlSupport;
import com.guet.qiusuo.fruittravel.dao.FruitMapper;
import com.guet.qiusuo.fruittravel.dao.ScenicDynamicSqlSupport;
import com.guet.qiusuo.fruittravel.dao.ScenicMapper;
import com.guet.qiusuo.fruittravel.model.Fruit;
import com.guet.qiusuo.fruittravel.model.Scenic;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.mybatis.dynamic.sql.render.RenderingStrategies;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;

import static java.lang.invoke.MethodHandles.lookup;
import static org.mybatis.dynamic.sql.SqlBuilder.*;
import static org.slf4j.LoggerFactory.getLogger;

@Api(tags = "搜索")
@RestController
@RequestMapping("/search")
public class SearchController {
    private static final Logger LOG = getLogger(lookup().lookupClass());

    private FruitMapper fruitMapper;
    private ScenicMapper scenicMapper;
    @Autowired

    public void setFruitMapper(FruitMapper fruitMapper) {
        this.fruitMapper = fruitMapper;
    }

    @Autowired
    public void setScenicMapper(ScenicMapper scenicMapper) {
        this.scenicMapper = scenicMapper;
    }
    @ApiOperation(value = "关键词搜索")
    @GetMapping
    public SearchVO searchByName(@RequestParam String nameLike){
        SearchVO res = new SearchVO();
        LOG.info("查询景区...");
        List<Scenic> scenicsLike = scenicMapper.selectMany(select(
                ScenicDynamicSqlSupport.id,
                ScenicDynamicSqlSupport.scenicName,
                ScenicDynamicSqlSupport.location,
                ScenicDynamicSqlSupport.openingHours,
                ScenicDynamicSqlSupport.description,
                ScenicDynamicSqlSupport.type,
                ScenicDynamicSqlSupport.status,
                ScenicDynamicSqlSupport.createTime,
                ScenicDynamicSqlSupport.updateTime,
                ScenicDynamicSqlSupport.createUserId,
                ScenicDynamicSqlSupport.updateUserId
        )
                .from(ScenicDynamicSqlSupport.scenic)
                .where(ScenicDynamicSqlSupport.status, isEqualTo(SystemConstants.STATUS_ACTIVE))
                .and(ScenicDynamicSqlSupport.scenicName,isLike(nameLike).filter(Objects::nonNull).map(s -> "%" + s + "%"))
                .build().render(RenderingStrategies.MYBATIS3));
        res.setScenic(scenicsLike);
        LOG.info("{}",scenicsLike);
        LOG.info("查询水果...");
        List<Fruit> fruitsLike = fruitMapper.selectMany(select(
                FruitDynamicSqlSupport.id,
                FruitDynamicSqlSupport.fruitName,
                FruitDynamicSqlSupport.fruitPrice,
                FruitDynamicSqlSupport.description,
                FruitDynamicSqlSupport.departurePoint,
                FruitDynamicSqlSupport.deliveryCost,
                FruitDynamicSqlSupport.status,
                FruitDynamicSqlSupport.createTime,
                FruitDynamicSqlSupport.updateTime,
                FruitDynamicSqlSupport.createUserId,
                FruitDynamicSqlSupport.updateUserId
        )
                .from(FruitDynamicSqlSupport.fruit)
                .where(FruitDynamicSqlSupport.fruitName, isLike(nameLike).filter(Objects::nonNull).map(s -> "%" + s + "%"))
                .and(FruitDynamicSqlSupport.status, isEqualTo(SystemConstants.STATUS_ACTIVE))
                .build().render(RenderingStrategies.MYBATIS3));
        res.setFruit(fruitsLike);
        return res;
    }
}
