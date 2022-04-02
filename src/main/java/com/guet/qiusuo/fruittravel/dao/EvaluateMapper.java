package com.guet.qiusuo.fruittravel.dao;

import static com.guet.qiusuo.fruittravel.dao.EvaluateDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.*;

import com.guet.qiusuo.fruittravel.model.Evaluate;

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
public interface EvaluateMapper {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-03-27T22:41:24.7685842+08:00", comments="Source Table: tbl_evaluate")
    BasicColumn[] selectList = BasicColumn.columnList(id, userId, productId, evaluateId, detail, grade, type, status, createTime, updateTime, createUserId, updateUserId);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-03-27T22:41:24.7585285+08:00", comments="Source Table: tbl_evaluate")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    Long count(SelectStatementProvider selectStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-03-27T22:41:24.7600556+08:00", comments="Source Table: tbl_evaluate")
    @DeleteProvider(type=SqlProviderAdapter.class, method="delete")
    int delete(DeleteStatementProvider deleteStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-03-27T22:41:24.7600556+08:00", comments="Source Table: tbl_evaluate")
    @InsertProvider(type=SqlProviderAdapter.class, method="insert")
    int insert(InsertStatementProvider<Evaluate> insertStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-03-27T22:41:24.7610496+08:00", comments="Source Table: tbl_evaluate")
    @InsertProvider(type=SqlProviderAdapter.class, method="insertMultiple")
    int insertMultiple(MultiRowInsertStatementProvider<Evaluate> multipleInsertStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-03-27T22:41:24.7610496+08:00", comments="Source Table: tbl_evaluate")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("EvaluateResult")
    Optional<Evaluate> selectOne(SelectStatementProvider selectStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-03-27T22:41:24.762051+08:00", comments="Source Table: tbl_evaluate")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="EvaluateResult", value = {
        @Result(column="id", property="id", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="user_id", property="userId", jdbcType=JdbcType.VARCHAR),
        @Result(column="product_id", property="productId", jdbcType=JdbcType.VARCHAR),
        @Result(column="evaluate_id", property="evaluateId", jdbcType=JdbcType.VARCHAR),
        @Result(column="detail", property="detail", jdbcType=JdbcType.VARCHAR),
        @Result(column="grade", property="grade", jdbcType=JdbcType.SMALLINT),
        @Result(column="type", property="type", jdbcType=JdbcType.SMALLINT),
        @Result(column="status", property="status", jdbcType=JdbcType.SMALLINT),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.BIGINT),
        @Result(column="update_time", property="updateTime", jdbcType=JdbcType.BIGINT),
        @Result(column="create_user_id", property="createUserId", jdbcType=JdbcType.VARCHAR),
        @Result(column="update_user_id", property="updateUserId", jdbcType=JdbcType.VARCHAR)
    })
    List<Evaluate> selectMany(SelectStatementProvider selectStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-03-27T22:41:24.7630514+08:00", comments="Source Table: tbl_evaluate")
    @UpdateProvider(type=SqlProviderAdapter.class, method="update")
    int update(UpdateStatementProvider updateStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-03-27T22:41:24.7640505+08:00", comments="Source Table: tbl_evaluate")
    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, evaluate, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-03-27T22:41:24.7640505+08:00", comments="Source Table: tbl_evaluate")
    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, evaluate, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-03-27T22:41:24.7640505+08:00", comments="Source Table: tbl_evaluate")
    default int deleteByPrimaryKey(String id_) {
        return delete(c -> 
            c.where(id, isEqualTo(id_))
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-03-27T22:41:24.7650751+08:00", comments="Source Table: tbl_evaluate")
    default int insert(Evaluate record) {
        return MyBatis3Utils.insert(this::insert, record, evaluate, c ->
            c.map(id).toProperty("id")
            .map(userId).toProperty("userId")
            .map(productId).toProperty("productId")
            .map(evaluateId).toProperty("evaluateId")
            .map(detail).toProperty("detail")
            .map(grade).toProperty("grade")
            .map(type).toProperty("type")
            .map(status).toProperty("status")
            .map(createTime).toProperty("createTime")
            .map(updateTime).toProperty("updateTime")
            .map(createUserId).toProperty("createUserId")
            .map(updateUserId).toProperty("updateUserId")
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-03-27T22:41:24.7657477+08:00", comments="Source Table: tbl_evaluate")
    default int insertMultiple(Collection<Evaluate> records) {
        return MyBatis3Utils.insertMultiple(this::insertMultiple, records, evaluate, c ->
            c.map(id).toProperty("id")
            .map(userId).toProperty("userId")
            .map(productId).toProperty("productId")
            .map(evaluateId).toProperty("evaluateId")
            .map(detail).toProperty("detail")
            .map(grade).toProperty("grade")
            .map(type).toProperty("type")
            .map(status).toProperty("status")
            .map(createTime).toProperty("createTime")
            .map(updateTime).toProperty("updateTime")
            .map(createUserId).toProperty("createUserId")
            .map(updateUserId).toProperty("updateUserId")
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-03-27T22:41:24.7675842+08:00", comments="Source Table: tbl_evaluate")
    default int insertSelective(Evaluate record) {
        return MyBatis3Utils.insert(this::insert, record, evaluate, c ->
            c.map(id).toPropertyWhenPresent("id", record::getId)
            .map(userId).toPropertyWhenPresent("userId", record::getUserId)
            .map(productId).toPropertyWhenPresent("productId", record::getProductId)
            .map(evaluateId).toPropertyWhenPresent("evaluateId", record::getEvaluateId)
            .map(detail).toPropertyWhenPresent("detail", record::getDetail)
            .map(grade).toPropertyWhenPresent("grade", record::getGrade)
            .map(type).toPropertyWhenPresent("type", record::getType)
            .map(status).toPropertyWhenPresent("status", record::getStatus)
            .map(createTime).toPropertyWhenPresent("createTime", record::getCreateTime)
            .map(updateTime).toPropertyWhenPresent("updateTime", record::getUpdateTime)
            .map(createUserId).toPropertyWhenPresent("createUserId", record::getCreateUserId)
            .map(updateUserId).toPropertyWhenPresent("updateUserId", record::getUpdateUserId)
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-03-27T22:41:24.769584+08:00", comments="Source Table: tbl_evaluate")
    default Optional<Evaluate> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, evaluate, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-03-27T22:41:24.769584+08:00", comments="Source Table: tbl_evaluate")
    default List<Evaluate> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, evaluate, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-03-27T22:41:24.769584+08:00", comments="Source Table: tbl_evaluate")
    default List<Evaluate> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, evaluate, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-03-27T22:41:24.7705939+08:00", comments="Source Table: tbl_evaluate")
    default Optional<Evaluate> selectByPrimaryKey(String id_) {
        return selectOne(c ->
            c.where(id, isEqualTo(id_))
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-03-27T22:41:24.7705939+08:00", comments="Source Table: tbl_evaluate")
    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, evaluate, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-03-27T22:41:24.7705939+08:00", comments="Source Table: tbl_evaluate")
    static UpdateDSL<UpdateModel> updateAllColumns(Evaluate record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(id).equalTo(record::getId)
                .set(userId).equalTo(record::getUserId)
                .set(productId).equalTo(record::getProductId)
                .set(evaluateId).equalTo(record::getEvaluateId)
                .set(detail).equalTo(record::getDetail)
                .set(grade).equalTo(record::getGrade)
                .set(type).equalTo(record::getType)
                .set(status).equalTo(record::getStatus)
                .set(createTime).equalTo(record::getCreateTime)
                .set(updateTime).equalTo(record::getUpdateTime)
                .set(createUserId).equalTo(record::getCreateUserId)
                .set(updateUserId).equalTo(record::getUpdateUserId);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-03-27T22:41:24.7715852+08:00", comments="Source Table: tbl_evaluate")
    static UpdateDSL<UpdateModel> updateSelectiveColumns(Evaluate record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(id).equalToWhenPresent(record::getId)
                .set(userId).equalToWhenPresent(record::getUserId)
                .set(productId).equalToWhenPresent(record::getProductId)
                .set(evaluateId).equalToWhenPresent(record::getEvaluateId)
                .set(detail).equalToWhenPresent(record::getDetail)
                .set(grade).equalToWhenPresent(record::getGrade)
                .set(type).equalToWhenPresent(record::getType)
                .set(status).equalToWhenPresent(record::getStatus)
                .set(createTime).equalToWhenPresent(record::getCreateTime)
                .set(updateTime).equalToWhenPresent(record::getUpdateTime)
                .set(createUserId).equalToWhenPresent(record::getCreateUserId)
                .set(updateUserId).equalToWhenPresent(record::getUpdateUserId);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-03-27T22:41:24.7725852+08:00", comments="Source Table: tbl_evaluate")
    default int updateByPrimaryKey(Evaluate record) {
        return update(c ->
            c.set(userId).equalTo(record::getUserId)
            .set(productId).equalTo(record::getProductId)
            .set(evaluateId).equalTo(record::getEvaluateId)
            .set(detail).equalTo(record::getDetail)
            .set(grade).equalTo(record::getGrade)
            .set(type).equalTo(record::getType)
            .set(status).equalTo(record::getStatus)
            .set(createTime).equalTo(record::getCreateTime)
            .set(updateTime).equalTo(record::getUpdateTime)
            .set(createUserId).equalTo(record::getCreateUserId)
            .set(updateUserId).equalTo(record::getUpdateUserId)
            .where(id, isEqualTo(record::getId))
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-03-27T22:41:24.7725852+08:00", comments="Source Table: tbl_evaluate")
    default int updateByPrimaryKeySelective(Evaluate record) {
        return update(c ->
            c.set(userId).equalToWhenPresent(record::getUserId)
            .set(productId).equalToWhenPresent(record::getProductId)
            .set(evaluateId).equalToWhenPresent(record::getEvaluateId)
            .set(detail).equalToWhenPresent(record::getDetail)
            .set(grade).equalToWhenPresent(record::getGrade)
            .set(type).equalToWhenPresent(record::getType)
            .set(status).equalToWhenPresent(record::getStatus)
            .set(createTime).equalToWhenPresent(record::getCreateTime)
            .set(updateTime).equalToWhenPresent(record::getUpdateTime)
            .set(createUserId).equalToWhenPresent(record::getCreateUserId)
            .set(updateUserId).equalToWhenPresent(record::getUpdateUserId)
            .where(id, isEqualTo(record::getId))
        );
    }
}