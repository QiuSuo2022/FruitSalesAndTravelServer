package com.guet.qiusuo.fruittravel.dao;

import static com.guet.qiusuo.fruittravel.dao.ChildFruitDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.*;

import com.guet.qiusuo.fruittravel.common.SystemConstants;
import com.guet.qiusuo.fruittravel.model.ChildFruit;

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
public interface ChildFruitMapper {
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.223+08:00",
            comments = "Source Table: tbl_child_fruit")
    BasicColumn[] selectList = BasicColumn.columnList(id, fruitId, fruitName, fruitPrice, stock, imageUrl, status,
            createTime, updateTime, createUserId, updateUserId);

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.221+08:00",
            comments = "Source Table: tbl_child_fruit")
    @SelectProvider(type = SqlProviderAdapter.class, method = "select")
    long count(SelectStatementProvider selectStatement);

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.221+08:00",
            comments = "Source Table: tbl_child_fruit")
    @DeleteProvider(type = SqlProviderAdapter.class, method = "delete")
    int delete(DeleteStatementProvider deleteStatement);

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.221+08:00",
            comments = "Source Table: tbl_child_fruit")
    @InsertProvider(type = SqlProviderAdapter.class, method = "insert")
    int insert(InsertStatementProvider<ChildFruit> insertStatement);

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.221+08:00",
            comments = "Source Table: tbl_child_fruit")
    @InsertProvider(type = SqlProviderAdapter.class, method = "insertMultiple")
    int insertMultiple(MultiRowInsertStatementProvider<ChildFruit> multipleInsertStatement);

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.222+08:00",
            comments = "Source Table: tbl_child_fruit")
    @SelectProvider(type = SqlProviderAdapter.class, method = "select")
    @ResultMap("ChildFruitResult")
    Optional<ChildFruit> selectOne(SelectStatementProvider selectStatement);

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.222+08:00",
            comments = "Source Table: tbl_child_fruit")
    @SelectProvider(type = SqlProviderAdapter.class, method = "select")
    @Results(id = "ChildFruitResult", value = {
            @Result(column = "id", property = "id", jdbcType = JdbcType.VARCHAR, id = true),
            @Result(column = "fruit_id", property = "fruitId", jdbcType = JdbcType.VARCHAR),
            @Result(column = "fruit_name", property = "fruitName", jdbcType = JdbcType.VARCHAR),
            @Result(column = "fruit_price", property = "fruitPrice", jdbcType = JdbcType.INTEGER),
            @Result(column = "stock", property = "stock", jdbcType = JdbcType.INTEGER),
            @Result(column = "image_url", property = "imageUrl", jdbcType = JdbcType.VARCHAR),
            @Result(column = "status", property = "status", jdbcType = JdbcType.SMALLINT),
            @Result(column = "create_time", property = "createTime", jdbcType = JdbcType.BIGINT),
            @Result(column = "update_time", property = "updateTime", jdbcType = JdbcType.BIGINT),
            @Result(column = "create_user_id", property = "createUserId", jdbcType = JdbcType.VARCHAR),
            @Result(column = "update_user_id", property = "updateUserId", jdbcType = JdbcType.VARCHAR)
    })
    List<ChildFruit> selectMany(SelectStatementProvider selectStatement);

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.222+08:00",
            comments = "Source Table: tbl_child_fruit")
    @UpdateProvider(type = SqlProviderAdapter.class, method = "update")
    int update(UpdateStatementProvider updateStatement);

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.222+08:00",
            comments = "Source Table: tbl_child_fruit")
    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, childFruit, completer);
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.222+08:00",
            comments = "Source Table: tbl_child_fruit")
    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, childFruit, completer);
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.222+08:00",
            comments = "Source Table: tbl_child_fruit")
    default int deleteByPrimaryKey(String id_) {
        return delete(c ->
                c.where(id, isEqualTo(id_))
        );
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.222+08:00",
            comments = "Source Table: tbl_child_fruit")
    default int insert(ChildFruit record) {
        return MyBatis3Utils.insert(this::insert, record, childFruit, c ->
                c.map(id).toProperty("id")
                        .map(fruitId).toProperty("fruitId")
                        .map(fruitName).toProperty("fruitName")
                        .map(fruitPrice).toProperty("fruitPrice")
                        .map(stock).toProperty("stock")
                        .map(imageUrl).toProperty("imageUrl")
                        .map(status).toProperty("status")
                        .map(createTime).toProperty("createTime")
                        .map(updateTime).toProperty("updateTime")
                        .map(createUserId).toProperty("createUserId")
                        .map(updateUserId).toProperty("updateUserId")
        );
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.222+08:00",
            comments = "Source Table: tbl_child_fruit")
    default int insertMultiple(Collection<ChildFruit> records) {
        return MyBatis3Utils.insertMultiple(this::insertMultiple, records, childFruit, c ->
                c.map(id).toProperty("id")
                        .map(fruitId).toProperty("fruitId")
                        .map(fruitName).toProperty("fruitName")
                        .map(fruitPrice).toProperty("fruitPrice")
                        .map(stock).toProperty("stock")
                        .map(imageUrl).toProperty("imageUrl")
                        .map(status).toProperty("status")
                        .map(createTime).toProperty("createTime")
                        .map(updateTime).toProperty("updateTime")
                        .map(createUserId).toProperty("createUserId")
                        .map(updateUserId).toProperty("updateUserId")
        );
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.222+08:00",
            comments = "Source Table: tbl_child_fruit")
    default int insertSelective(ChildFruit record) {
        return MyBatis3Utils.insert(this::insert, record, childFruit, c ->
                c.map(id).toPropertyWhenPresent("id", record::getId)
                        .map(fruitId).toPropertyWhenPresent("fruitId", record::getFruitId)
                        .map(fruitName).toPropertyWhenPresent("fruitName", record::getFruitName)
                        .map(fruitPrice).toPropertyWhenPresent("fruitPrice", record::getFruitPrice)
                        .map(stock).toPropertyWhenPresent("stock", record::getStock)
                        .map(imageUrl).toPropertyWhenPresent("imageUrl", record::getImageUrl)
                        .map(status).toPropertyWhenPresent("status", record::getStatus)
                        .map(createTime).toPropertyWhenPresent("createTime", record::getCreateTime)
                        .map(updateTime).toPropertyWhenPresent("updateTime", record::getUpdateTime)
                        .map(createUserId).toPropertyWhenPresent("createUserId", record::getCreateUserId)
                        .map(updateUserId).toPropertyWhenPresent("updateUserId", record::getUpdateUserId)
        );
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.223+08:00",
            comments = "Source Table: tbl_child_fruit")
    default Optional<ChildFruit> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, childFruit, completer);
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.223+08:00",
            comments = "Source Table: tbl_child_fruit")
    default List<ChildFruit> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, childFruit, completer);
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.223+08:00",
            comments = "Source Table: tbl_child_fruit")
    default List<ChildFruit> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, childFruit, completer);
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.223+08:00",
            comments = "Source Table: tbl_child_fruit")
    default Optional<ChildFruit> selectByPrimaryKey(String id_) {
        return selectOne(c ->
                c.where(id, isEqualTo(id_))
        );
    }
    //选择status = 1的childFruit
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-02-10T19:20:00.223+08:00",
            comments = "Source Table: tbl_child_fruit")
    default Optional<ChildFruit> selectByFruitId(String fruit_id) {
        return selectOne(c ->
                c.where(fruitId, isEqualTo(fruit_id)).and(status,isEqualTo(SystemConstants.STATUS_ACTIVE))
        );
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.223+08:00",
            comments = "Source Table: tbl_child_fruit")
    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, childFruit, completer);
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.223+08:00",
            comments = "Source Table: tbl_child_fruit")
    static UpdateDSL<UpdateModel> updateAllColumns(ChildFruit record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(id).equalTo(record::getId)
                .set(fruitId).equalTo(record::getFruitId)
                .set(fruitName).equalTo(record::getFruitName)
                .set(fruitPrice).equalTo(record::getFruitPrice)
                .set(stock).equalTo(record::getStock)
                .set(imageUrl).equalTo(record::getImageUrl)
                .set(status).equalTo(record::getStatus)
                .set(createTime).equalTo(record::getCreateTime)
                .set(updateTime).equalTo(record::getUpdateTime)
                .set(createUserId).equalTo(record::getCreateUserId)
                .set(updateUserId).equalTo(record::getUpdateUserId);
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.223+08:00",
            comments = "Source Table: tbl_child_fruit")
    static UpdateDSL<UpdateModel> updateSelectiveColumns(ChildFruit record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(id).equalToWhenPresent(record::getId)
                .set(fruitId).equalToWhenPresent(record::getFruitId)
                .set(fruitName).equalToWhenPresent(record::getFruitName)
                .set(fruitPrice).equalToWhenPresent(record::getFruitPrice)
                .set(stock).equalToWhenPresent(record::getStock)
                .set(imageUrl).equalToWhenPresent(record::getImageUrl)
                .set(status).equalToWhenPresent(record::getStatus)
                .set(createTime).equalToWhenPresent(record::getCreateTime)
                .set(updateTime).equalToWhenPresent(record::getUpdateTime)
                .set(createUserId).equalToWhenPresent(record::getCreateUserId)
                .set(updateUserId).equalToWhenPresent(record::getUpdateUserId);
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.224+08:00",
            comments = "Source Table: tbl_child_fruit")
    default int updateByPrimaryKey(ChildFruit record) {
        return update(c ->
                c.set(fruitId).equalTo(record::getFruitId)
                        .set(fruitName).equalTo(record::getFruitName)
                        .set(fruitPrice).equalTo(record::getFruitPrice)
                        .set(stock).equalTo(record::getStock)
                        .set(imageUrl).equalTo(record::getImageUrl)
                        .set(status).equalTo(record::getStatus)
                        .set(createTime).equalTo(record::getCreateTime)
                        .set(updateTime).equalTo(record::getUpdateTime)
                        .set(createUserId).equalTo(record::getCreateUserId)
                        .set(updateUserId).equalTo(record::getUpdateUserId)
                        .where(id, isEqualTo(record::getId))
        );
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.224+08:00",
            comments = "Source Table: tbl_child_fruit")
    default int updateByPrimaryKeySelective(ChildFruit record) {
        return update(c ->
                c.set(fruitId).equalToWhenPresent(record::getFruitId)
                        .set(fruitName).equalToWhenPresent(record::getFruitName)
                        .set(fruitPrice).equalToWhenPresent(record::getFruitPrice)
                        .set(stock).equalToWhenPresent(record::getStock)
                        .set(imageUrl).equalToWhenPresent(record::getImageUrl)
                        .set(status).equalToWhenPresent(record::getStatus)
                        .set(createTime).equalToWhenPresent(record::getCreateTime)
                        .set(updateTime).equalToWhenPresent(record::getUpdateTime)
                        .set(createUserId).equalToWhenPresent(record::getCreateUserId)
                        .set(updateUserId).equalToWhenPresent(record::getUpdateUserId)
                        .where(id, isEqualTo(record::getId))
        );
    }
}