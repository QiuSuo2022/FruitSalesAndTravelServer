package com.guet.qiusuo.fruittravel.dao;

import static com.guet.qiusuo.fruittravel.dao.TicketDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.*;

import com.guet.qiusuo.fruittravel.model.Ticket;

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
public interface TicketMapper {
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.29+08:00", comments
            = "Source Table: tbl_ticket")
    BasicColumn[] selectList = BasicColumn.columnList(id, scenicId, type, price, description, status, createTime,
            updateTime, createUserId, updateUserId);

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.287+08:00",
            comments = "Source Table: tbl_ticket")
    @SelectProvider(type = SqlProviderAdapter.class, method = "select")
    long count(SelectStatementProvider selectStatement);

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.287+08:00",
            comments = "Source Table: tbl_ticket")
    @DeleteProvider(type = SqlProviderAdapter.class, method = "delete")
    int delete(DeleteStatementProvider deleteStatement);

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.287+08:00",
            comments = "Source Table: tbl_ticket")
    @InsertProvider(type = SqlProviderAdapter.class, method = "insert")
    int insert(InsertStatementProvider<Ticket> insertStatement);

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.287+08:00",
            comments = "Source Table: tbl_ticket")
    @InsertProvider(type = SqlProviderAdapter.class, method = "insertMultiple")
    int insertMultiple(MultiRowInsertStatementProvider<Ticket> multipleInsertStatement);

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.287+08:00",
            comments = "Source Table: tbl_ticket")
    @SelectProvider(type = SqlProviderAdapter.class, method = "select")
    @ResultMap("TicketResult")
    Optional<Ticket> selectOne(SelectStatementProvider selectStatement);

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.287+08:00",
            comments = "Source Table: tbl_ticket")
    @SelectProvider(type = SqlProviderAdapter.class, method = "select")
    @Results(id = "TicketResult", value = {
            @Result(column = "id", property = "id", jdbcType = JdbcType.VARCHAR, id = true),
            @Result(column = "scenic_id", property = "scenicId", jdbcType = JdbcType.VARCHAR),
            @Result(column = "type", property = "type", jdbcType = JdbcType.SMALLINT),
            @Result(column = "price", property = "price", jdbcType = JdbcType.INTEGER),
            @Result(column = "description", property = "description", jdbcType = JdbcType.VARCHAR),
            @Result(column = "status", property = "status", jdbcType = JdbcType.SMALLINT),
            @Result(column = "create_time", property = "createTime", jdbcType = JdbcType.BIGINT),
            @Result(column = "update_time", property = "updateTime", jdbcType = JdbcType.BIGINT),
            @Result(column = "create_user_id", property = "createUserId", jdbcType = JdbcType.VARCHAR),
            @Result(column = "update_user_id", property = "updateUserId", jdbcType = JdbcType.VARCHAR)
    })
    List<Ticket> selectMany(SelectStatementProvider selectStatement);

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.289+08:00",
            comments = "Source Table: tbl_ticket")
    @UpdateProvider(type = SqlProviderAdapter.class, method = "update")
    int update(UpdateStatementProvider updateStatement);

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.29+08:00", comments
            = "Source Table: tbl_ticket")
    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, ticket, completer);
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.29+08:00", comments
            = "Source Table: tbl_ticket")
    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, ticket, completer);
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.29+08:00", comments
            = "Source Table: tbl_ticket")
    default int deleteByPrimaryKey(String id_) {
        return delete(c ->
                c.where(id, isEqualTo(id_))
        );
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.29+08:00", comments
            = "Source Table: tbl_ticket")
    default int insert(Ticket record) {
        return MyBatis3Utils.insert(this::insert, record, ticket, c ->
                c.map(id).toProperty("id")
                        .map(scenicId).toProperty("scenicId")
                        .map(type).toProperty("type")
                        .map(price).toProperty("price")
                        .map(description).toProperty("description")
                        .map(status).toProperty("status")
                        .map(createTime).toProperty("createTime")
                        .map(updateTime).toProperty("updateTime")
                        .map(createUserId).toProperty("createUserId")
                        .map(updateUserId).toProperty("updateUserId")
        );
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.29+08:00", comments
            = "Source Table: tbl_ticket")
    default int insertMultiple(Collection<Ticket> records) {
        return MyBatis3Utils.insertMultiple(this::insertMultiple, records, ticket, c ->
                c.map(id).toProperty("id")
                        .map(scenicId).toProperty("scenicId")
                        .map(type).toProperty("type")
                        .map(price).toProperty("price")
                        .map(description).toProperty("description")
                        .map(status).toProperty("status")
                        .map(createTime).toProperty("createTime")
                        .map(updateTime).toProperty("updateTime")
                        .map(createUserId).toProperty("createUserId")
                        .map(updateUserId).toProperty("updateUserId")
        );
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.29+08:00", comments
            = "Source Table: tbl_ticket")
    default int insertSelective(Ticket record) {
        return MyBatis3Utils.insert(this::insert, record, ticket, c ->
                c.map(id).toPropertyWhenPresent("id", record::getId)
                        .map(scenicId).toPropertyWhenPresent("scenicId", record::getScenicId)
                        .map(type).toPropertyWhenPresent("type", record::getType)
                        .map(price).toPropertyWhenPresent("price", record::getPrice)
                        .map(description).toPropertyWhenPresent("description", record::getDescription)
                        .map(status).toPropertyWhenPresent("status", record::getStatus)
                        .map(createTime).toPropertyWhenPresent("createTime", record::getCreateTime)
                        .map(updateTime).toPropertyWhenPresent("updateTime", record::getUpdateTime)
                        .map(createUserId).toPropertyWhenPresent("createUserId", record::getCreateUserId)
                        .map(updateUserId).toPropertyWhenPresent("updateUserId", record::getUpdateUserId)
        );
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.29+08:00", comments
            = "Source Table: tbl_ticket")
    default Optional<Ticket> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, ticket, completer);
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.29+08:00", comments
            = "Source Table: tbl_ticket")
    default List<Ticket> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, ticket, completer);
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.291+08:00",
            comments = "Source Table: tbl_ticket")
    default List<Ticket> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, ticket, completer);
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.291+08:00",
            comments = "Source Table: tbl_ticket")
    default Optional<Ticket> selectByPrimaryKey(String id_) {
        return selectOne(c ->
                c.where(id, isEqualTo(id_))
        );
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.291+08:00",
            comments = "Source Table: tbl_ticket")
    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, ticket, completer);
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.291+08:00",
            comments = "Source Table: tbl_ticket")
    static UpdateDSL<UpdateModel> updateAllColumns(Ticket record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(id).equalTo(record::getId)
                .set(scenicId).equalTo(record::getScenicId)
                .set(type).equalTo(record::getType)
                .set(price).equalTo(record::getPrice)
                .set(description).equalTo(record::getDescription)
                .set(status).equalTo(record::getStatus)
                .set(createTime).equalTo(record::getCreateTime)
                .set(updateTime).equalTo(record::getUpdateTime)
                .set(createUserId).equalTo(record::getCreateUserId)
                .set(updateUserId).equalTo(record::getUpdateUserId);
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.291+08:00",
            comments = "Source Table: tbl_ticket")
    static UpdateDSL<UpdateModel> updateSelectiveColumns(Ticket record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(id).equalToWhenPresent(record::getId)
                .set(scenicId).equalToWhenPresent(record::getScenicId)
                .set(type).equalToWhenPresent(record::getType)
                .set(price).equalToWhenPresent(record::getPrice)
                .set(description).equalToWhenPresent(record::getDescription)
                .set(status).equalToWhenPresent(record::getStatus)
                .set(createTime).equalToWhenPresent(record::getCreateTime)
                .set(updateTime).equalToWhenPresent(record::getUpdateTime)
                .set(createUserId).equalToWhenPresent(record::getCreateUserId)
                .set(updateUserId).equalToWhenPresent(record::getUpdateUserId);
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.291+08:00",
            comments = "Source Table: tbl_ticket")
    default int updateByPrimaryKey(Ticket record) {
        return update(c ->
                c.set(scenicId).equalTo(record::getScenicId)
                        .set(type).equalTo(record::getType)
                        .set(price).equalTo(record::getPrice)
                        .set(description).equalTo(record::getDescription)
                        .set(status).equalTo(record::getStatus)
                        .set(createTime).equalTo(record::getCreateTime)
                        .set(updateTime).equalTo(record::getUpdateTime)
                        .set(createUserId).equalTo(record::getCreateUserId)
                        .set(updateUserId).equalTo(record::getUpdateUserId)
                        .where(id, isEqualTo(record::getId))
        );
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.291+08:00",
            comments = "Source Table: tbl_ticket")
    default int updateByPrimaryKeySelective(Ticket record) {
        return update(c ->
                c.set(scenicId).equalToWhenPresent(record::getScenicId)
                        .set(type).equalToWhenPresent(record::getType)
                        .set(price).equalToWhenPresent(record::getPrice)
                        .set(description).equalToWhenPresent(record::getDescription)
                        .set(status).equalToWhenPresent(record::getStatus)
                        .set(createTime).equalToWhenPresent(record::getCreateTime)
                        .set(updateTime).equalToWhenPresent(record::getUpdateTime)
                        .set(createUserId).equalToWhenPresent(record::getCreateUserId)
                        .set(updateUserId).equalToWhenPresent(record::getUpdateUserId)
                        .where(id, isEqualTo(record::getId))
        );
    }
}