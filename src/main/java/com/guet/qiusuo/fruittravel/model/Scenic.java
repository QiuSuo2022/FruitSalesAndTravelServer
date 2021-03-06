package com.guet.qiusuo.fruittravel.model;

import javax.annotation.Generated;

/**
 * Database Table Remarks:
 * 景点表
 * <p>
 * This class was generated by MyBatis Generator.
 * This class corresponds to the database table tbl_scenic
 */
public class Scenic {
    /**
     * Database Column Remarks:
     * uuid
     */
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.273+08:00",
            comments = "Source field: tbl_scenic.id")
    private String id;

    /**
     * Database Column Remarks:
     * 景点名称
     */
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.273+08:00",
            comments = "Source field: tbl_scenic.scenic_name")
    private String scenicName;

    /**
     * Database Column Remarks:
     * 地理位置
     */
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.273+08:00",
            comments = "Source field: tbl_scenic.location")
    private String location;

    /**
     * Database Column Remarks:
     * 开放时间
     */
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.274+08:00",
            comments = "Source field: tbl_scenic.opening_hours")
    private String openingHours;

    /**
     * Database Column Remarks:
     * 景区介绍
     */
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.274+08:00",
            comments = "Source field: tbl_scenic.description")
    private String description;

    /**
     * Database Column Remarks:
     * 景区类型, 1-文化古迹, 2-园林花园, 3-古镇古村, 4-城堡教堂
     */
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.274+08:00",
            comments = "Source field: tbl_scenic.type")
    private Short type;

    /**
     * Database Column Remarks:
     * 状态, 0: 无效 1: 有效
     */
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.274+08:00",
            comments = "Source field: tbl_scenic.status")
    private Short status;

    /**
     * Database Column Remarks:
     * 创建时间
     */
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.274+08:00",
            comments = "Source field: tbl_scenic.create_time")
    private Long createTime;

    /**
     * Database Column Remarks:
     * 最后更新时间
     */
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.274+08:00",
            comments = "Source field: tbl_scenic.update_time")
    private Long updateTime;

    /**
     * Database Column Remarks:
     * 增加此条数据的用户id
     */
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.274+08:00",
            comments = "Source field: tbl_scenic.create_user_id")
    private String createUserId;

    /**
     * Database Column Remarks:
     * 最后更新此条数据的用户id
     */
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.274+08:00",
            comments = "Source field: tbl_scenic.update_user_id")
    private String updateUserId;

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.273+08:00",
            comments = "Source field: tbl_scenic.id")
    public String getId() {
        return id;
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.273+08:00",
            comments = "Source field: tbl_scenic.id")
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.273+08:00",
            comments = "Source field: tbl_scenic.scenic_name")
    public String getScenicName() {
        return scenicName;
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.273+08:00",
            comments = "Source field: tbl_scenic.scenic_name")
    public void setScenicName(String scenicName) {
        this.scenicName = scenicName == null ? null : scenicName.trim();
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.273+08:00",
            comments = "Source field: tbl_scenic.location")
    public String getLocation() {
        return location;
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.274+08:00",
            comments = "Source field: tbl_scenic.location")
    public void setLocation(String location) {
        this.location = location == null ? null : location.trim();
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.274+08:00",
            comments = "Source field: tbl_scenic.opening_hours")
    public String getOpeningHours() {
        return openingHours;
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.274+08:00",
            comments = "Source field: tbl_scenic.opening_hours")
    public void setOpeningHours(String openingHours) {
        this.openingHours = openingHours == null ? null : openingHours.trim();
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.274+08:00",
            comments = "Source field: tbl_scenic.description")
    public String getDescription() {
        return description;
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.274+08:00",
            comments = "Source field: tbl_scenic.description")
    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.274+08:00",
            comments = "Source field: tbl_scenic.type")
    public Short getType() {
        return type;
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.274+08:00",
            comments = "Source field: tbl_scenic.type")
    public void setType(Short type) {
        this.type = type;
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.274+08:00",
            comments = "Source field: tbl_scenic.status")
    public Short getStatus() {
        return status;
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.274+08:00",
            comments = "Source field: tbl_scenic.status")
    public void setStatus(Short status) {
        this.status = status;
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.274+08:00",
            comments = "Source field: tbl_scenic.create_time")
    public Long getCreateTime() {
        return createTime;
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.274+08:00",
            comments = "Source field: tbl_scenic.create_time")
    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.274+08:00",
            comments = "Source field: tbl_scenic.update_time")
    public Long getUpdateTime() {
        return updateTime;
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.274+08:00",
            comments = "Source field: tbl_scenic.update_time")
    public void setUpdateTime(Long updateTime) {
        this.updateTime = updateTime;
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.274+08:00",
            comments = "Source field: tbl_scenic.create_user_id")
    public String getCreateUserId() {
        return createUserId;
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.274+08:00",
            comments = "Source field: tbl_scenic.create_user_id")
    public void setCreateUserId(String createUserId) {
        this.createUserId = createUserId == null ? null : createUserId.trim();
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.274+08:00",
            comments = "Source field: tbl_scenic.update_user_id")
    public String getUpdateUserId() {
        return updateUserId;
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.275+08:00", comments = "Source field: tbl_scenic.update_user_id")
    public void setUpdateUserId(String updateUserId) {
        this.updateUserId = updateUserId == null ? null : updateUserId.trim();
    }
}