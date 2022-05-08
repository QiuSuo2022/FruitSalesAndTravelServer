package com.guet.qiusuo.fruittravel.dao;

import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

import javax.annotation.Generated;
import java.sql.JDBCType;

public final class UserRoleDynamicSqlSupport {
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-30T15:19:23.866+08:00",
            comments = "Source Table: tbl_user_role")
    public static final UserRole userRole = new UserRole();

    /**
     * Database Column Remarks:
     * uuid
     */
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-30T15:19:23.866+08:00",
            comments = "Source field: tbl_user_role.id")
    public static final SqlColumn<String> id = userRole.id;

    /**
     * Database Column Remarks:
     * 角色id
     */
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-30T15:19:23.867+08:00",
            comments = "Source field: tbl_user_role.role_id")
    public static final SqlColumn<String> roleId = userRole.roleId;

    /**
     * Database Column Remarks:
     * 用户id
     */
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-30T15:19:23.867+08:00",
            comments = "Source field: tbl_user_role.user_id")
    public static final SqlColumn<String> userId = userRole.userId;

    /**
     * Database Column Remarks:
     * 状态,0:禁用 1:启用
     */
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-30T15:19:23.867+08:00",
            comments = "Source field: tbl_user_role.status")
    public static final SqlColumn<Short> status = userRole.status;

    /**
     * Database Column Remarks:
     * 创建时间
     */
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-30T15:19:23.867+08:00",
            comments = "Source field: tbl_user_role.create_time")
    public static final SqlColumn<Long> createTime = userRole.createTime;

    /**
     * Database Column Remarks:
     * 更新时间
     */
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-30T15:19:23.867+08:00",
            comments = "Source field: tbl_user_role.update_time")
    public static final SqlColumn<Long> updateTime = userRole.updateTime;

    /**
     * Database Column Remarks:
     * 记录创建人ID
     */
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-30T15:19:23.867+08:00",
            comments = "Source field: tbl_user_role.create_user_id")
    public static final SqlColumn<String> createUserId = userRole.createUserId;

    /**
     * Database Column Remarks:
     * 记录修改人ID
     */
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-30T15:19:23.867+08:00",
            comments = "Source field: tbl_user_role.update_user_id")
    public static final SqlColumn<String> updateUserId = userRole.updateUserId;

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-30T15:19:23.866+08:00",
            comments = "Source Table: tbl_user_role")
    public static final class UserRole extends SqlTable {
        public final SqlColumn<String> id = column("id", JDBCType.VARCHAR);

        public final SqlColumn<String> roleId = column("role_id", JDBCType.VARCHAR);

        public final SqlColumn<String> userId = column("user_id", JDBCType.VARCHAR);

        public final SqlColumn<Short> status = column("status", JDBCType.SMALLINT);

        public final SqlColumn<Long> createTime = column("create_time", JDBCType.BIGINT);

        public final SqlColumn<Long> updateTime = column("update_time", JDBCType.BIGINT);

        public final SqlColumn<String> createUserId = column("create_user_id", JDBCType.VARCHAR);

        public final SqlColumn<String> updateUserId = column("update_user_id", JDBCType.VARCHAR);

        public UserRole() {
            super("tbl_user_role");
        }
    }
}