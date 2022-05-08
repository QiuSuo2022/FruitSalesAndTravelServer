package com.guet.qiusuo.fruittravel.dao;

import static com.guet.qiusuo.fruittravel.dao.RolePermissionDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.*;

import com.guet.qiusuo.fruittravel.model.RolePermission;

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
public interface RolePermissionMapper {
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.272+08:00",
            comments = "Source Table: tbl_role_permission")
    BasicColumn[] selectList = BasicColumn.columnList(id, roleId, permissionId, status, createTime, updateTime,
            createUserId, updateUserId);

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.27+08:00", comments
            = "Source Table: tbl_role_permission")
    @SelectProvider(type = SqlProviderAdapter.class, method = "select")
    long count(SelectStatementProvider selectStatement);

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.27+08:00", comments
            = "Source Table: tbl_role_permission")
    @DeleteProvider(type = SqlProviderAdapter.class, method = "delete")
    int delete(DeleteStatementProvider deleteStatement);

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.27+08:00", comments
            = "Source Table: tbl_role_permission")
    @InsertProvider(type = SqlProviderAdapter.class, method = "insert")
    int insert(InsertStatementProvider<RolePermission> insertStatement);

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.271+08:00",
            comments = "Source Table: tbl_role_permission")
    @InsertProvider(type = SqlProviderAdapter.class, method = "insertMultiple")
    int insertMultiple(MultiRowInsertStatementProvider<RolePermission> multipleInsertStatement);

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.271+08:00",
            comments = "Source Table: tbl_role_permission")
    @SelectProvider(type = SqlProviderAdapter.class, method = "select")
    @ResultMap("RolePermissionResult")
    Optional<RolePermission> selectOne(SelectStatementProvider selectStatement);

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.271+08:00",
            comments = "Source Table: tbl_role_permission")
    @SelectProvider(type = SqlProviderAdapter.class, method = "select")
    @Results(id = "RolePermissionResult", value = {
            @Result(column = "id", property = "id", jdbcType = JdbcType.VARCHAR, id = true),
            @Result(column = "role_id", property = "roleId", jdbcType = JdbcType.VARCHAR),
            @Result(column = "permission_id", property = "permissionId", jdbcType = JdbcType.VARCHAR),
            @Result(column = "status", property = "status", jdbcType = JdbcType.SMALLINT),
            @Result(column = "create_time", property = "createTime", jdbcType = JdbcType.BIGINT),
            @Result(column = "update_time", property = "updateTime", jdbcType = JdbcType.BIGINT),
            @Result(column = "create_user_id", property = "createUserId", jdbcType = JdbcType.VARCHAR),
            @Result(column = "update_user_id", property = "updateUserId", jdbcType = JdbcType.VARCHAR)
    })
    List<RolePermission> selectMany(SelectStatementProvider selectStatement);

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.271+08:00",
            comments = "Source Table: tbl_role_permission")
    @UpdateProvider(type = SqlProviderAdapter.class, method = "update")
    int update(UpdateStatementProvider updateStatement);

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.271+08:00",
            comments = "Source Table: tbl_role_permission")
    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, rolePermission, completer);
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.271+08:00",
            comments = "Source Table: tbl_role_permission")
    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, rolePermission, completer);
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.271+08:00",
            comments = "Source Table: tbl_role_permission")
    default int deleteByPrimaryKey(String id_) {
        return delete(c ->
                c.where(id, isEqualTo(id_))
        );
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.271+08:00",
            comments = "Source Table: tbl_role_permission")
    default int insert(RolePermission record) {
        return MyBatis3Utils.insert(this::insert, record, rolePermission, c ->
                c.map(id).toProperty("id")
                        .map(roleId).toProperty("roleId")
                        .map(permissionId).toProperty("permissionId")
                        .map(status).toProperty("status")
                        .map(createTime).toProperty("createTime")
                        .map(updateTime).toProperty("updateTime")
                        .map(createUserId).toProperty("createUserId")
                        .map(updateUserId).toProperty("updateUserId")
        );
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.271+08:00",
            comments = "Source Table: tbl_role_permission")
    default int insertMultiple(Collection<RolePermission> records) {
        return MyBatis3Utils.insertMultiple(this::insertMultiple, records, rolePermission, c ->
                c.map(id).toProperty("id")
                        .map(roleId).toProperty("roleId")
                        .map(permissionId).toProperty("permissionId")
                        .map(status).toProperty("status")
                        .map(createTime).toProperty("createTime")
                        .map(updateTime).toProperty("updateTime")
                        .map(createUserId).toProperty("createUserId")
                        .map(updateUserId).toProperty("updateUserId")
        );
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.272+08:00",
            comments = "Source Table: tbl_role_permission")
    default int insertSelective(RolePermission record) {
        return MyBatis3Utils.insert(this::insert, record, rolePermission, c ->
                c.map(id).toPropertyWhenPresent("id", record::getId)
                        .map(roleId).toPropertyWhenPresent("roleId", record::getRoleId)
                        .map(permissionId).toPropertyWhenPresent("permissionId", record::getPermissionId)
                        .map(status).toPropertyWhenPresent("status", record::getStatus)
                        .map(createTime).toPropertyWhenPresent("createTime", record::getCreateTime)
                        .map(updateTime).toPropertyWhenPresent("updateTime", record::getUpdateTime)
                        .map(createUserId).toPropertyWhenPresent("createUserId", record::getCreateUserId)
                        .map(updateUserId).toPropertyWhenPresent("updateUserId", record::getUpdateUserId)
        );
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.272+08:00",
            comments = "Source Table: tbl_role_permission")
    default Optional<RolePermission> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, rolePermission, completer);
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.272+08:00",
            comments = "Source Table: tbl_role_permission")
    default List<RolePermission> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, rolePermission, completer);
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.272+08:00",
            comments = "Source Table: tbl_role_permission")
    default List<RolePermission> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, rolePermission, completer);
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.272+08:00",
            comments = "Source Table: tbl_role_permission")
    default Optional<RolePermission> selectByPrimaryKey(String id_) {
        return selectOne(c ->
                c.where(id, isEqualTo(id_))
        );
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.272+08:00",
            comments = "Source Table: tbl_role_permission")
    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, rolePermission, completer);
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.272+08:00",
            comments = "Source Table: tbl_role_permission")
    static UpdateDSL<UpdateModel> updateAllColumns(RolePermission record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(id).equalTo(record::getId)
                .set(roleId).equalTo(record::getRoleId)
                .set(permissionId).equalTo(record::getPermissionId)
                .set(status).equalTo(record::getStatus)
                .set(createTime).equalTo(record::getCreateTime)
                .set(updateTime).equalTo(record::getUpdateTime)
                .set(createUserId).equalTo(record::getCreateUserId)
                .set(updateUserId).equalTo(record::getUpdateUserId);
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.272+08:00",
            comments = "Source Table: tbl_role_permission")
    static UpdateDSL<UpdateModel> updateSelectiveColumns(RolePermission record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(id).equalToWhenPresent(record::getId)
                .set(roleId).equalToWhenPresent(record::getRoleId)
                .set(permissionId).equalToWhenPresent(record::getPermissionId)
                .set(status).equalToWhenPresent(record::getStatus)
                .set(createTime).equalToWhenPresent(record::getCreateTime)
                .set(updateTime).equalToWhenPresent(record::getUpdateTime)
                .set(createUserId).equalToWhenPresent(record::getCreateUserId)
                .set(updateUserId).equalToWhenPresent(record::getUpdateUserId);
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.272+08:00",
            comments = "Source Table: tbl_role_permission")
    default int updateByPrimaryKey(RolePermission record) {
        return update(c ->
                c.set(roleId).equalTo(record::getRoleId)
                        .set(permissionId).equalTo(record::getPermissionId)
                        .set(status).equalTo(record::getStatus)
                        .set(createTime).equalTo(record::getCreateTime)
                        .set(updateTime).equalTo(record::getUpdateTime)
                        .set(createUserId).equalTo(record::getCreateUserId)
                        .set(updateUserId).equalTo(record::getUpdateUserId)
                        .where(id, isEqualTo(record::getId))
        );
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.273+08:00",
            comments = "Source Table: tbl_role_permission")
    default int updateByPrimaryKeySelective(RolePermission record) {
        return update(c ->
                c.set(roleId).equalToWhenPresent(record::getRoleId)
                        .set(permissionId).equalToWhenPresent(record::getPermissionId)
                        .set(status).equalToWhenPresent(record::getStatus)
                        .set(createTime).equalToWhenPresent(record::getCreateTime)
                        .set(updateTime).equalToWhenPresent(record::getUpdateTime)
                        .set(createUserId).equalToWhenPresent(record::getCreateUserId)
                        .set(updateUserId).equalToWhenPresent(record::getUpdateUserId)
                        .where(id, isEqualTo(record::getId))
        );
    }
}