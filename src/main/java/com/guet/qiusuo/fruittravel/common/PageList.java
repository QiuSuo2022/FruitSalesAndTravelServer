package com.guet.qiusuo.fruittravel.common;

import com.github.pagehelper.PageInfo;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class PageList<T> implements Serializable {
    private List<T> list;

    private PageInfo pageInfo;
}