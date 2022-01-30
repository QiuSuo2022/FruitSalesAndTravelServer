package com.guet.qiusuo.fruittravel.service;

import com.guet.qiusuo.fruittravel.bean.vo.UserRoleVO;
import com.guet.qiusuo.fruittravel.dao.RoleDynamicSqlSupport;
import com.guet.qiusuo.fruittravel.dao.RoleMapper;
import com.guet.qiusuo.fruittravel.dao.UserRoleDynamicSqlSupport;
import com.guet.qiusuo.fruittravel.dao.UserRoleMapper;
import org.mybatis.dynamic.sql.render.RenderingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.mybatis.dynamic.sql.SqlBuilder.*;

/**
 * @author libuyan
 * @date 2022/1/30 3:10
 */
@Service
public class RoleService {
    private UserRoleMapper userRoleMapper;

    private RoleMapper roleMapper;

    @Autowired
    public void setUserRoleMapper(UserRoleMapper userRoleMapper) {
        this.userRoleMapper = userRoleMapper;
    }

    @Autowired
    public void setRoleMapper(RoleMapper roleMapper) {
        this.roleMapper = roleMapper;
    }


    public List<UserRoleVO> selectUserRoleByUserId(String userId) {
        return userRoleMapper.selectUserRoleVo(select(
                UserRoleDynamicSqlSupport.userId,
                UserRoleDynamicSqlSupport.roleId,
                RoleDynamicSqlSupport.name)
                .from(UserRoleDynamicSqlSupport.userRole)
                .leftJoin(RoleDynamicSqlSupport.role)
                .on(UserRoleDynamicSqlSupport.roleId, equalTo(RoleDynamicSqlSupport.id))
                .where(UserRoleDynamicSqlSupport.userId, isEqualTo(userId))
                .build()
                .render(RenderingStrategies.MYBATIS3));
    }
}
