package com.guet.qiusuo.fruittravel.dao;

import java.sql.JDBCType;
import javax.annotation.Generated;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class EvaluateDynamicSqlSupport {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-03-27T22:41:24.7555181+08:00", comments="Source Table: tbl_evaluate")
    public static final Evaluate evaluate = new Evaluate();

    /**
     * Database Column Remarks:
     *   uuid
     */
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-03-27T22:41:24.7565178+08:00", comments="Source field: tbl_evaluate.id")
    public static final SqlColumn<String> id = evaluate.id;

    /**
     * Database Column Remarks:
     *   用户id
     */
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-03-27T22:41:24.7575195+08:00", comments="Source field: tbl_evaluate.user_id")
    public static final SqlColumn<String> userId = evaluate.userId;

    /**
     * Database Column Remarks:
     *   水果商品子项id
     */
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-03-27T22:41:24.7575195+08:00", comments="Source field: tbl_evaluate.product_id")
    public static final SqlColumn<String> productId = evaluate.productId;

    /**
     * Database Column Remarks:
     *   评价id
     */
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-03-27T22:41:24.7575195+08:00", comments="Source field: tbl_evaluate.evaluate_id")
    public static final SqlColumn<String> evaluateId = evaluate.evaluateId;

    /**
     * Database Column Remarks:
     *   评价详情
     */
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-03-27T22:41:24.7575195+08:00", comments="Source field: tbl_evaluate.detail")
    public static final SqlColumn<String> detail = evaluate.detail;

    /**
     * Database Column Remarks:
     *   对商品的整体评价, 0-5星评分
     */
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-03-27T22:41:24.7575195+08:00", comments="Source field: tbl_evaluate.grade")
    public static final SqlColumn<Short> grade = evaluate.grade;

    /**
     * Database Column Remarks:
     *   0-评价商品 1-对评价的评论
     */
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-03-27T22:41:24.7575195+08:00", comments="Source field: tbl_evaluate.type")
    public static final SqlColumn<Short> type = evaluate.type;

    /**
     * Database Column Remarks:
     *   状态, 0: 无效 1: 有效
     */
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-03-27T22:41:24.7575195+08:00", comments="Source field: tbl_evaluate.status")
    public static final SqlColumn<Short> status = evaluate.status;

    /**
     * Database Column Remarks:
     *   创建时间
     */
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-03-27T22:41:24.7575195+08:00", comments="Source field: tbl_evaluate.create_time")
    public static final SqlColumn<Long> createTime = evaluate.createTime;

    /**
     * Database Column Remarks:
     *   最后更新时间
     */
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-03-27T22:41:24.7575195+08:00", comments="Source field: tbl_evaluate.update_time")
    public static final SqlColumn<Long> updateTime = evaluate.updateTime;

    /**
     * Database Column Remarks:
     *   增加此条数据的用户id
     */
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-03-27T22:41:24.7585285+08:00", comments="Source field: tbl_evaluate.create_user_id")
    public static final SqlColumn<String> createUserId = evaluate.createUserId;

    /**
     * Database Column Remarks:
     *   最后更新此条数据的用户id
     */
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-03-27T22:41:24.7585285+08:00", comments="Source field: tbl_evaluate.update_user_id")
    public static final SqlColumn<String> updateUserId = evaluate.updateUserId;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-03-27T22:41:24.7565178+08:00", comments="Source Table: tbl_evaluate")
    public static final class Evaluate extends SqlTable {
        public final SqlColumn<String> id = column("id", JDBCType.VARCHAR);

        public final SqlColumn<String> userId = column("user_id", JDBCType.VARCHAR);

        public final SqlColumn<String> productId = column("product_id", JDBCType.VARCHAR);

        public final SqlColumn<String> evaluateId = column("evaluate_id", JDBCType.VARCHAR);

        public final SqlColumn<String> detail = column("detail", JDBCType.VARCHAR);

        public final SqlColumn<Short> grade = column("grade", JDBCType.SMALLINT);

        public final SqlColumn<Short> type = column("type", JDBCType.SMALLINT);

        public final SqlColumn<Short> status = column("status", JDBCType.SMALLINT);

        public final SqlColumn<Long> createTime = column("create_time", JDBCType.BIGINT);

        public final SqlColumn<Long> updateTime = column("update_time", JDBCType.BIGINT);

        public final SqlColumn<String> createUserId = column("create_user_id", JDBCType.VARCHAR);

        public final SqlColumn<String> updateUserId = column("update_user_id", JDBCType.VARCHAR);

        public Evaluate() {
            super("tbl_evaluate");
        }
    }
}