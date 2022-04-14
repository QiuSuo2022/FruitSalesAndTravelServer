package com.guet.qiusuo.fruittravel.service;

import com.guet.qiusuo.fruittravel.common.SystemConstants;
import com.guet.qiusuo.fruittravel.config.ErrorCode;
import com.guet.qiusuo.fruittravel.config.SystemException;
import com.guet.qiusuo.fruittravel.config.UserContextHolder;
import com.guet.qiusuo.fruittravel.dao.ChildFruitDynamicSqlSupport;
import com.guet.qiusuo.fruittravel.dao.ChildFruitMapper;
import com.guet.qiusuo.fruittravel.model.ChildFruit;
import org.mybatis.dynamic.sql.render.RenderingStrategies;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import static org.slf4j.LoggerFactory.getLogger;
import static java.lang.invoke.MethodHandles.lookup;
import static org.mybatis.dynamic.sql.SqlBuilder.*;

/**
 * @author lu
 */
@Service
public class ChildFruitService {

    private ChildFruitMapper childFruitMapper;

    private static final Logger LOG = getLogger(lookup().lookupClass());

    @Autowired
    public void setChildFruitMapper(ChildFruitMapper childFruitMapper){
        this.childFruitMapper = childFruitMapper;
    }

    /**
     * 添加childFruit
     * @param childFruit
     */
    public boolean addChildFruit(ChildFruit childFruit){
        UserContextHolder.validAdmin();
        long now = System.currentTimeMillis();
        childFruit.setId(UUID.randomUUID().toString().replace("-", ""));
        childFruit.setStatus(SystemConstants.STATUS_ACTIVE);
        childFruit.setCreateTime(now);
        childFruit.setUpdateTime(now);
        childFruit.setCreateUserId(UserContextHolder.getUserId());
        childFruit.setUpdateUserId(UserContextHolder.getUserId());
        int i = childFruitMapper.insertSelective(childFruit);
        if (i == 0){
            throw new SystemException(ErrorCode.INSERT_ERROR);
        }
        LOG.info("创建水果子项成功,Id:{}",childFruit.getFruitId());
        return true;
    }

    /**
     * 删除所有childFruit
     * @param fruitId
     */
    public void deleteAll(String fruitId){
        UserContextHolder.validAdmin();
        List<ChildFruit> childFruitList = getChildFruitListByFruitId(fruitId);
        if (childFruitList.isEmpty()){
            LOG.info("该水果没有子项");
        }
        for (ChildFruit childFruit:childFruitList
             ) {
            childFruit.setStatus(SystemConstants.STATUS_NEGATIVE);
            childFruit.setUpdateTime(System.currentTimeMillis());
            childFruit.setUpdateUserId(UserContextHolder.getUserId());
            int i = childFruitMapper.updateByPrimaryKeySelective(childFruit);
            if (i == 0){
                throw new SystemException(ErrorCode.DELETE_ERROR);
            }
        }
        LOG.info("删除所有水果子项成功");
    }

    /**
     * 删除单个水果子项
     * @param childFruitId
     * @return
     */
    public void deleteOne(String childFruitId){
        UserContextHolder.validAdmin();
        Optional<ChildFruit> optionalChildFruit = childFruitMapper.selectByFruitId(childFruitId);
        ChildFruit childFruit = optionalChildFruit.orElse(null);
        if (childFruit == null){
            LOG.info("无该水果子项");
            throw new SystemException(ErrorCode.DELETE_ERROR);
        }
        childFruit.setStatus(SystemConstants.STATUS_NEGATIVE);
        childFruit.setUpdateTime(System.currentTimeMillis());
        childFruit.setUpdateUserId(UserContextHolder.getUserId());
        int i = childFruitMapper.updateByPrimaryKeySelective(childFruit);
        if (i == 0){
            throw new SystemException(ErrorCode.DELETE_ERROR);
        }
        LOG.info("删除水果子项{}成功",childFruitId);
    }
    /**
     * 修改childFruit
     * @param childFruit
     */
    public boolean updateChildFruit(ChildFruit childFruit){
        UserContextHolder.validAdmin();
        childFruit.setUpdateTime(System.currentTimeMillis());
        childFruit.setUpdateUserId(UserContextHolder.getUserId());
        int i = childFruitMapper.updateByPrimaryKeySelective(childFruit);
        if (i == 0){
            throw new SystemException(ErrorCode.UPDATE_ERROR);
        }
        LOG.info("修改水果子项成功,Id:{}",childFruit.getFruitId());
        return true;
    }

    /**
     * 查找childFruit(status = SystemContext.STATUS_ACTIVE)
     * @param childFruitId
     * @return
     */
    public ChildFruit getChildFruit(String childFruitId){
        //如果不存在childFruit则返回null
        return childFruitMapper.selectByPrimaryKey(childFruitId).orElse(null);
    }

    public List<ChildFruit> getChildFruitListByFruitId(String FruitId){
        return childFruitMapper.selectMany(select(
                ChildFruitDynamicSqlSupport.id,
                ChildFruitDynamicSqlSupport.fruitName,
                ChildFruitDynamicSqlSupport.status,
                ChildFruitDynamicSqlSupport.updateTime)
                .from(ChildFruitDynamicSqlSupport.childFruit)
                .where(ChildFruitDynamicSqlSupport.status,isEqualTo(SystemConstants.STATUS_ACTIVE))
                .and(ChildFruitDynamicSqlSupport.fruitId,isEqualTo(FruitId))
                .build().render(RenderingStrategies.MYBATIS3));
    }


    public List<ChildFruit> getAllChildFruits(){
        List<ChildFruit> list  = childFruitMapper.selectMany(select(
                ChildFruitDynamicSqlSupport.id,
                ChildFruitDynamicSqlSupport.fruitName,
                ChildFruitDynamicSqlSupport.status,
                ChildFruitDynamicSqlSupport.updateTime)
                .from(ChildFruitDynamicSqlSupport.childFruit)
                .where(ChildFruitDynamicSqlSupport.status,isEqualTo(SystemConstants.STATUS_ACTIVE))
                .build().render(RenderingStrategies.MYBATIS3));
        return list;
    }
}
