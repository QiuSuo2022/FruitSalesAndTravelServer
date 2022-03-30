package com.guet.qiusuo.fruittravel.controller;

import com.guet.qiusuo.fruittravel.bean.request.LoginDTO;
import com.guet.qiusuo.fruittravel.bean.vo.UserVO;
import com.guet.qiusuo.fruittravel.common.SysRole;
import com.guet.qiusuo.fruittravel.config.UserContextHolder;
import com.guet.qiusuo.fruittravel.model.User;
import com.guet.qiusuo.fruittravel.model.UserRole;
import com.guet.qiusuo.fruittravel.service.RoleService;
import com.guet.qiusuo.fruittravel.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author libuyan
 * @date 2022/1/29 18:33
 */
@Api(tags = "用户")
@RestController
@RequestMapping("/user")
public class UserController {
    private UserService userService;
    private RoleService roleService;

    @Autowired
    public void setRoleService(RoleService roleService) {this.roleService = roleService; }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @ApiOperation(value = "用户登录")
    @PostMapping("/login")
    public UserVO login(@RequestBody LoginDTO loginDTO) {
        return userService.login(loginDTO);
    }

    @ApiOperation(value = "根据token获取用户信息")
    @GetMapping("/info")
    public UserVO getUserInfoByToken(@RequestParam String token) {
        return userService.getUserInfoByToken(token);
    }

    @ApiOperation(value = "添加用户")
    @PostMapping
    public void addUser(@RequestBody User user) {
        userService.addUser(user);
    }

    /*@ApiOperation(value = "添加超级管理员")
    @PostMapping("/superAdmin")
    public void setSuperAdmin(@RequestBody User user) {
        userService.addUser(user);
        user.setRoleId(SysRole.SUPERADMIN);
    }

    @ApiOperation(value = "分配管理员权限")
    @PutMapping("/grantAdmin")
    public void setAdmin(@RequestBody UserRole userRole) { roleService.grantPermission(userRole); }

    @ApiOperation(value = "收回管理员权限")
    @PutMapping("/revokeAdmin")
    public void revokeAdmin(@RequestBody UserRole userRole) { roleService.revokePermission(userRole); }*/

    @ApiOperation(value = "修改用户")
    @PutMapping
    public void updateUser(@RequestBody User user) {
        user.setId(UserContextHolder.getUserId());
        userService.updateUser(user);
    }

    @DeleteMapping
    @ApiOperation(value = "删除用户")
    public void deleteUser(@RequestParam String id) {
        userService.deleteUser(id);
    }

    @ApiOperation(value = "修改用户角色")
    @PutMapping("/role")
    public void updateUserRole(@RequestParam String userId, @RequestParam String roleId) {
        userService.updateUserRole(userId, roleId);
    }

}
