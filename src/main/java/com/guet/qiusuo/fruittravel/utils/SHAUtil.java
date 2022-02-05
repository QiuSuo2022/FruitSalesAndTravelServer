package com.guet.qiusuo.fruittravel.utils;

import org.apache.commons.codec.binary.Hex;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;

/**
 * @author libuyan
 * @date 2022/2/5 20:04
 */
public class SHAUtil {
    //   SECRET KEY
    private final static String secret_key = "ndE2jdZNFixH9G6Aidsfyf7lYT3PxW";

    /**
     * 生成 HMACSHA256
     *
     * @param data 待处理数据
     * @param key  密钥
     *
     * @return 加密结果
     *
     * @throws Exception
     */
    public static String HMACSHA256(String data, String key) throws Exception {
        Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
        SecretKeySpec secretKey = new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), "HmacSHA256");
        sha256_HMAC.init(secretKey);
        byte[] hash = sha256_HMAC.doFinal(data.getBytes(StandardCharsets.UTF_8));
        String check = Hex.encodeHexString(hash);
        return check;
    }
}