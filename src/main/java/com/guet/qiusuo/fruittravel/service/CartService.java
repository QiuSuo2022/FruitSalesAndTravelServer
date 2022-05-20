package com.guet.qiusuo.fruittravel.service;

import com.guet.qiusuo.fruittravel.bean.vo.CartVO;
import com.guet.qiusuo.fruittravel.common.SystemConstants;
import com.guet.qiusuo.fruittravel.config.ErrorCode;
import com.guet.qiusuo.fruittravel.config.SystemException;
import com.guet.qiusuo.fruittravel.config.UserContextHolder;
import com.guet.qiusuo.fruittravel.dao.CartMapper;

import com.guet.qiusuo.fruittravel.dao.CartDynamicSqlSupport;
import com.guet.qiusuo.fruittravel.dao.ChildFruitMapper;
import com.guet.qiusuo.fruittravel.model.Cart;
import com.guet.qiusuo.fruittravel.model.ChildFruit;
import org.mybatis.dynamic.sql.render.RenderingStrategies;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static java.lang.invoke.MethodHandles.lookup;
import static org.mybatis.dynamic.sql.SqlBuilder.*;
import static org.slf4j.LoggerFactory.getLogger;


@Service
public class CartService {

    private CartMapper cartMapper;

    private ChildFruitMapper childFruitMapper;

    private static final Logger LOG = getLogger(lookup().lookupClass());

    @Autowired
    public void setCartMapper(CartMapper cartMapper){
        this.cartMapper = cartMapper;
    }

    @Autowired
    public void setChildFruitMapper(ChildFruitMapper childFruitMapper) {
        this.childFruitMapper = childFruitMapper;
    }

    public List<Cart> getCartByUserIdAndChildFruitId(String userId, String childFruitId){
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
    public Cart addCart(Cart cart){
        String userId = UserContextHolder.getUserId();
        boolean flag = false;
        if (cart == null){
            LOG.info("参数为空!");
            throw new SystemException(ErrorCode.PARAM_ERROR);
        }

        //查找数据库中是否已经存在相同的水果
        List<Cart> cartList = getCartByUserId(cart.getUserId());
        //如果购物车为空
        if (cartList.isEmpty()){
            cart.setId(UUID.randomUUID().toString().replace("-", ""));
            cart.setUserId(userId);
            cart.setStatus(SystemConstants.STATUS_ACTIVE);
            cart.setCreateTime(System.currentTimeMillis());
            cart.setUpdateTime(System.currentTimeMillis());
            cart.setCreateUserId(UserContextHolder.getUserId());
            cart.setUpdateUserId(UserContextHolder.getUserId());
            int i = cartMapper.insert(cart);
            if (i == 0){
                throw new SystemException(ErrorCode.INSERT_ERROR);
            }
            flag = true;
            LOG.info("添加水果子项{}进入购物车成功",cart.getChildFruitId());
        }
        //搜索是否已经有相同的childFruit在购物车
        for (Cart c:cartList) {
            if (c.getChildFruitId().equals(cart.getChildFruitId())){
                int sum = c.getQuantity() + cart.getQuantity();
                c.setQuantity(sum);
                flag = true;
                updateCart(c);
                break;
            }
        }
        if (!flag){
            cart.setId(UUID.randomUUID().toString().replace("-", ""));
            cart.setUserId(userId);
            cart.setStatus(SystemConstants.STATUS_ACTIVE);
            cart.setCreateTime(System.currentTimeMillis());
            cart.setUpdateTime(System.currentTimeMillis());
            cart.setCreateUserId(UserContextHolder.getUserId());
            cart.setUpdateUserId(UserContextHolder.getUserId());
            int i = cartMapper.insert(cart);
            if (i == 0){
                throw new SystemException(ErrorCode.INSERT_ERROR);
            }
            LOG.info("添加水果子项{}进入购物车成功",cart.getChildFruitId());
        }
        return cart;
    }

    /**
     * 从购物车删除水果
     * @param childFruitId
     */
    public void deleteCart(String childFruitId){
        List<Cart> cartList = getCartByUserIdAndChildFruitId(UserContextHolder.getUserId(), childFruitId);
        if (cartList.isEmpty()){
            LOG.info("购物车中没有数据");
            throw new SystemException(ErrorCode.DELETE_ERROR);
        }

        Cart cart = cartList.get(0);
        cart.setStatus(SystemConstants.STATUS_NEGATIVE);
        cart.setUpdateTime(System.currentTimeMillis());
        cart.setUpdateUserId(UserContextHolder.getUserId());
        int i = cartMapper.updateByPrimaryKey(cart);
        if (i == 0){
            LOG.info("删除购物车内容失败");
            throw new SystemException(ErrorCode.DELETE_ERROR);
        }
        LOG.info("删除购物车内容成功");
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
            LOG.info("修改购物车内容失败");
            throw new SystemException(ErrorCode.UPDATE_ERROR);
        }
        LOG.info("修改购物车内容成功");
    }

    /**
     * 查找所有Cart
     * @return
     */
    public List<CartVO> selectCarts(){
        List<Cart> cartList = getCartByUserId(UserContextHolder.getUserId());
        if (cartList.isEmpty()){
            return null;
        }
        List<CartVO> cartVO = new ArrayList<CartVO>(cartList.size());
        for (int i = 0; i < cartList.size();i++) {
            CartVO vo = new CartVO();
            vo.setCart(cartList.get(i));
            Optional<ChildFruit> optionalChildFruit = childFruitMapper.selectByPrimaryKey(cartList.get(i).getChildFruitId());
            ChildFruit childFruit = optionalChildFruit.orElse(null);
            vo.setChildFruit(childFruit);
            cartVO.add(i,vo);
        }
        LOG.info("获取全部购物车对象成功");
        return cartVO;
    }
}
