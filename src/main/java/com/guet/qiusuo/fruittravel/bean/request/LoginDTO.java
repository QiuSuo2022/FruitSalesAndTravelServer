package com.guet.qiusuo.fruittravel.bean.request;

import lombok.Data;

@Data
public class LoginDTO {
    private String username;

    private String password;

    private String verifyKey;

    private String verifyCode;
}
