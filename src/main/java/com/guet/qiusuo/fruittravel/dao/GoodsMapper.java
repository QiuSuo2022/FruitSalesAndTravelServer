package com.guet.qiusuo.fruittravel.dao;

import static com.guet.qiusuo.fruittravel.dao.GoodsDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.*;

import com.guet.qiusuo.fruittravel.model.Goods;
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
public interface GoodsMapper {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-05-01T21:35:44.2619386+08:00", comments="Source Table: tbl_goods")
    BasicColumn[] selectList = BasicColumn.columnList(id, orderId, name, price, fruitId, scenicId, amount, payStatus, status, createTime, updateTime, createUserId, updateUserId);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-05-01T21:35:44.25396+08:00", comments="Source Table: tbl_goods")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    long count(SelectStatementProvider selectStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-05-01T21:35:44.2549579+08:00", comments="Source Table: tbl_goods")
    @DeleteProvider(type=SqlProviderAdapter.class, method="delete")
    int delete(DeleteStatementProvider deleteStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-05-01T21:35:44.2549579+08:00", comments="Source Table: tbl_goods")
    @InsertProvider(type=SqlProviderAdapter.class, method="insert")
    int insert(InsertStatementProvider<Goods> insertStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-05-01T21:35:44.2559548+08:00", comments="Source Table: tbl_goods")
    @InsertProvider(type=SqlProviderAdapter.class, method="insertMultiple")
    int insertMultiple(MultiRowInsertStatementProvider<Goods> multipleInsertStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-05-01T21:35:44.2559548+08:00", comments="Source Table: tbl_goods")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("GoodsResult")
    Optional<Goods> selectOne(SelectStatementProvider selectStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-05-01T21:35:44.2569515+08:00", comments="Source Table: tbl_goods")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="GoodsResult", value = {
        @Result(column="id", property="id", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="order_id", property="orderId", jdbcType=JdbcType.VARCHAR),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="price", property="price", jdbcType=JdbcType.INTEGER),
        @Result(column="fruit_id", property="fruitId", jdbcType=JdbcType.VARCHAR),
        @Result(column="scenic_id", property="scenicId", jdbcType=JdbcType.VARCHAR),
        @Result(column="amount", property="amount", jdbcType=JdbcType.INTEGER),
        @Result(column="pay_status", property="payStatus", jdbcType=JdbcType.SMALLINT),
        @Result(column="STATUS", property="status", jdbcType=JdbcType.SMALLINT),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.BIGINT),
        @Result(column="update_time", property="updateTime", jdbcType=JdbcType.BIGINT),
        @Result(column="create_user_id", property="createUserId", jdbcType=JdbcType.VARCHAR),
        @Result(column="update_user_id", property="updateUserId", jdbcType=JdbcType.VARCHAR)
    })
    List<Goods> selectMany(SelectStatementProvider selectStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-05-01T21:35:44.2569515+08:00", comments="Source Table: tbl_goods")
    @UpdateProvider(type=SqlProviderAdapter.class, method="update")
    int update(UpdateStatementProvider updateStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-05-01T21:35:44.257949+08:00", comments="Source Table: tbl_goods")
    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, goods, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-05-01T21:35:44.257949+08:00", comments="Source Table: tbl_goods")
    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, goods, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-05-01T21:35:44.257949+08:00", comments="Source Table: tbl_goods")
    default int deleteByPrimaryKey(String id_) {
        return delete(c -> 
            c.where(id, isEqualTo(id_))
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-05-01T21:35:44.2589463+08:00", comments="Source Table: tbl_goods")
    default int insert(Goods record) {
        return MyBatis3Utils.insert(this::insert, record, goods, c ->
            c.map(id).toProperty("id")
            .map(orderId).toProperty("orderId")
            .map(name).toProperty("name")
            .map(price).toProperty("price")
            .map(fruitId).toProperty("fruitId")
            .map(scenicId).toProperty("scenicId")
            .map(amount).toProperty("amount")
            .map(payStatus).toProperty("payStatus")
            .map(status).toProperty("status")
            .map(createTime).toProperty("createTime")
            .map(updateTime).toProperty("updateTime")
            .map(createUserId).toProperty("createUserId")
            .map(updateUserId).toProperty("updateUserId")
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-05-01T21:35:44.259953+08:00", comments="Source Table: tbl_goods")
    default int insertMultiple(Collection<Goods> records) {
        return MyBatis3Utils.insertMultiple(this::insertMultiple, records, goods, c ->
            c.map(id).toProperty("id")
            .map(orderId).toProperty("orderId")
            .map(name).toProperty("name")
            .map(price).toProperty("price")
            .map(fruitId).toProperty("fruitId")
            .map(scenicId).toProperty("scenicId")
            .map(amount).toProperty("amount")
            .map(payStatus).toProperty("payStatus")
            .map(status).toProperty("status")
            .map(createTime).toProperty("createTime")
            .map(updateTime).toProperty("updateTime")
            .map(createUserId).toProperty("createUserId")
            .map(updateUserId).toProperty("updateUserId")
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-05-01T21:35:44.2609411+08:00", comments="Source Table: tbl_goods")
    default int insertSelective(Goods record) {
        return MyBatis3Utils.insert(this::insert, record, goods, c ->
            c.map(id).toPropertyWhenPresent("id", record::getId)
            .map(orderId).toPropertyWhenPresent("orderId", record::getOrderId)
            .map(name).toPropertyWhenPresent("name", record::getName)
            .map(price).toPropertyWhenPresent("price", record::getPrice)
            .map(fruitId).toPropertyWhenPresent("fruitId", record::getFruitId)
            .map(scenicId).toPropertyWhenPresent("scenicId", record::getScenicId)
            .map(amount).toPropertyWhenPresent("amount", record::getAmount)
            .map(payStatus).toPropertyWhenPresent("payStatus", record::getPayStatus)
            .map(status).toPropertyWhenPresent("status", record::getStatus)
            .map(createTime).toPropertyWhenPresent("createTime", record::getCreateTime)
            .map(updateTime).toPropertyWhenPresent("updateTime", record::getUpdateTime)
            .map(createUserId).toPropertyWhenPresent("createUserId", record::getCreateUserId)
            .map(updateUserId).toPropertyWhenPresent("updateUserId", record::getUpdateUserId)
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-05-01T21:35:44.2629371+08:00", comments="Source Table: tbl_goods")
    default Optional<Goods> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, goods, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-05-01T21:35:44.2629371+08:00", comments="Source Table: tbl_goods")
    default List<Goods> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, goods, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-05-01T21:35:44.2629371+08:00", comments="Source Table: tbl_goods")
    default List<Goods> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, goods, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-05-01T21:35:44.2639336+08:00", comments="Source Table: tbl_goods")
    default Optional<Goods> selectByPrimaryKey(String id_) {
        return selectOne(c ->
            c.where(id, isEqualTo(id_))
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-05-01T21:35:44.2639336+08:00", comments="Source Table: tbl_goods")
    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, goods, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-05-01T21:35:44.2639336+08:00", comments="Source Table: tbl_goods")
    static UpdateDSL<UpdateModel> updateAllColumns(Goods record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(id).equalTo(record::getId)
                .set(orderId).equalTo(record::getOrderId)
                .set(name).equalTo(record::getName)
                .set(price).equalTo(record::getPrice)
                .set(fruitId).equalTo(record::getFruitId)
                .set(scenicId).equalTo(record::getScenicId)
                .set(amount).equalTo(record::getAmount)
                .set(payStatus).equalTo(record::getPayStatus)
                .set(status).equalTo(record::getStatus)
                .set(createTime).equalTo(record::getCreateTime)
                .set(updateTime).equalTo(record::getUpdateTime)
                .set(createUserId).equalTo(record::getCreateUserId)
                .set(updateUserId).equalTo(record::getUpdateUserId);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-05-01T21:35:44.2649308+08:00", comments="Source Table: tbl_goods")
    static UpdateDSL<UpdateModel> updateSelectiveColumns(Goods record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(id).equalToWhenPresent(record::getId)
                .set(orderId).equalToWhenPresent(record::getOrderId)
                .set(name).equalToWhenPresent(record::getName)
                .set(price).equalToWhenPresent(record::getPrice)
                .set(fruitId).equalToWhenPresent(record::getFruitId)
                .set(scenicId).equalToWhenPresent(record::getScenicId)
                .set(amount).equalToWhenPresent(record::getAmount)
                .set(payStatus).equalToWhenPresent(record::getPayStatus)
                .set(status).equalToWhenPresent(record::getStatus)
                .set(createTime).equalToWhenPresent(record::getCreateTime)
                .set(updateTime).equalToWhenPresent(record::getUpdateTime)
                .set(createUserId).equalToWhenPresent(record::getCreateUserId)
                .set(updateUserId).equalToWhenPresent(record::getUpdateUserId);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-05-01T21:35:44.2649308+08:00", comments="Source Table: tbl_goods")
    default int updateByPrimaryKey(Goods record) {
        return update(c ->
            c.set(orderId).equalTo(record::getOrderId)
            .set(name).equalTo(record::getName)
            .set(price).equalTo(record::getPrice)
            .set(fruitId).equalTo(record::getFruitId)
            .set(scenicId).equalTo(record::getScenicId)
            .set(amount).equalTo(record::getAmount)
            .set(payStatus).equalTo(record::getPayStatus)
            .set(status).equalTo(record::getStatus)
            .set(createTime).equalTo(record::getCreateTime)
            .set(updateTime).equalTo(record::getUpdateTime)
            .set(createUserId).equalTo(record::getCreateUserId)
            .set(updateUserId).equalTo(record::getUpdateUserId)
            .where(id, isEqualTo(record::getId))
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2022-05-01T21:35:44.2659281+08:00", comments="Source Table: tbl_goods")
    default int updateByPrimaryKeySelective(Goods record) {
        return update(c ->
            c.set(orderId).equalToWhenPresent(record::getOrderId)
            .set(name).equalToWhenPresent(record::getName)
            .set(price).equalToWhenPresent(record::getPrice)
            .set(fruitId).equalToWhenPresent(record::getFruitId)
            .set(scenicId).equalToWhenPresent(record::getScenicId)
            .set(amount).equalToWhenPresent(record::getAmount)
            .set(payStatus).equalToWhenPresent(record::getPayStatus)
            .set(status).equalToWhenPresent(record::getStatus)
            .set(createTime).equalToWhenPresent(record::getCreateTime)
            .set(updateTime).equalToWhenPresent(record::getUpdateTime)
            .set(createUserId).equalToWhenPresent(record::getCreateUserId)
            .set(updateUserId).equalToWhenPresent(record::getUpdateUserId)
            .where(id, isEqualTo(record::getId))
        );
    }
}