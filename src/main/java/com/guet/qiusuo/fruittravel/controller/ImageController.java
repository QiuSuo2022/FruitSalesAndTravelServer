package com.guet.qiusuo.fruittravel.controller;


import com.guet.qiusuo.fruittravel.config.ErrorCode;
import com.guet.qiusuo.fruittravel.config.SystemException;
import com.guet.qiusuo.fruittravel.config.UserContextHolder;
import com.guet.qiusuo.fruittravel.dao.ImageFileDynamicSqlSupport;
import com.guet.qiusuo.fruittravel.dao.ImageFileMapper;
import com.guet.qiusuo.fruittravel.model.ImageFile;
import com.guet.qiusuo.fruittravel.service.UploadImgService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.mybatis.dynamic.sql.render.RenderingStrategies;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

import static java.lang.invoke.MethodHandles.lookup;
import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;
import static org.mybatis.dynamic.sql.SqlBuilder.select;
import static org.slf4j.LoggerFactory.getLogger;

/**
 * @author lu
 * @date 2022/03/13
 */
@Api(tags = "上传图片与绑定产品id")
@RestController("/img")
public class ImageController {

    private static final Logger LOG = getLogger(lookup().lookupClass());

    private UploadImgService uploadImgService;

    ImageFileMapper imageFileMapper;

    @Autowired
    public void setImageFileMapper(ImageFileMapper imageFileMapper) {
        this.imageFileMapper = imageFileMapper;
    }

    @Autowired
    public void setUploadImgService(UploadImgService uploadImgService) {
        this.uploadImgService = uploadImgService;
    }

    @ApiOperation(value = "绑定图片",notes = "图片类别: 0--轮播图, 1--水果商品图, 2--景区图, 3--商品评价图")
    @PostMapping(value = "/upload")
    public ImageFile uploadImg(@RequestParam MultipartFile file,
                               @RequestParam(required = false) Short imgType,
                               @RequestParam(required = false) String remark)  {
        UserContextHolder.validAdmin();
        if (file.isEmpty()) {
            LOG.info("图片文件异常!");
            throw new SystemException(ErrorCode.PARAM_NULL_ERROR);
        }
        return uploadImgService.uploadImg(file, imgType, remark);
    }

    @ApiOperation(value = "根据图片url绑定产品",notes = "图片类别: 0--轮播图, 1--水果商品图, 2--景区图, 3--商品评价图")
    @PostMapping(value = "/bind")
    public void bindImg(@RequestParam String productId,@RequestParam String imgId,@RequestParam short type){
        UserContextHolder.validAdmin();
        if (productId == null || imgId == null){
            throw new SystemException(ErrorCode.PARAM_NULL_ERROR);
        }
        Optional<ImageFile> imageFileOptional = imageFileMapper.selectByPrimaryKey(imgId);
        ImageFile imageFile = imageFileOptional.orElse(null);
        if (imageFile == null){
            throw new SystemException(ErrorCode.NO_FOUND_IMAGE_ERROR);
        }
        imageFile.setProductId(productId);
        imageFile.setType(type);
        int i = imageFileMapper.updateByPrimaryKey(imageFile);
        if (i == 0){
            LOG.info("绑定图片失败");
            throw new SystemException(ErrorCode.UPDATE_ERROR);
        }
        LOG.info("绑定图片成功");
    }


    @ApiOperation(value = "获取图片url",notes = "图片类型(imgType) 0:轮播图 1:水果 2:景区 3:评论图片")
    @GetMapping(value = "/getImg")
    public List<ImageFile> getImg(@RequestParam String productId, @RequestParam short imgType){
         List<ImageFile>  imgList= imageFileMapper.selectMany(select(
                ImageFileDynamicSqlSupport.id,
                ImageFileDynamicSqlSupport.imageUrl
        )
                .from(ImageFileDynamicSqlSupport.imageFile)
                .where(ImageFileDynamicSqlSupport.productId, isEqualTo(productId))
                .and(ImageFileDynamicSqlSupport.type, isEqualTo(imgType))
                .build().render(RenderingStrategies.MYBATIS3));

        if (imgList.isEmpty()){
            throw new SystemException(ErrorCode.NO_FOUND_IMAGE_ERROR);
        }
        return imgList;
    }

}
