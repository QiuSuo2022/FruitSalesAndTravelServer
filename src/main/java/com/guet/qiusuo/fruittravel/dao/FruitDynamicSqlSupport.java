package com.guet.qiusuo.fruittravel.dao;

import java.sql.JDBCType;
import javax.annotation.Generated;

import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class FruitDynamicSqlSupport {
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.234+08:00",
            comments = "Source Table: tbl_fruit")
    public static final Fruit fruit = new Fruit();

    /**
     * Database Column Remarks:
     * uuid
     */
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.234+08:00",
            comments = "Source field: tbl_fruit.id")
    public static final SqlColumn<String> id = fruit.id;

    /**
     * Database Column Remarks:
     * 水果名称
     */
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.234+08:00",
            comments = "Source field: tbl_fruit.fruit_name")
    public static final SqlColumn<String> fruitName = fruit.fruitName;

    /**
     * Database Column Remarks:
     * 水果价钱(一个范围)
     */
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.235+08:00",
            comments = "Source field: tbl_fruit.fruit_price")
    public static final SqlColumn<String> fruitPrice = fruit.fruitPrice;

    /**
     * Database Column Remarks:
     * 水果描述
     */
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.235+08:00",
            comments = "Source field: tbl_fruit.description")
    public static final SqlColumn<String> description = fruit.description;

    /**
     * Database Column Remarks:
     * 发货地点
     */
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.235+08:00",
            comments = "Source field: tbl_fruit.departure_point")
    public static final SqlColumn<String> departurePoint = fruit.departurePoint;

    /**
     * Database Column Remarks:
     * 快递费
     */
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.235+08:00",
            comments = "Source field: tbl_fruit.delivery_cost")
    public static final SqlColumn<Integer> deliveryCost = fruit.deliveryCost;

    /**
     * Database Column Remarks:
     * 状态,0: 无效 1: 有效
     */
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.235+08:00",
            comments = "Source field: tbl_fruit.status")
    public static final SqlColumn<Short> status = fruit.status;

    /**
     * Database Column Remarks:
     * 创建时间
     */
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.235+08:00",
            comments = "Source field: tbl_fruit.create_time")
    public static final SqlColumn<Long> createTime = fruit.createTime;

    /**
     * Database Column Remarks:
     * 最后更新时间
     */
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.235+08:00",
            comments = "Source field: tbl_fruit.update_time")
    public static final SqlColumn<Long> updateTime = fruit.updateTime;

    /**
     * Database Column Remarks:
     * 增加此条数据的用户id
     */
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.235+08:00",
            comments = "Source field: tbl_fruit.create_user_id")
    public static final SqlColumn<String> createUserId = fruit.createUserId;

    /**
     * Database Column Remarks:
     * 最后更新此条数据的用户id
     */
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.236+08:00",
            comments = "Source field: tbl_fruit.update_user_id")
    public static final SqlColumn<String> updateUserId = fruit.updateUserId;

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.234+08:00",
            comments = "Source Table: tbl_fruit")
    public static final class Fruit extends SqlTable {
        public final SqlColumn<String> id = column("id", JDBCType.VARCHAR);

        public final SqlColumn<String> fruitName = column("fruit_name", JDBCType.VARCHAR);

        public final SqlColumn<String> fruitPrice = column("fruit_price", JDBCType.VARCHAR);

        public final SqlColumn<String> description = column("description", JDBCType.VARCHAR);

        public final SqlColumn<String> departurePoint = column("departure_point", JDBCType.VARCHAR);

        public final SqlColumn<Integer> deliveryCost = column("delivery_cost", JDBCType.INTEGER);

        public final SqlColumn<Short> status = column("status", JDBCType.SMALLINT);

        public final SqlColumn<Long> createTime = column("create_time", JDBCType.BIGINT);

        public final SqlColumn<Long> updateTime = column("update_time", JDBCType.BIGINT);

        public final SqlColumn<String> createUserId = column("create_user_id", JDBCType.VARCHAR);

        public final SqlColumn<String> updateUserId = column("update_user_id", JDBCType.VARCHAR);

        public Fruit() {
            super("tbl_fruit");
        }
    }
}