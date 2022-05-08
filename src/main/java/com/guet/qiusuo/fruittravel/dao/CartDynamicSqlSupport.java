package com.guet.qiusuo.fruittravel.dao;

import java.sql.JDBCType;
import javax.annotation.Generated;

import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class CartDynamicSqlSupport {
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.21+08:00", comments
            = "Source Table: tbl_cart")
    public static final Cart cart = new Cart();

    /**
     * Database Column Remarks:
     * uuid
     */
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.211+08:00",
            comments = "Source field: tbl_cart.id")
    public static final SqlColumn<String> id = cart.id;

    /**
     * Database Column Remarks:
     * 用户id
     */
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.211+08:00",
            comments = "Source field: tbl_cart.user_id")
    public static final SqlColumn<String> userId = cart.userId;

    /**
     * Database Column Remarks:
     * 水果商品子项id
     */
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.211+08:00",
            comments = "Source field: tbl_cart.child_fruit_id")
    public static final SqlColumn<String> childFruitId = cart.childFruitId;

    /**
     * Database Column Remarks:
     * 购买数量
     */
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.212+08:00",
            comments = "Source field: tbl_cart.quantity")
    public static final SqlColumn<Integer> quantity = cart.quantity;

    /**
     * Database Column Remarks:
     * 状态, 0: 无效 1: 有效
     */
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.212+08:00",
            comments = "Source field: tbl_cart.status")
    public static final SqlColumn<Short> status = cart.status;

    /**
     * Database Column Remarks:
     * 创建时间
     */
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.212+08:00",
            comments = "Source field: tbl_cart.create_time")
    public static final SqlColumn<Long> createTime = cart.createTime;

    /**
     * Database Column Remarks:
     * 最后更新时间
     */
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.213+08:00",
            comments = "Source field: tbl_cart.update_time")
    public static final SqlColumn<Long> updateTime = cart.updateTime;

    /**
     * Database Column Remarks:
     * 增加此条数据的用户id
     */
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.213+08:00",
            comments = "Source field: tbl_cart.create_user_id")
    public static final SqlColumn<String> createUserId = cart.createUserId;

    /**
     * Database Column Remarks:
     * 最后更新此条数据的用户id
     */
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.213+08:00",
            comments = "Source field: tbl_cart.update_user_id")
    public static final SqlColumn<String> updateUserId = cart.updateUserId;

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.211+08:00",
            comments = "Source Table: tbl_cart")
    public static final class Cart extends SqlTable {
        public final SqlColumn<String> id = column("id", JDBCType.VARCHAR);

        public final SqlColumn<String> userId = column("user_id", JDBCType.VARCHAR);

        public final SqlColumn<String> childFruitId = column("child_fruit_id", JDBCType.VARCHAR);

        public final SqlColumn<Integer> quantity = column("quantity", JDBCType.INTEGER);

        public final SqlColumn<Short> status = column("status", JDBCType.SMALLINT);

        public final SqlColumn<Long> createTime = column("create_time", JDBCType.BIGINT);

        public final SqlColumn<Long> updateTime = column("update_time", JDBCType.BIGINT);

        public final SqlColumn<String> createUserId = column("create_user_id", JDBCType.VARCHAR);

        public final SqlColumn<String> updateUserId = column("update_user_id", JDBCType.VARCHAR);

        public Cart() {
            super("tbl_cart");
        }
    }
}