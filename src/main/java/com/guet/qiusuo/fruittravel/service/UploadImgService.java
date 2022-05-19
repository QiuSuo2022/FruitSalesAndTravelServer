package com.guet.qiusuo.fruittravel.service;

import com.guet.qiusuo.fruittravel.common.SystemConstants;
import com.guet.qiusuo.fruittravel.config.ErrorCode;
import com.guet.qiusuo.fruittravel.config.SystemException;
import com.guet.qiusuo.fruittravel.config.UserContextHolder;
import com.guet.qiusuo.fruittravel.dao.ImageFileDynamicSqlSupport;
import com.guet.qiusuo.fruittravel.dao.ImageFileMapper;
import com.guet.qiusuo.fruittravel.model.ImageFile;
import org.mybatis.dynamic.sql.render.RenderingStrategies;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static java.lang.invoke.MethodHandles.lookup;
import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;
import static org.mybatis.dynamic.sql.SqlBuilder.select;
import static org.slf4j.LoggerFactory.getLogger;


@Service
public class UploadImgService {

    private static final String REMOTE_IP = "120.76.200.109";
    private static final String LOCAL_IP = "10.33.69.165";

    private static final String REMOTE_BASEPATH = "/root/home/images/";
    private static final String LOCAL_BASEPATH = "D://Nginx/root/images/";

    private static final Logger LOG = getLogger(lookup().lookupClass());
    private static String port = "80";

    private ImageFileMapper imageFileMapper;
    @Autowired
    public void setImageFileMapper(ImageFileMapper imageFileMapper) {
        this.imageFileMapper = imageFileMapper;
    }

    public List<ImageFile> uploadImages(MultipartFile[] files){
        ArrayList<ImageFile> list = new ArrayList<>();
        int i = 0;
        //多个图片上传,返回的img对象存入list
        for (MultipartFile f:files) {
            ImageFile file = uploadImgUtil(f);
                list.add(file);
            i++;
        }
        return list;
    }

    private ImageFile uploadImgUtil(MultipartFile file){
        String NGINX_URL = "http://" + REMOTE_IP + ":" + port + "/";
        String originalFilename = file.getOriginalFilename() ;
        String uuFileName = UUID.randomUUID().toString();
        String filePath = REMOTE_BASEPATH;
        String fileName = uuFileName + originalFilename ;
        String url = NGINX_URL+"images/"+fileName;
        //存储图片信息
        long now = System.currentTimeMillis();
        UserContextHolder.validAdmin();
        ImageFile img = new ImageFile();
        img.setId(uuFileName.replace("-", ""));
        img.setImageName(fileName);
        img.setImageSize((short)file.getSize());
        img.setImageUrl(url);
        img.setCreateTime(now);
        img.setUpdateTime(now);
        img.setCreateUserId(UserContextHolder.getUserId());
        img.setUpdateUserId(UserContextHolder.getUserId());
        int i = imageFileMapper.insert(img);
        if (i == 0){
            throw new SystemException(ErrorCode.INSERT_ERROR);
        }
        try {
            InputStream input = null;
            input = file.getInputStream();
            File dest = new File(new File(filePath).getAbsolutePath()+ "/" + fileName);
            //检查是否存在父目录
            if (!dest.getParentFile().exists()) {
                dest.getParentFile().mkdirs();
            }
            try {
                // 保存文件
                file.transferTo(dest);
                img.setStatus(SystemConstants.STATUS_ACTIVE);
            } catch (Exception e) {
                e.printStackTrace();
                LOG.error("文件保存异常",e);
                img.setStatus(SystemConstants.STATUS_NEGATIVE);
                throw new SystemException(ErrorCode.UPLOAD_IMAGE_ERROR);
            }
             } catch (IOException e) {
                LOG.error("文件上传异常",e);
                img.setStatus(SystemConstants.STATUS_NEGATIVE);
             }
        imageFileMapper.updateByPrimaryKey(img);
            return img;
    }

    /**
     * 根据产品id获取图片
     * @param productId
     * @param imgType
     * @return
     */
    public List<ImageFile> getImages(String productId,short imgType){
        List<ImageFile>  imgList= imageFileMapper.selectMany(select(
                ImageFileDynamicSqlSupport.id,
                ImageFileDynamicSqlSupport.imageUrl,
                ImageFileDynamicSqlSupport.productId
        )
                .from(ImageFileDynamicSqlSupport.imageFile)
                .where(ImageFileDynamicSqlSupport.productId, isEqualTo(productId))
                .and(ImageFileDynamicSqlSupport.type, isEqualTo(imgType))
                .and(ImageFileDynamicSqlSupport.status,isEqualTo(SystemConstants.STATUS_ACTIVE))
                .build().render(RenderingStrategies.MYBATIS3));

        if (imgList.isEmpty()){
            return null;
        }
        return imgList;
    }

    /**
     * 根据产品id获取url
     * @param productId
     * @return
     */
    public List<String> getUrlByProdId(String productId){
        ArrayList<String> list = new ArrayList<>();
        List<ImageFile>  imgList= imageFileMapper.selectMany(select(
                ImageFileDynamicSqlSupport.id,
                ImageFileDynamicSqlSupport.imageUrl
        )
                .from(ImageFileDynamicSqlSupport.imageFile)
                .where(ImageFileDynamicSqlSupport.productId, isEqualTo(productId))
                .and(ImageFileDynamicSqlSupport.status,isEqualTo(SystemConstants.STATUS_ACTIVE))
                .build().render(RenderingStrategies.MYBATIS3));
        if (imgList.isEmpty()){
            return null;
        }
        for (ImageFile img:imgList) {
            list.add(img.getImageUrl());
        }
        return list;
    }
    /**
     * 根据用户id与产品id获取评论图片
     * @param productId
     * @return
     */
    public List<ImageFile> getImagesById(String productId){
        List<ImageFile>  imgList= imageFileMapper.selectMany(select(
                ImageFileDynamicSqlSupport.id,
                ImageFileDynamicSqlSupport.imageUrl
        )
                .from(ImageFileDynamicSqlSupport.imageFile)
                .where(ImageFileDynamicSqlSupport.productId, isEqualTo(productId))
                .and(ImageFileDynamicSqlSupport.type, isEqualTo(SystemConstants.IMG_EVAL))
                .and(ImageFileDynamicSqlSupport.createUserId,isEqualTo(UserContextHolder.getUserId()))
                .and(ImageFileDynamicSqlSupport.status,isEqualTo(SystemConstants.STATUS_ACTIVE))
                .build().render(RenderingStrategies.MYBATIS3));

        if (imgList.isEmpty()){
           return null;
        }
        return imgList;
    }
}