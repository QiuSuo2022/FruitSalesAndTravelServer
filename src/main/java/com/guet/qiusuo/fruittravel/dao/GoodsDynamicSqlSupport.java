package com.guet.qiusuo.fruittravel.dao;

import java.sql.JDBCType;
import javax.annotation.Generated;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class GoodsDynamicSqlSupport {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-05-01T21:35:44.2519881+08:00", comments="Source Table: tbl_goods")
    public static final Goods goods = new Goods();

    /**
     * Database Column Remarks:
     *   商品uuid
     */
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-05-01T21:35:44.2519881+08:00", comments="Source field: tbl_goods.id")
    public static final SqlColumn<String> id = goods.id;

    /**
     * Database Column Remarks:
     *   订单编号
     */
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-05-01T21:35:44.2519881+08:00", comments="Source field: tbl_goods.order_id")
    public static final SqlColumn<String> orderId = goods.orderId;

    /**
     * Database Column Remarks:
     *   商品名
     */
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-05-01T21:35:44.2529951+08:00", comments="Source field: tbl_goods.name")
    public static final SqlColumn<String> name = goods.name;

    /**
     * Database Column Remarks:
     *   商品单价
     */
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-05-01T21:35:44.2529951+08:00", comments="Source field: tbl_goods.price")
    public static final SqlColumn<Integer> price = goods.price;

    /**
     * Database Column Remarks:
     *   水果id
     */
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-05-01T21:35:44.2529951+08:00", comments="Source field: tbl_goods.fruit_id")
    public static final SqlColumn<String> fruitId = goods.fruitId;

    /**
     * Database Column Remarks:
     *   景区id
     */
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-05-01T21:35:44.2529951+08:00", comments="Source field: tbl_goods.scenic_id")
    public static final SqlColumn<String> scenicId = goods.scenicId;

    /**
     * Database Column Remarks:
     *   数目
     */
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-05-01T21:35:44.2529951+08:00", comments="Source field: tbl_goods.amount")
    public static final SqlColumn<Integer> amount = goods.amount;

    /**
     * Database Column Remarks:
     *   订单状态:未支付-0 已支付-1 待发货-2 已发货-3 已完成-4 已退款-5
     */
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-05-01T21:35:44.2529951+08:00", comments="Source field: tbl_goods.pay_status")
    public static final SqlColumn<Short> payStatus = goods.payStatus;

    /**
     * Database Column Remarks:
     *   状态,0:禁用 1:启用
     */
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-05-01T21:35:44.2529951+08:00", comments="Source field: tbl_goods.STATUS")
    public static final SqlColumn<Short> status = goods.status;

    /**
     * Database Column Remarks:
     *   创建时间
     */
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-05-01T21:35:44.2529951+08:00", comments="Source field: tbl_goods.create_time")
    public static final SqlColumn<Long> createTime = goods.createTime;

    /**
     * Database Column Remarks:
     *   更新时间
     */
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-05-01T21:35:44.2529951+08:00", comments="Source field: tbl_goods.update_time")
    public static final SqlColumn<Long> updateTime = goods.updateTime;

    /**
     * Database Column Remarks:
     *   记录创建人ID
     */
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-05-01T21:35:44.2529951+08:00", comments="Source field: tbl_goods.create_user_id")
    public static final SqlColumn<String> createUserId = goods.createUserId;

    /**
     * Database Column Remarks:
     *   记录修改人ID
     */
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-05-01T21:35:44.2529951+08:00", comments="Source field: tbl_goods.update_user_id")
    public static final SqlColumn<String> updateUserId = goods.updateUserId;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-05-01T21:35:44.2519881+08:00", comments="Source Table: tbl_goods")
    public static final class Goods extends SqlTable {
        public final SqlColumn<String> id = column("id", JDBCType.VARCHAR);

        public final SqlColumn<String> orderId = column("order_id", JDBCType.VARCHAR);

        public final SqlColumn<String> name = column("name", JDBCType.VARCHAR);

        public final SqlColumn<Integer> price = column("price", JDBCType.INTEGER);

        public final SqlColumn<String> fruitId = column("fruit_id", JDBCType.VARCHAR);

        public final SqlColumn<String> scenicId = column("scenic_id", JDBCType.VARCHAR);

        public final SqlColumn<Integer> amount = column("amount", JDBCType.INTEGER);

        public final SqlColumn<Short> payStatus = column("pay_status", JDBCType.SMALLINT);

        public final SqlColumn<Short> status = column("STATUS", JDBCType.SMALLINT);

        public final SqlColumn<Long> createTime = column("create_time", JDBCType.BIGINT);

        public final SqlColumn<Long> updateTime = column("update_time", JDBCType.BIGINT);

        public final SqlColumn<String> createUserId = column("create_user_id", JDBCType.VARCHAR);

        public final SqlColumn<String> updateUserId = column("update_user_id", JDBCType.VARCHAR);

        public Goods() {
            super("tbl_goods");
        }
    }
}