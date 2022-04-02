package com.guet.qiusuo.fruittravel.dao;

import static com.guet.qiusuo.fruittravel.dao.OrderFormDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.*;

import com.guet.qiusuo.fruittravel.model.OrderForm;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import javax.annotation.Generated;
import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.type.JdbcType;
import org.mybatis.dynamic.sql.BasicColumn;
import org.mybatis.dynamic.sql.delete.DeleteDSLCompleter;
import org.mybatis.dynamic.sql.delete.render.DeleteStatementProvider;
import org.mybatis.dynamic.sql.insert.render.InsertStatementProvider;
import org.mybatis.dynamic.sql.insert.render.MultiRowInsertStatementProvider;
import org.mybatis.dynamic.sql.select.CountDSLCompleter;
import org.mybatis.dynamic.sql.select.SelectDSLCompleter;
import org.mybatis.dynamic.sql.select.render.SelectStatementProvider;
import org.mybatis.dynamic.sql.update.UpdateDSL;
import org.mybatis.dynamic.sql.update.UpdateDSLCompleter;
import org.mybatis.dynamic.sql.update.UpdateModel;
import org.mybatis.dynamic.sql.update.render.UpdateStatementProvider;
import org.mybatis.dynamic.sql.util.SqlProviderAdapter;
import org.mybatis.dynamic.sql.util.mybatis3.MyBatis3Utils;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface OrderFormMapper {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-03-29T14:31:39.3337702+08:00", comments="Source Table: tbl_order_form")
    BasicColumn[] selectList = BasicColumn.columnList(id, userId, address, express, fruitId, scenicId, amount, payment, orderFormStatus, hasEvaluate, bindEvaluateId, status, payTime, createTime, updateTime, createUserId, updateUserId);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-03-29T14:31:39.3267693+08:00", comments="Source Table: tbl_order_form")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    Long count(SelectStatementProvider selectStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-03-29T14:31:39.3277693+08:00", comments="Source Table: tbl_order_form")
    @DeleteProvider(type=SqlProviderAdapter.class, method="delete")
    int delete(DeleteStatementProvider deleteStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-03-29T14:31:39.3287708+08:00", comments="Source Table: tbl_order_form")
    @InsertProvider(type=SqlProviderAdapter.class, method="insert")
    int insert(InsertStatementProvider<OrderForm> insertStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-03-29T14:31:39.3287708+08:00", comments="Source Table: tbl_order_form")
    @InsertProvider(type=SqlProviderAdapter.class, method="insertMultiple")
    int insertMultiple(MultiRowInsertStatementProvider<OrderForm> multipleInsertStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-03-29T14:31:39.3297702+08:00", comments="Source Table: tbl_order_form")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("OrderFormResult")
    Optional<OrderForm> selectOne(SelectStatementProvider selectStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-03-29T14:31:39.3297702+08:00", comments="Source Table: tbl_order_form")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="OrderFormResult", value = {
        @Result(column="id", property="id", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="user_id", property="userId", jdbcType=JdbcType.VARCHAR),
        @Result(column="address", property="address", jdbcType=JdbcType.VARCHAR),
        @Result(column="express", property="express", jdbcType=JdbcType.VARCHAR),
        @Result(column="fruit_id", property="fruitId", jdbcType=JdbcType.VARCHAR),
        @Result(column="scenic_id", property="scenicId", jdbcType=JdbcType.VARCHAR),
        @Result(column="amount", property="amount", jdbcType=JdbcType.INTEGER),
        @Result(column="payment", property="payment", jdbcType=JdbcType.DOUBLE),
        @Result(column="order_form_status", property="orderFormStatus", jdbcType=JdbcType.SMALLINT),
        @Result(column="has_evaluate", property="hasEvaluate", jdbcType=JdbcType.SMALLINT),
        @Result(column="bind_evaluate_id", property="bindEvaluateId", jdbcType=JdbcType.VARCHAR),
        @Result(column="STATUS", property="status", jdbcType=JdbcType.SMALLINT),
        @Result(column="pay_time", property="payTime", jdbcType=JdbcType.BIGINT),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.BIGINT),
        @Result(column="update_time", property="updateTime", jdbcType=JdbcType.BIGINT),
        @Result(column="create_user_id", property="createUserId", jdbcType=JdbcType.VARCHAR),
        @Result(column="update_user_id", property="updateUserId", jdbcType=JdbcType.VARCHAR)
    })
    List<OrderForm> selectMany(SelectStatementProvider selectStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-03-29T14:31:39.3307693+08:00", comments="Source Table: tbl_order_form")
    @UpdateProvider(type=SqlProviderAdapter.class, method="update")
    int update(UpdateStatementProvider updateStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-03-29T14:31:39.3307693+08:00", comments="Source Table: tbl_order_form")
    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, orderForm, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-03-29T14:31:39.3307693+08:00", comments="Source Table: tbl_order_form")
    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, orderForm, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-03-29T14:31:39.3317702+08:00", comments="Source Table: tbl_order_form")
    default int deleteByPrimaryKey(String id_) {
        return delete(c -> 
            c.where(id, isEqualTo(id_))
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-03-29T14:31:39.3317702+08:00", comments="Source Table: tbl_order_form")
    default int insert(OrderForm record) {
        return MyBatis3Utils.insert(this::insert, record, orderForm, c ->
            c.map(id).toProperty("id")
            .map(userId).toProperty("userId")
            .map(address).toProperty("address")
            .map(express).toProperty("express")
            .map(fruitId).toProperty("fruitId")
            .map(scenicId).toProperty("scenicId")
            .map(amount).toProperty("amount")
            .map(payment).toProperty("payment")
            .map(orderFormStatus).toProperty("orderFormStatus")
            .map(hasEvaluate).toProperty("hasEvaluate")
            .map(bindEvaluateId).toProperty("bindEvaluateId")
            .map(status).toProperty("status")
            .map(payTime).toProperty("payTime")
            .map(createTime).toProperty("createTime")
            .map(updateTime).toProperty("updateTime")
            .map(createUserId).toProperty("createUserId")
            .map(updateUserId).toProperty("updateUserId")
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-03-29T14:31:39.3327701+08:00", comments="Source Table: tbl_order_form")
    default int insertMultiple(Collection<OrderForm> records) {
        return MyBatis3Utils.insertMultiple(this::insertMultiple, records, orderForm, c ->
            c.map(id).toProperty("id")
            .map(userId).toProperty("userId")
            .map(address).toProperty("address")
            .map(express).toProperty("express")
            .map(fruitId).toProperty("fruitId")
            .map(scenicId).toProperty("scenicId")
            .map(amount).toProperty("amount")
            .map(payment).toProperty("payment")
            .map(orderFormStatus).toProperty("orderFormStatus")
            .map(hasEvaluate).toProperty("hasEvaluate")
            .map(bindEvaluateId).toProperty("bindEvaluateId")
            .map(status).toProperty("status")
            .map(payTime).toProperty("payTime")
            .map(createTime).toProperty("createTime")
            .map(updateTime).toProperty("updateTime")
            .map(createUserId).toProperty("createUserId")
            .map(updateUserId).toProperty("updateUserId")
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-03-29T14:31:39.3337702+08:00", comments="Source Table: tbl_order_form")
    default int insertSelective(OrderForm record) {
        return MyBatis3Utils.insert(this::insert, record, orderForm, c ->
            c.map(id).toPropertyWhenPresent("id", record::getId)
            .map(userId).toPropertyWhenPresent("userId", record::getUserId)
            .map(address).toPropertyWhenPresent("address", record::getAddress)
            .map(express).toPropertyWhenPresent("express", record::getExpress)
            .map(fruitId).toPropertyWhenPresent("fruitId", record::getFruitId)
            .map(scenicId).toPropertyWhenPresent("scenicId", record::getScenicId)
            .map(amount).toPropertyWhenPresent("amount", record::getAmount)
            .map(payment).toPropertyWhenPresent("payment", record::getPayment)
            .map(orderFormStatus).toPropertyWhenPresent("orderFormStatus", record::getOrderFormStatus)
            .map(hasEvaluate).toPropertyWhenPresent("hasEvaluate", record::getHasEvaluate)
            .map(bindEvaluateId).toPropertyWhenPresent("bindEvaluateId", record::getBindEvaluateId)
            .map(status).toPropertyWhenPresent("status", record::getStatus)
            .map(payTime).toPropertyWhenPresent("payTime", record::getPayTime)
            .map(createTime).toPropertyWhenPresent("createTime", record::getCreateTime)
            .map(updateTime).toPropertyWhenPresent("updateTime", record::getUpdateTime)
            .map(createUserId).toPropertyWhenPresent("createUserId", record::getCreateUserId)
            .map(updateUserId).toPropertyWhenPresent("updateUserId", record::getUpdateUserId)
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-03-29T14:31:39.3347701+08:00", comments="Source Table: tbl_order_form")
    default Optional<OrderForm> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, orderForm, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-03-29T14:31:39.3347701+08:00", comments="Source Table: tbl_order_form")
    default List<OrderForm> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, orderForm, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-03-29T14:31:39.3357698+08:00", comments="Source Table: tbl_order_form")
    default List<OrderForm> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, orderForm, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-03-29T14:31:39.3357698+08:00", comments="Source Table: tbl_order_form")
    default Optional<OrderForm> selectByPrimaryKey(String id_) {
        return selectOne(c ->
            c.where(id, isEqualTo(id_))
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-03-29T14:31:39.3357698+08:00", comments="Source Table: tbl_order_form")
    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, orderForm, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-03-29T14:31:39.3357698+08:00", comments="Source Table: tbl_order_form")
    static UpdateDSL<UpdateModel> updateAllColumns(OrderForm record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(id).equalTo(record::getId)
                .set(userId).equalTo(record::getUserId)
                .set(address).equalTo(record::getAddress)
                .set(express).equalTo(record::getExpress)
                .set(fruitId).equalTo(record::getFruitId)
                .set(scenicId).equalTo(record::getScenicId)
                .set(amount).equalTo(record::getAmount)
                .set(payment).equalTo(record::getPayment)
                .set(orderFormStatus).equalTo(record::getOrderFormStatus)
                .set(hasEvaluate).equalTo(record::getHasEvaluate)
                .set(bindEvaluateId).equalTo(record::getBindEvaluateId)
                .set(status).equalTo(record::getStatus)
                .set(payTime).equalTo(record::getPayTime)
                .set(createTime).equalTo(record::getCreateTime)
                .set(updateTime).equalTo(record::getUpdateTime)
                .set(createUserId).equalTo(record::getCreateUserId)
                .set(updateUserId).equalTo(record::getUpdateUserId);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-03-29T14:31:39.3367783+08:00", comments="Source Table: tbl_order_form")
    static UpdateDSL<UpdateModel> updateSelectiveColumns(OrderForm record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(id).equalToWhenPresent(record::getId)
                .set(userId).equalToWhenPresent(record::getUserId)
                .set(address).equalToWhenPresent(record::getAddress)
                .set(express).equalToWhenPresent(record::getExpress)
                .set(fruitId).equalToWhenPresent(record::getFruitId)
                .set(scenicId).equalToWhenPresent(record::getScenicId)
                .set(amount).equalToWhenPresent(record::getAmount)
                .set(payment).equalToWhenPresent(record::getPayment)
                .set(orderFormStatus).equalToWhenPresent(record::getOrderFormStatus)
                .set(hasEvaluate).equalToWhenPresent(record::getHasEvaluate)
                .set(bindEvaluateId).equalToWhenPresent(record::getBindEvaluateId)
                .set(status).equalToWhenPresent(record::getStatus)
                .set(payTime).equalToWhenPresent(record::getPayTime)
                .set(createTime).equalToWhenPresent(record::getCreateTime)
                .set(updateTime).equalToWhenPresent(record::getUpdateTime)
                .set(createUserId).equalToWhenPresent(record::getCreateUserId)
                .set(updateUserId).equalToWhenPresent(record::getUpdateUserId);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-03-29T14:31:39.3377701+08:00", comments="Source Table: tbl_order_form")
    default int updateByPrimaryKey(OrderForm record) {
        return update(c ->
            c.set(userId).equalTo(record::getUserId)
            .set(address).equalTo(record::getAddress)
            .set(express).equalTo(record::getExpress)
            .set(fruitId).equalTo(record::getFruitId)
            .set(scenicId).equalTo(record::getScenicId)
            .set(amount).equalTo(record::getAmount)
            .set(payment).equalTo(record::getPayment)
            .set(orderFormStatus).equalTo(record::getOrderFormStatus)
            .set(hasEvaluate).equalTo(record::getHasEvaluate)
            .set(bindEvaluateId).equalTo(record::getBindEvaluateId)
            .set(status).equalTo(record::getStatus)
            .set(payTime).equalTo(record::getPayTime)
            .set(createTime).equalTo(record::getCreateTime)
            .set(updateTime).equalTo(record::getUpdateTime)
            .set(createUserId).equalTo(record::getCreateUserId)
            .set(updateUserId).equalTo(record::getUpdateUserId)
            .where(id, isEqualTo(record::getId))
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-03-29T14:31:39.3377701+08:00", comments="Source Table: tbl_order_form")
    default int updateByPrimaryKeySelective(OrderForm record) {
        return update(c ->
            c.set(userId).equalToWhenPresent(record::getUserId)
            .set(address).equalToWhenPresent(record::getAddress)
            .set(express).equalToWhenPresent(record::getExpress)
            .set(fruitId).equalToWhenPresent(record::getFruitId)
            .set(scenicId).equalToWhenPresent(record::getScenicId)
            .set(amount).equalToWhenPresent(record::getAmount)
            .set(payment).equalToWhenPresent(record::getPayment)
            .set(orderFormStatus).equalToWhenPresent(record::getOrderFormStatus)
            .set(hasEvaluate).equalToWhenPresent(record::getHasEvaluate)
            .set(bindEvaluateId).equalToWhenPresent(record::getBindEvaluateId)
            .set(status).equalToWhenPresent(record::getStatus)
            .set(payTime).equalToWhenPresent(record::getPayTime)
            .set(createTime).equalToWhenPresent(record::getCreateTime)
            .set(updateTime).equalToWhenPresent(record::getUpdateTime)
            .set(createUserId).equalToWhenPresent(record::getCreateUserId)
            .set(updateUserId).equalToWhenPresent(record::getUpdateUserId)
            .where(id, isEqualTo(record::getId))
        );
    }
}