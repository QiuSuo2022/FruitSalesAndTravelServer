package com.guet.qiusuo.fruittravel.service;


import com.guet.qiusuo.fruittravel.common.SystemConstants;
import com.guet.qiusuo.fruittravel.config.ErrorCode;
import com.guet.qiusuo.fruittravel.config.SystemException;
import com.guet.qiusuo.fruittravel.config.UserContextHolder;
import com.guet.qiusuo.fruittravel.dao.FruitMapper;
import com.guet.qiusuo.fruittravel.dao.FruitDynamicSqlSupport;
import com.guet.qiusuo.fruittravel.model.Fruit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.mybatis.dynamic.sql.render.RenderingStrategies;


import static org.mybatis.dynamic.sql.SqlBuilder.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Service
public class FruitService {

    private FruitMapper fruitMapper;
    private ChildFruitService childFruitService;

    @Autowired
    public void setFruitMapper(FruitMapper fruitMapper) {
        this.fruitMapper = fruitMapper;
    }
    @Autowired
    public void setChildFruitService(ChildFruitService childFruitService) {
        this.childFruitService = childFruitService;
    }
    private List<Fruit> getFruitByName(String fruitName){
        return fruitMapper.selectMany(select(
                FruitDynamicSqlSupport.id,
                FruitDynamicSqlSupport.fruitName,
                FruitDynamicSqlSupport.fruitPrice,//范围
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
                .build().render(RenderingStrategies.MYBATIS3));
    }

    /**
     * 增加Fruit
     * @param fruit
     */

    public void addFruit(Fruit fruit){
        long now = System.currentTimeMillis();
        String userRoleId = UserContextHolder.getUser().getRoleId();
        if (userRoleId.equals("1")){
            throw new SystemException(ErrorCode.NO_PERMISSION);
        }
        if (getFruitByName(fruit.getFruitName()).get(0) != null){
            //已经存在该Fruit
            throw new SystemException(ErrorCode.FRUIT_ALREADY_EXITS);
        }else {
            fruit.setId(UUID.randomUUID().toString());
            fruit.setCreateTime(now);
            fruit.setUpdateTime(now);
            /*
             * 1.如果前端中 Fruit与childFruit一起提交的时候,直接使用STATUS_ACTIVE
             * 2.否则,设置为CHILD_FRUIT_INFO_NOT_COMPLETE
             * */
            //假设一起提交
            fruit.setStatus(SystemConstants.STATUS_ACTIVE);
            fruitMapper.insertSelective(fruit);
            System.out.println("创建水果成功:" + fruit.toString());
        }
    }


    /**
     * 删除Fruit(with childFruit)
     * @param fruitName
     */
    public void deleteFruit(String fruitName){
        String userRoleId = UserContextHolder.getUser().getRoleId();
        if (userRoleId.equals("1")){
            throw new SystemException(ErrorCode.NO_PERMISSION);
        }
        Optional<Fruit> optionalFruit = fruitMapper.selectByPrimaryKey(fruitName);
        Fruit fruit = optionalFruit.orElseThrow(() -> new SystemException(ErrorCode.NO_FOUND_FRUIT));
        fruit.setStatus(SystemConstants.STATUS_NEGATIVE);
        fruit.setUpdateTime(System.currentTimeMillis());
        //先删除childFruit
        childFruitService.deleteChildFruit(fruit.getId());
        //删除Fruit
        fruitMapper.deleteByPrimaryKey(fruit.getId());
    }

    /**
     * 修改Fruit
     * @param fruit
     */
    public void updateFruit(Fruit fruit){
        String userRoleId = UserContextHolder.getUser().getRoleId();
        if (userRoleId.equals("1")){
            throw new SystemException(ErrorCode.NO_PERMISSION);
        }
        fruit.setUpdateTime(System.currentTimeMillis());
        fruitMapper.updateByPrimaryKeySelective(fruit);
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
}


