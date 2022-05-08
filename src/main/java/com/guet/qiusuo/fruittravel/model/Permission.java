package com.guet.qiusuo.fruittravel.model;

import javax.annotation.Generated;

/**
 * Database Table Remarks:
 * 权限表
 * <p>
 * This class was generated by MyBatis Generator.
 * This class corresponds to the database table tbl_permission
 */
public class Permission {
    /**
     * Database Column Remarks:
     * uuid
     */
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.247+08:00",
            comments = "Source field: tbl_permission.id")
    private String id;

    /**
     * Database Column Remarks:
     * 权限名称
     */
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.247+08:00",
            comments = "Source field: tbl_permission.name")
    private String name;

    /**
     * Database Column Remarks:
     * API URL
     */
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.247+08:00",
            comments = "Source field: tbl_permission.api_path")
    private String apiPath;

    /**
     * Database Column Remarks:
     * 请求方式，GET,PUT,POST,DELETE
     */
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.247+08:00",
            comments = "Source field: tbl_permission.method")
    private String method;

    /**
     * Database Column Remarks:
     * 路由 URL
     */
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.248+08:00",
            comments = "Source field: tbl_permission.route_path")
    private String routePath;

    /**
     * Database Column Remarks:
     * 权限类型；0：路由，1：API
     */
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.248+08:00",
            comments = "Source field: tbl_permission.type")
    private Short type;

    /**
     * Database Column Remarks:
     * 状态,0:禁用 1:启用
     */
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.248+08:00",
            comments = "Source field: tbl_permission.status")
    private Short status;

    /**
     * Database Column Remarks:
     * 创建时间
     */
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.248+08:00",
            comments = "Source field: tbl_permission.create_time")
    private Long createTime;

    /**
     * Database Column Remarks:
     * 更新时间
     */
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.248+08:00",
            comments = "Source field: tbl_permission.update_time")
    private Long updateTime;

    /**
     * Database Column Remarks:
     * 记录创建人ID
     */
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.248+08:00",
            comments = "Source field: tbl_permission.create_user_id")
    private String createUserId;

    /**
     * Database Column Remarks:
     * 记录修改人ID
     */
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.249+08:00",
            comments = "Source field: tbl_permission.update_user_id")
    private String updateUserId;

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.247+08:00",
            comments = "Source field: tbl_permission.id")
    public String getId() {
        return id;
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.247+08:00",
            comments = "Source field: tbl_permission.id")
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.247+08:00",
            comments = "Source field: tbl_permission.name")
    public String getName() {
        return name;
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.247+08:00",
            comments = "Source field: tbl_permission.name")
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.247+08:00",
            comments = "Source field: tbl_permission.api_path")
    public String getApiPath() {
        return apiPath;
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.247+08:00",
            comments = "Source field: tbl_permission.api_path")
    public void setApiPath(String apiPath) {
        this.apiPath = apiPath == null ? null : apiPath.trim();
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.247+08:00",
            comments = "Source field: tbl_permission.method")
    public String getMethod() {
        return method;
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.248+08:00",
            comments = "Source field: tbl_permission.method")
    public void setMethod(String method) {
        this.method = method == null ? null : method.trim();
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.248+08:00",
            comments = "Source field: tbl_permission.route_path")
    public String getRoutePath() {
        return routePath;
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.248+08:00",
            comments = "Source field: tbl_permission.route_path")
    public void setRoutePath(String routePath) {
        this.routePath = routePath == null ? null : routePath.trim();
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.248+08:00",
            comments = "Source field: tbl_permission.type")
    public Short getType() {
        return type;
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.248+08:00",
            comments = "Source field: tbl_permission.type")
    public void setType(Short type) {
        this.type = type;
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.248+08:00",
            comments = "Source field: tbl_permission.status")
    public Short getStatus() {
        return status;
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.248+08:00",
            comments = "Source field: tbl_permission.status")
    public void setStatus(Short status) {
        this.status = status;
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.248+08:00",
            comments = "Source field: tbl_permission.create_time")
    public Long getCreateTime() {
        return createTime;
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.248+08:00",
            comments = "Source field: tbl_permission.create_time")
    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.248+08:00",
            comments = "Source field: tbl_permission.update_time")
    public Long getUpdateTime() {
        return updateTime;
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.248+08:00",
            comments = "Source field: tbl_permission.update_time")
    public void setUpdateTime(Long updateTime) {
        this.updateTime = updateTime;
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.248+08:00",
            comments = "Source field: tbl_permission.create_user_id")
    public String getCreateUserId() {
        return createUserId;
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.249+08:00",
            comments = "Source field: tbl_permission.create_user_id")
    public void setCreateUserId(String createUserId) {
        this.createUserId = createUserId == null ? null : createUserId.trim();
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.249+08:00",
            comments = "Source field: tbl_permission.update_user_id")
    public String getUpdateUserId() {
        return updateUserId;
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.249+08:00", comments = "Source field: tbl_permission.update_user_id")
    public void setUpdateUserId(String updateUserId) {
        this.updateUserId = updateUserId == null ? null : updateUserId.trim();
    }
}