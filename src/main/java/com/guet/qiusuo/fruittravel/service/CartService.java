package com.guet.qiusuo.fruittravel.service;

import com.guet.qiusuo.fruittravel.common.SystemConstants;
import com.guet.qiusuo.fruittravel.config.ErrorCode;
import com.guet.qiusuo.fruittravel.config.SystemException;
import com.guet.qiusuo.fruittravel.config.UserContextHolder;
import com.guet.qiusuo.fruittravel.dao.CartMapper;

import com.guet.qiusuo.fruittravel.dao.CartDynamicSqlSupport;
import com.guet.qiusuo.fruittravel.model.Cart;
import org.mybatis.dynamic.sql.render.RenderingStrategies;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

import static java.lang.invoke.MethodHandles.lookup;
import static org.mybatis.dynamic.sql.SqlBuilder.*;
import static org.slf4j.LoggerFactory.getLogger;

/**
 * @author lu
 */
@Service
public class CartService {

    private CartMapper cartMapper;

    private static final Logger LOG = getLogger(lookup().lookupClass());

    @Autowired
    public void setCartMapper(CartMapper cartMapper){
        this.cartMapper = cartMapper;
    }

    public List<Cart> getCartByUserIdAndChildFruitId(String userId,String childFruitId){
        return cartMapper.selectMany(select(
                CartDynamicSqlSupport.id,
                CartDynamicSqlSupport.userId,
                CartDynamicSqlSupport.childFruitId,
                CartDynamicSqlSupport.quantity,
                CartDynamicSqlSupport.status,
                CartDynamicSqlSupport.createTime,
                CartDynamicSqlSupport.updateTime,
                CartDynamicSqlSupport.createUserId,
                CartDynamicSqlSupport.updateUserId
        )
                .from(CartDynamicSqlSupport.cart)
                .where(CartDynamicSqlSupport.userId, isEqualTo(userId))
                .and(CartDynamicSqlSupport.status,isEqualTo(SystemConstants.STATUS_ACTIVE))
                .and(CartDynamicSqlSupport.childFruitId,isEqualTo(childFruitId))
                .and(CartDynamicSqlSupport.quantity,isNotEqualTo(0))
                .build().render(RenderingStrategies.MYBATIS3));
    }
    public List<Cart> getCartByUserId(String userId){
        return cartMapper.selectMany(select(
                CartDynamicSqlSupport.id,
                CartDynamicSqlSupport.userId,
                CartDynamicSqlSupport.childFruitId,
                CartDynamicSqlSupport.quantity,
                CartDynamicSqlSupport.status,
                CartDynamicSqlSupport.createTime,
                CartDynamicSqlSupport.updateTime,
                CartDynamicSqlSupport.createUserId,
                CartDynamicSqlSupport.updateUserId
        )
                .from(CartDynamicSqlSupport.cart)
                .where(CartDynamicSqlSupport.userId, isEqualTo(userId))
                .and(CartDynamicSqlSupport.status,isEqualTo(SystemConstants.STATUS_ACTIVE))
                .and(CartDynamicSqlSupport.quantity,isNotEqualTo(0))
                .build().render(RenderingStrategies.MYBATIS3));
    }
    /**
     * 添加水果种类进购物车
     * @param cart
     */
    public void addCart(Cart cart){
        if (cart == null){
            LOG.info("参数为空!");
            throw new SystemException(ErrorCode.PARAM_ERROR);
        }
        //查找数据库中是否已经存在相同的水果
        Cart c = getCartByUserId(cart.getUserId()).get(0);
        if (c.getChildFruitId().equals(cart.getChildFruitId())){
            //如果有,仅增加数目
            int sum = c.getQuantity() + cart.getQuantity();
            c.setQuantity(sum);
            updateCart(c);
        }else {
            cart.setStatus(SystemConstants.STATUS_ACTIVE);
            cart.setCreateTime(System.currentTimeMillis());
            cart.setUpdateTime(System.currentTimeMillis());
            cart.setCreateUserId(UserContextHolder.getUserId());
            cart.setUpdateUserId(UserContextHolder.getUserId());
            int i = cartMapper.insert(cart);
            if (i == 0){
                throw new SystemException(ErrorCode.INSERT_ERROR);
            }
            LOG.info("添加{}成功",cart.getChildFruitId());
        }
    }

    /**
     * 从购物车删除水果
     * @param userId
     * @param childFruitId
     */
    public void deleteCart(String userId, String childFruitId){
        Cart cart = getCartByUserIdAndChildFruitId(userId,childFruitId).get(0);
        if (cart != null){
            cart.setStatus(SystemConstants.STATUS_NEGATIVE);
            cart.setUpdateTime(System.currentTimeMillis());
            cart.setUpdateUserId(UserContextHolder.getUserId());
            int i = cartMapper.updateByPrimaryKey(cart);
            if (i == 0){
                throw new SystemException(ErrorCode.DELETE_ERROR);
            }
        }else {
            LOG.info("不存在该数据!");
            throw new SystemException(ErrorCode.DELETE_ERROR);
        }
    }

    /**
     * 修改Cart
     * @param cart
     */
    public void updateCart(Cart cart){
        cart.setUpdateTime(System.currentTimeMillis());
        cart.setUpdateUserId(UserContextHolder.getUserId());
        int i = cartMapper.updateByPrimaryKey(cart);
        if (i == 0){
            throw new SystemException(ErrorCode.UPDATE_ERROR);
        }
    }

    /**
     * 查找所有Cart
     * @param userId
     * @return
     */
    public List<Cart> selectCarts(String userId){
        return getCartByUserId(userId);
    }
}
