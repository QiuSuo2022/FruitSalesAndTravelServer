package com.guet.qiusuo.fruittravel.dao;

import java.sql.JDBCType;
import javax.annotation.Generated;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class DeliveryInfoDynamicSqlSupport {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-04-14T19:34:43.261+08:00", comments="Source Table: tbl_deliveryinfo")
    public static final DeliveryInfo deliveryInfo = new DeliveryInfo();

    /**
     * Database Column Remarks:
     *   uuid
     */
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-04-14T19:34:43.262+08:00", comments="Source field: tbl_deliveryinfo.id")
    public static final SqlColumn<String> id = deliveryInfo.id;

    /**
     * Database Column Remarks:
     *   用户id
     */
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-04-14T19:34:43.262+08:00", comments="Source field: tbl_deliveryinfo.user_id")
    public static final SqlColumn<String> userId = deliveryInfo.userId;

    /**
     * Database Column Remarks:
     *   收货人姓名
     */
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-04-14T19:34:43.262+08:00", comments="Source field: tbl_deliveryinfo.delivery_name")
    public static final SqlColumn<String> deliveryName = deliveryInfo.deliveryName;

    /**
     * Database Column Remarks:
     *   收货人电话
     */
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-04-14T19:34:43.262+08:00", comments="Source field: tbl_deliveryinfo.delivery_phone")
    public static final SqlColumn<String> deliveryPhone = deliveryInfo.deliveryPhone;

    /**
     * Database Column Remarks:
     *   收货人地址
     */
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-04-14T19:34:43.262+08:00", comments="Source field: tbl_deliveryinfo.delivery_address")
    public static final SqlColumn<String> deliveryAddress = deliveryInfo.deliveryAddress;

    /**
     * Database Column Remarks:
     *   状态，0：不启用；1：启用
     */
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-04-14T19:34:43.262+08:00", comments="Source field: tbl_deliveryinfo.status")
    public static final SqlColumn<Short> status = deliveryInfo.status;

    /**
     * Database Column Remarks:
     *   创建时间
     */
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-04-14T19:34:43.262+08:00", comments="Source field: tbl_deliveryinfo.create_time")
    public static final SqlColumn<Long> createTime = deliveryInfo.createTime;

    /**
     * Database Column Remarks:
     *   最后更新时间
     */
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-04-14T19:34:43.262+08:00", comments="Source field: tbl_deliveryinfo.update_time")
    public static final SqlColumn<Long> updateTime = deliveryInfo.updateTime;

    /**
     * Database Column Remarks:
     *   增加此条数据的用户id
     */
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-04-14T19:34:43.262+08:00", comments="Source field: tbl_deliveryinfo.create_user_id")
    public static final SqlColumn<String> createUserId = deliveryInfo.createUserId;

    /**
     * Database Column Remarks:
     *   最后更新此条数据的用户id
     */
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-04-14T19:34:43.263+08:00", comments="Source field: tbl_deliveryinfo.update_user_id")
    public static final SqlColumn<String> updateUserId = deliveryInfo.updateUserId;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-04-14T19:34:43.262+08:00", comments="Source Table: tbl_deliveryinfo")
    public static final class DeliveryInfo extends SqlTable {
        public final SqlColumn<String> id = column("id", JDBCType.VARCHAR);

        public final SqlColumn<String> userId = column("user_id", JDBCType.VARCHAR);

        public final SqlColumn<String> deliveryName = column("delivery_name", JDBCType.VARCHAR);

        public final SqlColumn<String> deliveryPhone = column("delivery_phone", JDBCType.VARCHAR);

        public final SqlColumn<String> deliveryAddress = column("delivery_address", JDBCType.VARCHAR);

        public final SqlColumn<Short> status = column("status", JDBCType.SMALLINT);

        public final SqlColumn<Long> createTime = column("create_time", JDBCType.BIGINT);

        public final SqlColumn<Long> updateTime = column("update_time", JDBCType.BIGINT);

        public final SqlColumn<String> createUserId = column("create_user_id", JDBCType.VARCHAR);

        public final SqlColumn<String> updateUserId = column("update_user_id", JDBCType.VARCHAR);

        public DeliveryInfo() {
            super("tbl_deliveryinfo");
        }
    }
}