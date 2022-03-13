package com.guet.qiusuo.fruittravel.service;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.guet.qiusuo.fruittravel.bean.vo.FruitVO;
import com.guet.qiusuo.fruittravel.common.PageList;
import com.guet.qiusuo.fruittravel.common.SystemConstants;
import com.guet.qiusuo.fruittravel.config.ErrorCode;
import com.guet.qiusuo.fruittravel.config.SystemException;
import com.guet.qiusuo.fruittravel.config.UserContextHolder;
import com.guet.qiusuo.fruittravel.dao.FruitMapper;
import com.guet.qiusuo.fruittravel.dao.FruitDynamicSqlSupport;
import com.guet.qiusuo.fruittravel.model.Fruit;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.mybatis.dynamic.sql.render.RenderingStrategies;
import org.springframework.transaction.annotation.Transactional;


import static java.lang.invoke.MethodHandles.lookup;
import static org.mybatis.dynamic.sql.SqlBuilder.*;
import static org.slf4j.LoggerFactory.getLogger;

import java.util.List;
import java.util.UUID;

/**
 * @author lu
 */
@Service
public class FruitService {

    private FruitMapper fruitMapper;

    private ChildFruitService childFruitService;

    private static final Logger LOG = getLogger(lookup().lookupClass());

    @Autowired
    public void setFruitMapper(FruitMapper fruitMapper) {
        this.fruitMapper = fruitMapper;
    }

    @Autowired
    public void setChildFruitService(ChildFruitService childFruitService) {
        this.childFruitService = childFruitService;
    }

    public PageList<Fruit> getFruitList(String id, String fruitName, String fruitPrice, String nameLike, String departurePoint
            , String description, Integer deliveryCost, Integer page, Integer pageSize) {
        if (nameLike == null || nameLike.length() == 0) {
            nameLike = "";
        }

        PageHelper.startPage(page,pageSize);
        List<Fruit> fruitList = fruitMapper.selectFruit(select(
                FruitDynamicSqlSupport.id,
                FruitDynamicSqlSupport.fruitName,
                FruitDynamicSqlSupport.fruitPrice,
                FruitDynamicSqlSupport.description,
                FruitDynamicSqlSupport.departurePoint,
                FruitDynamicSqlSupport.deliveryCost,
                FruitDynamicSqlSupport.status,
                FruitDynamicSqlSupport.createTime
            )
                .from(FruitDynamicSqlSupport.fruit)
                .where(FruitDynamicSqlSupport.status,isEqualTo(SystemConstants.STATUS_ACTIVE))
                .and(FruitDynamicSqlSupport.fruitName,isLike("%" + nameLike + "%"))
                .orderBy(FruitDynamicSqlSupport.createTime.descending())
                .build().render(RenderingStrategies.MYBATIS3)
        );

        PageList<Fruit> pageList = new PageList<>();
        pageList.setList(fruitList);
        pageList.setPageInfo(new PageInfo<>(fruitList));
        return pageList;
    }

