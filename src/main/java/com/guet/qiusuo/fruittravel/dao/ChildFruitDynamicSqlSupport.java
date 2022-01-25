package com.guet.qiusuo.fruittravel.dao;

import java.sql.JDBCType;
import javax.annotation.Generated;

import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class ChildFruitDynamicSqlSupport {
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.219+08:00",
            comments = "Source Table: tbl_child_fruit")
    public static final ChildFruit childFruit = new ChildFruit();

    /**
     * Database Column Remarks:
     * uuid
     */
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.219+08:00",
            comments = "Source field: tbl_child_fruit.id")
    public static final SqlColumn<String> id = childFruit.id;

    /**
     * Database Column Remarks:
     * 水果商品id
     */
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.22+08:00", comments
            = "Source field: tbl_child_fruit.fruit_id")
    public static final SqlColumn<String> fruitId = childFruit.fruitId;

    /**
     * Database Column Remarks:
     * 水果名称
     */
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.22+08:00", comments
            = "Source field: tbl_child_fruit.fruit_name")
    public static final SqlColumn<String> fruitName = childFruit.fruitName;

    /**
     * Database Column Remarks:
     * 水果价钱(一个数字)
     */
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.22+08:00", comments
            = "Source field: tbl_child_fruit.fruit_price")
    public static final SqlColumn<Integer> fruitPrice = childFruit.fruitPrice;

    /**
     * Database Column Remarks:
     * 库存数量
     */
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.22+08:00", comments
            = "Source field: tbl_child_fruit.stock")
    public static final SqlColumn<Integer> stock = childFruit.stock;

    /**
     * Database Column Remarks:
     * 图片存储路径
     */
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.22+08:00", comments
            = "Source field: tbl_child_fruit.image_url")
    public static final SqlColumn<String> imageUrl = childFruit.imageUrl;

    /**
     * Database Column Remarks:
     * 状态,0: 无效 1: 有效
     */
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.22+08:00", comments
            = "Source field: tbl_child_fruit.status")
    public static final SqlColumn<Short> status = childFruit.status;

    /**
     * Database Column Remarks:
     * 创建时间
     */
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.22+08:00", comments
            = "Source field: tbl_child_fruit.create_time")
    public static final SqlColumn<Long> createTime = childFruit.createTime;

    /**
     * Database Column Remarks:
     * 最后更新时间
     */
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.221+08:00",
            comments = "Source field: tbl_child_fruit.update_time")
    public static final SqlColumn<Long> updateTime = childFruit.updateTime;

    /**
     * Database Column Remarks:
     * 增加此条数据的用户id
     */
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.221+08:00",
            comments = "Source field: tbl_child_fruit.create_user_id")
    public static final SqlColumn<String> createUserId = childFruit.createUserId;

    /**
     * Database Column Remarks:
     * 最后更新此条数据的用户id
     */
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.221+08:00",
            comments = "Source field: tbl_child_fruit.update_user_id")
    public static final SqlColumn<String> updateUserId = childFruit.updateUserId;

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.219+08:00",
            comments = "Source Table: tbl_child_fruit")
    public static final class ChildFruit extends SqlTable {
        public final SqlColumn<String> id = column("id", JDBCType.VARCHAR);

        public final SqlColumn<String> fruitId = column("fruit_id", JDBCType.VARCHAR);

        public final SqlColumn<String> fruitName = column("fruit_name", JDBCType.VARCHAR);

        public final SqlColumn<Integer> fruitPrice = column("fruit_price", JDBCType.INTEGER);

        public final SqlColumn<Integer> stock = column("stock", JDBCType.INTEGER);

        public final SqlColumn<String> imageUrl = column("image_url", JDBCType.VARCHAR);

        public final SqlColumn<Short> status = column("status", JDBCType.SMALLINT);

        public final SqlColumn<Long> createTime = column("create_time", JDBCType.BIGINT);

        public final SqlColumn<Long> updateTime = column("update_time", JDBCType.BIGINT);

        public final SqlColumn<String> createUserId = column("create_user_id", JDBCType.VARCHAR);

        public final SqlColumn<String> updateUserId = column("update_user_id", JDBCType.VARCHAR);

        public ChildFruit() {
            super("tbl_child_fruit");
        }
    }
}