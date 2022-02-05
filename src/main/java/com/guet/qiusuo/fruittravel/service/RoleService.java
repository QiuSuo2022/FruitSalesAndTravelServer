package com.guet.qiusuo.fruittravel.service;

import com.guet.qiusuo.fruittravel.bean.vo.UserRoleVO;
import com.guet.qiusuo.fruittravel.common.SystemConstants;
import com.guet.qiusuo.fruittravel.config.ErrorCode;
import com.guet.qiusuo.fruittravel.config.SystemException;
import com.guet.qiusuo.fruittravel.dao.RoleDynamicSqlSupport;
import com.guet.qiusuo.fruittravel.dao.RoleMapper;
import com.guet.qiusuo.fruittravel.dao.UserRoleDynamicSqlSupport;
import com.guet.qiusuo.fruittravel.dao.UserRoleMapper;
import com.guet.qiusuo.fruittravel.model.UserRole;
import org.mybatis.dynamic.sql.render.RenderingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

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


    List<UserRoleVO> selectUserRoleByUserId(String userId) {
        return userRoleMapper.selectUserRoleVo(select(
                UserRoleDynamicSqlSupport.userId,
                UserRoleDynamicSqlSupport.roleId,
                RoleDynamicSqlSupport.name.as("roleName"))
                .from(UserRoleDynamicSqlSupport.userRole)
                .leftJoin(RoleDynamicSqlSupport.role)
                .on(UserRoleDynamicSqlSupport.roleId, equalTo(RoleDynamicSqlSupport.id))
                .where(UserRoleDynamicSqlSupport.userId, isEqualTo(userId))
                .and(UserRoleDynamicSqlSupport.status, isEqualTo(SystemConstants.STATUS_NORMAL))
                .and(RoleDynamicSqlSupport.status, isEqualTo(SystemConstants.STATUS_NORMAL))
                .build()
                .render(RenderingStrategies.MYBATIS3));
    }

    void insertUserRole(String userId, String roleId) {
        UserRole userRole = new UserRole();
        userRole.setId(UUID.randomUUID().toString());
        userRole.setUserId(userId);
        userRole.setRoleId(roleId);
        userRole.setStatus(SystemConstants.STATUS_NORMAL);
        long now = System.currentTimeMillis();
        userRole.setCreateTime(now);
        userRole.setUpdateTime(now);
        userRole.setCreateUserId(userId);
        userRole.setUpdateUserId(userId);
        int i = userRoleMapper.insert(userRole);
        if (i == 0) {
            throw new SystemException(ErrorCode.INSERT_ERROR);
        }
    }
}
