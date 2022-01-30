package com.guet.qiusuo.fruittravel.dao;

import static com.guet.qiusuo.fruittravel.dao.UserRoleDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.*;

import com.guet.qiusuo.fruittravel.model.UserRole;

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
public interface UserRoleMapper {
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-30T15:19:23.879+08:00",
            comments = "Source Table: tbl_user_role")
    BasicColumn[] selectList = BasicColumn.columnList(id, roleId, userId, status, createTime, updateTime,
            createUserId, updateUserId);

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-30T15:19:23.868+08:00",
            comments = "Source Table: tbl_user_role")
    @SelectProvider(type = SqlProviderAdapter.class, method = "select")
    long count(SelectStatementProvider selectStatement);

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-30T15:19:23.87+08:00", comments
            = "Source Table: tbl_user_role")
    @DeleteProvider(type = SqlProviderAdapter.class, method = "delete")
    int delete(DeleteStatementProvider deleteStatement);

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-30T15:19:23.87+08:00", comments
            = "Source Table: tbl_user_role")
    @InsertProvider(type = SqlProviderAdapter.class, method = "insert")
    int insert(InsertStatementProvider<UserRole> insertStatement);

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-30T15:19:23.871+08:00",
            comments = "Source Table: tbl_user_role")
    @InsertProvider(type = SqlProviderAdapter.class, method = "insertMultiple")
    int insertMultiple(MultiRowInsertStatementProvider<UserRole> multipleInsertStatement);

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-30T15:19:23.871+08:00",
            comments = "Source Table: tbl_user_role")
    @SelectProvider(type = SqlProviderAdapter.class, method = "select")
    @ResultMap("UserRoleResult")
    Optional<UserRole> selectOne(SelectStatementProvider selectStatement);

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-30T15:19:23.872+08:00",
            comments = "Source Table: tbl_user_role")
    @SelectProvider(type = SqlProviderAdapter.class, method = "select")
    @Results(id = "UserRoleResult", value = {
            @Result(column = "id", property = "id", jdbcType = JdbcType.VARCHAR, id = true),
            @Result(column = "role_id", property = "roleId", jdbcType = JdbcType.VARCHAR),
            @Result(column = "user_id", property = "userId", jdbcType = JdbcType.VARCHAR),
            @Result(column = "status", property = "status", jdbcType = JdbcType.SMALLINT),
            @Result(column = "create_time", property = "createTime", jdbcType = JdbcType.BIGINT),
            @Result(column = "update_time", property = "updateTime", jdbcType = JdbcType.BIGINT),
            @Result(column = "create_user_id", property = "createUserId", jdbcType = JdbcType.VARCHAR),
            @Result(column = "update_user_id", property = "updateUserId", jdbcType = JdbcType.VARCHAR)
    })
    List<UserRole> selectMany(SelectStatementProvider selectStatement);

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-30T15:19:23.873+08:00",
            comments = "Source Table: tbl_user_role")
    @UpdateProvider(type = SqlProviderAdapter.class, method = "update")
    int update(UpdateStatementProvider updateStatement);

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-30T15:19:23.873+08:00",
            comments = "Source Table: tbl_user_role")
    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, userRole, completer);
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-30T15:19:23.874+08:00",
            comments = "Source Table: tbl_user_role")
    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, userRole, completer);
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-30T15:19:23.874+08:00",
            comments = "Source Table: tbl_user_role")
    default int deleteByPrimaryKey(String id_) {
        return delete(c ->
                c.where(id, isEqualTo(id_))
        );
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-30T15:19:23.875+08:00",
            comments = "Source Table: tbl_user_role")
    default int insert(UserRole record) {
        return MyBatis3Utils.insert(this::insert, record, userRole, c ->
                c.map(id).toProperty("id")
                        .map(roleId).toProperty("roleId")
                        .map(userId).toProperty("userId")
                        .map(status).toProperty("status")
                        .map(createTime).toProperty("createTime")
                        .map(updateTime).toProperty("updateTime")
                        .map(createUserId).toProperty("createUserId")
                        .map(updateUserId).toProperty("updateUserId")
        );
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-30T15:19:23.877+08:00",
            comments = "Source Table: tbl_user_role")
    default int insertMultiple(Collection<UserRole> records) {
        return MyBatis3Utils.insertMultiple(this::insertMultiple, records, userRole, c ->
                c.map(id).toProperty("id")
                        .map(roleId).toProperty("roleId")
                        .map(userId).toProperty("userId")
                        .map(status).toProperty("status")
                        .map(createTime).toProperty("createTime")
                        .map(updateTime).toProperty("updateTime")
                        .map(createUserId).toProperty("createUserId")
                        .map(updateUserId).toProperty("updateUserId")
        );
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-30T15:19:23.877+08:00",
            comments = "Source Table: tbl_user_role")
    default int insertSelective(UserRole record) {
        return MyBatis3Utils.insert(this::insert, record, userRole, c ->
                c.map(id).toPropertyWhenPresent("id", record::getId)
                        .map(roleId).toPropertyWhenPresent("roleId", record::getRoleId)
                        .map(userId).toPropertyWhenPresent("userId", record::getUserId)
                        .map(status).toPropertyWhenPresent("status", record::getStatus)
                        .map(createTime).toPropertyWhenPresent("createTime", record::getCreateTime)
                        .map(updateTime).toPropertyWhenPresent("updateTime", record::getUpdateTime)
                        .map(createUserId).toPropertyWhenPresent("createUserId", record::getCreateUserId)
                        .map(updateUserId).toPropertyWhenPresent("updateUserId", record::getUpdateUserId)
        );
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-30T15:19:23.88+08:00", comments
            = "Source Table: tbl_user_role")
    default Optional<UserRole> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, userRole, completer);
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-30T15:19:23.88+08:00", comments
            = "Source Table: tbl_user_role")
    default List<UserRole> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, userRole, completer);
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-30T15:19:23.881+08:00",
            comments = "Source Table: tbl_user_role")
    default List<UserRole> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, userRole, completer);
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-30T15:19:23.881+08:00",
            comments = "Source Table: tbl_user_role")
    default Optional<UserRole> selectByPrimaryKey(String id_) {
        return selectOne(c ->
                c.where(id, isEqualTo(id_))
        );
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-30T15:19:23.881+08:00",
            comments = "Source Table: tbl_user_role")
    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, userRole, completer);
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-30T15:19:23.882+08:00",
            comments = "Source Table: tbl_user_role")
    static UpdateDSL<UpdateModel> updateAllColumns(UserRole record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(id).equalTo(record::getId)
                .set(roleId).equalTo(record::getRoleId)
                .set(userId).equalTo(record::getUserId)
                .set(status).equalTo(record::getStatus)
                .set(createTime).equalTo(record::getCreateTime)
                .set(updateTime).equalTo(record::getUpdateTime)
                .set(createUserId).equalTo(record::getCreateUserId)
                .set(updateUserId).equalTo(record::getUpdateUserId);
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-30T15:19:23.882+08:00",
            comments = "Source Table: tbl_user_role")
    static UpdateDSL<UpdateModel> updateSelectiveColumns(UserRole record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(id).equalToWhenPresent(record::getId)
                .set(roleId).equalToWhenPresent(record::getRoleId)
                .set(userId).equalToWhenPresent(record::getUserId)
                .set(status).equalToWhenPresent(record::getStatus)
                .set(createTime).equalToWhenPresent(record::getCreateTime)
                .set(updateTime).equalToWhenPresent(record::getUpdateTime)
                .set(createUserId).equalToWhenPresent(record::getCreateUserId)
                .set(updateUserId).equalToWhenPresent(record::getUpdateUserId);
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-30T15:19:23.883+08:00",
            comments = "Source Table: tbl_user_role")
    default int updateByPrimaryKey(UserRole record) {
        return update(c ->
                c.set(roleId).equalTo(record::getRoleId)
                        .set(userId).equalTo(record::getUserId)
                        .set(status).equalTo(record::getStatus)
                        .set(createTime).equalTo(record::getCreateTime)
                        .set(updateTime).equalTo(record::getUpdateTime)
                        .set(createUserId).equalTo(record::getCreateUserId)
                        .set(updateUserId).equalTo(record::getUpdateUserId)
                        .where(id, isEqualTo(record::getId))
        );
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-30T15:19:23.884+08:00",
            comments = "Source Table: tbl_user_role")
    default int updateByPrimaryKeySelective(UserRole record) {
        return update(c ->
                c.set(roleId).equalToWhenPresent(record::getRoleId)
                        .set(userId).equalToWhenPresent(record::getUserId)
                        .set(status).equalToWhenPresent(record::getStatus)
                        .set(createTime).equalToWhenPresent(record::getCreateTime)
                        .set(updateTime).equalToWhenPresent(record::getUpdateTime)
                        .set(createUserId).equalToWhenPresent(record::getCreateUserId)
                        .set(updateUserId).equalToWhenPresent(record::getUpdateUserId)
                        .where(id, isEqualTo(record::getId))
        );
    }
}