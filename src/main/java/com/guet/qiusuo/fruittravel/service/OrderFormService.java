package com.guet.qiusuo.fruittravel.service;

import com.guet.qiusuo.fruittravel.bean.vo.FruitOrderVO;
import com.guet.qiusuo.fruittravel.bean.vo.GoodsVO;
import com.guet.qiusuo.fruittravel.bean.vo.OrderAndProductVO;
import com.guet.qiusuo.fruittravel.common.SystemConstants;
import com.guet.qiusuo.fruittravel.config.ErrorCode;
import com.guet.qiusuo.fruittravel.config.SystemException;
import com.guet.qiusuo.fruittravel.config.UserContextHolder;
import com.guet.qiusuo.fruittravel.dao.ChildFruitMapper;
import com.guet.qiusuo.fruittravel.dao.OrderFormDynamicSqlSupport;
import com.guet.qiusuo.fruittravel.dao.OrderFormMapper;
import com.guet.qiusuo.fruittravel.model.*;
import org.mybatis.dynamic.sql.render.RenderingStrategies;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;
import static org.mybatis.dynamic.sql.SqlBuilder.select;
import static java.lang.invoke.MethodHandles.lookup;
import static org.slf4j.LoggerFactory.getLogger;


@Service
public class OrderFormService {

    private OrderFormMapper orderFormMapper;
    private static final Logger LOG = getLogger(lookup().lookupClass());
    private GoodsService goodsService;

    private ChildFruitMapper childFruitMapper;

    private UploadImgService uploadImgService;
    @Autowired

    public void setUploadImgService(UploadImgService uploadImgService) {
        this.uploadImgService = uploadImgService;
    }


