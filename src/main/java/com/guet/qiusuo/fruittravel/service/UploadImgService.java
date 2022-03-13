package com.guet.qiusuo.fruittravel.service;

import com.guet.qiusuo.fruittravel.common.SystemConstants;
import com.guet.qiusuo.fruittravel.config.UserContextHolder;
import com.guet.qiusuo.fruittravel.model.ImageFile;
import com.guet.qiusuo.fruittravel.utils.FtpFileUtil;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

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

    private static final String FTP_IP = "120.76.200.109";

    private static final String FTP_USERNAME = "ftpuser";

    private static final String FTP_PASSWORD = "public2022.";

    private static final String FTP_BASEPATH = "/home/ftpuser/images";

    private static final Logger LOG = getLogger(lookup().lookupClass());

    private static final int FTP_PORT = 80;

    String port = "80";

    public ImageFile uploadImg(MultipartFile file,Short imgType,String remark){
        String NGINX_URL = "http://" + FTP_IP + ":" + port;
        String fileName=file.getOriginalFilename();
        String dirType = imgType.toString();
        String uuFileName= UUID.randomUUID().toString();
        String filePath = FTP_BASEPATH +"/" + dirType + "/" + uuFileName;
        String url = NGINX_URL+filePath+fileName;
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
            /** 连接ftp进行上传
             * ftpIp:自定义的ftpip地址
             * port: 端口 (默认21)
             * ftpUser: ftp用户名
             * ftpPass：ftp密码
             * basePath：上传后的根目录
             * filePath：上传文件的文件路径
             * fileName：上传后的文件名
             * input：输入流数据*/
            boolean success = FtpFileUtil.uploadFile(FTP_IP, FTP_PORT, FTP_USERNAME, FTP_PASSWORD, FTP_BASEPATH, filePath, fileName, input);
            if(success){
                img.setStatus(SystemConstants.STATUS_ACTIVE);
                return img;
             }else {
                img.setStatus(SystemConstants.STATUS_NEGATIVE);
             }
             } catch (IOException e) {
                LOG.error("文件上传异常",e);
                img.setStatus(SystemConstants.STATUS_NEGATIVE);
             }

             return img;
             }
    }
