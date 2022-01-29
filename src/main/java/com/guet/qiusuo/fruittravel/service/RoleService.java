package com.guet.qiusuo.fruittravel.service;

import com.guet.qiusuo.fruittravel.bean.vo.UserVO;
import com.guet.qiusuo.fruittravel.dao.RoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author libuyan
 * @date 2022/1/30 3:10
 */
@Service
public class RoleService {
    private RoleMapper roleMapper;

    @Autowired
    public void setRoleMapper(RoleMapper roleMapper) {
        this.roleMapper = roleMapper;
    }


    public List<UserVO> selectUserRoleByUserId(String id) {
        return null;
    }
}
