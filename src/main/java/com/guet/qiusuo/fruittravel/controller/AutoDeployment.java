package com.guet.qiusuo.fruittravel.controller;

import com.guet.qiusuo.fruittravel.config.ErrorCode;
import com.guet.qiusuo.fruittravel.config.Response;
import com.guet.qiusuo.fruittravel.config.SystemException;
import com.guet.qiusuo.fruittravel.utils.SHAUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
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
        String headSignature256 = request.getHeader("X-Hub-Signature-256");
        System.out.println(headSignature256);
        String requestBody = getBodyStringByReader(request);
        try {
            String genSignature256 = SHAUtil.HMACSHA256(requestBody, "fruitSale123.");
            if (!genSignature256.equals(headSignature256)) {
                throw new SystemException(ErrorCode.X_GITEE_TOKEN_MISMATCH);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        Runtime run = Runtime.getRuntime();
//        Process exec = run.exec(new String[]{"/bin/sh", "-c", "cd /root/project/fruitSaleAndTravel && ./deploy.sh"});
        return new Response<>(200, "自动部署成功", "");
    }

    public static String getBodyStringByReader(HttpServletRequest request) {
        BufferedReader bufferedReader = null;
        StringBuilder sb = new StringBuilder();
        try {
            bufferedReader = request.getReader();
            String line = "";
            while ((line = bufferedReader.readLine()) != null) {
                sb.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return sb.toString();
    }
}