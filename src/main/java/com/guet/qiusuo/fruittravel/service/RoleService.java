package com.guet.qiusuo.fruittravel.service;

import com.guet.qiusuo.fruittravel.bean.vo.UserRoleVO;
import com.guet.qiusuo.fruittravel.common.SysRole;
import com.guet.qiusuo.fruittravel.common.SystemConstants;
import com.guet.qiusuo.fruittravel.config.ErrorCode;
import com.guet.qiusuo.fruittravel.config.SystemException;
import com.guet.qiusuo.fruittravel.config.UserContextHolder;
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

    public void insertUserRole(String userId, String roleId) {
        UserContextHolder.validAdmin();
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

    /**
     * 超级管理员修改用户信息
     * @param userRole
     */
    public void updateUserRole(UserRole userRole) {
        UserContextHolder.validSuperAdmin();
        if(userRole.getRoleId().equals(SysRole.SUPERADMIN)) {
            throw new SystemException(ErrorCode.NO_ACCESS);
        }
        userRole.setUpdateUserId(UserContextHolder.getUserId());
        userRole.setUpdateTime(System.currentTimeMillis());
        int i = userRoleMapper.updateByPrimaryKeySelective(userRole);
        if (i == 0){
            throw new SystemException(ErrorCode.UPDATE_ERROR);
        }
    }

    /**
     * 超级管理员分配权限
     * @param userRole
     */
    public void grantPermission(UserRole userRole) {
        UserContextHolder.validSuperAdmin();
        userRole.setUpdateUserId(UserContextHolder.getUserId());
        userRole.setUpdateTime(System.currentTimeMillis());
        if(userRole.getRoleId().equals(SysRole.USER)) {
            userRole.setRoleId(SysRole.ADMIN);
        }
        if(userRole.getRoleId().equals(SysRole.ADMIN)) {
            userRole.setRoleId(SysRole.SUPERADMIN);
        }
    }

    /**
     * 超级管理员收回权限
     * @param userRole
     */
    public void revokePermission(UserRole userRole) {
        UserContextHolder.validSuperAdmin();
        userRole.setUpdateUserId(UserContextHolder.getUserId());
        userRole.setUpdateTime(System.currentTimeMillis());
        if(userRole.getRoleId().equals(SysRole.ADMIN)) {
            userRole.setRoleId(SysRole.USER);
        }
    }

    /**
     * 超级管理员封禁管理员和用户
     * @param userRole
     */
    public void confineUserRoles(UserRole userRole) {
        UserContextHolder.validSuperAdmin();
        if(userRole.getRoleId().equals(SysRole.SUPERADMIN)) {
            throw new SystemException(ErrorCode.NO_ACCESS);
        }
        userRole.setUpdateUserId(UserContextHolder.getUserId());
        userRole.setUpdateTime(System.currentTimeMillis());
        userRole.setStatus(SystemConstants.STATUS_NEGATIVE);
    }

    /**
     * 管理员封禁普通用户
     * @param userRole
     */
    public void confineUserRole(UserRole userRole) {
        UserContextHolder.validAdmin();
        if(userRole.getRoleId().equals(SysRole.SUPERADMIN) || userRole.getRoleId().equals(SysRole.ADMIN)) {
            throw new SystemException(ErrorCode.NO_ACCESS);
        }
        userRole.setUpdateUserId(UserContextHolder.getUserId());
        userRole.setUpdateTime(System.currentTimeMillis());
        userRole.setStatus(SystemConstants.STATUS_NEGATIVE);
    }
}
