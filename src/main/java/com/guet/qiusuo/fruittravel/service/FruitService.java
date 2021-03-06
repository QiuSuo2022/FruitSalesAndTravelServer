package com.guet.qiusuo.fruittravel.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.guet.qiusuo.fruittravel.bean.vo.FruitRecVO;
import com.guet.qiusuo.fruittravel.bean.vo.FruitUrlVO;
import com.guet.qiusuo.fruittravel.bean.vo.FruitVO;
import com.guet.qiusuo.fruittravel.common.PageList;
import com.guet.qiusuo.fruittravel.common.SystemConstants;
import com.guet.qiusuo.fruittravel.config.ErrorCode;
import com.guet.qiusuo.fruittravel.config.SystemException;
import com.guet.qiusuo.fruittravel.config.UserContextHolder;
import com.guet.qiusuo.fruittravel.dao.*;
import com.guet.qiusuo.fruittravel.model.ChildFruit;
import com.guet.qiusuo.fruittravel.model.Fruit;
import com.guet.qiusuo.fruittravel.model.Goods;
import com.guet.qiusuo.fruittravel.model.OrderForm;
import org.mybatis.dynamic.sql.render.RenderingStrategies;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static java.lang.invoke.MethodHandles.lookup;
import static org.mybatis.dynamic.sql.SqlBuilder.*;
import static org.slf4j.LoggerFactory.getLogger;


@Service
public class FruitService {

    private FruitMapper fruitMapper;

    private ChildFruitService childFruitService;

    private UploadImgService uploadImgService;

    private GoodsService goodsService;

    private OrderFormMapper orderFormMapper;
    private GoodsMapper goodsMapper;
    @Autowired
    public void setOrderFormMapper(OrderFormMapper orderFormMapper) {
        this.orderFormMapper = orderFormMapper;
    }
    @Autowired
    public void setGoodsMapper(GoodsMapper goodsMapper) {
        this.goodsMapper = goodsMapper;
    }

    @Autowired
    public void setGoodsService(GoodsService goodsService) {
        this.goodsService = goodsService;
    }

