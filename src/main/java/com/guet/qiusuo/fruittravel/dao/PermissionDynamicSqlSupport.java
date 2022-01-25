package com.guet.qiusuo.fruittravel.dao;

import java.sql.JDBCType;
import javax.annotation.Generated;

import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class PermissionDynamicSqlSupport {
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.249+08:00",
            comments = "Source Table: tbl_permission")
    public static final Permission permission = new Permission();

    /**
     * Database Column Remarks:
     * uuid
     */
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.249+08:00",
            comments = "Source field: tbl_permission.id")
    public static final SqlColumn<String> id = permission.id;

    /**
     * Database Column Remarks:
     * 权限名称
     */
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.249+08:00",
            comments = "Source field: tbl_permission.name")
    public static final SqlColumn<String> name = permission.name;

    /**
     * Database Column Remarks:
     * API URL
     */
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.249+08:00",
            comments = "Source field: tbl_permission.api_path")
    public static final SqlColumn<String> apiPath = permission.apiPath;

    /**
     * Database Column Remarks:
     * 请求方式，GET,PUT,POST,DELETE
     */
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.25+08:00", comments
            = "Source field: tbl_permission.method")
    public static final SqlColumn<String> method = permission.method;

    /**
     * Database Column Remarks:
     * 路由 URL
     */
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.25+08:00", comments
            = "Source field: tbl_permission.route_path")
    public static final SqlColumn<String> routePath = permission.routePath;

    /**
     * Database Column Remarks:
     * 权限类型；0：路由，1：API
     */
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.25+08:00", comments
            = "Source field: tbl_permission.type")
    public static final SqlColumn<Short> type = permission.type;

    /**
     * Database Column Remarks:
     * 状态,0:禁用 1:启用
     */
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.25+08:00", comments
            = "Source field: tbl_permission.status")
    public static final SqlColumn<Short> status = permission.status;

    /**
     * Database Column Remarks:
     * 创建时间
     */
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.25+08:00", comments
            = "Source field: tbl_permission.create_time")
    public static final SqlColumn<Long> createTime = permission.createTime;

    /**
     * Database Column Remarks:
     * 更新时间
     */
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.25+08:00", comments
            = "Source field: tbl_permission.update_time")
    public static final SqlColumn<Long> updateTime = permission.updateTime;

    /**
     * Database Column Remarks:
     * 记录创建人ID
     */
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.25+08:00", comments
            = "Source field: tbl_permission.create_user_id")
    public static final SqlColumn<String> createUserId = permission.createUserId;

    /**
     * Database Column Remarks:
     * 记录修改人ID
     */
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.251+08:00",
            comments = "Source field: tbl_permission.update_user_id")
    public static final SqlColumn<String> updateUserId = permission.updateUserId;

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.249+08:00",
            comments = "Source Table: tbl_permission")
    public static final class Permission extends SqlTable {
        public final SqlColumn<String> id = column("id", JDBCType.VARCHAR);

        public final SqlColumn<String> name = column("name", JDBCType.VARCHAR);

        public final SqlColumn<String> apiPath = column("api_path", JDBCType.VARCHAR);

        public final SqlColumn<String> method = column("method", JDBCType.VARCHAR);

        public final SqlColumn<String> routePath = column("route_path", JDBCType.VARCHAR);

        public final SqlColumn<Short> type = column("type", JDBCType.SMALLINT);

        public final SqlColumn<Short> status = column("status", JDBCType.SMALLINT);

        public final SqlColumn<Long> createTime = column("create_time", JDBCType.BIGINT);

        public final SqlColumn<Long> updateTime = column("update_time", JDBCType.BIGINT);

        public final SqlColumn<String> createUserId = column("create_user_id", JDBCType.VARCHAR);

        public final SqlColumn<String> updateUserId = column("update_user_id", JDBCType.VARCHAR);

        public Permission() {
            super("tbl_permission");
        }
    }
}