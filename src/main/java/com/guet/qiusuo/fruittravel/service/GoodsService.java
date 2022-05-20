package com.guet.qiusuo.fruittravel.service;

import com.guet.qiusuo.fruittravel.bean.vo.GoodsVO;
import com.guet.qiusuo.fruittravel.common.SystemConstants;
import com.guet.qiusuo.fruittravel.config.ErrorCode;
import com.guet.qiusuo.fruittravel.config.SystemException;
import com.guet.qiusuo.fruittravel.config.UserContextHolder;
import com.guet.qiusuo.fruittravel.dao.GoodsDynamicSqlSupport;
import com.guet.qiusuo.fruittravel.dao.GoodsMapper;
import com.guet.qiusuo.fruittravel.model.Cart;
import com.guet.qiusuo.fruittravel.model.ChildFruit;
import com.guet.qiusuo.fruittravel.model.Goods;
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
import static org.slf4j.LoggerFactory.getLogger;
import static org.mybatis.dynamic.sql.SqlBuilder.*;

@Service
public class GoodsService {

    private GoodsMapper goodsMapper;
    private static final Logger LOG = getLogger(lookup().lookupClass());

    private  UploadImgService uploadImgService;
    @Autowired
    public void setUploadImgService(UploadImgService uploadImgService) {
        this.uploadImgService = uploadImgService;
    }

    private ChildFruitService childFruitService;
    @Autowired
    public void setChildFruitService(ChildFruitService childFruitService) {
        this.childFruitService = childFruitService;
    }

    @Autowired
    public void setGoodsMapper(GoodsMapper goodsMapper) { this.goodsMapper = goodsMapper; }
    /**
     * 添加订单商品
     */
    @Transactional(rollbackFor = Exception.class)
    public GoodsVO addGood(Cart cart, String orderId) {
        GoodsVO goodVO = new GoodsVO();
        long now = System.currentTimeMillis();
        ChildFruit c = childFruitService.getChildFruit(cart.getChildFruitId());
        goodVO.setId(UUID.randomUUID().toString().replace("-", ""));
        goodVO.setOrderId(orderId);
        goodVO.setName(c.getFruitName());
        goodVO.setPrice(c.getFruitPrice());
        //水果子项id
        goodVO.setFruitId(c.getId());
        goodVO.setScenicId(null);
        goodVO.setAmount(cart.getQuantity());
        goodVO.setPayStatus(SystemConstants.UNPAID);
        goodVO.setStatus(SystemConstants.STATUS_ACTIVE);
        goodVO.setCreateTime(now);
        goodVO.setUpdateTime(now);
        goodVO.setCreateUserId(UserContextHolder.getUserId());
        goodVO.setUpdateUserId(UserContextHolder.getUserId());
        goodVO.setImgUrl(uploadImgService.getUrlByProdId(goodVO.getFruitId()).get(0));


        Goods goods = new Goods();
        goods.setId(UUID.randomUUID().toString().replace("-", ""));
        goods.setOrderId(orderId);
        goods.setName(c.getFruitName());
        goods.setPrice(c.getFruitPrice());
        //水果子项id
        goods.setFruitId(c.getId());
        goods.setScenicId(null);
        goods.setAmount(cart.getQuantity());
        goods.setPayStatus(SystemConstants.UNPAID);
        goods.setStatus(SystemConstants.STATUS_ACTIVE);
        goods.setCreateTime(now);
        goods.setUpdateTime(now);
        goods.setCreateUserId(UserContextHolder.getUserId());
        goods.setUpdateUserId(UserContextHolder.getUserId());

        int i = goodsMapper.insertSelective(goods);
        if(i == 0) {
            throw new SystemException(ErrorCode.INSERT_ERROR);
        }
        LOG.info("创建订单映射商品{}成功",goods.getName());
        return goodVO;
    }

    /**
     * 逻辑删除订单商品
     * @param orderId
     */
    public void deleteGoods(String orderId) {
        List<Goods> goods = getGoods(orderId);
        for (Goods g:goods) {
            g.setStatus(SystemConstants.STATUS_NEGATIVE);
            g.setUpdateUserId(UserContextHolder.getUserId());
            g.setUpdateTime(System.currentTimeMillis());
            int i = goodsMapper.updateByPrimaryKeySelective(g);
            if (i == 0) {
                throw new SystemException(ErrorCode.DELETE_ERROR);
            }
            LOG.info("删除订单商品:{}成功",g.getName());
        }
    }