    @Autowired
    public void setUploadImgService(UploadImgService uploadImgService) {
        this.uploadImgService = uploadImgService;
    }

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
     * ??????????????????
     */
    public PageList<FruitVO> getFruitList(String id, String fruitName, String fruitPrice, String nameLike, String departurePoint
            , String description, Integer deliveryCost,Short orderByType, Integer page, Integer pageSize) {
        if (nameLike == null || nameLike.length() == 0) {
            nameLike = "";
        }

        PageHelper.startPage(page,pageSize);
        List<FruitVO> fruitList;
        if(orderByType.equals(SystemConstants.PRICE_ASC)) {
            fruitList = fruitMapper.selectFruitSortByPriceASC(nameLike);
            for(FruitVO fruitVO : fruitList) {
                fruitVO.setSales(Math.toIntExact(getSalesByFruitIdSql(fruitVO.getId(), SystemConstants.MONTH)));
            }
        }
        else if(orderByType.equals(SystemConstants.PRICE_DESC)) {
            fruitList = fruitMapper.selectFruitSortByPriceDESC(nameLike);
            for(FruitVO fruitVO : fruitList) {
                fruitVO.setSales(Math.toIntExact(getSalesByFruitIdSql(fruitVO.getId(), SystemConstants.MONTH)));
            }
        }
        else if(orderByType.equals(SystemConstants.SALE_ASC)) {
            fruitList = fruitMapper.selectFruitSortBySalesASC(nameLike);
            for(FruitVO fruitVO : fruitList) {
                fruitVO.setSales(Math.toIntExact(getSalesByFruitIdSql(fruitVO.getId(), SystemConstants.MONTH_MILLIS)));
            }
        }
        else if(orderByType.equals(SystemConstants.SALE_DESC)) {
            fruitList = fruitMapper.selectFruitSortBySalesDESC(nameLike);
            for(FruitVO fruitVO : fruitList) {
                fruitVO.setSales(Math.toIntExact(getSalesByFruitIdSql(fruitVO.getId(), SystemConstants.MONTH_MILLIS)));
            }
        }
        else if(orderByType.equals(SystemConstants.GRADE_ASC)) {
            fruitList = fruitMapper.selectFruitSortByGradeASC(nameLike);
            for(FruitVO fruitVO : fruitList) {
                fruitVO.setSales(Math.toIntExact(getSalesByFruitIdSql(fruitVO.getId(), SystemConstants.MONTH_MILLIS)));
            }
        }
        else if(orderByType.equals(SystemConstants.GRADE_DESC)) {
            fruitList = fruitMapper.selectFruitSortByGradeDESC(nameLike);
            for(FruitVO fruitVO : fruitList) {
                fruitVO.setSales(Math.toIntExact(getSalesByFruitIdSql(fruitVO.getId(), SystemConstants.MONTH_MILLIS)));
            }
        }
        //????????????,???????????????????????????,???????????????0.5,?????????????????????0.25
        else if(orderByType.equals(SystemConstants.SORT_ALL)) {
            fruitList = fruitMapper.selectFruitSort(nameLike);
            for(FruitVO fruitVO : fruitList) {
                fruitVO.setSales(Math.toIntExact(getSalesByFruitIdSql(fruitVO.getId(), SystemConstants.MONTH_MILLIS)));
            }
        }
        else {
            throw new SystemException(ErrorCode.ORDERBYTYPE_ERROR);
        }
        PageList<FruitVO> pageList = new PageList<>();
        pageList.setList(fruitList);
        pageList.setPageInfo(new PageInfo<>(fruitList));
        LOG.info("????????????????????????");
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
     * ??????Fruit
     * @param fruit
     */

    public Fruit addFruit(Fruit fruit){
        long now = System.currentTimeMillis();
        UserContextHolder.validAdmin();
        if (!getFruitByName(fruit.getFruitName()).isEmpty()) {
            //???????????????Fruit
            throw new SystemException(ErrorCode.FRUIT_ALREADY_EXITS);
        }

        fruit.setId(UUID.randomUUID().toString().replace("-", ""));
        fruit.setCreateTime(now);
        fruit.setUpdateTime(now);
        fruit.setCreateUserId(UserContextHolder.getUserId());
        fruit.setUpdateUserId(UserContextHolder.getUserId());
        /*
         * 1.??????????????? Fruit???childFruit?????????????????????,????????????STATUS_ACTIVE
         * 2.??????,?????????CHILD_FRUIT_INFO_NOT_COMPLETE
         * */
        //??????????????????
        fruit.setStatus(SystemConstants.STATUS_ACTIVE);
        int i = fruitMapper.insertSelective(fruit);
        if(i == 0){
            throw new SystemException(ErrorCode.INSERT_ERROR);
        }
        LOG.info("????????????{}??????,id={}",fruit.getFruitName(),fruit.getId());
        return fruit;
    }


    /**
     * ??????Fruit(with childFruit)
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
        //???????????????childFruit
        childFruitService.deleteAll(check.getId());
        check.setStatus(SystemConstants.STATUS_NEGATIVE);
        check.setUpdateTime(System.currentTimeMillis());
        check.setUpdateUserId(UserContextHolder.getUserId());
        //??????Fruit
        int i = fruitMapper.updateByPrimaryKey(check);
        if (i == 0){
            throw new SystemException(ErrorCode.DELETE_ERROR);
        }
        LOG.info("????????????{}??????,id={}",check.getFruitName(),fruitId);
    }

    /**
     * ??????Fruit
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
        LOG.info("????????????{}??????,id={}",fruit.getFruitName(),fruit.getId());
        return true;
    }

    /**
     * ????????????id????????????
     * @param fruitId
     * @return
     */
    public Fruit getFruit(String fruitId){
        Optional<Fruit> optionalFruit =  fruitMapper.selectByPrimaryKey(fruitId);
        Fruit fruit = optionalFruit.orElseThrow(() -> new SystemException(ErrorCode.NO_FOUND_FRUIT));
        FruitUrlVO fruitUrlVO = new FruitUrlVO();
        fruitUrlVO.setId(fruit.getId());
        fruitUrlVO.setFruitName(fruit.getFruitName());
        fruitUrlVO.setFruitPrice(fruit.getFruitPrice());
        fruitUrlVO.setDeliveryCost(fruit.getDeliveryCost());
        fruitUrlVO.setDeparturePoint(fruit.getDeparturePoint());
        fruitUrlVO.setDescription(fruit.getDescription());
        fruitUrlVO.setStatus(fruit.getStatus());
        fruitUrlVO.setUpdateTime(fruit.getUpdateTime());
        fruitUrlVO.setUpdateUserId(fruit.getUpdateUserId());
        fruitUrlVO.setCreateTime(fruit.getCreateTime());
        fruitUrlVO.setCreateUserId(fruit.getCreateUserId());
        fruitUrlVO.setImgUrl(uploadImgService.getUrlByProdId(fruitId));
        LOG.info("??????????????????,id={}",fruitId);
        return fruitUrlVO;

    }
    /**
     * ????????????Fruit
     * @return
     */
    public PageList<FruitVO> getAllFruits(Integer page, Integer pageSize){
        PageHelper.startPage(page,pageSize);
        List<FruitVO> fruitVOList = fruitMapper.getAllFruits();
        if (fruitVOList.isEmpty()){
            return null;
        }
        for (FruitVO fruitVO:fruitVOList) {
            List<String> fruitUrls = uploadImgService.getUrlByProdId(fruitVO.getId());
            fruitVO.setFruitImageUrl(fruitUrls);
            List<String> childFUrls = uploadImgService.getUrlByProdId(fruitVO.getChildFruitId());
            fruitVO.setChildFImageUrl(childFUrls);
            fruitVO.setSales(Math.toIntExact(getSalesByFruitIdSql(fruitVO.getId(), SystemConstants.MONTH_MILLIS)));
        }
        PageList<FruitVO> pageList = new PageList<>();
        pageList.setList(fruitVOList);
        pageList.setPageInfo(new PageInfo<>(fruitVOList));
        LOG.info("????????????????????????");
        return pageList;
    }

    /**
     * ????????????Fruit - ?????????
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
     * ??????fruit_id??????FruitVO
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
        //????????????url
        List<String> fruitUrls = uploadImgService.getUrlByProdId(fruitVO.getId());
        fruitVO.setFruitImageUrl(fruitUrls);
        List<String> childFUrls = uploadImgService.getUrlByProdId(fruitVO.getChildFruitId());
        fruitVO.setChildFImageUrl(childFUrls);
        List<ChildFruit> childFruitListByFruitId = childFruitService.getChildFruitListByFruitId(fruitId);
        int stock = 0;
        for (ChildFruit c:childFruitListByFruitId) {
            stock=+c.getStock();
        }
        fruitVO.setStock(stock);
        return fruitVO;
    }

    public PageList<FruitVO> getFruitRecommendList(Integer page, Integer pageSize) {
        PageHelper.startPage(page,pageSize);
        List<FruitVO> fruitList = fruitMapper.FruitRecommend();

        for (FruitVO fruitVO:fruitList) {
            List<String> fruitUrls = uploadImgService.getUrlByProdId(fruitVO.getId());
            fruitVO.setFruitImageUrl(fruitUrls);
            List<String> childFUrls = uploadImgService.getUrlByProdId(fruitVO.getChildFruitId());
            fruitVO.setChildFImageUrl(childFUrls);
        }

        PageList<FruitVO> pageList = new PageList<>();
        pageList.setList(fruitList);
        pageList.setPageInfo(new PageInfo<>(fruitList));
        return pageList;
    }

    /**
     * ????????????????????????
     */
    public List<FruitRecVO> getFruitRec() {
        List<FruitRecVO> res = new ArrayList<>();
        List<ChildFruit> allChildFruits = childFruitService.getAllChildFruits();
            for (int i = 0; i < 6; i++) {
                FruitRecVO fruitRecVO = new FruitRecVO();
                 fruitRecVO.setId(allChildFruits.get(i).getId());
                 fruitRecVO.setFruitId(allChildFruits.get(i).getFruitId());
                 fruitRecVO.setFruitName(allChildFruits.get(i).getFruitName());
                 fruitRecVO.setFruitPrice(allChildFruits.get(i).getFruitPrice());
                 fruitRecVO.setStock(allChildFruits.get(i).getStock());
                 fruitRecVO.setImageUrl(allChildFruits.get(i).getImageUrl());
                 fruitRecVO.setStatus(allChildFruits.get(i).getStatus());
                 fruitRecVO.setCreateTime(allChildFruits.get(i).getCreateTime());
                 fruitRecVO.setCreateUserId(allChildFruits.get(i).getCreateUserId());
                 fruitRecVO.setUpdateTime(allChildFruits.get(i).getUpdateTime());
                 fruitRecVO.setUpdateUserId(allChildFruits.get(i).getUpdateUserId());
                 fruitRecVO.setSales(goodsService.getSaleByChildFruitId(allChildFruits.get(i).getId()));
                 res.add(fruitRecVO);
            }
            return res;
    }
    private Long getSalesByFruitIdSql(String fruitId,long past) {
        Long now = System.currentTimeMillis();
        long count = 0L;
        //????????????????????????????????????????????????
        List<OrderForm> orderForms = orderFormMapper.selectMany(select(
                OrderFormDynamicSqlSupport.id,
                OrderFormDynamicSqlSupport.payTime,
                OrderFormDynamicSqlSupport.createUserId,
                OrderFormDynamicSqlSupport.createTime
        )
                .from(OrderFormDynamicSqlSupport.orderForm)
                .where(OrderFormDynamicSqlSupport.status, isEqualTo(SystemConstants.STATUS_ACTIVE))
                .and(OrderFormDynamicSqlSupport.payTime, isBetween(past).and(now))
                .and(OrderFormDynamicSqlSupport.scenicId, isNull())
                .and(OrderFormDynamicSqlSupport.payStatus, isNotEqualTo(SystemConstants.UNPAID))
                .build().render(RenderingStrategies.MYBATIS3));
        //??????????????????
        for (OrderForm o:orderForms) {
            //???????????????????????????goods
            List<Goods> goodsList = goodsMapper.selectMany(select(
                    GoodsDynamicSqlSupport.id,
                    GoodsDynamicSqlSupport.orderId,
                    GoodsDynamicSqlSupport.fruitId,
                    GoodsDynamicSqlSupport.amount
            )
                    .from(GoodsDynamicSqlSupport.goods)
                    .where(GoodsDynamicSqlSupport.orderId, isEqualTo(o.getId()))
                    .and(GoodsDynamicSqlSupport.status, isEqualTo(SystemConstants.STATUS_ACTIVE))
                    .build().render(RenderingStrategies.MYBATIS3));
            //????????????goods???ChildFruitId????????????this.fruitId?????????
            for (Goods g:goodsList) {
                //goods???????????????id
                String childFruitId = g.getFruitId();
                //??????????????????id????????????id
                String fruitId2 = childFruitService.getChildFruit(childFruitId).getFruitId();
                //??????fruitId??????
                if (fruitId2.equals(fruitId) && g.getAmount() != null){
                    //??????
                    count+=g.getAmount();
                }
            }
        }
        return count;
    }

}


