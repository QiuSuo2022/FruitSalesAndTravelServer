package com.guet.qiusuo.fruittravel.controller;

import com.guet.qiusuo.fruittravel.config.UserContextHolder;
import com.guet.qiusuo.fruittravel.model.ImageFile;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static java.lang.invoke.MethodHandles.lookup;
import static org.slf4j.LoggerFactory.getLogger;

/**
 * @Description 图片绑定景区或水果id
 */
@Api(tags = "绑定图片")
@RestController
public class BindImgController {

    private static final Logger LOG = getLogger(lookup().lookupClass());

    @PostMapping
    @ApiOperation(value = "绑定图片",notes = "图片类别: 0--轮播图, 1--水果商品图, 2--景区图, 3--商品评价图")
    public void bindImg(@RequestParam("prodId") String prodId,@RequestBody ImageFile imageFile){
        if (!prodId.isEmpty()){
            long now = System.currentTimeMillis();
            UserContextHolder.validAdmin();
            imageFile.setProductId(prodId);
            imageFile.setUpdateTime(now);
            imageFile.setUpdateUserId(UserContextHolder.getUserId());
        }else {
            LOG.info("fk_id为空,绑定失败");
        }
    }
}
