package com.guet.qiusuo.fruittravel.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.guet.qiusuo.fruittravel.bean.vo.FruitVO;
import com.guet.qiusuo.fruittravel.common.PageList;
import com.guet.qiusuo.fruittravel.common.SystemConstants;
import com.guet.qiusuo.fruittravel.config.ErrorCode;
import com.guet.qiusuo.fruittravel.config.SystemException;
import com.guet.qiusuo.fruittravel.config.UserContextHolder;
import com.guet.qiusuo.fruittravel.dao.*;
import com.guet.qiusuo.fruittravel.model.Fruit;
import org.mybatis.dynamic.sql.render.RenderingStrategies;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static java.lang.invoke.MethodHandles.lookup;
import static org.mybatis.dynamic.sql.SqlBuilder.*;
import static org.slf4j.LoggerFactory.getLogger;

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

    /**
     * 获取水果列表
     */
    public PageList<Fruit> getFruitList(String id, String fruitName, String fruitPrice, String nameLike, String departurePoint
            , String description, Integer deliveryCost,Short orderByType, Integer page, Integer pageSize) {
        if (nameLike == null || nameLike.length() == 0) {
            nameLike = "";
        }

        PageHelper.startPage(page,pageSize);
        List<Fruit> fruitList;
        if(orderByType.equals(SystemConstants.PRICE_ASC)) {
            fruitList = fruitMapper.selectFruitSortByPriceASC(nameLike);
        }
        else if(orderByType.equals(SystemConstants.PRICE_DESC)) {
            fruitList = fruitMapper.selectFruitSortByPriceDESC(nameLike);
        }
        else if(orderByType.equals(SystemConstants.SALE_ASC)) {
            fruitList = fruitMapper.selectFruit(select(
                            FruitDynamicSqlSupport.fruitName,
                            FruitDynamicSqlSupport.fruitPrice,
                            FruitDynamicSqlSupport.description,
                            FruitDynamicSqlSupport.departurePoint,
                            FruitDynamicSqlSupport.deliveryCost,
                            FruitDynamicSqlSupport.createTime
                    )
                            .from(FruitDynamicSqlSupport.fruit)
                            .leftJoin(OrderFormDynamicSqlSupport.orderForm)
                            .on(FruitDynamicSqlSupport.id, equalTo(OrderFormDynamicSqlSupport.fruitId))
                            .where(FruitDynamicSqlSupport.status,isEqualTo(SystemConstants.STATUS_ACTIVE))
                            .and(FruitDynamicSqlSupport.fruitName,isLike("%" + nameLike + "%"))
                            .and(OrderFormDynamicSqlSupport.status,isEqualTo(SystemConstants.STATUS_ACTIVE))
                            .and(OrderFormDynamicSqlSupport.payStatus,isEqualTo(SystemConstants.PAID))
                            .orderBy(OrderFormDynamicSqlSupport.amount)
                            .build().render(RenderingStrategies.MYBATIS3)
            );
        }
        else if(orderByType.equals(SystemConstants.SALE_DESC)) {
            fruitList = fruitMapper.selectFruit(select(
                            FruitDynamicSqlSupport.fruitName,
                            FruitDynamicSqlSupport.fruitPrice,
                            FruitDynamicSqlSupport.description,
                            FruitDynamicSqlSupport.departurePoint,
                            FruitDynamicSqlSupport.deliveryCost,
                            FruitDynamicSqlSupport.createTime
                    )
                        .from(FruitDynamicSqlSupport.fruit)
                        .leftJoin(OrderFormDynamicSqlSupport.orderForm)
                        .on(FruitDynamicSqlSupport.id, equalTo(OrderFormDynamicSqlSupport.fruitId))
                        .where(FruitDynamicSqlSupport.status,isEqualTo(SystemConstants.STATUS_ACTIVE))
                        .and(FruitDynamicSqlSupport.fruitName,isLike("%" + nameLike + "%"))
                        .and(OrderFormDynamicSqlSupport.status,isEqualTo(SystemConstants.STATUS_ACTIVE))
                        .and(OrderFormDynamicSqlSupport.payStatus,isEqualTo(SystemConstants.PAID))
                        .orderBy(OrderFormDynamicSqlSupport.amount.descending())
                        .build().render(RenderingStrategies.MYBATIS3)
            );
        }
        else if(orderByType.equals(SystemConstants.GRADE_ASC)) {
            fruitList = fruitMapper.selectFruit(select(
                            FruitDynamicSqlSupport.fruitName,
                            FruitDynamicSqlSupport.fruitPrice,
                            FruitDynamicSqlSupport.description,
                            FruitDynamicSqlSupport.departurePoint,
                            FruitDynamicSqlSupport.deliveryCost,
                            FruitDynamicSqlSupport.createTime
                    )
                            .from(FruitDynamicSqlSupport.fruit)
                            .leftJoin(EvaluateDynamicSqlSupport.evaluate)
                            .on(FruitDynamicSqlSupport.id, equalTo(EvaluateDynamicSqlSupport.productId))
                            .where(FruitDynamicSqlSupport.status,isEqualTo(SystemConstants.STATUS_ACTIVE))
                            .and(FruitDynamicSqlSupport.fruitName,isLike("%" + nameLike + "%"))
                            .and(EvaluateDynamicSqlSupport.status,isEqualTo(SystemConstants.STATUS_ACTIVE))
                            .orderBy(EvaluateDynamicSqlSupport.grade)
                            .build().render(RenderingStrategies.MYBATIS3)
            );
        }
        else if(orderByType.equals(SystemConstants.GRADE_DESC)) {
            fruitList = fruitMapper.selectFruit(select(
                            FruitDynamicSqlSupport.fruitName,
                            FruitDynamicSqlSupport.fruitPrice,
                            FruitDynamicSqlSupport.description,
                            FruitDynamicSqlSupport.departurePoint,
                            FruitDynamicSqlSupport.deliveryCost,
                            FruitDynamicSqlSupport.createTime
                    )
                            .from(FruitDynamicSqlSupport.fruit)
                            .leftJoin(EvaluateDynamicSqlSupport.evaluate)
                            .on(FruitDynamicSqlSupport.id, equalTo(EvaluateDynamicSqlSupport.productId))
                            .where(FruitDynamicSqlSupport.status,isEqualTo(SystemConstants.STATUS_ACTIVE))
                            .and(FruitDynamicSqlSupport.fruitName,isLike("%" + nameLike + "%"))
                            .and(EvaluateDynamicSqlSupport.status,isEqualTo(SystemConstants.STATUS_ACTIVE))
                            .orderBy(EvaluateDynamicSqlSupport.grade.descending())
                            .build().render(RenderingStrategies.MYBATIS3)
            );
        }
        //综合排序,先按照加权平均计算,销量权重占0.5,价格和评分各占0.25
        else if(orderByType.equals(SystemConstants.SORT_ALL)) {
            fruitList = fruitMapper.selectFruitSort(nameLike);
        }
        else {
            throw new SystemException(ErrorCode.ORDERBYTYPE_ERROR);
        }
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

    public boolean addFruit(Fruit fruit){
        long now = System.currentTimeMillis();
        UserContextHolder.validAdmin();
        if (!getFruitByName(fruit.getFruitName()).isEmpty()) {
            //已经存在该Fruit
            throw new SystemException(ErrorCode.FRUIT_ALREADY_EXITS);
        }

        fruit.setId(UUID.randomUUID().toString().replace("-", ""));
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
        return true;
    }


    /**
     * 删除Fruit(with childFruit)
     * @param fruitId
     */
    @Transactional(rollbackFor = Exception.class)
    public void deleteFruit(String fruitId){
        UserContextHolder.validAdmin();
        Optional<Fruit> optionalFruit = fruitMapper.selectByPrimaryKey(fruitId);
        Fruit check = optionalFruit.orElse(null);
        if (check == null){
            throw new SystemException(ErrorCode.DELETE_ERROR);
        }
        //先删除所有childFruit
        childFruitService.deleteAll(check.getId());
        check.setStatus(SystemConstants.STATUS_NEGATIVE);
        check.setUpdateTime(System.currentTimeMillis());
        check.setUpdateUserId(UserContextHolder.getUserId());
        //删除Fruit
        int i = fruitMapper.updateByPrimaryKey(check);
        if (i == 0){
            throw new SystemException(ErrorCode.DELETE_ERROR);
        }
    }

    /**
     * 修改Fruit
     * @param fruit
     */
    public boolean updateFruit(Fruit fruit){
        UserContextHolder.validAdmin();
        fruit.setUpdateTime(System.currentTimeMillis());
        fruit.setUpdateUserId(UserContextHolder.getUserId());
        int i =  fruitMapper.updateByPrimaryKeySelective(fruit);
        if (i == 0){
            throw new SystemException(ErrorCode.UPDATE_ERROR);
        }
        return true;
    }

    public Fruit getFruit(String fruitId){
        Optional<Fruit> optionalFruit =  fruitMapper.selectByPrimaryKey(fruitId);
        Fruit fruit = optionalFruit.orElseThrow(() -> new SystemException(ErrorCode.NO_FOUND_FRUIT));
        return fruit;
    }
    /**
     * 查询全部Fruit
     * @return
     */
    public PageList<Fruit> getAllFruits(Integer page, Integer pageSize){
        PageHelper.startPage(page,pageSize);
        List<Fruit> fruitList;
        fruitList = fruitMapper.selectMany(select(
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
                .orderBy(FruitDynamicSqlSupport.createTime)
                .build().render(RenderingStrategies.MYBATIS3));

        PageList<Fruit> pageList = new PageList<>();
        pageList.setList(fruitList);
        pageList.setPageInfo(new PageInfo<>(fruitList));
        return pageList;
    }

    /**
     * 查询全部Fruit - 不分页
     * @return
     */
    public List<Fruit> getAllFruitList(){
        return fruitMapper.selectMany(select(
                FruitDynamicSqlSupport.id,
                FruitDynamicSqlSupport.fruitName,
                FruitDynamicSqlSupport.status,
                FruitDynamicSqlSupport.updateTime
        )
                .from(FruitDynamicSqlSupport.fruit)
                .where(FruitDynamicSqlSupport.status,isEqualTo(SystemConstants.STATUS_ACTIVE))
                .orderBy(FruitDynamicSqlSupport.createTime)
                .build().render(RenderingStrategies.MYBATIS3));
    }




    /**
     * 根据fruit_id查找FruitVO
     * @param fruitId
     */
    public FruitVO getFruitVOByFruitId(String fruitId){
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
                .where(FruitDynamicSqlSupport.id, isEqualTo(fruitId))
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

        fruitVO.setStock(childFruitService.getChildFruit(fruitId).getStock());
        fruitVO.setChildFruitName(childFruitService.getChildFruit(fruitId).getFruitName());
        return fruitVO;
    }

    /**
     * 获取水果推荐列表
     * @param page
     * @param pageSize
     * @return
     */
    public PageList<FruitVO> getFruitRecommendList(Integer page, Integer pageSize) {
        PageHelper.startPage(page,pageSize);
        List<FruitVO> fruitList;
        fruitList = fruitMapper.FruitRecommend();
        PageList<FruitVO> pageList = new PageList<>();
        pageList.setList(fruitList);
        pageList.setPageInfo(new PageInfo<>(fruitList));
        return pageList;
    }

}


