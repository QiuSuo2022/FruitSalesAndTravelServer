package com.guet.qiusuo.fruittravel.utils;

import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

import static org.apache.commons.codec.digest.DigestUtils.md5Hex;

/**
 * @description: md5加密工具类
 */
public class MD5Util {
    private static final String SALT = "fruitx123321x";

    public static String getMd5String(String origin) {
        if (origin == null) {
            return null;
        }
        String res = null;
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            //md5.update(SALT.getBytes());
            origin = origin + SALT;
            md5.update(origin.getBytes());
            byte[] digest = md5.digest();
            res = DatatypeConverter.printHexBinary(digest).toUpperCase();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return res;
    }


    //加盐MD5加密
    public static String getSaltMD5(String password) {
        // 生成一个16位的随机数
        Random random = new Random();
        StringBuilder sBuilder = new StringBuilder(16);
        sBuilder.append(random.nextInt(99999999)).append(random.nextInt(99999999));
        int len = sBuilder.length();
        if (len < 16) {
            for (int i = 0; i < 16 - len; i++) {
                sBuilder.append("0");
            }
        }
        // 生成最终的加密盐
        String Salt = sBuilder.toString();
        password = md5Hex(password + Salt);
        char[] cs = new char[48];
        for (int i = 0; i < 48; i += 3) {
            cs[i] = password.charAt(i / 3 * 2);
            char c = Salt.charAt(i / 3);
            cs[i + 1] = c;
            cs[i + 2] = password.charAt(i / 3 * 2 + 1);
        }
        return String.valueOf(cs);
    }


    //验证加盐后是否和原文一致
    public static boolean getSaltverifyMD5(String password, String md5str) {
        char[] cs1 = new char[32];
        char[] cs2 = new char[16];
        for (int i = 0; i < 48; i += 3) {
            cs1[i / 3 * 2] = md5str.charAt(i);
            cs1[i / 3 * 2 + 1] = md5str.charAt(i + 2);
            cs2[i / 3] = md5str.charAt(i + 1);
        }
        String Salt = new String(cs2);
        return md5Hex(password + Salt).equals(String.valueOf(cs1));
    }


}
