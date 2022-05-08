package com.guet.qiusuo.fruittravel.service;

import com.guet.qiusuo.fruittravel.common.SystemConstants;
import com.guet.qiusuo.fruittravel.config.ErrorCode;
import com.guet.qiusuo.fruittravel.config.SystemException;
import com.guet.qiusuo.fruittravel.config.UserContextHolder;
import com.guet.qiusuo.fruittravel.dao.DeliveryInfoDynamicSqlSupport;
import com.guet.qiusuo.fruittravel.dao.DeliveryInfoMapper;
import com.guet.qiusuo.fruittravel.model.DeliveryInfo;
import org.mybatis.dynamic.sql.render.RenderingStrategies;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static java.lang.invoke.MethodHandles.lookup;
import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;
import static org.mybatis.dynamic.sql.SqlBuilder.select;
import static org.slf4j.LoggerFactory.getLogger;

@Service
public class DeliveryInfoService {

    private static final Logger LOG = getLogger(lookup().lookupClass());

    DeliveryInfoMapper deliveryInfoMapper;

    @Autowired
    public void setDeliveryInfoMapper(DeliveryInfoMapper deliveryInfoMapper) {
        this.deliveryInfoMapper = deliveryInfoMapper;
    }

    /**
     * 获取默认地址
     * @param userId
     * @return
     */
    public DeliveryInfo getDefaultDeliInfo(String userId){
        Optional<DeliveryInfo> deliveryInfo = deliveryInfoMapper.selectOne(select(
                DeliveryInfoDynamicSqlSupport.id,
                DeliveryInfoDynamicSqlSupport.deliveryName,
                DeliveryInfoDynamicSqlSupport.deliveryPhone,
                DeliveryInfoDynamicSqlSupport.deliveryAddress)
                .from(DeliveryInfoDynamicSqlSupport.deliveryInfo)
                .where(DeliveryInfoDynamicSqlSupport.userId, isEqualTo(userId))
                .and(DeliveryInfoDynamicSqlSupport.status, isEqualTo(SystemConstants.defaultInfo))
                .build().render(RenderingStrategies.MYBATIS3));
        LOG.info("获取默认地址成功");
        return deliveryInfo.orElse(null);
    }

    /**
     * 添加收货地址
     * @param deliveryInfo
     */
    public void addDeliInfo(DeliveryInfo deliveryInfo){
        //获取用户id
        String userId = UserContextHolder.getUserId();
        //设置信息
        deliveryInfo.setId(UUID.randomUUID().toString().replace("-",""));
        deliveryInfo.setUserId(userId);
        deliveryInfo.setCreateTime(System.currentTimeMillis());
        deliveryInfo.setUpdateTime(System.currentTimeMillis());
        deliveryInfo.setCreateUserId(userId);
        deliveryInfo.setUpdateUserId(userId);
        //如果没有添加过地址
        if (getDefaultDeliInfo(userId) == null){
            deliveryInfo.setStatus(SystemConstants.defaultInfo);
        }else {
            deliveryInfo.setStatus(SystemConstants.STATUS_ACTIVE);
        }
        //存入数据库
        int i = deliveryInfoMapper.insert(deliveryInfo);
        if (i == 0){
            throw new SystemException(ErrorCode.INSERT_ERROR);
        }
        LOG.info("添加收货地址成功");
    }

    /**
     * 逻辑删除收货地址
     * @param deliId
     */
    @Transactional(rollbackFor = Exception.class)
    public void deleteDeliInfo(String deliId){
        Optional<DeliveryInfo> deliveryInfoOptional = deliveryInfoMapper.selectByPrimaryKey(deliId);
        DeliveryInfo check = deliveryInfoOptional.orElse(null);
        if (check == null){
            throw new SystemException(ErrorCode.DELETE_ERROR);
        }
        check.setStatus(SystemConstants.STATUS_NEGATIVE);
        check.setUpdateUserId(UserContextHolder.getUserId());
        check.setUpdateTime(System.currentTimeMillis());
        int i = deliveryInfoMapper.updateByPrimaryKey(check);
        if (i == 0){
            throw new SystemException(ErrorCode.DELETE_ERROR);
        }
        LOG.info("删除收货地址成功");
    }

    /**
     * 获取所有填写过的收货地址
     * @return
     */
    public List<DeliveryInfo> getAllDeliInfo(){
        List<DeliveryInfo> deliveryInfos = deliveryInfoMapper.selectMany(select(
                DeliveryInfoDynamicSqlSupport.id,
                DeliveryInfoDynamicSqlSupport.deliveryName,
                DeliveryInfoDynamicSqlSupport.deliveryPhone,
                DeliveryInfoDynamicSqlSupport.deliveryAddress
        )
                .from(DeliveryInfoDynamicSqlSupport.deliveryInfo)
                .where(DeliveryInfoDynamicSqlSupport.userId, isEqualTo(UserContextHolder.getUserId()))
                .and(DeliveryInfoDynamicSqlSupport.status, isEqualTo(SystemConstants.STATUS_ACTIVE))
                .or(DeliveryInfoDynamicSqlSupport.status, isEqualTo(SystemConstants.defaultInfo))
                .build().render(RenderingStrategies.MYBATIS3));
        LOG.info("获取所有地址成功");
        return deliveryInfos;
    }

    /**
     * 更新收货地址(包括设置默认)
     * @param newInfo
     */
    public void updateDeliInfo(DeliveryInfo newInfo){
        newInfo.setUpdateTime(System.currentTimeMillis());
        newInfo.setUpdateUserId(UserContextHolder.getUserId());
        if (newInfo.getStatus().equals(SystemConstants.defaultInfo)){
            DeliveryInfo defaultDeliInfo = getDefaultDeliInfo(UserContextHolder.getUserId());
            //如果已经存在默认地址
            if (defaultDeliInfo != null){
                defaultDeliInfo.setStatus(SystemConstants.STATUS_ACTIVE);
                int i = deliveryInfoMapper.updateByPrimaryKey(defaultDeliInfo);
                if (i == 0){
                    throw new SystemException(ErrorCode.UPDATE_ERROR);
                }
            }
        }
        int i = deliveryInfoMapper.updateByPrimaryKey(newInfo);
        if (i == 0){
            throw new SystemException(ErrorCode.UPDATE_ERROR);
        }
        if (newInfo.getStatus().equals(SystemConstants.defaultInfo)){
            LOG.info("更新默认收货地址成功");
        }else {
            LOG.info("更新收货地址成功");
        }
    }
}
