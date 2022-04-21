package com.guet.qiusuo.fruittravel.service;

import com.guet.qiusuo.fruittravel.bean.vo.UserRoleVO;
import com.guet.qiusuo.fruittravel.common.SysRole;
import com.guet.qiusuo.fruittravel.common.SystemConstants;
import com.guet.qiusuo.fruittravel.config.ErrorCode;
import com.guet.qiusuo.fruittravel.config.SystemException;
import com.guet.qiusuo.fruittravel.config.UserContextHolder;
import com.guet.qiusuo.fruittravel.dao.*;
import com.guet.qiusuo.fruittravel.model.User;
import com.guet.qiusuo.fruittravel.model.UserRole;
import org.mybatis.dynamic.sql.render.RenderingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
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

    private UserMapper userMapper;

    private UserService userService;

    @Autowired
    public void setUserRoleMapper(UserRoleMapper userRoleMapper) {
        this.userRoleMapper = userRoleMapper;
    }

    @Autowired
    public void setRoleMapper(RoleMapper roleMapper) {
        this.roleMapper = roleMapper;
    }

    @Autowired
    public void setUserMapper(UserMapper userMapper) { this.userMapper = userMapper; }


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
        Optional<User> optionalUser = userMapper.selectByPrimaryKey(userRole.getUserId());
        User user = optionalUser.orElse(null);
        if(user == null) {
            throw new SystemException(ErrorCode.USER_NOT_FOUND);
        }
        userService.updateUser(user);
    }

    /**
     * 超级管理员分配权限
     * @param userRole
     */
    public void grantPermission(UserRole userRole) {
        UserContextHolder.validSuperAdmin();
        if(userRole.getRoleId().equals(SysRole.USER)) {
            userRole.setUpdateUserId(UserContextHolder.getUserId());
            userRole.setUpdateTime(System.currentTimeMillis());
            userRole.setRoleId(SysRole.ADMIN);
            int i = userRoleMapper.updateByPrimaryKeySelective(userRole);
            if (i == 0){
                throw new SystemException(ErrorCode.GRANT_ERROR);
            }
            Optional<User> optionalUser = userMapper.selectByPrimaryKey(userRole.getUserId());
            User user = optionalUser.orElse(null);
            if(user == null) {
                throw new SystemException(ErrorCode.USER_NOT_FOUND);
            }
            user.setUpdateTime(System.currentTimeMillis());
            user.setUpdateUserId(UserContextHolder.getUserId());
            user.setRoleId(SysRole.ADMIN);
            int j = userMapper.updateByPrimaryKeySelective(user);
            if (j == 0){
                throw new SystemException(ErrorCode.GRANT_ERROR);
            }
            return;
        }
        if(userRole.getRoleId().equals(SysRole.ADMIN)) {
            userRole.setUpdateUserId(UserContextHolder.getUserId());
            userRole.setUpdateTime(System.currentTimeMillis());
            userRole.setRoleId(SysRole.SUPERADMIN);
            int i = userRoleMapper.updateByPrimaryKeySelective(userRole);
            if (i == 0){
                throw new SystemException(ErrorCode.GRANT_ERROR);
            }
            Optional<User> optionalUser = userMapper.selectByPrimaryKey(userRole.getUserId());
            User user = optionalUser.orElse(null);
            if(user == null) {
                throw new SystemException(ErrorCode.USER_NOT_FOUND);
            }
            user.setUpdateTime(System.currentTimeMillis());
            user.setUpdateUserId(UserContextHolder.getUserId());
            user.setRoleId(SysRole.SUPERADMIN);
            int j = userMapper.updateByPrimaryKeySelective(user);
            if (j == 0){
                throw new SystemException(ErrorCode.GRANT_ERROR);
            }
        }
    }

    /**
     * 超级管理员收回权限
     * @param userRole
     */
    public void revokePermission(UserRole userRole) {
        UserContextHolder.validSuperAdmin();
        if(userRole.getRoleId().equals(SysRole.ADMIN)) {
            userRole.setUpdateUserId(UserContextHolder.getUserId());
            userRole.setUpdateTime(System.currentTimeMillis());
            userRole.setRoleId(SysRole.USER);
            int i = userRoleMapper.updateByPrimaryKeySelective(userRole);
            if (i == 0){
                throw new SystemException(ErrorCode.REVOKE_ERROR);
            }
            Optional<User> optionalUser = userMapper.selectByPrimaryKey(userRole.getUserId());
            User user = optionalUser.orElse(null);
            if(user == null) {
                throw new SystemException(ErrorCode.USER_NOT_FOUND);
            }
            user.setRoleId(SysRole.USER);
            user.setUpdateTime(System.currentTimeMillis());
            user.setUpdateUserId(UserContextHolder.getUserId());
            int j = userMapper.updateByPrimaryKeySelective(user);
            if (j == 0){
                throw new SystemException(ErrorCode.REVOKE_ERROR);
            }
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
        int i = userRoleMapper.updateByPrimaryKeySelective(userRole);
        if (i == 0){
            throw new SystemException(ErrorCode.CONFINE_ERROR);
        }
        userService.deleteUser(userRole.getUserId());
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
        int i = userRoleMapper.updateByPrimaryKeySelective(userRole);
        if (i == 0){
            throw new SystemException(ErrorCode.CONFINE_ERROR);
        }
        userService.deleteUser(userRole.getUserId());
    }
}
