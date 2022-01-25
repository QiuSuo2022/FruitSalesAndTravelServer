package com.guet.qiusuo.fruittravel.dao;

import java.sql.JDBCType;
import javax.annotation.Generated;

import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class RolePermissionDynamicSqlSupport {
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.269+08:00",
            comments = "Source Table: tbl_role_permission")
    public static final RolePermission rolePermission = new RolePermission();

    /**
     * Database Column Remarks:
     * uuid
     */
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.269+08:00",
            comments = "Source field: tbl_role_permission.id")
    public static final SqlColumn<String> id = rolePermission.id;

    /**
     * Database Column Remarks:
     * 角色id
     */
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.269+08:00",
            comments = "Source field: tbl_role_permission.role_id")
    public static final SqlColumn<String> roleId = rolePermission.roleId;

    /**
     * Database Column Remarks:
     * 权限id
     */
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.269+08:00",
            comments = "Source field: tbl_role_permission.permission_id")
    public static final SqlColumn<String> permissionId = rolePermission.permissionId;

    /**
     * Database Column Remarks:
     * 状态,0:禁用 1:启用
     */
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.27+08:00", comments
            = "Source field: tbl_role_permission.status")
    public static final SqlColumn<Short> status = rolePermission.status;

    /**
     * Database Column Remarks:
     * 创建时间
     */
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.27+08:00", comments
            = "Source field: tbl_role_permission.create_time")
    public static final SqlColumn<Long> createTime = rolePermission.createTime;

    /**
     * Database Column Remarks:
     * 更新时间
     */
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.27+08:00", comments
            = "Source field: tbl_role_permission.update_time")
    public static final SqlColumn<Long> updateTime = rolePermission.updateTime;

    /**
     * Database Column Remarks:
     * 记录创建人ID
     */
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.27+08:00", comments
            = "Source field: tbl_role_permission.create_user_id")
    public static final SqlColumn<String> createUserId = rolePermission.createUserId;

    /**
     * Database Column Remarks:
     * 记录修改人ID
     */
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.27+08:00", comments
            = "Source field: tbl_role_permission.update_user_id")
    public static final SqlColumn<String> updateUserId = rolePermission.updateUserId;

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.269+08:00",
            comments = "Source Table: tbl_role_permission")
    public static final class RolePermission extends SqlTable {
        public final SqlColumn<String> id = column("id", JDBCType.VARCHAR);

        public final SqlColumn<String> roleId = column("role_id", JDBCType.VARCHAR);

        public final SqlColumn<String> permissionId = column("permission_id", JDBCType.VARCHAR);

        public final SqlColumn<Short> status = column("status", JDBCType.SMALLINT);

        public final SqlColumn<Long> createTime = column("create_time", JDBCType.BIGINT);

        public final SqlColumn<Long> updateTime = column("update_time", JDBCType.BIGINT);

        public final SqlColumn<String> createUserId = column("create_user_id", JDBCType.VARCHAR);

        public final SqlColumn<String> updateUserId = column("update_user_id", JDBCType.VARCHAR);

        public RolePermission() {
            super("tbl_role_permission");
        }
    }
}