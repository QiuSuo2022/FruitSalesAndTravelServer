package com.guet.qiusuo.fruittravel.service;

import com.guet.qiusuo.fruittravel.common.SystemConstants;
import com.guet.qiusuo.fruittravel.config.ErrorCode;
import com.guet.qiusuo.fruittravel.config.SystemException;
import com.guet.qiusuo.fruittravel.config.UserContextHolder;
import com.guet.qiusuo.fruittravel.dao.ChildFruitMapper;
import com.guet.qiusuo.fruittravel.model.ChildFruit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;
@Service
public class ChildFruitService {
    private ChildFruitMapper childFruitMapper;

    @Autowired
    public void setChildFruitMapper(ChildFruitMapper childFruitMapper){
        this.childFruitMapper = childFruitMapper;
    }

    /**
     * 添加childFruit
     * @param childFruit
     */
    public void addChildFruit(ChildFruit childFruit){
        String userRoleId = UserContextHolder.getUser().getRoleId();
        if (userRoleId.equals("1")){
            throw new SystemException(ErrorCode.NO_PERMISSION);
        }
        long now = System.currentTimeMillis();
        childFruit.setId(UUID.randomUUID().toString());
        childFruit.setStatus(SystemConstants.STATUS_ACTIVE);
        childFruit.setCreateTime(now);
        childFruit.setUpdateTime(now);
        childFruitMapper.insertSelective(childFruit);
        System.out.println("创建水果子项成功:" + childFruit.toString());
    }

    /**
     * 删除childFruit
     * @param fruitId
     */
    public void deleteChildFruit(String fruitId){
        String userRoleId = UserContextHolder.getUser().getRoleId();
        if (userRoleId.equals("1")){
            throw new SystemException(ErrorCode.NO_PERMISSION);
        }
        Optional<ChildFruit> optionalChildFruit = childFruitMapper.selectByPrimaryKey(fruitId);
        ChildFruit childFruit = optionalChildFruit.orElseThrow(() -> new SystemException(ErrorCode.NO_FOUND_CHILD_FRUIT));
        childFruit.setStatus(SystemConstants.STATUS_NEGATIVE);
        childFruit.setUpdateTime(System.currentTimeMillis());
        childFruitMapper.deleteByPrimaryKey(fruitId);
    }

    /**
     * 修改childFruit
     * @param childFruit
     */
    public void updateChildFruit(ChildFruit childFruit){
        String userRoleId = UserContextHolder.getUser().getRoleId();
        if (userRoleId.equals("1")){
            throw new SystemException(ErrorCode.NO_PERMISSION);
        }
        childFruit.setUpdateTime(System.currentTimeMillis());
        childFruitMapper.updateByPrimaryKeySelective(childFruit);
    }

    /**
     * 查找childFruit
     * @param fruitId
     * @return
     */
    public ChildFruit searchChildFruit(String fruitId){
        //如果不存在childFruit则返回null
        return childFruitMapper.selectByPrimaryKey(fruitId).orElse(null);
    }
}
