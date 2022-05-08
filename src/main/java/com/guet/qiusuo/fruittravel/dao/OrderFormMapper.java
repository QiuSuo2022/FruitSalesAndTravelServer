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

@Mapper
public interface OrderFormMapper {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-04-30T10:36:01.5699936+08:00", comments="Source Table: tbl_order_form")
    BasicColumn[] selectList = BasicColumn.columnList(id, address, express, fee, payStatus, hasEvaluate, bindEvaluateId, status, payTime, createTime, updateTime, createUserId, updateUserId);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-04-30T10:36:01.56401+08:00", comments="Source Table: tbl_order_form")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    long count(SelectStatementProvider selectStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-04-30T10:36:01.5650076+08:00", comments="Source Table: tbl_order_form")
    @DeleteProvider(type=SqlProviderAdapter.class, method="delete")
    int delete(DeleteStatementProvider deleteStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-04-30T10:36:01.5650076+08:00", comments="Source Table: tbl_order_form")
    @InsertProvider(type=SqlProviderAdapter.class, method="insert")
    int insert(InsertStatementProvider<OrderForm> insertStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-04-30T10:36:01.5660044+08:00", comments="Source Table: tbl_order_form")
    @InsertProvider(type=SqlProviderAdapter.class, method="insertMultiple")
    int insertMultiple(MultiRowInsertStatementProvider<OrderForm> multipleInsertStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-04-30T10:36:01.5660044+08:00", comments="Source Table: tbl_order_form")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("OrderFormResult")
    Optional<OrderForm> selectOne(SelectStatementProvider selectStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-04-30T10:36:01.5660044+08:00", comments="Source Table: tbl_order_form")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="OrderFormResult", value = {
        @Result(column="id", property="id", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="address", property="address", jdbcType=JdbcType.VARCHAR),
        @Result(column="express", property="express", jdbcType=JdbcType.VARCHAR),
        @Result(column="fee", property="fee", jdbcType=JdbcType.INTEGER),
        @Result(column="pay_status", property="payStatus", jdbcType=JdbcType.SMALLINT),
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

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-04-30T10:36:01.5670016+08:00", comments="Source Table: tbl_order_form")
    @UpdateProvider(type=SqlProviderAdapter.class, method="update")
    int update(UpdateStatementProvider updateStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-04-30T10:36:01.5670016+08:00", comments="Source Table: tbl_order_form")
    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, orderForm, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-04-30T10:36:01.5670016+08:00", comments="Source Table: tbl_order_form")
    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, orderForm, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-04-30T10:36:01.5679991+08:00", comments="Source Table: tbl_order_form")
    default int deleteByPrimaryKey(String id_) {
        return delete(c -> 
            c.where(id, isEqualTo(id_))
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-04-30T10:36:01.5679991+08:00", comments="Source Table: tbl_order_form")
    default int insert(OrderForm record) {
        return MyBatis3Utils.insert(this::insert, record, orderForm, c ->
            c.map(id).toProperty("id")
            .map(address).toProperty("address")
            .map(express).toProperty("express")
            .map(fee).toProperty("fee")
            .map(payStatus).toProperty("payStatus")
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

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-04-30T10:36:01.5689964+08:00", comments="Source Table: tbl_order_form")
    default int insertMultiple(Collection<OrderForm> records) {
        return MyBatis3Utils.insertMultiple(this::insertMultiple, records, orderForm, c ->
            c.map(id).toProperty("id")
            .map(address).toProperty("address")
            .map(express).toProperty("express")
            .map(fee).toProperty("fee")
            .map(payStatus).toProperty("payStatus")
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

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-04-30T10:36:01.5689964+08:00", comments="Source Table: tbl_order_form")
    default int insertSelective(OrderForm record) {
        return MyBatis3Utils.insert(this::insert, record, orderForm, c ->
            c.map(id).toPropertyWhenPresent("id", record::getId)
            .map(address).toPropertyWhenPresent("address", record::getAddress)
            .map(express).toPropertyWhenPresent("express", record::getExpress)
            .map(fee).toPropertyWhenPresent("fee", record::getFee)
            .map(payStatus).toPropertyWhenPresent("payStatus", record::getPayStatus)
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

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-04-30T10:36:01.5699936+08:00", comments="Source Table: tbl_order_form")
    default Optional<OrderForm> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, orderForm, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-04-30T10:36:01.5709911+08:00", comments="Source Table: tbl_order_form")
    default List<OrderForm> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, orderForm, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-04-30T10:36:01.5709911+08:00", comments="Source Table: tbl_order_form")
    default List<OrderForm> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, orderForm, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-04-30T10:36:01.5709911+08:00", comments="Source Table: tbl_order_form")
    default Optional<OrderForm> selectByPrimaryKey(String id_) {
        return selectOne(c ->
            c.where(id, isEqualTo(id_))
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-04-30T10:36:01.5709911+08:00", comments="Source Table: tbl_order_form")
    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, orderForm, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-04-30T10:36:01.5719883+08:00", comments="Source Table: tbl_order_form")
    static UpdateDSL<UpdateModel> updateAllColumns(OrderForm record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(id).equalTo(record::getId)
                .set(address).equalTo(record::getAddress)
                .set(express).equalTo(record::getExpress)
                .set(fee).equalTo(record::getFee)
                .set(payStatus).equalTo(record::getPayStatus)
                .set(hasEvaluate).equalTo(record::getHasEvaluate)
                .set(bindEvaluateId).equalTo(record::getBindEvaluateId)
                .set(status).equalTo(record::getStatus)
                .set(payTime).equalTo(record::getPayTime)
                .set(createTime).equalTo(record::getCreateTime)
                .set(updateTime).equalTo(record::getUpdateTime)
                .set(createUserId).equalTo(record::getCreateUserId)
                .set(updateUserId).equalTo(record::getUpdateUserId);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-04-30T10:36:01.5719883+08:00", comments="Source Table: tbl_order_form")
    static UpdateDSL<UpdateModel> updateSelectiveColumns(OrderForm record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(id).equalToWhenPresent(record::getId)
                .set(address).equalToWhenPresent(record::getAddress)
                .set(express).equalToWhenPresent(record::getExpress)
                .set(fee).equalToWhenPresent(record::getFee)
                .set(payStatus).equalToWhenPresent(record::getPayStatus)
                .set(hasEvaluate).equalToWhenPresent(record::getHasEvaluate)
                .set(bindEvaluateId).equalToWhenPresent(record::getBindEvaluateId)
                .set(status).equalToWhenPresent(record::getStatus)
                .set(payTime).equalToWhenPresent(record::getPayTime)
                .set(createTime).equalToWhenPresent(record::getCreateTime)
                .set(updateTime).equalToWhenPresent(record::getUpdateTime)
                .set(createUserId).equalToWhenPresent(record::getCreateUserId)
                .set(updateUserId).equalToWhenPresent(record::getUpdateUserId);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-04-30T10:36:01.5719883+08:00", comments="Source Table: tbl_order_form")
    default int updateByPrimaryKey(OrderForm record) {
        return update(c ->
            c.set(address).equalTo(record::getAddress)
            .set(express).equalTo(record::getExpress)
            .set(fee).equalTo(record::getFee)
            .set(payStatus).equalTo(record::getPayStatus)
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

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-04-30T10:36:01.5729858+08:00", comments="Source Table: tbl_order_form")
    default int updateByPrimaryKeySelective(OrderForm record) {
        return update(c ->
            c.set(address).equalToWhenPresent(record::getAddress)
            .set(express).equalToWhenPresent(record::getExpress)
            .set(fee).equalToWhenPresent(record::getFee)
            .set(payStatus).equalToWhenPresent(record::getPayStatus)
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