package com.guet.qiusuo.fruittravel.controller;

import com.guet.qiusuo.fruittravel.config.ErrorCode;
import com.guet.qiusuo.fruittravel.config.Response;
import com.guet.qiusuo.fruittravel.config.SystemException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @Author: libuyan
 * @Date: 2020/12/8 19:05
 */
@Api(tags = "执行自动部署的脚本")
@RestController
@RequestMapping("/auto_deployment")
public class AutoDeployment {

    @ApiOperation(value = "执行自动更新脚本")
    @PostMapping("/update")
    public Response autoUpdate(HttpServletRequest request) throws IOException {
        String xGiteeToken = request.getHeader("X-Gitee-Token");
        if (!"fruitSale123.".equals(xGiteeToken)) {
            throw new SystemException(ErrorCode.X_GITEE_TOKEN_MISMATCH);
        }
        Runtime run = Runtime.getRuntime();
        Process exec = run.exec(new String[]{"/bin/sh", "-c", "cd /root/project/fruitSaleAndTravel && ./deploy.sh"});
        return new Response<>(200, "自动部署成功", "");
    }
}