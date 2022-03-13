package com.guet.qiusuo.fruittravel.controller;

import com.guet.qiusuo.fruittravel.config.UserContextHolder;
import com.guet.qiusuo.fruittravel.model.ImageFile;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static java.lang.invoke.MethodHandles.lookup;
import static org.slf4j.LoggerFactory.getLogger;

/**
 * @author lu
 * @date 2022/03/13
 * @Description 图片绑定景区或水果id
 */
@Api(tags = "绑定图片")
@RestController
public class BindImgController {

    private static final Logger LOG = getLogger(lookup().lookupClass());

    @PostMapping
    public void bindImg(@RequestParam("fk_id") String fk_id,@RequestBody ImageFile imageFile){
        if (!fk_id.isEmpty()){
            long now = System.currentTimeMillis();
            UserContextHolder.validAdmin();
            imageFile.setFk_id(fk_id);
            imageFile.setUpdateTime(now);
            imageFile.setUpdateUserId(UserContextHolder.getUserId());
        }else {
            LOG.info("fk_id为空,绑定失败");
        }
    }
}
