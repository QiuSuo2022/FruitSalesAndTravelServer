package com.guet.qiusuo.fruittravel.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

/**
 * @author libuyan
 * @date 2022/1/25 17:05
 */
@Api(tags = "Hello")
@RestController
@RequestMapping("/hello")
public class HelloController {

    @ApiOperation(value = "添加用户")
    @GetMapping
    public String hello() {
        return "hello";
    }
}
