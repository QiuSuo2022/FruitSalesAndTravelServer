package com.guet.qiusuo.fruittravel.controller;


import com.guet.qiusuo.fruittravel.common.SystemConstants;
import com.guet.qiusuo.fruittravel.config.ErrorCode;
import com.guet.qiusuo.fruittravel.config.SystemException;
import com.guet.qiusuo.fruittravel.config.UserContextHolder;
import com.guet.qiusuo.fruittravel.dao.ImageFileMapper;
import com.guet.qiusuo.fruittravel.model.ChildFruit;
import com.guet.qiusuo.fruittravel.model.ImageFile;
import com.guet.qiusuo.fruittravel.service.ChildFruitService;
import com.guet.qiusuo.fruittravel.service.UploadImgService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

import static java.lang.invoke.MethodHandles.lookup;
import static org.slf4j.LoggerFactory.getLogger;


@Api(tags = "上传图片与绑定产品id")
@RestController
@RequestMapping("/img")
public class ImageController {

    private static final Logger LOG = getLogger(lookup().lookupClass());

    private UploadImgService uploadImgService;

    ImageFileMapper imageFileMapper;

    private ChildFruitService childFruitService;

    @Autowired
    public void setChildFruitService(ChildFruitService childFruitService) {
        this.childFruitService = childFruitService;
    }

    @Autowired
    public void setImageFileMapper(ImageFileMapper imageFileMapper) {
        this.imageFileMapper = imageFileMapper;
    }

    @Autowired
    public void setUploadImgService(UploadImgService uploadImgService) {
        this.uploadImgService = uploadImgService;
    }

    @ApiOperation(value = "上传多个图片")
    @PostMapping(value = "/upload")
    public List<ImageFile> uploadImg(@RequestParam MultipartFile[] files)  {
        UserContextHolder.validAdmin();
        if (files.length == 0) {
            LOG.info("图片文件异常!");
            throw new SystemException(ErrorCode.PARAM_NULL_ERROR);
        }
        return uploadImgService.uploadImages(files);
    }

    @ApiOperation(value = "根据图片url绑定产品",notes = "图片类别: 0:水果/景区图, 1:评价图, 2:轮播图 3:水果子项图")
    @PostMapping(value = "/bind")
    public void bindImg(@RequestParam String productId,@RequestParam List<String> imgIds,@RequestParam short imgType){
        UserContextHolder.validAdmin();
        if (productId == null || imgIds == null || imgIds.isEmpty()){
            throw new SystemException(ErrorCode.PARAM_NULL_ERROR);
        }
        for (String imgId:imgIds) {
        Optional<ImageFile> imageFileOptional = imageFileMapper.selectByPrimaryKey(imgId);
        ImageFile imageFile = imageFileOptional.orElse(null);
        if (imageFile == null){
            throw new SystemException(ErrorCode.NO_FOUND_IMAGE_ERROR);
        }
        imageFile.setProductId(productId);
        imageFile.setType(imgType);
        if (imgType == SystemConstants.IMG_CHILD){
            ChildFruit childFruit = childFruitService.getChildFruit(productId);
            childFruit.setImageUrl(imageFile.getImageUrl());
            childFruitService.updateChildFruit(childFruit);
        }
        int i = imageFileMapper.updateByPrimaryKey(imageFile);
        if (i == 0){
            LOG.info("绑定图片失败");
            throw new SystemException(ErrorCode.UPDATE_ERROR);
        }
        LOG.info("绑定图片成功");
        }
    }


    @ApiOperation(value = "获取多个图片url",notes = "图片类别: 0:水果/景区图, 1:评价图, 2:轮播图 3:水果子项图")
    @GetMapping(value = "/getImg")
    public List<ImageFile> getImg(@RequestParam String productId, @RequestParam short imgType){
        return uploadImgService.getImages(productId,imgType);
    }

    @ApiOperation(value = "获取用户评论某项产品的图片url",notes = "图片类别: 0:水果/景区图, 1:评价图, 2:轮播图 3:水果子项图")
    @GetMapping(value = "/getImgByUserId")
    public List<ImageFile> getImg(@RequestParam String productId){
        return uploadImgService.getImagesById(productId);
    }


}
