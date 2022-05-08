package com.guet.qiusuo.fruittravel.dao;

import java.sql.JDBCType;
import javax.annotation.Generated;

import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class RoleDynamicSqlSupport {
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.263+08:00",
            comments = "Source Table: tbl_role")
    public static final Role role = new Role();

    /**
     * Database Column Remarks:
     * uuid
     */
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.263+08:00",
            comments = "Source field: tbl_role.id")
    public static final SqlColumn<String> id = role.id;

    /**
     * Database Column Remarks:
     * 角色名称
     */
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.264+08:00",
            comments = "Source field: tbl_role.name")
    public static final SqlColumn<String> name = role.name;

    /**
     * Database Column Remarks:
     * 状态, 0:禁用 1:启用
     */
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.264+08:00",
            comments = "Source field: tbl_role.status")
    public static final SqlColumn<Short> status = role.status;

    /**
     * Database Column Remarks:
     * 创建时间
     */
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.264+08:00",
            comments = "Source field: tbl_role.create_time")
    public static final SqlColumn<Long> createTime = role.createTime;

    /**
     * Database Column Remarks:
     * 最后更新时间
     */
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.264+08:00",
            comments = "Source field: tbl_role.update_time")
    public static final SqlColumn<Long> updateTime = role.updateTime;

    /**
     * Database Column Remarks:
     * 增加此条数据的用户id
     */
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.264+08:00",
            comments = "Source field: tbl_role.create_user_id")
    public static final SqlColumn<String> createUserId = role.createUserId;

    /**
     * Database Column Remarks:
     * 最后更新此条数据的用户id
     */
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.264+08:00",
            comments = "Source field: tbl_role.update_user_id")
    public static final SqlColumn<String> updateUserId = role.updateUserId;

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.263+08:00",
            comments = "Source Table: tbl_role")
    public static final class Role extends SqlTable {
        public final SqlColumn<String> id = column("id", JDBCType.VARCHAR);

        public final SqlColumn<String> name = column("name", JDBCType.VARCHAR);

        public final SqlColumn<Short> status = column("status", JDBCType.SMALLINT);

        public final SqlColumn<Long> createTime = column("create_time", JDBCType.BIGINT);

        public final SqlColumn<Long> updateTime = column("update_time", JDBCType.BIGINT);

        public final SqlColumn<String> createUserId = column("create_user_id", JDBCType.VARCHAR);

        public final SqlColumn<String> updateUserId = column("update_user_id", JDBCType.VARCHAR);

        public Role() {
            super("tbl_role");
        }
    }
}