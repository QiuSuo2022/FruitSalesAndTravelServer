package com.guet.qiusuo.fruittravel.controller;

import com.guet.qiusuo.fruittravel.config.ErrorCode;
import com.guet.qiusuo.fruittravel.config.Response;
import com.guet.qiusuo.fruittravel.config.SystemException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.codec.binary.Hex;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * @Author: libuyan
 * @Date: 2022/2/5 23:39
 */
@Api(tags = "执行自动部署的脚本")
@RestController
@RequestMapping("/auto_deployment")
public class AutoDeployment {

    @ApiOperation(value = "执行自动更新脚本")
    @PostMapping("/update")
    public Response autoUpdate(HttpServletRequest request) {
        String headSignature256 = request.getHeader("X-Hub-Signature-256");
        String requestBody = getBodyStringByReader(request);
        String genSignature256 = null;
        try {
            genSignature256 = "sha256=" + HMAC_SHA256(requestBody, "fruitSale123.");
        } catch (Exception e) {
            e.printStackTrace();
        }

        /*
         * 判断github webhooks发送的request中携带的 'X-Hub-Signature-256' header 的值
         * 与 将request body使用HMAC_SHA256 16进制形式加密后产生的密文 是否一致
         */
        if (!headSignature256.equals(genSignature256)) {
            throw new SystemException(ErrorCode.X_Hub_Signature_256);
        }

        Runtime run = Runtime.getRuntime();
        try {
            run.exec(new String[]{"/bin/sh", "-c", "cd /root/project/fruitSaleAndTravel && ./deploy.sh"});
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new Response<>(200, "自动部署成功", "");
    }

    /**
     * 读取request中的body部分
     *
     * @param request HttpServletRequest
     *
     * @return body toString
     */
    private static String getBodyStringByReader(HttpServletRequest request) {
        BufferedReader bufferedReader = null;
        StringBuilder sb = new StringBuilder();
        try {
            bufferedReader = request.getReader();
            String line;
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

    /**
     * 生成 HMAC_SHA256 16进制形式
     *
     * @param data 待处理数据
     * @param key  密钥
     *
     * @return 加密结果
     *
     * @throws Exception
     */
    private static String HMAC_SHA256(String data, String key) throws Exception {
        Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
        SecretKeySpec secretKey = new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), "HmacSHA256");
        sha256_HMAC.init(secretKey);
        byte[] hash = sha256_HMAC.doFinal(data.getBytes(StandardCharsets.UTF_8));
        return Hex.encodeHexString(hash);
    }
}