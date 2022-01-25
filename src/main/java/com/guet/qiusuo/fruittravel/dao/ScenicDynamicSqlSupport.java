package com.guet.qiusuo.fruittravel.dao;

import java.sql.JDBCType;
import javax.annotation.Generated;

import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class ScenicDynamicSqlSupport {
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.275+08:00",
            comments = "Source Table: tbl_scenic")
    public static final Scenic scenic = new Scenic();

    /**
     * Database Column Remarks:
     * uuid
     */
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.275+08:00",
            comments = "Source field: tbl_scenic.id")
    public static final SqlColumn<String> id = scenic.id;

    /**
     * Database Column Remarks:
     * 景点名称
     */
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.276+08:00",
            comments = "Source field: tbl_scenic.scenic_name")
    public static final SqlColumn<String> scenicName = scenic.scenicName;

    /**
     * Database Column Remarks:
     * 地理位置
     */
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.276+08:00",
            comments = "Source field: tbl_scenic.location")
    public static final SqlColumn<String> location = scenic.location;

    /**
     * Database Column Remarks:
     * 开放时间
     */
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.276+08:00",
            comments = "Source field: tbl_scenic.opening_hours")
    public static final SqlColumn<String> openingHours = scenic.openingHours;

    /**
     * Database Column Remarks:
     * 景区介绍
     */
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.276+08:00",
            comments = "Source field: tbl_scenic.description")
    public static final SqlColumn<String> description = scenic.description;

    /**
     * Database Column Remarks:
     * 景区类型, 1-文化古迹, 2-园林花园, 3-古镇古村, 4-城堡教堂
     */
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.276+08:00",
            comments = "Source field: tbl_scenic.type")
    public static final SqlColumn<Short> type = scenic.type;

    /**
     * Database Column Remarks:
     * 状态, 0: 无效 1: 有效
     */
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.276+08:00",
            comments = "Source field: tbl_scenic.status")
    public static final SqlColumn<Short> status = scenic.status;

    /**
     * Database Column Remarks:
     * 创建时间
     */
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.277+08:00",
            comments = "Source field: tbl_scenic.create_time")
    public static final SqlColumn<Long> createTime = scenic.createTime;

    /**
     * Database Column Remarks:
     * 最后更新时间
     */
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.277+08:00",
            comments = "Source field: tbl_scenic.update_time")
    public static final SqlColumn<Long> updateTime = scenic.updateTime;

    /**
     * Database Column Remarks:
     * 增加此条数据的用户id
     */
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.277+08:00",
            comments = "Source field: tbl_scenic.create_user_id")
    public static final SqlColumn<String> createUserId = scenic.createUserId;

    /**
     * Database Column Remarks:
     * 最后更新此条数据的用户id
     */
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.277+08:00",
            comments = "Source field: tbl_scenic.update_user_id")
    public static final SqlColumn<String> updateUserId = scenic.updateUserId;

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.275+08:00",
            comments = "Source Table: tbl_scenic")
    public static final class Scenic extends SqlTable {
        public final SqlColumn<String> id = column("id", JDBCType.VARCHAR);

        public final SqlColumn<String> scenicName = column("scenic_name", JDBCType.VARCHAR);

        public final SqlColumn<String> location = column("location", JDBCType.VARCHAR);

        public final SqlColumn<String> openingHours = column("opening_hours", JDBCType.VARCHAR);

        public final SqlColumn<String> description = column("description", JDBCType.VARCHAR);

        public final SqlColumn<Short> type = column("type", JDBCType.SMALLINT);

        public final SqlColumn<Short> status = column("status", JDBCType.SMALLINT);

        public final SqlColumn<Long> createTime = column("create_time", JDBCType.BIGINT);

        public final SqlColumn<Long> updateTime = column("update_time", JDBCType.BIGINT);

        public final SqlColumn<String> createUserId = column("create_user_id", JDBCType.VARCHAR);

        public final SqlColumn<String> updateUserId = column("update_user_id", JDBCType.VARCHAR);

        public Scenic() {
            super("tbl_scenic");
        }
    }
}