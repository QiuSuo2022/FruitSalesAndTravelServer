package com.guet.qiusuo.fruittravel.model;

import javax.annotation.Generated;

/**
 * Database Table Remarks:
 * 用户表
 * <p>
 * This class was generated by MyBatis Generator.
 * This class corresponds to the database table tbl_user
 */
public class User {
    /**
     * Database Column Remarks:
     * uuid
     */
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.152+08:00",
            comments = "Source field: tbl_user.id")
    private String id;

    /**
     * Database Column Remarks:
     * 用户名
     */
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.16+08:00", comments
            = "Source field: tbl_user.user_name")
    private String userName;

    /**
     * Database Column Remarks:
     * 真实姓名
     */
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.16+08:00", comments
            = "Source field: tbl_user.real_name")
    private String realName;

    /**
     * Database Column Remarks:
     * 性别
     */
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.16+08:00", comments
            = "Source field: tbl_user.gender")
    private String gender;

    /**
     * Database Column Remarks:
     * 密码
     */
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.161+08:00",
            comments = "Source field: tbl_user.password")
    private String password;

    /**
     * Database Column Remarks:
     * 手机号
     */
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.162+08:00",
            comments = "Source field: tbl_user.phone")
    private String phone;

    /**
     * Database Column Remarks:
     * email
     */
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.162+08:00",
            comments = "Source field: tbl_user.email")
    private String email;

    /**
     * Database Column Remarks:
     * 身份证号
     */
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.162+08:00",
            comments = "Source field: tbl_user.id_card")
    private String idCard;

    /**
     * Database Column Remarks:
     * 头像地址
     */
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.163+08:00",
            comments = "Source field: tbl_user.avatar_url")
    private String avatarUrl;

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.163+08:00",
            comments = "Source field: tbl_user.token")
    private String token;

    /**
     * Database Column Remarks:
     * 微信用户openId
     */
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.163+08:00",
            comments = "Source field: tbl_user.openid")
    private String openid;

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.164+08:00",
            comments = "Source field: tbl_user.role_id")
    private String roleId;

    /**
     * Database Column Remarks:
     * 状态，0：不正常；1：正常
     */
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.164+08:00",
            comments = "Source field: tbl_user.status")
    private Short status;

    /**
     * Database Column Remarks:
     * 创建时间
     */
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.165+08:00",
            comments = "Source field: tbl_user.create_time")
    private Long createTime;

    /**
     * Database Column Remarks:
     * 最后更新时间
     */
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.165+08:00",
            comments = "Source field: tbl_user.update_time")
    private Long updateTime;

    /**
     * Database Column Remarks:
     * 增加此条数据的用户id
     */
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.165+08:00",
            comments = "Source field: tbl_user.create_user_id")
    private String createUserId;

    /**
     * Database Column Remarks:
     * 最后更新此条数据的用户id
     */
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.165+08:00",
            comments = "Source field: tbl_user.update_user_id")
    private String updateUserId;

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.159+08:00",
            comments = "Source field: tbl_user.id")
    public String getId() {
        return id;
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.159+08:00",
            comments = "Source field: tbl_user.id")
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.16+08:00", comments
            = "Source field: tbl_user.user_name")
    public String getUserName() {
        return userName;
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.16+08:00", comments
            = "Source field: tbl_user.user_name")
    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.16+08:00", comments
            = "Source field: tbl_user.real_name")
    public String getRealName() {
        return realName;
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.16+08:00", comments
            = "Source field: tbl_user.real_name")
    public void setRealName(String realName) {
        this.realName = realName == null ? null : realName.trim();
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.161+08:00",
            comments = "Source field: tbl_user.gender")
    public String getGender() {
        return gender;
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.161+08:00",
            comments = "Source field: tbl_user.gender")
    public void setGender(String gender) {
        this.gender = gender == null ? null : gender.trim();
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.161+08:00",
            comments = "Source field: tbl_user.password")
    public String getPassword() {
        return password;
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.161+08:00",
            comments = "Source field: tbl_user.password")
    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.162+08:00",
            comments = "Source field: tbl_user.phone")
    public String getPhone() {
        return phone;
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.162+08:00",
            comments = "Source field: tbl_user.phone")
    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.162+08:00",
            comments = "Source field: tbl_user.email")
    public String getEmail() {
        return email;
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.162+08:00",
            comments = "Source field: tbl_user.email")
    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.162+08:00",
            comments = "Source field: tbl_user.id_card")
    public String getIdCard() {
        return idCard;
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.162+08:00",
            comments = "Source field: tbl_user.id_card")
    public void setIdCard(String idCard) {
        this.idCard = idCard == null ? null : idCard.trim();
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.163+08:00",
            comments = "Source field: tbl_user.avatar_url")
    public String getAvatarUrl() {
        return avatarUrl;
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.163+08:00",
            comments = "Source field: tbl_user.avatar_url")
    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl == null ? null : avatarUrl.trim();
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.163+08:00",
            comments = "Source field: tbl_user.token")
    public String getToken() {
        return token;
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.163+08:00",
            comments = "Source field: tbl_user.token")
    public void setToken(String token) {
        this.token = token == null ? null : token.trim();
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.163+08:00",
            comments = "Source field: tbl_user.openid")
    public String getOpenid() {
        return openid;
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.164+08:00",
            comments = "Source field: tbl_user.openid")
    public void setOpenid(String openid) {
        this.openid = openid == null ? null : openid.trim();
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.164+08:00",
            comments = "Source field: tbl_user.role_id")
    public String getRoleId() {
        return roleId;
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.164+08:00",
            comments = "Source field: tbl_user.role_id")
    public void setRoleId(String roleId) {
        this.roleId = roleId == null ? null : roleId.trim();
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.164+08:00",
            comments = "Source field: tbl_user.status")
    public Short getStatus() {
        return status;
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.164+08:00",
            comments = "Source field: tbl_user.status")
    public void setStatus(Short status) {
        this.status = status;
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.165+08:00",
            comments = "Source field: tbl_user.create_time")
    public Long getCreateTime() {
        return createTime;
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.165+08:00",
            comments = "Source field: tbl_user.create_time")
    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.165+08:00",
            comments = "Source field: tbl_user.update_time")
    public Long getUpdateTime() {
        return updateTime;
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.165+08:00",
            comments = "Source field: tbl_user.update_time")
    public void setUpdateTime(Long updateTime) {
        this.updateTime = updateTime;
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.165+08:00",
            comments = "Source field: tbl_user.create_user_id")
    public String getCreateUserId() {
        return createUserId;
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.165+08:00",
            comments = "Source field: tbl_user.create_user_id")
    public void setCreateUserId(String createUserId) {
        this.createUserId = createUserId == null ? null : createUserId.trim();
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.166+08:00",
            comments = "Source field: tbl_user.update_user_id")
    public String getUpdateUserId() {
        return updateUserId;
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.166+08:00", comments = "Source field: tbl_user.update_user_id")
    public void setUpdateUserId(String updateUserId) {
        this.updateUserId = updateUserId == null ? null : updateUserId.trim();
    }
}