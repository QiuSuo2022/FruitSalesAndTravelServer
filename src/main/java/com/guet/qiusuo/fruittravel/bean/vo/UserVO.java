package com.guet.qiusuo.fruittravel.bean.vo;

import com.guet.qiusuo.fruittravel.model.User;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author libuyan
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class UserVO extends User {
    private String roleName;
}
