package com.guet.qiusuo.fruittravel.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.guet.qiusuo.fruittravel.common.PageList;
import com.guet.qiusuo.fruittravel.common.SystemConstants;
import com.guet.qiusuo.fruittravel.dao.FruitDynamicSqlSupport;
import com.guet.qiusuo.fruittravel.dao.FruitScenicDynamicSqlSupport;
import com.guet.qiusuo.fruittravel.dao.FruitScenicMapper;
import com.guet.qiusuo.fruittravel.dao.ScenicDynamicSqlSupport;
import com.guet.qiusuo.fruittravel.model.FruitScenic;
import org.mybatis.dynamic.sql.render.RenderingStrategies;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.mybatis.dynamic.sql.SqlBuilder.*;

@Service
public class SelectFruitScenicService {
    private FruitScenicMapper fruitScenicMapper;

    public PageList<FruitScenic> getFruitScenicList(String id, String fruitId, String fruitName, String fruitPrice, String nameLike, String departurePoint
            , Integer deliveryCost, String scenicId, String scenicName, String location, Short type, String scenicDescription,
                                                    String openingHours, Integer page, Integer pageSize) {
        if (nameLike == null || nameLike.length() == 0) {
            nameLike = "";
        }

        PageHelper.startPage(page,pageSize);
        List<FruitScenic> fruitScenicList = fruitScenicMapper.selectFruitScenic(select(
                FruitScenicDynamicSqlSupport.id,
                FruitScenicDynamicSqlSupport.fruitId,
                FruitScenicDynamicSqlSupport.fruitName,
                FruitScenicDynamicSqlSupport.fruitPrice,
                FruitScenicDynamicSqlSupport.departurePoint,
                FruitScenicDynamicSqlSupport.fruitDescription,
                FruitScenicDynamicSqlSupport.deliveryCost,
                FruitScenicDynamicSqlSupport.scenicId,
                FruitScenicDynamicSqlSupport.scenicName,
                FruitScenicDynamicSqlSupport.location,
                FruitScenicDynamicSqlSupport.type,
                FruitScenicDynamicSqlSupport.scenicDescription,
                FruitScenicDynamicSqlSupport.openingHours,
                FruitScenicDynamicSqlSupport.createTime
        )
                .from(FruitScenicDynamicSqlSupport.fruitScenic)
                .leftJoin(FruitDynamicSqlSupport.fruit)
                .on(FruitDynamicSqlSupport.id, equalTo(FruitScenicDynamicSqlSupport.fruitId))
                .leftJoin(ScenicDynamicSqlSupport.scenic)
                .on(ScenicDynamicSqlSupport.id, equalTo(FruitScenicDynamicSqlSupport.scenicId))
                .where(FruitScenicDynamicSqlSupport.status, isEqualTo(SystemConstants.STATUS_ACTIVE))
                .and(FruitDynamicSqlSupport.status, isEqualTo(SystemConstants.STATUS_ACTIVE))
                .and(FruitScenicDynamicSqlSupport.fruitName, isLike("%" + nameLike + "%"))
                .and(FruitScenicDynamicSqlSupport.id, isEqualToWhenPresent(id))
                .and(FruitScenicDynamicSqlSupport.fruitId, isEqualToWhenPresent(fruitId))
                .and(FruitScenicDynamicSqlSupport.scenicId, isEqualToWhenPresent(scenicId))
                .orderBy(FruitScenicDynamicSqlSupport.createTime.descending())
                .build()
                .render(RenderingStrategies.MYBATIS3));

        PageList<FruitScenic> pageList = new PageList<>();
        pageList.setList(fruitScenicList);
        pageList.setPageInfo(new PageInfo<>(fruitScenicList));

        return pageList;
    }
}
