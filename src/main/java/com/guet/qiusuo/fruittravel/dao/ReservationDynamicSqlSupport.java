package com.guet.qiusuo.fruittravel.dao;

import java.sql.JDBCType;
import javax.annotation.Generated;

import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class ReservationDynamicSqlSupport {
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.256+08:00",
            comments = "Source Table: tbl_reservation")
    public static final Reservation reservation = new Reservation();

    /**
     * Database Column Remarks:
     * uuid
     */
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.257+08:00",
            comments = "Source field: tbl_reservation.id")
    public static final SqlColumn<String> id = reservation.id;

    /**
     * Database Column Remarks:
     * 用户id
     */
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.257+08:00",
            comments = "Source field: tbl_reservation.user_id")
    public static final SqlColumn<String> userId = reservation.userId;

    /**
     * Database Column Remarks:
     * 门票id
     */
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.257+08:00",
            comments = "Source field: tbl_reservation.ticket_id")
    public static final SqlColumn<String> ticketId = reservation.ticketId;

    /**
     * Database Column Remarks:
     * 门票预订的时间
     */
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.257+08:00",
            comments = "Source field: tbl_reservation.reserve_time")
    public static final SqlColumn<Long> reserveTime = reservation.reserveTime;

    /**
     * Database Column Remarks:
     * 购买数量
     */
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.258+08:00",
            comments = "Source field: tbl_reservation.quantity")
    public static final SqlColumn<Integer> quantity = reservation.quantity;

    /**
     * Database Column Remarks:
     * 状态, 0: 无效 1: 有效
     */
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.258+08:00",
            comments = "Source field: tbl_reservation.status")
    public static final SqlColumn<Short> status = reservation.status;

    /**
     * Database Column Remarks:
     * 创建时间
     */
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.258+08:00",
            comments = "Source field: tbl_reservation.create_time")
    public static final SqlColumn<Long> createTime = reservation.createTime;

    /**
     * Database Column Remarks:
     * 最后更新时间
     */
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.258+08:00",
            comments = "Source field: tbl_reservation.update_time")
    public static final SqlColumn<Long> updateTime = reservation.updateTime;

    /**
     * Database Column Remarks:
     * 增加此条数据的用户id
     */
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.258+08:00",
            comments = "Source field: tbl_reservation.create_user_id")
    public static final SqlColumn<String> createUserId = reservation.createUserId;

    /**
     * Database Column Remarks:
     * 最后更新此条数据的用户id
     */
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.258+08:00",
            comments = "Source field: tbl_reservation.update_user_id")
    public static final SqlColumn<String> updateUserId = reservation.updateUserId;

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.257+08:00",
            comments = "Source Table: tbl_reservation")
    public static final class Reservation extends SqlTable {
        public final SqlColumn<String> id = column("id", JDBCType.VARCHAR);

        public final SqlColumn<String> userId = column("user_id", JDBCType.VARCHAR);

        public final SqlColumn<String> ticketId = column("ticket_id", JDBCType.VARCHAR);

        public final SqlColumn<Long> reserveTime = column("reserve_time", JDBCType.BIGINT);

        public final SqlColumn<Integer> quantity = column("quantity", JDBCType.INTEGER);

        public final SqlColumn<Short> status = column("status", JDBCType.SMALLINT);

        public final SqlColumn<Long> createTime = column("create_time", JDBCType.BIGINT);

        public final SqlColumn<Long> updateTime = column("update_time", JDBCType.BIGINT);

        public final SqlColumn<String> createUserId = column("create_user_id", JDBCType.VARCHAR);

        public final SqlColumn<String> updateUserId = column("update_user_id", JDBCType.VARCHAR);

        public Reservation() {
            super("tbl_reservation");
        }
    }
}