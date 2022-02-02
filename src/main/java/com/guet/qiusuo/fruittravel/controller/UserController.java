package com.guet.qiusuo.fruittravel.controller;

import com.guet.qiusuo.fruittravel.bean.vo.UserVO;
import com.guet.qiusuo.fruittravel.model.User;
import com.guet.qiusuo.fruittravel.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
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

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @ApiOperation(value = "用户登录")
    @ApiImplicitParam(name = "code", value = "登录验证码", required = true, paramType = "query", dataType = "String",
            example = "1")
    @PostMapping("/login")
    public UserVO login(@RequestParam("code") String code) {
        return userService.login(code);
    }

    @ApiOperation(value = "添加用户")
    @PostMapping
    public void addUser(@RequestBody User user) {
        userService.addUser(user);
    }

    @ApiOperation(value = "修改用户")
    @PutMapping
    public void updateUser(@RequestBody User user) {
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
