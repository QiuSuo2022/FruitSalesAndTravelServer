package com.guet.qiusuo.fruittravel.dao;

import com.guet.qiusuo.fruittravel.bean.vo.ScenicVO;
import com.guet.qiusuo.fruittravel.bean.vo.UserRoleVO;
import com.guet.qiusuo.fruittravel.model.UserRole;
import org.apache.ibatis.annotations.*;
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

import javax.annotation.Generated;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static com.guet.qiusuo.fruittravel.dao.ScenicTicketDynamicSqlSupport.*;
import static com.guet.qiusuo.fruittravel.dao.UserRoleDynamicSqlSupport.*;
import static com.guet.qiusuo.fruittravel.dao.UserRoleDynamicSqlSupport.createTime;
import static com.guet.qiusuo.fruittravel.dao.UserRoleDynamicSqlSupport.createUserId;
import static com.guet.qiusuo.fruittravel.dao.UserRoleDynamicSqlSupport.id;
import static com.guet.qiusuo.fruittravel.dao.UserRoleDynamicSqlSupport.status;
import static com.guet.qiusuo.fruittravel.dao.UserRoleDynamicSqlSupport.updateTime;
import static com.guet.qiusuo.fruittravel.dao.UserRoleDynamicSqlSupport.updateUserId;
import static com.guet.qiusuo.fruittravel.dao.UserRoleDynamicSqlSupport.userId;
import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;

