package com.guet.qiusuo.fruittravel.dao;

import static com.guet.qiusuo.fruittravel.dao.ReservationDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.*;

import com.guet.qiusuo.fruittravel.model.Reservation;

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
public interface ReservationMapper {
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.26+08:00", comments
            = "Source Table: tbl_reservation")
    BasicColumn[] selectList = BasicColumn.columnList(id, userId, ticketId, reserveTime, quantity, status, createTime
            , updateTime, createUserId, updateUserId);

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.259+08:00",
            comments = "Source Table: tbl_reservation")
    @SelectProvider(type = SqlProviderAdapter.class, method = "select")
    long count(SelectStatementProvider selectStatement);

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.259+08:00",
            comments = "Source Table: tbl_reservation")
    @DeleteProvider(type = SqlProviderAdapter.class, method = "delete")
    int delete(DeleteStatementProvider deleteStatement);

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.259+08:00",
            comments = "Source Table: tbl_reservation")
    @InsertProvider(type = SqlProviderAdapter.class, method = "insert")
    int insert(InsertStatementProvider<Reservation> insertStatement);

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.259+08:00",
            comments = "Source Table: tbl_reservation")
    @InsertProvider(type = SqlProviderAdapter.class, method = "insertMultiple")
    int insertMultiple(MultiRowInsertStatementProvider<Reservation> multipleInsertStatement);

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.259+08:00",
            comments = "Source Table: tbl_reservation")
    @SelectProvider(type = SqlProviderAdapter.class, method = "select")
    @ResultMap("ReservationResult")
    Optional<Reservation> selectOne(SelectStatementProvider selectStatement);

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.259+08:00",
            comments = "Source Table: tbl_reservation")
    @SelectProvider(type = SqlProviderAdapter.class, method = "select")
    @Results(id = "ReservationResult", value = {
            @Result(column = "id", property = "id", jdbcType = JdbcType.VARCHAR, id = true),
            @Result(column = "user_id", property = "userId", jdbcType = JdbcType.VARCHAR),
            @Result(column = "ticket_id", property = "ticketId", jdbcType = JdbcType.VARCHAR),
            @Result(column = "reserve_time", property = "reserveTime", jdbcType = JdbcType.BIGINT),
            @Result(column = "quantity", property = "quantity", jdbcType = JdbcType.INTEGER),
            @Result(column = "status", property = "status", jdbcType = JdbcType.SMALLINT),
            @Result(column = "create_time", property = "createTime", jdbcType = JdbcType.BIGINT),
            @Result(column = "update_time", property = "updateTime", jdbcType = JdbcType.BIGINT),
            @Result(column = "create_user_id", property = "createUserId", jdbcType = JdbcType.VARCHAR),
            @Result(column = "update_user_id", property = "updateUserId", jdbcType = JdbcType.VARCHAR)
    })
    List<Reservation> selectMany(SelectStatementProvider selectStatement);

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.259+08:00",
            comments = "Source Table: tbl_reservation")
    @UpdateProvider(type = SqlProviderAdapter.class, method = "update")
    int update(UpdateStatementProvider updateStatement);

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.259+08:00",
            comments = "Source Table: tbl_reservation")
    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, reservation, completer);
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.259+08:00",
            comments = "Source Table: tbl_reservation")
    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, reservation, completer);
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.259+08:00",
            comments = "Source Table: tbl_reservation")
    default int deleteByPrimaryKey(String id_) {
        return delete(c ->
                c.where(id, isEqualTo(id_))
        );
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.259+08:00",
            comments = "Source Table: tbl_reservation")
    default int insert(Reservation record) {
        return MyBatis3Utils.insert(this::insert, record, reservation, c ->
                c.map(id).toProperty("id")
                        .map(userId).toProperty("userId")
                        .map(ticketId).toProperty("ticketId")
                        .map(reserveTime).toProperty("reserveTime")
                        .map(quantity).toProperty("quantity")
                        .map(status).toProperty("status")
                        .map(createTime).toProperty("createTime")
                        .map(updateTime).toProperty("updateTime")
                        .map(createUserId).toProperty("createUserId")
                        .map(updateUserId).toProperty("updateUserId")
        );
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.26+08:00", comments
            = "Source Table: tbl_reservation")
    default int insertMultiple(Collection<Reservation> records) {
        return MyBatis3Utils.insertMultiple(this::insertMultiple, records, reservation, c ->
                c.map(id).toProperty("id")
                        .map(userId).toProperty("userId")
                        .map(ticketId).toProperty("ticketId")
                        .map(reserveTime).toProperty("reserveTime")
                        .map(quantity).toProperty("quantity")
                        .map(status).toProperty("status")
                        .map(createTime).toProperty("createTime")
                        .map(updateTime).toProperty("updateTime")
                        .map(createUserId).toProperty("createUserId")
                        .map(updateUserId).toProperty("updateUserId")
        );
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.26+08:00", comments
            = "Source Table: tbl_reservation")
    default int insertSelective(Reservation record) {
        return MyBatis3Utils.insert(this::insert, record, reservation, c ->
                c.map(id).toPropertyWhenPresent("id", record::getId)
                        .map(userId).toPropertyWhenPresent("userId", record::getUserId)
                        .map(ticketId).toPropertyWhenPresent("ticketId", record::getTicketId)
                        .map(reserveTime).toPropertyWhenPresent("reserveTime", record::getReserveTime)
                        .map(quantity).toPropertyWhenPresent("quantity", record::getQuantity)
                        .map(status).toPropertyWhenPresent("status", record::getStatus)
                        .map(createTime).toPropertyWhenPresent("createTime", record::getCreateTime)
                        .map(updateTime).toPropertyWhenPresent("updateTime", record::getUpdateTime)
                        .map(createUserId).toPropertyWhenPresent("createUserId", record::getCreateUserId)
                        .map(updateUserId).toPropertyWhenPresent("updateUserId", record::getUpdateUserId)
        );
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.26+08:00", comments
            = "Source Table: tbl_reservation")
    default Optional<Reservation> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, reservation, completer);
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.26+08:00", comments
            = "Source Table: tbl_reservation")
    default List<Reservation> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, reservation, completer);
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.261+08:00",
            comments = "Source Table: tbl_reservation")
    default List<Reservation> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, reservation, completer);
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.261+08:00",
            comments = "Source Table: tbl_reservation")
    default Optional<Reservation> selectByPrimaryKey(String id_) {
        return selectOne(c ->
                c.where(id, isEqualTo(id_))
        );
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.261+08:00",
            comments = "Source Table: tbl_reservation")
    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, reservation, completer);
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.261+08:00",
            comments = "Source Table: tbl_reservation")
    static UpdateDSL<UpdateModel> updateAllColumns(Reservation record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(id).equalTo(record::getId)
                .set(userId).equalTo(record::getUserId)
                .set(ticketId).equalTo(record::getTicketId)
                .set(reserveTime).equalTo(record::getReserveTime)
                .set(quantity).equalTo(record::getQuantity)
                .set(status).equalTo(record::getStatus)
                .set(createTime).equalTo(record::getCreateTime)
                .set(updateTime).equalTo(record::getUpdateTime)
                .set(createUserId).equalTo(record::getCreateUserId)
                .set(updateUserId).equalTo(record::getUpdateUserId);
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.261+08:00",
            comments = "Source Table: tbl_reservation")
    static UpdateDSL<UpdateModel> updateSelectiveColumns(Reservation record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(id).equalToWhenPresent(record::getId)
                .set(userId).equalToWhenPresent(record::getUserId)
                .set(ticketId).equalToWhenPresent(record::getTicketId)
                .set(reserveTime).equalToWhenPresent(record::getReserveTime)
                .set(quantity).equalToWhenPresent(record::getQuantity)
                .set(status).equalToWhenPresent(record::getStatus)
                .set(createTime).equalToWhenPresent(record::getCreateTime)
                .set(updateTime).equalToWhenPresent(record::getUpdateTime)
                .set(createUserId).equalToWhenPresent(record::getCreateUserId)
                .set(updateUserId).equalToWhenPresent(record::getUpdateUserId);
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.261+08:00",
            comments = "Source Table: tbl_reservation")
    default int updateByPrimaryKey(Reservation record) {
        return update(c ->
                c.set(userId).equalTo(record::getUserId)
                        .set(ticketId).equalTo(record::getTicketId)
                        .set(reserveTime).equalTo(record::getReserveTime)
                        .set(quantity).equalTo(record::getQuantity)
                        .set(status).equalTo(record::getStatus)
                        .set(createTime).equalTo(record::getCreateTime)
                        .set(updateTime).equalTo(record::getUpdateTime)
                        .set(createUserId).equalTo(record::getCreateUserId)
                        .set(updateUserId).equalTo(record::getUpdateUserId)
                        .where(id, isEqualTo(record::getId))
        );
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.261+08:00",
            comments = "Source Table: tbl_reservation")
    default int updateByPrimaryKeySelective(Reservation record) {
        return update(c ->
                c.set(userId).equalToWhenPresent(record::getUserId)
                        .set(ticketId).equalToWhenPresent(record::getTicketId)
                        .set(reserveTime).equalToWhenPresent(record::getReserveTime)
                        .set(quantity).equalToWhenPresent(record::getQuantity)
                        .set(status).equalToWhenPresent(record::getStatus)
                        .set(createTime).equalToWhenPresent(record::getCreateTime)
                        .set(updateTime).equalToWhenPresent(record::getUpdateTime)
                        .set(createUserId).equalToWhenPresent(record::getCreateUserId)
                        .set(updateUserId).equalToWhenPresent(record::getUpdateUserId)
                        .where(id, isEqualTo(record::getId))
        );
    }
}