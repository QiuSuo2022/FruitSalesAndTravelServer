package com.guet.qiusuo.fruittravel.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorMsg {
    private long location;

    private String msg;

    private Integer level;
}
