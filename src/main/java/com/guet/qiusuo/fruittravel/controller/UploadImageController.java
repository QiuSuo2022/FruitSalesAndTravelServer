package com.guet.qiusuo.fruittravel.controller;


import com.guet.qiusuo.fruittravel.config.ErrorCode;
import com.guet.qiusuo.fruittravel.config.SystemException;
import com.guet.qiusuo.fruittravel.config.UserContextHolder;
import com.guet.qiusuo.fruittravel.model.ImageFile;
import com.guet.qiusuo.fruittravel.service.UploadImgService;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import static java.lang.invoke.MethodHandles.lookup;
import static org.slf4j.LoggerFactory.getLogger;

/**
 * @author lu
 * @date 2022/03/13
 */
@Api(tags = "上传图片与绑定产品id")
@RestController
public class UploadImageController {

    private static final Logger LOG = getLogger(lookup().lookupClass());

    private UploadImgService uploadImgService;

    @Autowired
    public void setUploadImgService(UploadImgService uploadImgService) {
        this.uploadImgService = uploadImgService;
    }

    @PostMapping(value = "/uploadImg")
    public ImageFile uploadImg(@RequestParam MultipartFile file,
                               @RequestParam Short imgType,
                               @RequestParam String remark)  {
        UserContextHolder.validAdmin();
        if (file.isEmpty()) {
            LOG.info("图片文件异常!");
            throw new SystemException(ErrorCode.PARAM_NULL_ERROR);
        }
        return uploadImgService.uploadImg(file, imgType, remark);
    }

    @PostMapping(value = "/bindImg")
    public boolean bindImg(@RequestParam String bindId,@RequestParam ImageFile img){
        UserContextHolder.validAdmin();
        if (bindId.isEmpty() || img == null){
            throw new SystemException(ErrorCode.PARAM_NULL_ERROR);
        }
        img.setProductId(bindId);
        img.setUpdateTime(System.currentTimeMillis());
        img.setUpdateUserId(UserContextHolder.getUserId());
        return true;
    }
}
