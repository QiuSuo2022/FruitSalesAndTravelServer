package com.guet.qiusuo.fruittravel.service;

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

    private ChildFruitService childFruitService;
    @Autowired
    public void setChildFruitService(ChildFruitService childFruitService) {
        this.childFruitService = childFruitService;
    }

    @Autowired
    public void setGoodsMapper(GoodsMapper goodsMapper) { this.goodsMapper = goodsMapper; }

    public Goods selectGoodsByOrderId(String orderId) {
        List<Goods> goodsList = goodsMapper.selectMany(select(
                GoodsDynamicSqlSupport.fruitId,
                GoodsDynamicSqlSupport.scenicId,
                GoodsDynamicSqlSupport.name,
                GoodsDynamicSqlSupport.price,
                GoodsDynamicSqlSupport.amount
        )
                        .from(GoodsDynamicSqlSupport.goods)
                        .where(GoodsDynamicSqlSupport.status,isEqualTo(SystemConstants.STATUS_ACTIVE))
                        .and(GoodsDynamicSqlSupport.orderId,isEqualTo(orderId))
                        .build().render(RenderingStrategies.MYBATIS3));
        if (goodsList.isEmpty()) {
            throw new SystemException(ErrorCode.NO_FOUND_GOODS);
        }
        return goodsList.get(0);
    }

    /**
     * 添加商品
     */
    @Transactional(rollbackFor = Exception.class)
    public Goods addGood(Cart cart, String orderId) {
        Goods good = new Goods();
        long now = System.currentTimeMillis();
        ChildFruit c = childFruitService.getChildFruit(cart.getChildFruitId());
        good.setId(UUID.randomUUID().toString().replace("-", ""));
        good.setOrderId(orderId);
        good.setName(c.getFruitName());
        good.setPrice(c.getFruitPrice());
        good.setFruitId(c.getFruitId());
        good.setScenicId(null);
        good.setAmount(cart.getQuantity());
        good.setPayStatus(SystemConstants.UNPAID);
        good.setStatus(SystemConstants.STATUS_ACTIVE);
        good.setCreateTime(now);
        good.setUpdateTime(now);
        good.setCreateUserId(UserContextHolder.getUserId());
        good.setUpdateUserId(UserContextHolder.getUserId());
        int i = goodsMapper.insertSelective(good);
        if(i == 0) {
            throw new SystemException(ErrorCode.INSERT_ERROR);
        }
        LOG.info("创建订单映射商品{}成功",good.getName());
        return good;
    }

    /**
     * 删除商品
     * @param orderId
     */
    public void deleteGoods(String orderId) {
        Optional<Goods> optionalGoods = goodsMapper.selectByPrimaryKey(orderId);
        Goods goods = optionalGoods.orElseThrow(() -> new SystemException(ErrorCode.NO_FOUND_GOODS));
        goods.setStatus(SystemConstants.STATUS_NEGATIVE);
        goods.setUpdateUserId(UserContextHolder.getUserId());
        goods.setUpdateTime(System.currentTimeMillis());
        int i = goodsMapper.updateByPrimaryKeySelective(goods);
        if (i == 0) {
            throw new SystemException(ErrorCode.DELETE_ERROR);
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
    public List<Goods> searchAllGoods(String orderId) {
        return goodsMapper.selectMany(select(
                GoodsDynamicSqlSupport.id,
                GoodsDynamicSqlSupport.orderId,
                GoodsDynamicSqlSupport.name,
                GoodsDynamicSqlSupport.price,
                GoodsDynamicSqlSupport.fruitId,
                GoodsDynamicSqlSupport.scenicId,
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
}