@Repository
@Mapper
public interface ScenicTicketMapper {
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-02-17T18:19:23.879+08:00",
            comments = "Source Table: tbl_scenic_ticket")
    BasicColumn[] selectList = BasicColumn.columnList(id,description,ticketId,ticketName,price,type,status,
            createTime,updateTime,createUserId,updateUserId);

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-02-17T18:19:23.879+08:00",
            comments = "Source Table: tbl_scenic_ticket")
    @SelectProvider(type = SqlProviderAdapter.class, method = "select")
    long count(SelectStatementProvider selectStatement);

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-02-17T18:19:23.879+08:00",
            comments = "Source Table: tbl_scenic_ticket")
    @DeleteProvider(type = SqlProviderAdapter.class, method = "delete")
    int delete(DeleteStatementProvider deleteStatement);

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-02-17T18:19:23.879+08:00",
            comments = "Source Table: tbl_scenic_ticket")
    @InsertProvider(type = SqlProviderAdapter.class, method = "insert")
    int insert(InsertStatementProvider<ScenicVO> insertStatement);

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-02-17T18:19:23.879+08:00",
            comments = "Source Table: tbl_scenic_ticket")
    @InsertProvider(type = SqlProviderAdapter.class, method = "insertMultiple")
    int insertMultiple(MultiRowInsertStatementProvider<ScenicVO> multipleInsertStatement);

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-02-17T18:19:23.879+08:00",
            comments = "Source Table: tbl_scenic_ticket")
    @SelectProvider(type = SqlProviderAdapter.class, method = "select")
    @ResultMap("ScenicTicketResult")
    Optional<ScenicVO> selectOne(SelectStatementProvider selectStatement);

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-02-17T18:19:23.879+08:00",
            comments = "Source Table: tbl_scenic_ticket")
    @SelectProvider(type = SqlProviderAdapter.class, method = "select")
    @Results(id = "ScenicTicketResult", value = {
            @Result(column = "id", property = "id", jdbcType = JdbcType.VARCHAR, id = true),
            @Result(column = "description", property = "description", jdbcType = JdbcType.VARCHAR),
            @Result(column = "ticket_id", property = "ticketId", jdbcType = JdbcType.VARCHAR),
            @Result(column = "ticket_name", property = "ticketName", jdbcType = JdbcType.VARCHAR),
            @Result(column = "price",property = "price",jdbcType = JdbcType.INTEGER),
            @Result(column = "type", property = "type", jdbcType = JdbcType.SMALLINT),
            @Result(column = "status", property = "status", jdbcType = JdbcType.SMALLINT),
            @Result(column = "create_time", property = "createTime", jdbcType = JdbcType.BIGINT),
            @Result(column = "update_time", property = "updateTime", jdbcType = JdbcType.BIGINT),
            @Result(column = "create_user_id", property = "createUserId", jdbcType = JdbcType.VARCHAR),
            @Result(column = "update_user_id", property = "updateUserId", jdbcType = JdbcType.VARCHAR)
    })
    List<ScenicVO> selectMany(SelectStatementProvider selectStatement);

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-02-17T18:19:23.879+08:00",
            comments = "Source Table: tbl_scenic_ticket")
    @UpdateProvider(type = SqlProviderAdapter.class, method = "update")
    int update(UpdateStatementProvider updateStatement);

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-02-17T18:19:23.879+08:00",
            comments = "Source Table: tbl_scenic_ticket")
    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, scenicVO, completer);
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-02-17T18:19:23.879+08:00",
            comments = "Source Table: tbl_scenic_ticket")
    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, scenicVO, completer);
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-02-17T18:19:23.879+08:00",
            comments = "Source Table: tbl_scenic_ticket")
    default int deleteByPrimaryKey(String id_) {
        return delete(c ->
                c.where(id, isEqualTo(id_))
        );
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-02-17T18:19:23.879+08:00",
            comments = "Source Table: tbl_scenic_ticket")
    default int insert(ScenicVO record) {
        return MyBatis3Utils.insert(this::insert, record, scenicVO, c ->
                c.map(id).toProperty("id")
                        .map(description).toProperty("description")
                        .map(ticketId).toProperty("ticketId")
                        .map(ticketName).toProperty("ticketName")
                        .map(price).toProperty("price")
                        .map(type).toProperty("type")
                        .map(status).toProperty("status")
                        .map(createTime).toProperty("createTime")
                        .map(updateTime).toProperty("updateTime")
                        .map(createUserId).toProperty("createUserId")
                        .map(updateUserId).toProperty("updateUserId")
        );
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-02-17T18:19:23.879+08:00",
            comments = "Source Table: tbl_scenic_ticket")
    default int insertMultiple(Collection<ScenicVO> records) {
        return MyBatis3Utils.insertMultiple(this::insertMultiple, records, scenicVO, c ->
                c.map(id).toProperty("id")
                        .map(description).toProperty("description")
                        .map(ticketId).toProperty("ticketId")
                        .map(ticketName).toProperty("ticketName")
                        .map(price).toProperty("price")
                        .map(type).toProperty("type")
                        .map(status).toProperty("status")
                        .map(createTime).toProperty("createTime")
                        .map(updateTime).toProperty("updateTime")
                        .map(createUserId).toProperty("createUserId")
                        .map(updateUserId).toProperty("updateUserId")
        );
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-02-17T18:19:23.879+08:00",
            comments = "Source Table: tbl_scenic_ticket")
    default int insertSelective(ScenicVO record) {
        return MyBatis3Utils.insert(this::insert, record, scenicVO, c ->
                c.map(id).toPropertyWhenPresent("id", record::getId)
                        .map(description).toPropertyWhenPresent("description", record::getDescription)
                        .map(ticketId).toPropertyWhenPresent("ticketId", record::getTicketId)
                        .map(ticketName).toPropertyWhenPresent("ticketName",record::getTicketName)
                        .map(price).toPropertyWhenPresent("price",record::getPrice)
                        .map(type).toPropertyWhenPresent("type",record::getType)
                        .map(status).toPropertyWhenPresent("status", record::getStatus)
                        .map(createTime).toPropertyWhenPresent("createTime", record::getCreateTime)
                        .map(updateTime).toPropertyWhenPresent("updateTime", record::getUpdateTime)
                        .map(createUserId).toPropertyWhenPresent("createUserId", record::getCreateUserId)
                        .map(updateUserId).toPropertyWhenPresent("updateUserId", record::getUpdateUserId)
        );
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-02-17T18:19:23.879+08:00",
            comments = "Source Table: tbl_scenic_ticket")
    default Optional<ScenicVO> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, scenicVO, completer);
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-02-17T18:19:23.879+08:00",
            comments = "Source Table: tbl_scenic_ticket")
    default List<ScenicVO> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, scenicVO, completer);
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-02-17T18:19:23.879+08:00",
            comments = "Source Table: tbl_scenic_ticket")
    default List<ScenicVO> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, scenicVO, completer);
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-02-17T18:19:23.879+08:00",
            comments = "Source Table: tbl_scenic_ticket")
    default Optional<ScenicVO> selectByPrimaryKey(String id_) {
        return selectOne(c ->
                c.where(id, isEqualTo(id_))
        );
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-02-17T18:19:23.879+08:00",
            comments = "Source Table: tbl_scenic_ticket")
    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, scenicVO, completer);
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-02-17T18:19:23.879+08:00",
            comments = "Source Table: tbl_scenic_ticket")
    static UpdateDSL<UpdateModel> updateAllColumns(ScenicVO record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(id).equalTo(record::getId)
                .set(description).equalTo(record::getDescription)
                .set(ticketId).equalTo(record::getTicketId)
                .set(ticketName).equalTo(record::getTicketName)
                .set(price).equalTo(record::getPrice)
                .set(type).equalTo(record::getType)
                .set(status).equalTo(record::getStatus)
                .set(createTime).equalTo(record::getCreateTime)
                .set(updateTime).equalTo(record::getUpdateTime)
                .set(createUserId).equalTo(record::getCreateUserId)
                .set(updateUserId).equalTo(record::getUpdateUserId);
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-02-17T18:19:23.879+08:00",
            comments = "Source Table: tbl_scenic_ticket")
    static UpdateDSL<UpdateModel> updateSelectiveColumns(ScenicVO record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(id).equalToWhenPresent(record::getId)
                .set(description).equalToWhenPresent(record::getDescription)
                .set(ticketId).equalToWhenPresent(record::getTicketId)
                .set(ticketName).equalToWhenPresent(record::getTicketName)
                .set(price).equalToWhenPresent(record::getPrice)
                .set(type).equalToWhenPresent(record::getType)
                .set(status).equalToWhenPresent(record::getStatus)
                .set(createTime).equalToWhenPresent(record::getCreateTime)
                .set(updateTime).equalToWhenPresent(record::getUpdateTime)
                .set(createUserId).equalToWhenPresent(record::getCreateUserId)
                .set(updateUserId).equalToWhenPresent(record::getUpdateUserId);
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-02-17T18:19:23.879+08:00",
            comments = "Source Table: tbl_scenic_ticket")
    default int updateByPrimaryKey(ScenicVO record) {
        return update(c ->
                c.set(description).equalTo(record::getDescription)
                        .set(ticketId).equalTo(record::getTicketId)
                        .set(ticketName).equalTo(record::getTicketName)
                        .set(price).equalTo(record::getPrice)
                        .set(type).equalTo(record::getType)
                        .set(status).equalTo(record::getStatus)
                        .set(createTime).equalTo(record::getCreateTime)
                        .set(updateTime).equalTo(record::getUpdateTime)
                        .set(createUserId).equalTo(record::getCreateUserId)
                        .set(updateUserId).equalTo(record::getUpdateUserId)
                        .where(id, isEqualTo(record::getId))
        );
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-02-17T18:19:23.879+08:00",
            comments = "Source Table: tbl_scenic_ticket")
    default int updateByPrimaryKeySelective(ScenicVO record) {
        return update(c ->
                c.set(description).equalToWhenPresent(record::getDescription)
                        .set(ticketId).equalToWhenPresent(record::getTicketId)
                        .set(ticketName).equalToWhenPresent(record::getTicketName)
                        .set(price).equalToWhenPresent(record::getPrice)
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
    @Results(id = "ScenicVO", value = {
            @Result(column = "ticket_id", property = "ticketId", jdbcType = JdbcType.VARCHAR, id = true),
            @Result(column = "ticket_name", property = "ticketName", jdbcType = JdbcType.VARCHAR),
            @Result(column = "price", property = "price", jdbcType = JdbcType.INTEGER),
            @Result(column = "type", property = "type", jdbcType = JdbcType.SMALLINT),
            @Result(column = "description", property = "description", jdbcType = JdbcType.VARCHAR),
    })
    List<ScenicVO> selectScenicVO(SelectStatementProvider selectStatement);
}
