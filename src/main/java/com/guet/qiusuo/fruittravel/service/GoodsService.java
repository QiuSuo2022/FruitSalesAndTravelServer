package com.guet.qiusuo.fruittravel.service;

import com.guet.qiusuo.fruittravel.common.SystemConstants;
import com.guet.qiusuo.fruittravel.config.ErrorCode;
import com.guet.qiusuo.fruittravel.config.SystemException;
import com.guet.qiusuo.fruittravel.config.UserContextHolder;
import com.guet.qiusuo.fruittravel.dao.GoodsDynamicSqlSupport;
import com.guet.qiusuo.fruittravel.dao.GoodsMapper;
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
     * @param goods
     */
    @Transactional(rollbackFor = Exception.class)
    public void addGoods(Goods goods) {
        long now = System.currentTimeMillis();
        goods.setId(UUID.randomUUID().toString().replace("-", ""));
        goods.setCreateTime(now);
        goods.setUpdateTime(now);
        goods.setCreateUserId(UserContextHolder.getUserId());
        goods.setUpdateUserId(UserContextHolder.getUserId());
        //若goods与OrderForm一起提交
        goods.setStatus(SystemConstants.STATUS_ACTIVE);
        //防止外键约束错误
        if(goods.getFruitId() == null) {
            goods.setFruitId(SystemConstants.nullFlag);
        }
        else if(goods.getScenicId() == null) {
            goods.setScenicId(SystemConstants.nullFlag);
        }
        int i = goodsMapper.insertSelective(goods);
        if(i == 0) {
            throw new SystemException(ErrorCode.INSERT_ERROR);
        }
        LOG.info("商品{}添加成功",goods.getName());
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
