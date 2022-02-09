package com.guet.qiusuo.fruittravel.service;

import com.guet.qiusuo.fruittravel.common.SystemConstants;
import com.guet.qiusuo.fruittravel.config.ErrorCode;
import com.guet.qiusuo.fruittravel.config.SystemException;
import com.guet.qiusuo.fruittravel.config.UserContextHolder;
import com.guet.qiusuo.fruittravel.dao.ScenicMapper;
import com.guet.qiusuo.fruittravel.model.Scenic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ScenicService {
    private ScenicMapper scenicMapper;

    @Autowired
    public void setScenicMapper(ScenicMapper scenicMapper){this.scenicMapper = scenicMapper;}

    /**
     * 添加Scenic
     * @param scenic
     */
    public void addScenic(Scenic scenic){
        String userRoleId = UserContextHolder.getUser().getRoleId();
        if (userRoleId.equals("1")){
            throw new SystemException(ErrorCode.NO_PERMISSION);
        }
        long now = System.currentTimeMillis();
        scenic.setCreateTime(now);
        scenic.setUpdateTime(now);
        int i = scenicMapper.insertSelective(scenic);
        if(i == 0){
            throw new SystemException(ErrorCode.INSERT_ERROR);
        }
        System.out.println("创建新景点成功:" + scenic.toString());
    }

    /**
     * 删除Scenic
     * @param scenicId
     */
    public void deleteScenic(String scenicId){
        String userRoleId = UserContextHolder.getUser().getRoleId();
        if (userRoleId.equals("1")){
            throw new SystemException(ErrorCode.NO_PERMISSION);
        }
        Optional<Scenic> optionalScenic = scenicMapper.selectByPrimaryKey(scenicId);
        Scenic scenic = optionalScenic.orElseThrow(() -> new SystemException(ErrorCode.NO_FOUND_CHILD_FRUIT));
        scenic.setStatus(SystemConstants.STATUS_NEGATIVE);
        scenic.setUpdateTime(System.currentTimeMillis());
        int i = scenicMapper.deleteByPrimaryKey(scenicId);
        if(i == 0){
            throw new SystemException(ErrorCode.INSERT_ERROR);
        }
    }
    /**
     * 修改Scenic
     * @param scenic
     */
    public void updateScenic(Scenic scenic){
        String userRoleId = UserContextHolder.getUser().getRoleId();
        if (userRoleId.equals("1")){
            throw new SystemException(ErrorCode.NO_PERMISSION);
        }
        scenic.setUpdateTime(System.currentTimeMillis());
        int i = scenicMapper.updateByPrimaryKeySelective(scenic);
        if (i == 0) {
            throw new SystemException(ErrorCode.UPDATE_ERROR);
        }
    }
    /**
     * 查找Scenic
     * @param scenicId
     * @return
     */
    public Scenic searchScenic(String scenicId){
        return scenicMapper.selectByPrimaryKey(scenicId).orElse(null);
    }
}
