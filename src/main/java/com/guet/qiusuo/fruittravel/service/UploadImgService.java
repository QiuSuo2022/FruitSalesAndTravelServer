package com.guet.qiusuo.fruittravel.service;

import com.guet.qiusuo.fruittravel.common.SystemConstants;
import com.guet.qiusuo.fruittravel.config.UserContextHolder;
import com.guet.qiusuo.fruittravel.model.ImageFile;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

import static java.lang.invoke.MethodHandles.lookup;
import static org.slf4j.LoggerFactory.getLogger;

/**
 * @author lu
 * @date 2022/03/13
 */
@Service
public class UploadImgService {

    private static final String LOCAL_IP = "120.76.200.109";

    private static final String LOCAL_BASEPATH = "/root/home/images";

    private static final Logger LOG = getLogger(lookup().lookupClass());

    String port = "80";

    public ImageFile uploadImg(MultipartFile file,Short imgType,String remark){
        String NGINX_URL = "http://" + LOCAL_IP + ":" + port;
        String fileName=file.getOriginalFilename();
        String dirType = imgType.toString();
        String uuFileName= UUID.randomUUID().toString();
        String filePath = LOCAL_BASEPATH +"/" + dirType + "/" + uuFileName+"/";
        String url = NGINX_URL+filePath+fileName;

        //存储图片信息
        long now = System.currentTimeMillis();
        UserContextHolder.validAdmin();
        ImageFile img = new ImageFile();
        img.setId(uuFileName);
        img.setImageName(fileName);
        img.setType(imgType);
        img.setImageSize((short)file.getSize());
        img.setImageUrl(url);
        img.setRemark(remark);
        img.setCreateTime(now);
        img.setUpdateTime(now);
        img.setCreateUserId(UserContextHolder.getUserId());
        img.setUpdateUserId(UserContextHolder.getUserId());
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
             return img;
             }
    }