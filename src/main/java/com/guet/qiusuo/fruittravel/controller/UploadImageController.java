package com.guet.qiusuo.fruittravel.controller;


import com.guet.qiusuo.fruittravel.model.ImageFile;
import com.guet.qiusuo.fruittravel.service.UploadImgService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @author lu
 * @date 2022/03/13
 */
@Api(tags = "上传图片")
@RestController
public class UploadImageController {

    private UploadImgService uploadImgService;

    @Autowired
    public void setUploadImgService(UploadImgService uploadImgService) {
        this.uploadImgService = uploadImgService;
    }

    @PostMapping(value = "/uploadImg")
    public ImageFile uploadImg(@RequestParam("file") MultipartFile file,
                               @RequestParam("imgType") Short imgType,
                               @RequestParam("imgRemark") String remark) throws IOException {
        if(file.isEmpty()){
            return null;
        }
        return uploadImgService.uploadImg(file,imgType,remark);
    }
}
