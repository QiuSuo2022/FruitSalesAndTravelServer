package com.guet.qiusuo.fruittravel.service;

import com.guet.qiusuo.fruittravel.common.SystemConstants;
import com.guet.qiusuo.fruittravel.config.ErrorCode;
import com.guet.qiusuo.fruittravel.config.SystemException;
import com.guet.qiusuo.fruittravel.config.UserContextHolder;
import com.guet.qiusuo.fruittravel.dao.ImageFileMapper;
import com.guet.qiusuo.fruittravel.model.ImageFile;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

import static java.lang.invoke.MethodHandles.lookup;
import static org.slf4j.LoggerFactory.getLogger;


@Service
public class UploadImgService {

    private static final String REMOTE_IP = "120.76.200.109";
    private static final String LOCAL_IP = "10.33.47.170";

    private static final String REMOTE_BASEPATH = "/root/home/images/";
    private static final String LOCAL_BASEPATH = "D://Nginx/root/images/";

    private static final Logger LOG = getLogger(lookup().lookupClass());

    private ImageFileMapper imageFileMapper;
    @Autowired
    public void setImageFileMapper(ImageFileMapper imageFileMapper) {
        this.imageFileMapper = imageFileMapper;
    }



    String port = "80";

    public ImageFile uploadImg(MultipartFile file,Short imgType,String remark){


        String NGINX_URL = "http://" + LOCAL_IP + ":" + port + "/";
        String originalFilename = file.getOriginalFilename() ;
        String uuFileName = UUID.randomUUID().toString();
        String filePath = LOCAL_BASEPATH;
        String fileName = uuFileName + originalFilename ;
        String url = NGINX_URL+"images/"+fileName;
        //存储图片信息
        long now = System.currentTimeMillis();
        UserContextHolder.validAdmin();
        ImageFile img = new ImageFile();
        img.setId(uuFileName.replace("-", ""));
        img.setImageName(fileName);
        img.setType(imgType);
        img.setImageSize((short)file.getSize());
        img.setImageUrl(url);
        img.setRemark(remark);
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
                return img;
            } catch (Exception e) {
                e.printStackTrace();
                LOG.error("文件保存异常",e);
                img.setStatus(SystemConstants.STATUS_NEGATIVE);
                return img;
            }
             } catch (IOException e) {
                LOG.error("文件上传异常",e);
                img.setStatus(SystemConstants.STATUS_NEGATIVE);
             }
        imageFileMapper.updateByPrimaryKey(img);
        return img;
             }
    }