package com.guet.qiusuo.fruittravel.dao;

import java.sql.JDBCType;
import javax.annotation.Generated;

import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class TicketDynamicSqlSupport {
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.283+08:00",
            comments = "Source Table: tbl_ticket")
    public static final Ticket ticket = new Ticket();

    /**
     * Database Column Remarks:
     * uuid
     */
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.284+08:00",
            comments = "Source field: tbl_ticket.id")
    public static final SqlColumn<String> id = ticket.id;

    /**
     * Database Column Remarks:
     * 景点id
     */
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.284+08:00",
            comments = "Source field: tbl_ticket.scenic_id")
    public static final SqlColumn<String> scenicId = ticket.scenicId;

    /**
     * Database Column Remarks:
     * 门票类型, 1--成人票, 2--儿童票, 3--老人票
     */
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.284+08:00",
            comments = "Source field: tbl_ticket.type")
    public static final SqlColumn<Short> type = ticket.type;

    /**
     * Database Column Remarks:
     * 门票价格
     */
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.284+08:00",
            comments = "Source field: tbl_ticket.price")
    public static final SqlColumn<Integer> price = ticket.price;

    /**
     * Database Column Remarks:
     * 门票描述
     */
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.284+08:00",
            comments = "Source field: tbl_ticket.description")
    public static final SqlColumn<String> description = ticket.description;

    /**
     * Database Column Remarks:
     * 状态, 0: 无效 1: 有效
     */
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.286+08:00",
            comments = "Source field: tbl_ticket.status")
    public static final SqlColumn<Short> status = ticket.status;

    /**
     * Database Column Remarks:
     * 创建时间
     */
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.286+08:00",
            comments = "Source field: tbl_ticket.create_time")
    public static final SqlColumn<Long> createTime = ticket.createTime;

    /**
     * Database Column Remarks:
     * 最后更新时间
     */
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.286+08:00",
            comments = "Source field: tbl_ticket.update_time")
    public static final SqlColumn<Long> updateTime = ticket.updateTime;

    /**
     * Database Column Remarks:
     * 增加此条数据的用户id
     */
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.287+08:00",
            comments = "Source field: tbl_ticket.create_user_id")
    public static final SqlColumn<String> createUserId = ticket.createUserId;

    /**
     * Database Column Remarks:
     * 最后更新此条数据的用户id
     */
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.287+08:00",
            comments = "Source field: tbl_ticket.update_user_id")
    public static final SqlColumn<String> updateUserId = ticket.updateUserId;

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.284+08:00",
            comments = "Source Table: tbl_ticket")
    public static final class Ticket extends SqlTable {
        public final SqlColumn<String> id = column("id", JDBCType.VARCHAR);

        public final SqlColumn<String> scenicId = column("scenic_id", JDBCType.VARCHAR);

        public final SqlColumn<Short> type = column("type", JDBCType.SMALLINT);

        public final SqlColumn<Integer> price = column("price", JDBCType.INTEGER);

        public final SqlColumn<String> description = column("description", JDBCType.VARCHAR);

        public final SqlColumn<Short> status = column("status", JDBCType.SMALLINT);

        public final SqlColumn<Long> createTime = column("create_time", JDBCType.BIGINT);

        public final SqlColumn<Long> updateTime = column("update_time", JDBCType.BIGINT);

        public final SqlColumn<String> createUserId = column("create_user_id", JDBCType.VARCHAR);

        public final SqlColumn<String> updateUserId = column("update_user_id", JDBCType.VARCHAR);

        public Ticket() {
            super("tbl_ticket");
        }
    }
}