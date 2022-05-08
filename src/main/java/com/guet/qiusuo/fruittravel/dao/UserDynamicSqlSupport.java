package com.guet.qiusuo.fruittravel.dao;

import java.sql.JDBCType;
import javax.annotation.Generated;

import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class UserDynamicSqlSupport {
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.171+08:00",
            comments = "Source Table: tbl_user")
    public static final User user = new User();

    /**
     * Database Column Remarks:
     * uuid
     */
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.172+08:00",
            comments = "Source field: tbl_user.id")
    public static final SqlColumn<String> id = user.id;

    /**
     * Database Column Remarks:
     * 用户名
     */
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.173+08:00",
            comments = "Source field: tbl_user.user_name")
    public static final SqlColumn<String> userName = user.userName;

    /**
     * Database Column Remarks:
     * 真实姓名
     */
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.174+08:00",
            comments = "Source field: tbl_user.real_name")
    public static final SqlColumn<String> realName = user.realName;

    /**
     * Database Column Remarks:
     * 性别
     */
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.174+08:00",
            comments = "Source field: tbl_user.gender")
    public static final SqlColumn<String> gender = user.gender;

    /**
     * Database Column Remarks:
     * 密码
     */
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.174+08:00",
            comments = "Source field: tbl_user.password")
    public static final SqlColumn<String> password = user.password;

    /**
     * Database Column Remarks:
     * 手机号
     */
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.174+08:00",
            comments = "Source field: tbl_user.phone")
    public static final SqlColumn<String> phone = user.phone;

    /**
     * Database Column Remarks:
     * email
     */
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.174+08:00",
            comments = "Source field: tbl_user.email")
    public static final SqlColumn<String> email = user.email;

    /**
     * Database Column Remarks:
     * 身份证号
     */
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.175+08:00",
            comments = "Source field: tbl_user.id_card")
    public static final SqlColumn<String> idCard = user.idCard;

    /**
     * Database Column Remarks:
     * 头像地址
     */
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.175+08:00",
            comments = "Source field: tbl_user.avatar_url")
    public static final SqlColumn<String> avatarUrl = user.avatarUrl;

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.175+08:00",
            comments = "Source field: tbl_user.token")
    public static final SqlColumn<String> token = user.token;

    /**
     * Database Column Remarks:
     * 微信用户openId
     */
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.176+08:00",
            comments = "Source field: tbl_user.openid")
    public static final SqlColumn<String> openid = user.openid;

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.176+08:00",
            comments = "Source field: tbl_user.role_id")
    public static final SqlColumn<String> roleId = user.roleId;

    /**
     * Database Column Remarks:
     * 状态，0：不正常；1：正常
     */
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.176+08:00",
            comments = "Source field: tbl_user.status")
    public static final SqlColumn<Short> status = user.status;

    /**
     * Database Column Remarks:
     * 创建时间
     */
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.176+08:00",
            comments = "Source field: tbl_user.create_time")
    public static final SqlColumn<Long> createTime = user.createTime;

    /**
     * Database Column Remarks:
     * 最后更新时间
     */
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.177+08:00",
            comments = "Source field: tbl_user.update_time")
    public static final SqlColumn<Long> updateTime = user.updateTime;

    /**
     * Database Column Remarks:
     * 增加此条数据的用户id
     */
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.177+08:00",
            comments = "Source field: tbl_user.create_user_id")
    public static final SqlColumn<String> createUserId = user.createUserId;

    /**
     * Database Column Remarks:
     * 最后更新此条数据的用户id
     */
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.177+08:00",
            comments = "Source field: tbl_user.update_user_id")
    public static final SqlColumn<String> updateUserId = user.updateUserId;

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.172+08:00",
            comments = "Source Table: tbl_user")
    public static final class User extends SqlTable {
        public final SqlColumn<String> id = column("id", JDBCType.VARCHAR);

        public final SqlColumn<String> userName = column("user_name", JDBCType.VARCHAR);

        public final SqlColumn<String> realName = column("real_name", JDBCType.VARCHAR);

        public final SqlColumn<String> gender = column("gender", JDBCType.VARCHAR);

        public final SqlColumn<String> password = column("password", JDBCType.VARCHAR);

        public final SqlColumn<String> phone = column("phone", JDBCType.VARCHAR);

        public final SqlColumn<String> email = column("email", JDBCType.VARCHAR);

        public final SqlColumn<String> idCard = column("id_card", JDBCType.VARCHAR);

        public final SqlColumn<String> avatarUrl = column("avatar_url", JDBCType.VARCHAR);

        public final SqlColumn<String> token = column("token", JDBCType.VARCHAR);

        public final SqlColumn<String> openid = column("openid", JDBCType.VARCHAR);

        public final SqlColumn<String> roleId = column("role_id", JDBCType.VARCHAR);

        public final SqlColumn<Short> status = column("status", JDBCType.SMALLINT);

        public final SqlColumn<Long> createTime = column("create_time", JDBCType.BIGINT);

        public final SqlColumn<Long> updateTime = column("update_time", JDBCType.BIGINT);

        public final SqlColumn<String> createUserId = column("create_user_id", JDBCType.VARCHAR);

        public final SqlColumn<String> updateUserId = column("update_user_id", JDBCType.VARCHAR);

        public User() {
            super("tbl_user");
        }
    }
}