package com.guet.qiusuo.fruittravel.bean.request;

import lombok.Data;

@Data
public class LoginDTO {
    private String code;

    private String avatarUrl;

    private String nickName;

    private String gender;
}