    private TicketService ticketService;
    @Autowired
    public void setTicketService(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @Autowired
    public void setChildFruitMapper(ChildFruitMapper childFruitMapper) {
        this.childFruitMapper = childFruitMapper;
    }

    @Autowired
    public void setOrderFormMapper(OrderFormMapper orderFormMapper) {
        this.orderFormMapper = orderFormMapper;
    }

    @Autowired
    public void setGoodsService(GoodsService goodsService) { this.goodsService = goodsService; }
    /**
     * ??????????????????
     */

    public OrderAndProductVO createFruitOrder(FruitOrderVO fruitOrderVO){
        OrderForm fruitOrder = new OrderForm();
        fruitOrder.setId(UUID.randomUUID().toString().replace("-",""));
        fruitOrder.setAddress(fruitOrderVO.getAddress());
        fruitOrder.setPayStatus(SystemConstants.UNPAID);
        fruitOrder.setStatus(SystemConstants.STATUS_ACTIVE);
        fruitOrder.setBindEvaluateId(null);
        fruitOrder.setScenicId(null);
        fruitOrder.setHasEvaluate(SystemConstants.UNEVAL);
        fruitOrder.setExpress(null);
        long now = System.currentTimeMillis();
        fruitOrder.setCreateTime(now);
        fruitOrder.setUpdateTime(now);
        fruitOrder.setCreateUserId(UserContextHolder.getUserId());
        fruitOrder.setUpdateUserId(UserContextHolder.getUserId());
        //?????????????????????
        int i = orderFormMapper.insertSelective(fruitOrder);
        if (i == 0){
            throw new SystemException(ErrorCode.INSERT_ERROR);
        }
        //????????????
        int fee = 0;
        List<GoodsVO> goodsArrayList = new ArrayList<>();
        /***????????????goods???***/
        for (Cart cart:fruitOrderVO.getCartList()) {
            if (cart.getId() == null){
                cart.setId(UUID.randomUUID().toString().replace("-",""));
            }
            GoodsVO goodsVO = goodsService.addGood(cart, fruitOrder.getId());
            if (goodsVO != null) {
                goodsArrayList.add(goodsVO);
            }

            ChildFruit childFruit = childFruitMapper.selectByPrimaryKey(cart.getChildFruitId()).orElse(null);
            if (childFruit == null){
                continue;
            }
            //??????????????????
            fee += childFruit.getFruitPrice() * cart.getQuantity();
        }

        fruitOrder.setFee(fee);
        int j = orderFormMapper.updateByPrimaryKey(fruitOrder);
        if (j == 0){
            throw new SystemException(ErrorCode.INSERT_ERROR);
        }
        LOG.info("??????id={}?????????????????????????????????",fruitOrder.getId());
        OrderAndProductVO vo = new OrderAndProductVO();
        vo.setOrderForm(fruitOrder);
        vo.setGoodsVOS(goodsArrayList);
        vo.setThisTicket(null);
        vo.setOrderForm(fruitOrder);
        return vo;
    }

    /**
     * ??????????????????
     * ???????????????address??????????????????(1?????? 2?????? 3??????)  express????????????
     */
    public OrderAndProductVO createScenicOrder(OrderForm scenicOrder) {
        long now = System.currentTimeMillis();
        //????????????
        Short type = Short.valueOf(scenicOrder.getAddress());
        Ticket thisTicket = ticketService.getTicketByType(scenicOrder.getScenicId(), type);
        Integer amount = Integer.valueOf(scenicOrder.getExpress());
        Integer price = thisTicket.getPrice();
        int fee = amount*price;
        LOG.info("???????????????{}",fee);
        //?????????????????????
        scenicOrder.setId(UUID.randomUUID().toString().replace("-",""));
        scenicOrder.setFee(fee);
        scenicOrder.setHasEvaluate(SystemConstants.UNEVAL);
        scenicOrder.setStatus(SystemConstants.STATUS_ACTIVE);
        scenicOrder.setPayStatus(SystemConstants.UNPAID);
        scenicOrder.setCreateUserId(UserContextHolder.getUserId());
        scenicOrder.setCreateTime(now);
        scenicOrder.setUpdateUserId(UserContextHolder.getUserId());
        scenicOrder.setUpdateTime(now);
        LOG.info("????????????????????????,id={}",scenicOrder.getId());
        OrderAndProductVO vo = new OrderAndProductVO();
        vo.setOrderForm(scenicOrder);
        vo.setGoodsVOS(null);
        vo.setThisTicket(thisTicket);
        vo.setOrderForm(scenicOrder);
        vo.setImgUrl(uploadImgService.getUrlByProdId(scenicOrder.getScenicId()).get(0));
        return vo;
    }
    /**
     * ????????????????????????
     * @param orderId
     * @param orderStatus
     * @return
     */
    public boolean setOrderStatus(String orderId, Short orderStatus){
        OrderForm order = orderFormMapper.selectByPrimaryKey(orderId).orElse(null);
        if(order == null){
            LOG.info("?????????????????????!");
            throw new SystemException(ErrorCode.PARAM_ERROR);
        }
        if (orderStatus == null){
            throw new SystemException(ErrorCode.PARAM_NULL_ERROR);
        }
        order.setPayStatus(orderStatus);
        order.setUpdateUserId(UserContextHolder.getUserId());
        order.setUpdateTime(System.currentTimeMillis());
        int i = orderFormMapper.updateByPrimaryKey(order);
        if (i == 0){
            throw new SystemException(ErrorCode.UPDATE_ERROR);
        }
        LOG.info("????????????????????????");
        return true;
    }

    /**
     * ????????????
     * @param orderId
     * @param evalId
     */
    public void finishOrder(String orderId,String evalId){
        OrderForm order = orderFormMapper.selectByPrimaryKey(orderId).orElse(null);
        if(order != null){
            LOG.info("?????????????????????!");
            throw new SystemException(ErrorCode.PARAM_ERROR);
        }
        order.setPayStatus(SystemConstants.FINISHED);
        order.setHasEvaluate(SystemConstants.EVAL);
        order.setBindEvaluateId(evalId);
        order.setUpdateUserId(UserContextHolder.getUserId());
        order.setUpdateTime(System.currentTimeMillis());
        int i = orderFormMapper.updateByPrimaryKey(order);
        if (i == 0){
            throw new SystemException(ErrorCode.UPDATE_ERROR);
        }
        LOG.info("????????????????????????");
    }

    /**
     * ????????????
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public void deleteOrderForm(String orderFormId){
        Optional<OrderForm> orderFormOptional = orderFormMapper.selectByPrimaryKey(orderFormId);
        OrderForm orderForm = orderFormOptional.orElseThrow(() -> new SystemException(ErrorCode.DELETE_ERROR));
        orderForm.setUpdateTime(System.currentTimeMillis());
        orderForm.setUpdateUserId(UserContextHolder.getUserId());
        orderForm.setStatus(SystemConstants.STATUS_NEGATIVE);
        int i = orderFormMapper.updateByPrimaryKey(orderForm);
        if (i == 0){
            throw new SystemException(ErrorCode.DELETE_ERROR);
        }
        LOG.info("??????????????????");
    }




    /**
     * ????????????id????????????
     * @param orderFormId
     * @return
     */
    public OrderAndProductVO getOrderVOFormById(String orderFormId){
        Optional<OrderForm> orderFormOptional = orderFormMapper.selectByPrimaryKey(orderFormId);
        OrderForm orderForm = orderFormOptional.orElseThrow(()->new SystemException(ErrorCode.NO_FOUND_ORDER_FOMR));
        OrderAndProductVO res = new OrderAndProductVO();
        res.setOrderForm(orderForm);
        //????????????
        if (orderForm.getScenicId() == null){
            List<GoodsVO> goods = goodsService.getGoodsVO(orderFormId);
            res.setGoodsVOS(goods);
            res.setThisTicket(null);
        }else {
        //????????????
            res.setThisTicket(ticketService.getTicketByType(orderFormId,Short.valueOf(orderForm.getAddress())));
            res.setGoodsVOS(null);
        }
        return res;
    }

    /**
     * ?????????????????????????????????id??????????????????
     * @param payStatus
     * @return
     */
    public List<OrderAndProductVO> getOrderVOsByType_User(Short payStatus) {
        List<OrderForm> orderForms = orderFormMapper.selectMany(select(
                OrderFormDynamicSqlSupport.id,
                OrderFormDynamicSqlSupport.scenicId,
                OrderFormDynamicSqlSupport.address,
                OrderFormDynamicSqlSupport.express,
                OrderFormDynamicSqlSupport.fee,
                OrderFormDynamicSqlSupport.payStatus,
                OrderFormDynamicSqlSupport.hasEvaluate,
                OrderFormDynamicSqlSupport.bindEvaluateId,
                OrderFormDynamicSqlSupport.status,
                OrderFormDynamicSqlSupport.payTime,
                OrderFormDynamicSqlSupport.createTime,
                OrderFormDynamicSqlSupport.updateTime,
                OrderFormDynamicSqlSupport.createUserId,
                OrderFormDynamicSqlSupport.updateUserId
        )
                .from(OrderFormDynamicSqlSupport.orderForm)
                .where(OrderFormDynamicSqlSupport.status, isEqualTo(SystemConstants.STATUS_ACTIVE))
                .and(OrderFormDynamicSqlSupport.payStatus, isEqualTo(payStatus))
                .and(OrderFormDynamicSqlSupport.createUserId, isEqualTo(UserContextHolder.getUserId()))
                .build().render(RenderingStrategies.MYBATIS3));
        List<OrderAndProductVO> list = new ArrayList<>();
        for (OrderForm o:orderForms) {
            OrderAndProductVO orderVO = getOrderVOFormById(o.getId());
            list.add(orderVO);
        }
        return list;
    }

    /**
     * ????????????????????????
     * @return
     */
    public List<OrderAndProductVO> getAllOrderVO_User() {
        List<OrderForm> orderForms = orderFormMapper.selectMany(select(
                OrderFormDynamicSqlSupport.id,
                OrderFormDynamicSqlSupport.scenicId,
                OrderFormDynamicSqlSupport.address,
                OrderFormDynamicSqlSupport.express,
                OrderFormDynamicSqlSupport.fee,
                OrderFormDynamicSqlSupport.payStatus,
                OrderFormDynamicSqlSupport.hasEvaluate,
                OrderFormDynamicSqlSupport.bindEvaluateId,
                OrderFormDynamicSqlSupport.status,
                OrderFormDynamicSqlSupport.payTime,
                OrderFormDynamicSqlSupport.createTime,
                OrderFormDynamicSqlSupport.updateTime,
                OrderFormDynamicSqlSupport.createUserId,
                OrderFormDynamicSqlSupport.updateUserId
        )
                .from(OrderFormDynamicSqlSupport.orderForm)
                .where(OrderFormDynamicSqlSupport.status, isEqualTo(SystemConstants.STATUS_ACTIVE))
                .and(OrderFormDynamicSqlSupport.createUserId, isEqualTo(UserContextHolder.getUserId()))
                .build().render(RenderingStrategies.MYBATIS3));
        List<OrderAndProductVO> list = new ArrayList<>();
        for (OrderForm o:orderForms) {
            OrderAndProductVO orderVO = getOrderVOFormById(o.getId());
            list.add(orderVO);
        }
        return list;
    }

    public List<OrderAndProductVO> getAllOrderVO_Admin() {
        UserContextHolder.validAdmin();
        List<OrderForm> orderForms = orderFormMapper.selectMany(select(
                OrderFormDynamicSqlSupport.id,
                OrderFormDynamicSqlSupport.scenicId,
                OrderFormDynamicSqlSupport.address,
                OrderFormDynamicSqlSupport.express,
                OrderFormDynamicSqlSupport.fee,
                OrderFormDynamicSqlSupport.payStatus,
                OrderFormDynamicSqlSupport.hasEvaluate,
                OrderFormDynamicSqlSupport.bindEvaluateId,
                OrderFormDynamicSqlSupport.status,
                OrderFormDynamicSqlSupport.payTime,
                OrderFormDynamicSqlSupport.createTime,
                OrderFormDynamicSqlSupport.updateTime,
                OrderFormDynamicSqlSupport.createUserId,
                OrderFormDynamicSqlSupport.updateUserId
        )
                .from(OrderFormDynamicSqlSupport.orderForm)
                .where(OrderFormDynamicSqlSupport.status, isEqualTo(SystemConstants.STATUS_ACTIVE))
                .build().render(RenderingStrategies.MYBATIS3));
        List<OrderAndProductVO> list = new ArrayList<>();
        for (OrderForm o:orderForms) {
            OrderAndProductVO orderVO = getOrderVOFormById(o.getId());
            list.add(orderVO);
        }
        return list;
    }
    /**
     * ????????????????????????????????????
     * @param orderFormId
     * @return
     */
    public short getPaymentStatus(String orderFormId){
        Optional<OrderForm> optionalOrderForm = orderFormMapper.selectByPrimaryKey(orderFormId);
        OrderForm orderForm = optionalOrderForm.orElse(null);
        if (orderForm == null){
            throw new SystemException(ErrorCode.PARAM_NULL_ERROR);
        }
        return orderForm.getPayStatus();
    }
    /**
     * ??????
     * @param orderId
     */
    public boolean fakePay(String orderId){
        OrderForm orderForm = orderFormMapper.selectByPrimaryKey(orderId).orElse(null);
        if (orderForm == null){
            throw new SystemException(ErrorCode.PARAM_ERROR);
        }
        if (!orderForm.getPayStatus().equals(SystemConstants.UNPAID)){
            LOG.info("??????????????????!");
            throw new SystemException(ErrorCode.PAY_ERROR);
        }
        long now = System.currentTimeMillis();
        orderForm.setPayStatus(SystemConstants.PAID);
        orderForm.setPayTime(now);
        LOG.info("????????????!");

        int i = orderFormMapper.updateByPrimaryKey(orderForm);
        if (i == 0){
            throw new SystemException(ErrorCode.UPDATE_ERROR);
        }
        return true;
    }

    /**
     * ??????
     * @param orderId
     */
    public boolean fakeRefund(String orderId){
        OrderForm orderForm = orderFormMapper.selectByPrimaryKey(orderId).orElse(null);
        if (orderForm == null){
            throw new SystemException(ErrorCode.PARAM_ERROR);
        }
        if (orderForm.getPayStatus().equals(SystemConstants.UNPAID)){
            throw new SystemException(ErrorCode.UNPAID_ERROR);
        }
        orderForm.setPayStatus(SystemConstants.REFUND);
        int i = orderFormMapper.updateByPrimaryKey(orderForm);
        if (i == 0){
            throw new SystemException(ErrorCode.UPDATE_ERROR);
        }
        LOG.info("????????????!");
        return true;
    }

    public List<OrderAndProductVO> getOrderVOsByType_Admin(Short orderStatus) {
        List<OrderForm> orderForms = orderFormMapper.selectMany(select(
                OrderFormDynamicSqlSupport.id,
                OrderFormDynamicSqlSupport.scenicId,
                OrderFormDynamicSqlSupport.address,
                OrderFormDynamicSqlSupport.express,
                OrderFormDynamicSqlSupport.fee,
                OrderFormDynamicSqlSupport.payStatus,
                OrderFormDynamicSqlSupport.hasEvaluate,
                OrderFormDynamicSqlSupport.bindEvaluateId,
                OrderFormDynamicSqlSupport.status,
                OrderFormDynamicSqlSupport.payTime,
                OrderFormDynamicSqlSupport.createTime,
                OrderFormDynamicSqlSupport.updateTime,
                OrderFormDynamicSqlSupport.createUserId,
                OrderFormDynamicSqlSupport.updateUserId
        )
                .from(OrderFormDynamicSqlSupport.orderForm)
                .where(OrderFormDynamicSqlSupport.status, isEqualTo(SystemConstants.STATUS_ACTIVE))
                .and(OrderFormDynamicSqlSupport.payStatus, isEqualTo(orderStatus))
                .build().render(RenderingStrategies.MYBATIS3));
        List<OrderAndProductVO> list = new ArrayList<>();
        for (OrderForm o:orderForms) {
            OrderAndProductVO orderVO = getOrderVOFormById(o.getId());
            list.add(orderVO);
        }
        return list;
    }
    /*
    public WxObject createOrderForm(HttpServletRequest request, OrderForm orderForm) throws JSONException {
        OrderForm check = orderFormMapper.selectByPrimaryKey(orderForm.getId()).orElse(null);
        if(check != null){
            LOG.info("??????????????????");
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("hadCreated",true);
            WxObject wxObject = new WxObject();
            wxObject.setJsonObject(jsonObject);
            return wxObject;
        }
        long now = System.currentTimeMillis();
        orderForm.setId(UUID.randomUUID().toString().replace("-", ""));
        orderForm.setCreateTime(now);
        orderForm.setUpdateTime(now);
        orderForm.setCreateUserId(UserContextHolder.getUserId());
        orderForm.setUpdateUserId(UserContextHolder.getUserId());
        orderForm.setPayStatus(SystemConstants.UNPAID);
        orderForm.setStatus(SystemConstants.STATUS_ACTIVE);
        int i = orderFormMapper.insert(orderForm);
        if (i == 0){
            throw new SystemException(ErrorCode.INSERT_ERROR);
        }
        LOG.info("????????????????????????");
        //?????????????????????Object??????
        WxObject wxObject = new WxObject();
        wxObject.setJsonObject(payService.wxPay(request,orderForm));
        return wxObject;
    }
*/


}
