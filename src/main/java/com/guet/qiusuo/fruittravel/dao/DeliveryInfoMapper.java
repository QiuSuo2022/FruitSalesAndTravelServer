package com.guet.qiusuo.fruittravel.dao;

import static com.guet.qiusuo.fruittravel.dao.DeliveryInfoDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.*;

import com.guet.qiusuo.fruittravel.model.DeliveryInfo;
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

@Mapper
@Repository
public interface DeliveryInfoMapper {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-04-14T19:34:43.271+08:00", comments="Source Table: tbl_deliveryinfo")
    BasicColumn[] selectList = BasicColumn.columnList(id, userId, deliveryName, deliveryPhone, deliveryAddress, status, createTime, updateTime, createUserId, updateUserId);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-04-14T19:34:43.263+08:00", comments="Source Table: tbl_deliveryinfo")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    long count(SelectStatementProvider selectStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-04-14T19:34:43.264+08:00", comments="Source Table: tbl_deliveryinfo")
    @DeleteProvider(type=SqlProviderAdapter.class, method="delete")
    int delete(DeleteStatementProvider deleteStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-04-14T19:34:43.264+08:00", comments="Source Table: tbl_deliveryinfo")
    @InsertProvider(type=SqlProviderAdapter.class, method="insert")
    int insert(InsertStatementProvider<DeliveryInfo> insertStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-04-14T19:34:43.265+08:00", comments="Source Table: tbl_deliveryinfo")
    @InsertProvider(type=SqlProviderAdapter.class, method="insertMultiple")
    int insertMultiple(MultiRowInsertStatementProvider<DeliveryInfo> multipleInsertStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-04-14T19:34:43.265+08:00", comments="Source Table: tbl_deliveryinfo")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("DeliveryInfoResult")
    Optional<DeliveryInfo> selectOne(SelectStatementProvider selectStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-04-14T19:34:43.266+08:00", comments="Source Table: tbl_deliveryinfo")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="DeliveryInfoResult", value = {
        @Result(column="id", property="id", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="user_id", property="userId", jdbcType=JdbcType.VARCHAR),
        @Result(column="delivery_name", property="deliveryName", jdbcType=JdbcType.VARCHAR),
        @Result(column="delivery_phone", property="deliveryPhone", jdbcType=JdbcType.VARCHAR),
        @Result(column="delivery_address", property="deliveryAddress", jdbcType=JdbcType.VARCHAR),
        @Result(column="status", property="status", jdbcType=JdbcType.SMALLINT),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.BIGINT),
        @Result(column="update_time", property="updateTime", jdbcType=JdbcType.BIGINT),
        @Result(column="create_user_id", property="createUserId", jdbcType=JdbcType.VARCHAR),
        @Result(column="update_user_id", property="updateUserId", jdbcType=JdbcType.VARCHAR)
    })
    List<DeliveryInfo> selectMany(SelectStatementProvider selectStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-04-14T19:34:43.267+08:00", comments="Source Table: tbl_deliveryinfo")
    @UpdateProvider(type=SqlProviderAdapter.class, method="update")
    int update(UpdateStatementProvider updateStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-04-14T19:34:43.267+08:00", comments="Source Table: tbl_deliveryinfo")
    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, deliveryInfo, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-04-14T19:34:43.268+08:00", comments="Source Table: tbl_deliveryinfo")
    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, deliveryInfo, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-04-14T19:34:43.268+08:00", comments="Source Table: tbl_deliveryinfo")
    default int deleteByPrimaryKey(String id_) {
        return delete(c -> 
            c.where(id, isEqualTo(id_))
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-04-14T19:34:43.268+08:00", comments="Source Table: tbl_deliveryinfo")
    default int insert(DeliveryInfo record) {
        return MyBatis3Utils.insert(this::insert, record, deliveryInfo, c ->
            c.map(id).toProperty("id")
            .map(userId).toProperty("userId")
            .map(deliveryName).toProperty("deliveryName")
            .map(deliveryPhone).toProperty("deliveryPhone")
            .map(deliveryAddress).toProperty("deliveryAddress")
            .map(status).toProperty("status")
            .map(createTime).toProperty("createTime")
            .map(updateTime).toProperty("updateTime")
            .map(createUserId).toProperty("createUserId")
            .map(updateUserId).toProperty("updateUserId")
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-04-14T19:34:43.27+08:00", comments="Source Table: tbl_deliveryinfo")
    default int insertMultiple(Collection<DeliveryInfo> records) {
        return MyBatis3Utils.insertMultiple(this::insertMultiple, records, deliveryInfo, c ->
            c.map(id).toProperty("id")
            .map(userId).toProperty("userId")
            .map(deliveryName).toProperty("deliveryName")
            .map(deliveryPhone).toProperty("deliveryPhone")
            .map(deliveryAddress).toProperty("deliveryAddress")
            .map(status).toProperty("status")
            .map(createTime).toProperty("createTime")
            .map(updateTime).toProperty("updateTime")
            .map(createUserId).toProperty("createUserId")
            .map(updateUserId).toProperty("updateUserId")
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-04-14T19:34:43.27+08:00", comments="Source Table: tbl_deliveryinfo")
    default int insertSelective(DeliveryInfo record) {
        return MyBatis3Utils.insert(this::insert, record, deliveryInfo, c ->
            c.map(id).toPropertyWhenPresent("id", record::getId)
            .map(userId).toPropertyWhenPresent("userId", record::getUserId)
            .map(deliveryName).toPropertyWhenPresent("deliveryName", record::getDeliveryName)
            .map(deliveryPhone).toPropertyWhenPresent("deliveryPhone", record::getDeliveryPhone)
            .map(deliveryAddress).toPropertyWhenPresent("deliveryAddress", record::getDeliveryAddress)
            .map(status).toPropertyWhenPresent("status", record::getStatus)
            .map(createTime).toPropertyWhenPresent("createTime", record::getCreateTime)
            .map(updateTime).toPropertyWhenPresent("updateTime", record::getUpdateTime)
            .map(createUserId).toPropertyWhenPresent("createUserId", record::getCreateUserId)
            .map(updateUserId).toPropertyWhenPresent("updateUserId", record::getUpdateUserId)
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-04-14T19:34:43.272+08:00", comments="Source Table: tbl_deliveryinfo")
    default Optional<DeliveryInfo> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, deliveryInfo, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-04-14T19:34:43.272+08:00", comments="Source Table: tbl_deliveryinfo")
    default List<DeliveryInfo> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, deliveryInfo, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-04-14T19:34:43.273+08:00", comments="Source Table: tbl_deliveryinfo")
    default List<DeliveryInfo> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, deliveryInfo, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-04-14T19:34:43.273+08:00", comments="Source Table: tbl_deliveryinfo")
    default Optional<DeliveryInfo> selectByPrimaryKey(String id_) {
        return selectOne(c ->
            c.where(id, isEqualTo(id_))
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-04-14T19:34:43.273+08:00", comments="Source Table: tbl_deliveryinfo")
    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, deliveryInfo, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-04-14T19:34:43.273+08:00", comments="Source Table: tbl_deliveryinfo")
    static UpdateDSL<UpdateModel> updateAllColumns(DeliveryInfo record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(id).equalTo(record::getId)
                .set(userId).equalTo(record::getUserId)
                .set(deliveryName).equalTo(record::getDeliveryName)
                .set(deliveryPhone).equalTo(record::getDeliveryPhone)
                .set(deliveryAddress).equalTo(record::getDeliveryAddress)
                .set(status).equalTo(record::getStatus)
                .set(createTime).equalTo(record::getCreateTime)
                .set(updateTime).equalTo(record::getUpdateTime)
                .set(createUserId).equalTo(record::getCreateUserId)
                .set(updateUserId).equalTo(record::getUpdateUserId);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-04-14T19:34:43.274+08:00", comments="Source Table: tbl_deliveryinfo")
    static UpdateDSL<UpdateModel> updateSelectiveColumns(DeliveryInfo record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(id).equalToWhenPresent(record::getId)
                .set(userId).equalToWhenPresent(record::getUserId)
                .set(deliveryName).equalToWhenPresent(record::getDeliveryName)
                .set(deliveryPhone).equalToWhenPresent(record::getDeliveryPhone)
                .set(deliveryAddress).equalToWhenPresent(record::getDeliveryAddress)
                .set(status).equalToWhenPresent(record::getStatus)
                .set(createTime).equalToWhenPresent(record::getCreateTime)
                .set(updateTime).equalToWhenPresent(record::getUpdateTime)
                .set(createUserId).equalToWhenPresent(record::getCreateUserId)
                .set(updateUserId).equalToWhenPresent(record::getUpdateUserId);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-04-14T19:34:43.275+08:00", comments="Source Table: tbl_deliveryinfo")
    default int updateByPrimaryKey(DeliveryInfo record) {
        return update(c ->
            c.set(userId).equalTo(record::getUserId)
            .set(deliveryName).equalTo(record::getDeliveryName)
            .set(deliveryPhone).equalTo(record::getDeliveryPhone)
            .set(deliveryAddress).equalTo(record::getDeliveryAddress)
            .set(status).equalTo(record::getStatus)
            .set(createTime).equalTo(record::getCreateTime)
            .set(updateTime).equalTo(record::getUpdateTime)
            .set(createUserId).equalTo(record::getCreateUserId)
            .set(updateUserId).equalTo(record::getUpdateUserId)
            .where(id, isEqualTo(record::getId))
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-04-14T19:34:43.275+08:00", comments="Source Table: tbl_deliveryinfo")
    default int updateByPrimaryKeySelective(DeliveryInfo record) {
        return update(c ->
            c.set(userId).equalToWhenPresent(record::getUserId)
            .set(deliveryName).equalToWhenPresent(record::getDeliveryName)
            .set(deliveryPhone).equalToWhenPresent(record::getDeliveryPhone)
            .set(deliveryAddress).equalToWhenPresent(record::getDeliveryAddress)
            .set(status).equalToWhenPresent(record::getStatus)
            .set(createTime).equalToWhenPresent(record::getCreateTime)
            .set(updateTime).equalToWhenPresent(record::getUpdateTime)
            .set(createUserId).equalToWhenPresent(record::getCreateUserId)
            .set(updateUserId).equalToWhenPresent(record::getUpdateUserId)
            .where(id, isEqualTo(record::getId))
        );
    }
}