package com.guet.qiusuo.fruittravel.controller;

import com.guet.qiusuo.fruittravel.bean.vo.FruitUrlVO;
import com.guet.qiusuo.fruittravel.bean.vo.ScenicUrlVO;
import com.guet.qiusuo.fruittravel.bean.vo.SearchVO;
import com.guet.qiusuo.fruittravel.common.SystemConstants;
import com.guet.qiusuo.fruittravel.dao.FruitDynamicSqlSupport;
import com.guet.qiusuo.fruittravel.dao.FruitMapper;
import com.guet.qiusuo.fruittravel.dao.ScenicDynamicSqlSupport;
import com.guet.qiusuo.fruittravel.dao.ScenicMapper;
import com.guet.qiusuo.fruittravel.model.Fruit;
import com.guet.qiusuo.fruittravel.model.Scenic;
import com.guet.qiusuo.fruittravel.service.UploadImgService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.mybatis.dynamic.sql.render.RenderingStrategies;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static java.lang.invoke.MethodHandles.lookup;
import static org.mybatis.dynamic.sql.SqlBuilder.*;
import static org.slf4j.LoggerFactory.getLogger;

@Api(tags = "搜索")
@RestController
@RequestMapping("/search")
public class SearchController {
    private static final Logger LOG = getLogger(lookup().lookupClass());

    private UploadImgService uploadImgService;

    @Autowired
    public void setUploadImgService(UploadImgService uploadImgService) {
        this.uploadImgService = uploadImgService;
    }

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
        List<Scenic> scenicList = scenicMapper.selectMany(select(
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
        List<ScenicUrlVO> scenicUrlList = new ArrayList<>();
        for (Scenic s:scenicList) {
            ScenicUrlVO scenicUrlVO = new ScenicUrlVO();
            scenicUrlVO.setImgUrl(uploadImgService.getUrlByProdId(s.getId()));
            scenicUrlVO.setId(s.getId());
            scenicUrlVO.setScenicName(s.getScenicName());
            scenicUrlVO.setType(s.getType());
            scenicUrlVO.setDescription(s.getDescription());
            scenicUrlVO.setOpeningHours(s.getOpeningHours());
            scenicUrlVO.setLocation(s.getLocation());
            scenicUrlVO.setStatus(s.getStatus());
            scenicUrlList.add(scenicUrlVO);
        }
        res.setScenic(scenicUrlList);
        LOG.info("{}",scenicList);
        LOG.info("查询水果...");
        List<Fruit> fruitList = fruitMapper.selectMany(select(
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
        List<FruitUrlVO> fruitUrlVOList = new ArrayList<>();
        for (Fruit s:fruitList) {
            FruitUrlVO fruitUrlVO = new FruitUrlVO();
            fruitUrlVO.setImgUrl(uploadImgService.getUrlByProdId(s.getId()));
            fruitUrlVO.setId(s.getId());
            fruitUrlVO.setFruitName(s.getFruitName());
            fruitUrlVO.setFruitPrice(s.getFruitPrice());
            fruitUrlVO.setDescription(s.getDescription());
            fruitUrlVO.setDescription(s.getDescription());
            fruitUrlVO.setDeliveryCost(s.getDeliveryCost());
            fruitUrlVO.setStatus(s.getStatus());
            fruitUrlVOList.add(fruitUrlVO);
        }
        res.setFruit(fruitUrlVOList);
        return res;
    }
}
