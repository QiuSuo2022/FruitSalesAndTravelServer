package com.guet.qiusuo.fruittravel.config;

import com.wechat.pay.contrib.apache.httpclient.util.PemUtil;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.security.PrivateKey;

@Configuration
@PropertySource(value = "classpath:application-dev.properties")
@ConfigurationProperties(prefix="wxpay")
@Data
public class WxPayConfig {
    /**
     * 商户号
     */
    private String mchId;
    /**
     * 商户API证书序列号
     */
    private String mchSerialNo;

    /**
     * 商户私钥文件
     */
    private String privateKeyPath;

    /**
     * APIv3密钥
     */
    private String apiV3Key;

    /**
     * APPID
     */
    private String appId;

    /**
     * 微信服务器地址
     */
    private String domain;

    /**
     * 接收结果通知地址
     */
    private String notifyDomain;

    /**
     * 交易类型，小程序支付的固定值为JSAPI
     */
    public String tradeType = "JSAPI";

    public PrivateKey getPrivateKey(){
        try {
            return PemUtil.loadPrivateKey(new FileInputStream(privateKeyPath));
        } catch (FileNotFoundException e) {
            throw new RuntimeException("私匙文件不存在",e);
        }
    }

    public String getPrivateKeyString(){
        return getPrivateKey().toString();
    }
}
