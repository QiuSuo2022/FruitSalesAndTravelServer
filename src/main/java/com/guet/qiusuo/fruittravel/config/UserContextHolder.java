package com.guet.qiusuo.fruittravel.config;

import com.guet.qiusuo.fruittravel.bean.vo.UserVO;
import com.guet.qiusuo.fruittravel.common.SysRole;
import com.guet.qiusuo.fruittravel.model.User;

public class UserContextHolder {
    private static ThreadLocal<User> userThreadLocal = new ThreadLocal<>();

    private static ThreadLocal<UserVO> userInfoVOThreadLocal = new ThreadLocal<>();

    public static void setUser(User user) {
        userThreadLocal.set(user);
    }

    static void setUserVO(UserVO user) {
        userInfoVOThreadLocal.set(user);
    }

    public static User getUser() {
        User user = userInfoVOThreadLocal.get();
        if (user == null) {
            throw new SystemException(ErrorCode.USER_NOT_FOUND);
        }
        return user;
    }

    public static String getUserId() {
        if (getUser().getId() == null) {
            throw new SystemException(ErrorCode.USER_NOT_FOUND);
        }
        return getUser().getId();
    }

    public static String getUserName() {
        return getUser().getUserName();
    }

    public static void validAdmin() {
        if (!isAdmin() && !isSuperAdmin()) {
            throw new SystemException(ErrorCode.NO_ACCESS);
        }
    }

    public static void validSuperAdmin() {
        if(!isSuperAdmin()) {
            throw new SystemException(ErrorCode.NO_ACCESS);
        }
    }

    /**
     * 校验用户权限
     *
     * @param userId
     */
    public static void validUser(String userId) {
        boolean isRightUser = getUserId().equals(userId);
        if (!isRightUser) {
            throw new SystemException(ErrorCode.NO_ACCESS);
        }
    }

    private static boolean isAdmin() {
        return isRole(SysRole.ADMIN);
    }

    private static boolean isSuperAdmin() {
        return isRole(SysRole.SUPERADMIN);
    }

    private static boolean isRole(String role) {
        UserVO userVO = userInfoVOThreadLocal.get();
        String roleId = userVO.getRoleId();
        return roleId.equals(role);
    }
}
