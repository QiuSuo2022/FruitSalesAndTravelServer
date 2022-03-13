package com.guet.qiusuo.fruittravel.model;

import javax.annotation.Generated;

/**
 * Database Table Remarks:
 * 图库表
 * <p>
 * This class was generated by MyBatis Generator.
 * This class corresponds to the database table tbl_image_file
 */
public class ImageFile {
    /**
     * Database Column Remarks:
     * uuid
     */
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.239+08:00",
            comments = "Source field: tbl_image_file.id")
    private String id;
    /**
     * Database Column Remarks:
     * 绑定景区/水果
     */
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.239+08:00",
            comments = "Source field: tbl_image_file.id")
    private String fk_id;

    /**
     * Database Column Remarks:
     * 图片名
     */
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.239+08:00",
            comments = "Source field: tbl_image_file.image_name")
    private String imageName;

    /**
     * Database Column Remarks:
     * 图片类别: 0--轮播图, 1--水果商品图, 2--商品评价图
     */
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.239+08:00",
            comments = "Source field: tbl_image_file.type")
    private Short type;

    /**
     * Database Column Remarks:
     * 文件大小
     */
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.24+08:00", comments
            = "Source field: tbl_image_file.image_size")
    private Short imageSize;

    /**
     * Database Column Remarks:
     * 文件存储路径
     */
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.24+08:00", comments
            = "Source field: tbl_image_file.image_url")
    private String imageUrl;

    /**
     * Database Column Remarks:
     * 备注
     */
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.24+08:00", comments
            = "Source field: tbl_image_file.remark")
    private String remark;

    /**
     * Database Column Remarks:
     * 状态, 0: 无效 1: 有效
     */
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.24+08:00", comments
            = "Source field: tbl_image_file.status")
    private Short status;

    /**
     * Database Column Remarks:
     * 创建时间
     */
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.241+08:00",
            comments = "Source field: tbl_image_file.create_time")
    private Long createTime;

    /**
     * Database Column Remarks:
     * 最后更新时间
     */
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.241+08:00",
            comments = "Source field: tbl_image_file.update_time")
    private Long updateTime;

    /**
     * Database Column Remarks:
     * 增加此条数据的用户id
     */
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.241+08:00",
            comments = "Source field: tbl_image_file.create_user_id")
    private String createUserId;

    /**
     * Database Column Remarks:
     * 最后更新此条数据的用户id
     */
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.241+08:00",
            comments = "Source field: tbl_image_file.update_user_id")
    private String updateUserId;

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.239+08:00",
            comments = "Source field: tbl_image_file.id")
    public String getId() {
        return id;
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.239+08:00",
            comments = "Source field: tbl_image_file.id")
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.239+08:00",
            comments = "Source field: tbl_image_file.image_name")
    public String getImageName() {
        return imageName;
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.239+08:00",
            comments = "Source field: tbl_image_file.image_name")
    public void setImageName(String imageName) {
        this.imageName = imageName == null ? null : imageName.trim();
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.239+08:00",
            comments = "Source field: tbl_image_file.type")
    public Short getType() {
        return type;
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.24+08:00", comments
            = "Source field: tbl_image_file.type")
    public void setType(Short type) {
        this.type = type;
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.24+08:00", comments
            = "Source field: tbl_image_file.image_size")
    public Short getImageSize() {
        return imageSize;
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.24+08:00", comments
            = "Source field: tbl_image_file.image_size")
    public void setImageSize(Short imageSize) {
        this.imageSize = imageSize;
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.24+08:00", comments
            = "Source field: tbl_image_file.image_url")
    public String getImageUrl() {
        return imageUrl;
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.24+08:00", comments
            = "Source field: tbl_image_file.image_url")
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl == null ? null : imageUrl.trim();
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.24+08:00", comments
            = "Source field: tbl_image_file.remark")
    public String getRemark() {
        return remark;
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.24+08:00", comments
            = "Source field: tbl_image_file.remark")
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.24+08:00", comments
            = "Source field: tbl_image_file.status")
    public Short getStatus() {
        return status;
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.241+08:00",
            comments = "Source field: tbl_image_file.status")
    public void setStatus(Short status) {
        this.status = status;
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.241+08:00",
            comments = "Source field: tbl_image_file.create_time")
    public Long getCreateTime() {
        return createTime;
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.241+08:00",
            comments = "Source field: tbl_image_file.create_time")
    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.241+08:00",
            comments = "Source field: tbl_image_file.update_time")
    public Long getUpdateTime() {
        return updateTime;
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.241+08:00",
            comments = "Source field: tbl_image_file.update_time")
    public void setUpdateTime(Long updateTime) {
        this.updateTime = updateTime;
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.241+08:00",
            comments = "Source field: tbl_image_file.create_user_id")
    public String getCreateUserId() {
        return createUserId;
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.241+08:00",
            comments = "Source field: tbl_image_file.create_user_id")
    public void setCreateUserId(String createUserId) {
        this.createUserId = createUserId == null ? null : createUserId.trim();
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.241+08:00",
            comments = "Source field: tbl_image_file.update_user_id")
    public String getUpdateUserId() {
        return updateUserId;
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.241+08:00", comments = "Source field: tbl_image_file.update_user_id")
    public void setUpdateUserId(String updateUserId) {
        this.updateUserId = updateUserId == null ? null : updateUserId.trim();
    }
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-03-13T17:08:00.241+08:00", comments = "Source field: tbl_image_file.fk_id")
    public void setFk_id(String fk_id) {
        this.fk_id = fk_id == null ? null : fk_id.trim();
    }
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-03-13T17:08:00.241+08:00",
            comments = "Source field: tbl_image_file.fk_id")
    public String getFk_id() {
        return fk_id;
    }

}