    /**
     * 修改商品
     * @param goods
     */
    public void updateGoods(Goods goods) {
        goods.setUpdateTime(System.currentTimeMillis());
        goods.setUpdateUserId(UserContextHolder.getUserId());
        int i = goodsMapper.updateByPrimaryKeySelective(goods);
        if (i == 0) {
            throw new SystemException(ErrorCode.UPDATE_ERROR);
        }
    }

    /**
     * 根据订单号查找该订单下的所有商品
     * @param orderId
     * @return
     */
    public List<Goods> getGoods(String orderId) {
         return goodsMapper.selectMany(select(
                GoodsDynamicSqlSupport.id,
                GoodsDynamicSqlSupport.orderId,
                GoodsDynamicSqlSupport.name,
                GoodsDynamicSqlSupport.price,
                GoodsDynamicSqlSupport.fruitId,
                GoodsDynamicSqlSupport.amount,
                GoodsDynamicSqlSupport.status,
                GoodsDynamicSqlSupport.createTime,
                GoodsDynamicSqlSupport.updateTime,
                GoodsDynamicSqlSupport.createUserId,
                GoodsDynamicSqlSupport.updateUserId
        )
                        .from(GoodsDynamicSqlSupport.goods)
                        .where(GoodsDynamicSqlSupport.status,isEqualTo(SystemConstants.STATUS_ACTIVE))
                        .and(GoodsDynamicSqlSupport.orderId,isEqualTo(orderId))
                        .build().render(RenderingStrategies.MYBATIS3));
        }
        public List<GoodsVO> getGoodsVO(String orderId) {
            List<Goods> goodsList = goodsMapper.selectMany(select(
                    GoodsDynamicSqlSupport.id,
                    GoodsDynamicSqlSupport.orderId,
                    GoodsDynamicSqlSupport.name,
                    GoodsDynamicSqlSupport.price,
                    GoodsDynamicSqlSupport.fruitId,
                    GoodsDynamicSqlSupport.amount,
                    GoodsDynamicSqlSupport.status,
                    GoodsDynamicSqlSupport.createTime,
                    GoodsDynamicSqlSupport.updateTime,
                    GoodsDynamicSqlSupport.createUserId,
                    GoodsDynamicSqlSupport.updateUserId
            )
                    .from(GoodsDynamicSqlSupport.goods)
                    .where(GoodsDynamicSqlSupport.status,isEqualTo(SystemConstants.STATUS_ACTIVE))
                    .and(GoodsDynamicSqlSupport.orderId,isEqualTo(orderId))
                    .build().render(RenderingStrategies.MYBATIS3));
            ArrayList<GoodsVO> goodsVOArrayList = new ArrayList<>();
            for (Goods g:goodsList) {
                GoodsVO goodsVO = new GoodsVO();
                goodsVO.setId(g.getId());
                goodsVO.setOrderId(g.getOrderId());
                goodsVO.setName(g.getName());
                goodsVO.setPrice(g.getPrice());
                goodsVO.setFruitId(g.getFruitId());
                goodsVO.setScenicId(g.getScenicId());
                goodsVO.setAmount(g.getAmount());
                goodsVO.setPayStatus(g.getPayStatus());
                goodsVO.setStatus(g.getStatus());
                goodsVO.setCreateTime(g.getCreateTime());
                goodsVO.setCreateUserId(g.getCreateUserId());
                goodsVO.setUpdateTime(g.getUpdateTime());
                goodsVO.setUpdateUserId(g.getUpdateUserId());
                goodsVO.setImgUrl(uploadImgService.getUrlByProdId(g.getFruitId()).get(0));
                goodsVOArrayList.add(goodsVO);
            }
            return goodsVOArrayList;
        }

    public Integer getSaleByChildFruitId(String childFruitId){
        List<Goods> list = goodsMapper.selectMany(select(
                GoodsDynamicSqlSupport.amount
        )
                .from(GoodsDynamicSqlSupport.goods)
                .where(GoodsDynamicSqlSupport.fruitId, isEqualTo(childFruitId))
                .and(GoodsDynamicSqlSupport.status, isEqualTo(SystemConstants.STATUS_ACTIVE))
                .build().render(RenderingStrategies.MYBATIS3));
        int res = 0;
        for (Goods g:list) {
            res += g.getAmount();
        }
        return res;
    }
}
