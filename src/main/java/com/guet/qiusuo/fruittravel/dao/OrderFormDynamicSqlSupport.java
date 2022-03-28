package com.guet.qiusuo.fruittravel.dao;

import java.sql.JDBCType;
import javax.annotation.Generated;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class OrderFormDynamicSqlSupport {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-03-28T19:50:20.2924174+08:00", comments="Source Table: tbl_order_form")
    public static final OrderForm orderForm = new OrderForm();

    /**
     * Database Column Remarks:
     *   订单编号uuid
     */
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-03-28T19:50:20.2924174+08:00", comments="Source field: tbl_order_form.id")
    public static final SqlColumn<String> id = orderForm.id;

    /**
     * Database Column Remarks:
     *   用户id
     */
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-03-28T19:50:20.293417+08:00", comments="Source field: tbl_order_form.user_id")
    public static final SqlColumn<String> userId = orderForm.userId;

    /**
     * Database Column Remarks:
     *   收货地址
     */
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-03-28T19:50:20.293417+08:00", comments="Source field: tbl_order_form.address")
    public static final SqlColumn<String> address = orderForm.address;

    /**
     * Database Column Remarks:
     *   物流信息
     */
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-03-28T19:50:20.293417+08:00", comments="Source field: tbl_order_form.express")
    public static final SqlColumn<String> express = orderForm.express;

    /**
     * Database Column Remarks:
     *   水果id
     */
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-03-28T19:50:20.293417+08:00", comments="Source field: tbl_order_form.fruit_id")
    public static final SqlColumn<String> fruitId = orderForm.fruitId;

    /**
     * Database Column Remarks:
     *   景区id
     */
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-03-28T19:50:20.293417+08:00", comments="Source field: tbl_order_form.scenic_id")
    public static final SqlColumn<String> scenicId = orderForm.scenicId;

    /**
     * Database Column Remarks:
     *   数目
     */
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-03-28T19:50:20.293417+08:00", comments="Source field: tbl_order_form.amount")
    public static final SqlColumn<Integer> amount = orderForm.amount;

    /**
     * Database Column Remarks:
     *   实际付款金额
     */
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-03-28T19:50:20.293417+08:00", comments="Source field: tbl_order_form.payment")
    public static final SqlColumn<Double> payment = orderForm.payment;

    /**
     * Database Column Remarks:
     *   订单状态:未支付-0 已支付-1 待发货-2 已发货-3 已完成-4
     */
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-03-28T19:50:20.293417+08:00", comments="Source field: tbl_order_form.order_form_status")
    public static final SqlColumn<Short> orderFormStatus = orderForm.orderFormStatus;

    /**
     * Database Column Remarks:
     *   是否已经评价:未评价-0 已评价-1
     */
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-03-28T19:50:20.293417+08:00", comments="Source field: tbl_order_form.has_evaluate")
    public static final SqlColumn<Short> hasEvaluate = orderForm.hasEvaluate;

    /**
     * Database Column Remarks:
     *   评价表的id
     */
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-03-28T19:50:20.293417+08:00", comments="Source field: tbl_order_form.bind_evaluate_id")
    public static final SqlColumn<String> bindEvaluateId = orderForm.bindEvaluateId;

    /**
     * Database Column Remarks:
     *   状态,0:禁用 1:启用
     */
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-03-28T19:50:20.2944067+08:00", comments="Source field: tbl_order_form.STATUS")
    public static final SqlColumn<Short> status = orderForm.status;

    /**
     * Database Column Remarks:
     *   支付时间
     */
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-03-28T19:50:20.2944067+08:00", comments="Source field: tbl_order_form.pay_time")
    public static final SqlColumn<Long> payTime = orderForm.payTime;

    /**
     * Database Column Remarks:
     *   创建时间
     */
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-03-28T19:50:20.2944067+08:00", comments="Source field: tbl_order_form.create_time")
    public static final SqlColumn<Long> createTime = orderForm.createTime;

    /**
     * Database Column Remarks:
     *   更新时间
     */
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-03-28T19:50:20.2944067+08:00", comments="Source field: tbl_order_form.update_time")
    public static final SqlColumn<Long> updateTime = orderForm.updateTime;

    /**
     * Database Column Remarks:
     *   记录创建人ID
     */
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-03-28T19:50:20.2944067+08:00", comments="Source field: tbl_order_form.create_user_id")
    public static final SqlColumn<String> createUserId = orderForm.createUserId;

    /**
     * Database Column Remarks:
     *   记录修改人ID
     */
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-03-28T19:50:20.2944067+08:00", comments="Source field: tbl_order_form.update_user_id")
    public static final SqlColumn<String> updateUserId = orderForm.updateUserId;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-03-28T19:50:20.2924174+08:00", comments="Source Table: tbl_order_form")
    public static final class OrderForm extends SqlTable {
        public final SqlColumn<String> id = column("id", JDBCType.VARCHAR);

        public final SqlColumn<String> userId = column("user_id", JDBCType.VARCHAR);

        public final SqlColumn<String> address = column("address", JDBCType.VARCHAR);

        public final SqlColumn<String> express = column("express", JDBCType.VARCHAR);

        public final SqlColumn<String> fruitId = column("fruit_id", JDBCType.VARCHAR);

        public final SqlColumn<String> scenicId = column("scenic_id", JDBCType.VARCHAR);

        public final SqlColumn<Integer> amount = column("amount", JDBCType.INTEGER);

        public final SqlColumn<Double> payment = column("payment", JDBCType.DOUBLE);

        public final SqlColumn<Short> orderFormStatus = column("order_form_status", JDBCType.SMALLINT);

        public final SqlColumn<Short> hasEvaluate = column("has_evaluate", JDBCType.SMALLINT);

        public final SqlColumn<String> bindEvaluateId = column("bind_evaluate_id", JDBCType.VARCHAR);

        public final SqlColumn<Short> status = column("STATUS", JDBCType.SMALLINT);

        public final SqlColumn<Long> payTime = column("pay_time", JDBCType.BIGINT);

        public final SqlColumn<Long> createTime = column("create_time", JDBCType.BIGINT);

        public final SqlColumn<Long> updateTime = column("update_time", JDBCType.BIGINT);

        public final SqlColumn<String> createUserId = column("create_user_id", JDBCType.VARCHAR);

        public final SqlColumn<String> updateUserId = column("update_user_id", JDBCType.VARCHAR);

        public OrderForm() {
            super("tbl_order_form");
        }
    }
}