    private List<Fruit> getFruitByName(String fruitName){
        return fruitMapper.selectMany(select(
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
                .where(FruitDynamicSqlSupport.fruitName, isEqualTo(fruitName))
                .and(FruitDynamicSqlSupport.status,isEqualTo(SystemConstants.STATUS_ACTIVE))
                .build().render(RenderingStrategies.MYBATIS3));
    }

    /**
     * 增加Fruit
     * @param fruit
     */

    public void addFruit(Fruit fruit){
        long now = System.currentTimeMillis();
        UserContextHolder.validAdmin();
        if (getFruitByName(fruit.getFruitName()).get(0) != null){
            //已经存在该Fruit
            throw new SystemException(ErrorCode.FRUIT_ALREADY_EXITS);
        }

        fruit.setId(UUID.randomUUID().toString());
        fruit.setCreateTime(now);
        fruit.setUpdateTime(now);
        fruit.setCreateUserId(UserContextHolder.getUserId());
        fruit.setUpdateUserId(UserContextHolder.getUserId());
        /*
         * 1.如果前端中 Fruit与childFruit一起提交的时候,直接使用STATUS_ACTIVE
         * 2.否则,设置为CHILD_FRUIT_INFO_NOT_COMPLETE
         * */
        //假设一起提交
        fruit.setStatus(SystemConstants.STATUS_ACTIVE);
        int i = fruitMapper.insertSelective(fruit);
        if(i == 0){
            throw new SystemException(ErrorCode.INSERT_ERROR);
        }
        LOG.info("水果{}添加成功",fruit.getFruitName());
    }


    /**
     * 删除Fruit(with childFruit)
     * @param fruitName
     */
    @Transactional(rollbackFor = Exception.class)
    public void deleteFruit(String fruitName){
        UserContextHolder.validAdmin();
        List<Fruit> fruitList = getFruitByName(fruitName);
        if (fruitList.isEmpty()){
            throw new SystemException(ErrorCode.DELETE_ERROR);
        }
        Fruit fruit = new Fruit();
        fruit = fruitList.get(0);
        fruit.setStatus(SystemConstants.STATUS_NEGATIVE);
        fruit.setUpdateTime(System.currentTimeMillis());
        fruit.setUpdateUserId(UserContextHolder.getUserId());
        //先删除childFruit
        childFruitService.deleteChildFruit(fruit.getId());
        //删除Fruit
        int i = fruitMapper.deleteByPrimaryKey(fruit.getId());
        if (i == 0){
            throw new SystemException(ErrorCode.DELETE_ERROR);
        }
    }

    /**
     * 修改Fruit
     * @param fruit
     */
    public void updateFruit(Fruit fruit){
        UserContextHolder.validAdmin();
        fruit.setUpdateTime(System.currentTimeMillis());
        fruit.setUpdateUserId(UserContextHolder.getUserId());
        int i =  fruitMapper.updateByPrimaryKeySelective(fruit);
        if (i == 0){
            throw new SystemException(ErrorCode.UPDATE_ERROR);
        }
    }

    /**
     * 查询全部Fruit
     * @return
     */
    public List<Fruit> searchAllFruits(){
        return fruitMapper.selectMany(select(
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
                .where(FruitDynamicSqlSupport.status,isEqualTo(SystemConstants.STATUS_ACTIVE))
                .build().render(RenderingStrategies.MYBATIS3));
    }

    /**
     * 根据fruit_id查找FruitVO
     * @param fruit_id
     * @return
     */
    public FruitVO getFruitVOByFruitId(String fruit_id){
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
                .where(FruitDynamicSqlSupport.id, isEqualTo(fruit_id))
                .and(FruitDynamicSqlSupport.status, isEqualTo(SystemConstants.STATUS_ACTIVE))
                .build().render(RenderingStrategies.MYBATIS3));
        if (fruitList.isEmpty()){
            return null;
        }
        Fruit fruit = fruitList.get(0);
        FruitVO fruitVO = new FruitVO();
        fruitVO.setId(fruit.getId());
        fruitVO.setFruitName(fruit.getFruitName());
        fruitVO.setFruitPrice(fruit.getFruitPrice());
        fruitVO.setDescription(fruit.getDescription());
        fruitVO.setDeparturePoint(fruit.getDeparturePoint());
        fruitVO.setDeliveryCost(fruit.getDeliveryCost());
        fruitVO.setStatus(fruit.getStatus());
        fruitVO.setCreateTime(fruit.getCreateTime());
        fruitVO.setUpdateTime(fruit.getUpdateTime());
        fruitVO.setCreateUserId(fruit.getUpdateUserId());

        fruitVO.setStock(childFruitService.searchChildFruit(fruit_id).getStock());
        fruitVO.setChildFruitName(childFruitService.searchChildFruit(fruit_id).getFruitName());
        return fruitVO;
    }
}


