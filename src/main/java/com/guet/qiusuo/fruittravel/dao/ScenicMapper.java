package com.guet.qiusuo.fruittravel.dao;

import static com.guet.qiusuo.fruittravel.dao.ScenicDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.*;

import com.guet.qiusuo.fruittravel.bean.vo.ScenicVO;
import com.guet.qiusuo.fruittravel.model.Scenic;

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
public interface ScenicMapper {
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.279+08:00",
            comments = "Source Table: tbl_scenic")
    BasicColumn[] selectList = BasicColumn.columnList(id, scenicName, location, openingHours, description, type,
            status, createTime, updateTime, createUserId, updateUserId);

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.278+08:00",
            comments = "Source Table: tbl_scenic")
    @SelectProvider(type = SqlProviderAdapter.class, method = "select")
    long count(SelectStatementProvider selectStatement);

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.278+08:00",
            comments = "Source Table: tbl_scenic")
    @DeleteProvider(type = SqlProviderAdapter.class, method = "delete")
    int delete(DeleteStatementProvider deleteStatement);

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.278+08:00",
            comments = "Source Table: tbl_scenic")
    @InsertProvider(type = SqlProviderAdapter.class, method = "insert")
    int insert(InsertStatementProvider<Scenic> insertStatement);

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.278+08:00",
            comments = "Source Table: tbl_scenic")
    @InsertProvider(type = SqlProviderAdapter.class, method = "insertMultiple")
    int insertMultiple(MultiRowInsertStatementProvider<Scenic> multipleInsertStatement);

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.278+08:00",
            comments = "Source Table: tbl_scenic")
    @SelectProvider(type = SqlProviderAdapter.class, method = "select")
    @ResultMap("ScenicResult")
    Optional<Scenic> selectOne(SelectStatementProvider selectStatement);

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.278+08:00",
            comments = "Source Table: tbl_scenic")
    @SelectProvider(type = SqlProviderAdapter.class, method = "select")
    @Results(id = "ScenicResult", value = {
            @Result(column = "id", property = "id", jdbcType = JdbcType.VARCHAR, id = true),
            @Result(column = "scenic_name", property = "scenicName", jdbcType = JdbcType.VARCHAR),
            @Result(column = "location", property = "location", jdbcType = JdbcType.VARCHAR),
            @Result(column = "opening_hours", property = "openingHours", jdbcType = JdbcType.VARCHAR),
            @Result(column = "description", property = "description", jdbcType = JdbcType.VARCHAR),
            @Result(column = "type", property = "type", jdbcType = JdbcType.SMALLINT),
            @Result(column = "status", property = "status", jdbcType = JdbcType.SMALLINT),
            @Result(column = "create_time", property = "createTime", jdbcType = JdbcType.BIGINT),
            @Result(column = "update_time", property = "updateTime", jdbcType = JdbcType.BIGINT),
            @Result(column = "create_user_id", property = "createUserId", jdbcType = JdbcType.VARCHAR),
            @Result(column = "update_user_id", property = "updateUserId", jdbcType = JdbcType.VARCHAR)
    })
    List<Scenic> selectMany(SelectStatementProvider selectStatement);

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.278+08:00",
            comments = "Source Table: tbl_scenic")
    @UpdateProvider(type = SqlProviderAdapter.class, method = "update")
    int update(UpdateStatementProvider updateStatement);

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.279+08:00",
            comments = "Source Table: tbl_scenic")
    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, scenic, completer);
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.279+08:00",
            comments = "Source Table: tbl_scenic")
    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, scenic, completer);
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.279+08:00",
            comments = "Source Table: tbl_scenic")
    default int deleteByPrimaryKey(String id_) {
        return delete(c ->
                c.where(id, isEqualTo(id_))
        );
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.279+08:00",
            comments = "Source Table: tbl_scenic")
    default int insert(Scenic record) {
        return MyBatis3Utils.insert(this::insert, record, scenic, c ->
                c.map(id).toProperty("id")
                        .map(scenicName).toProperty("scenicName")
                        .map(location).toProperty("location")
                        .map(openingHours).toProperty("openingHours")
                        .map(description).toProperty("description")
                        .map(type).toProperty("type")
                        .map(status).toProperty("status")
                        .map(createTime).toProperty("createTime")
                        .map(updateTime).toProperty("updateTime")
                        .map(createUserId).toProperty("createUserId")
                        .map(updateUserId).toProperty("updateUserId")
        );
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.279+08:00",
            comments = "Source Table: tbl_scenic")
    default int insertMultiple(Collection<Scenic> records) {
        return MyBatis3Utils.insertMultiple(this::insertMultiple, records, scenic, c ->
                c.map(id).toProperty("id")
                        .map(scenicName).toProperty("scenicName")
                        .map(location).toProperty("location")
                        .map(openingHours).toProperty("openingHours")
                        .map(description).toProperty("description")
                        .map(type).toProperty("type")
                        .map(status).toProperty("status")
                        .map(createTime).toProperty("createTime")
                        .map(updateTime).toProperty("updateTime")
                        .map(createUserId).toProperty("createUserId")
                        .map(updateUserId).toProperty("updateUserId")
        );
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.279+08:00",
            comments = "Source Table: tbl_scenic")
    default int insertSelective(Scenic record) {
        return MyBatis3Utils.insert(this::insert, record, scenic, c ->
                c.map(id).toPropertyWhenPresent("id", record::getId)
                        .map(scenicName).toPropertyWhenPresent("scenicName", record::getScenicName)
                        .map(location).toPropertyWhenPresent("location", record::getLocation)
                        .map(openingHours).toPropertyWhenPresent("openingHours", record::getOpeningHours)
                        .map(description).toPropertyWhenPresent("description", record::getDescription)
                        .map(type).toPropertyWhenPresent("type", record::getType)
                        .map(status).toPropertyWhenPresent("status", record::getStatus)
                        .map(createTime).toPropertyWhenPresent("createTime", record::getCreateTime)
                        .map(updateTime).toPropertyWhenPresent("updateTime", record::getUpdateTime)
                        .map(createUserId).toPropertyWhenPresent("createUserId", record::getCreateUserId)
                        .map(updateUserId).toPropertyWhenPresent("updateUserId", record::getUpdateUserId)
        );
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.279+08:00",
            comments = "Source Table: tbl_scenic")
    default Optional<Scenic> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, scenic, completer);
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.28+08:00", comments
            = "Source Table: tbl_scenic")
    default List<Scenic> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, scenic, completer);
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.28+08:00", comments
            = "Source Table: tbl_scenic")
    default List<Scenic> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, scenic, completer);
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.28+08:00", comments
            = "Source Table: tbl_scenic")
    default Optional<Scenic> selectByPrimaryKey(String id_) {
        return selectOne(c ->
                c.where(id, isEqualTo(id_))
        );
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.28+08:00", comments
            = "Source Table: tbl_scenic")
    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, scenic, completer);
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.28+08:00", comments
            = "Source Table: tbl_scenic")
    static UpdateDSL<UpdateModel> updateAllColumns(Scenic record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(id).equalTo(record::getId)
                .set(scenicName).equalTo(record::getScenicName)
                .set(location).equalTo(record::getLocation)
                .set(openingHours).equalTo(record::getOpeningHours)
                .set(description).equalTo(record::getDescription)
                .set(type).equalTo(record::getType)
                .set(status).equalTo(record::getStatus)
                .set(createTime).equalTo(record::getCreateTime)
                .set(updateTime).equalTo(record::getUpdateTime)
                .set(createUserId).equalTo(record::getCreateUserId)
                .set(updateUserId).equalTo(record::getUpdateUserId);
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.28+08:00", comments
            = "Source Table: tbl_scenic")
    static UpdateDSL<UpdateModel> updateSelectiveColumns(Scenic record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(id).equalToWhenPresent(record::getId)
                .set(scenicName).equalToWhenPresent(record::getScenicName)
                .set(location).equalToWhenPresent(record::getLocation)
                .set(openingHours).equalToWhenPresent(record::getOpeningHours)
                .set(description).equalToWhenPresent(record::getDescription)
                .set(type).equalToWhenPresent(record::getType)
                .set(status).equalToWhenPresent(record::getStatus)
                .set(createTime).equalToWhenPresent(record::getCreateTime)
                .set(updateTime).equalToWhenPresent(record::getUpdateTime)
                .set(createUserId).equalToWhenPresent(record::getCreateUserId)
                .set(updateUserId).equalToWhenPresent(record::getUpdateUserId);
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.28+08:00", comments
            = "Source Table: tbl_scenic")
    default int updateByPrimaryKey(Scenic record) {
        return update(c ->
                c.set(scenicName).equalTo(record::getScenicName)
                        .set(location).equalTo(record::getLocation)
                        .set(openingHours).equalTo(record::getOpeningHours)
                        .set(description).equalTo(record::getDescription)
                        .set(type).equalTo(record::getType)
                        .set(status).equalTo(record::getStatus)
                        .set(createTime).equalTo(record::getCreateTime)
                        .set(updateTime).equalTo(record::getUpdateTime)
                        .set(createUserId).equalTo(record::getCreateUserId)
                        .set(updateUserId).equalTo(record::getUpdateUserId)
                        .where(id, isEqualTo(record::getId))
        );
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.281+08:00",
            comments = "Source Table: tbl_scenic")
    default int updateByPrimaryKeySelective(Scenic record) {
        return update(c ->
                c.set(scenicName).equalToWhenPresent(record::getScenicName)
                        .set(location).equalToWhenPresent(record::getLocation)
                        .set(openingHours).equalToWhenPresent(record::getOpeningHours)
                        .set(description).equalToWhenPresent(record::getDescription)
                        .set(type).equalToWhenPresent(record::getType)
                        .set(status).equalToWhenPresent(record::getStatus)
                        .set(createTime).equalToWhenPresent(record::getCreateTime)
                        .set(updateTime).equalToWhenPresent(record::getUpdateTime)
                        .set(createUserId).equalToWhenPresent(record::getCreateUserId)
                        .set(updateUserId).equalToWhenPresent(record::getUpdateUserId)
                        .where(id, isEqualTo(record::getId))
        );
    }

    @SelectProvider(type = SqlProviderAdapter.class, method = "select")
    @Results(id = "Scenic", value = {
            @Result(column = "opening_hours", property = "openingHours", jdbcType = JdbcType.VARCHAR, id = true),
            @Result(column = "scenic_name", property = "scenicName", jdbcType = JdbcType.VARCHAR),
            @Result(column = "location", property = "location", jdbcType = JdbcType.VARCHAR),
            @Result(column = "type", property = "type", jdbcType = JdbcType.SMALLINT),
            @Result(column = "description", property = "description", jdbcType = JdbcType.VARCHAR),
    })
    List<Scenic> selectScenic(SelectStatementProvider selectStatement);
}