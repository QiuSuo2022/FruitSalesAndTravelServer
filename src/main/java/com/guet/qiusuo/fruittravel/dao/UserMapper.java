package com.guet.qiusuo.fruittravel.dao;

import com.guet.qiusuo.fruittravel.bean.vo.UserVO;
import com.guet.qiusuo.fruittravel.model.User;
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

import static com.guet.qiusuo.fruittravel.dao.UserDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;

@Mapper
@Repository
public interface UserMapper {
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.196+08:00",
            comments = "Source Table: tbl_user")
    BasicColumn[] selectList = BasicColumn.columnList(id, userName, realName, gender, password, phone, email, idCard,
            avatarUrl, token, openid, roleId, status, createTime, updateTime, createUserId, updateUserId);

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.179+08:00",
            comments = "Source Table: tbl_user")
    @SelectProvider(type = SqlProviderAdapter.class, method = "select")
    long count(SelectStatementProvider selectStatement);

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.181+08:00",
            comments = "Source Table: tbl_user")
    @DeleteProvider(type = SqlProviderAdapter.class, method = "delete")
    int delete(DeleteStatementProvider deleteStatement);

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.182+08:00",
            comments = "Source Table: tbl_user")
    @InsertProvider(type = SqlProviderAdapter.class, method = "insert")
    int insert(InsertStatementProvider<User> insertStatement);

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.183+08:00",
            comments = "Source Table: tbl_user")
    @InsertProvider(type = SqlProviderAdapter.class, method = "insertMultiple")
    int insertMultiple(MultiRowInsertStatementProvider<User> multipleInsertStatement);

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.184+08:00",
            comments = "Source Table: tbl_user")
    @SelectProvider(type = SqlProviderAdapter.class, method = "select")
    @ResultMap("UserResult")
    Optional<User> selectOne(SelectStatementProvider selectStatement);

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.185+08:00",
            comments = "Source Table: tbl_user")
    @SelectProvider(type = SqlProviderAdapter.class, method = "select")
    @Results(id = "UserResult", value = {
            @Result(column = "id", property = "id", jdbcType = JdbcType.VARCHAR, id = true),
            @Result(column = "user_name", property = "userName", jdbcType = JdbcType.VARCHAR),
            @Result(column = "real_name", property = "realName", jdbcType = JdbcType.VARCHAR),
            @Result(column = "gender", property = "gender", jdbcType = JdbcType.VARCHAR),
            @Result(column = "password", property = "password", jdbcType = JdbcType.VARCHAR),
            @Result(column = "phone", property = "phone", jdbcType = JdbcType.VARCHAR),
            @Result(column = "email", property = "email", jdbcType = JdbcType.VARCHAR),
            @Result(column = "id_card", property = "idCard", jdbcType = JdbcType.VARCHAR),
            @Result(column = "avatar_url", property = "avatarUrl", jdbcType = JdbcType.VARCHAR),
            @Result(column = "token", property = "token", jdbcType = JdbcType.VARCHAR),
            @Result(column = "openid", property = "openid", jdbcType = JdbcType.VARCHAR),
            @Result(column = "role_id", property = "roleId", jdbcType = JdbcType.VARCHAR),
            @Result(column = "status", property = "status", jdbcType = JdbcType.SMALLINT),
            @Result(column = "create_time", property = "createTime", jdbcType = JdbcType.BIGINT),
            @Result(column = "update_time", property = "updateTime", jdbcType = JdbcType.BIGINT),
            @Result(column = "create_user_id", property = "createUserId", jdbcType = JdbcType.VARCHAR),
            @Result(column = "update_user_id", property = "updateUserId", jdbcType = JdbcType.VARCHAR)
    })
    List<User> selectMany(SelectStatementProvider selectStatement);

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.188+08:00",
            comments = "Source Table: tbl_user")
    @UpdateProvider(type = SqlProviderAdapter.class, method = "update")
    int update(UpdateStatementProvider updateStatement);

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.188+08:00",
            comments = "Source Table: tbl_user")
    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, user, completer);
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.189+08:00",
            comments = "Source Table: tbl_user")
    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, user, completer);
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.189+08:00",
            comments = "Source Table: tbl_user")
    default int deleteByPrimaryKey(String id_) {
        return delete(c ->
                c.where(id, isEqualTo(id_))
        );
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.19+08:00", comments
            = "Source Table: tbl_user")
    default int insert(User record) {
        return MyBatis3Utils.insert(this::insert, record, user, c ->
                c.map(id).toProperty("id")
                        .map(userName).toProperty("userName")
                        .map(realName).toProperty("realName")
                        .map(gender).toProperty("gender")
                        .map(password).toProperty("password")
                        .map(phone).toProperty("phone")
                        .map(email).toProperty("email")
                        .map(idCard).toProperty("idCard")
                        .map(avatarUrl).toProperty("avatarUrl")
                        .map(token).toProperty("token")
                        .map(openid).toProperty("openid")
                        .map(roleId).toProperty("roleId")
                        .map(status).toProperty("status")
                        .map(createTime).toProperty("createTime")
                        .map(updateTime).toProperty("updateTime")
                        .map(createUserId).toProperty("createUserId")
                        .map(updateUserId).toProperty("updateUserId")
        );
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.193+08:00",
            comments = "Source Table: tbl_user")
    default int insertMultiple(Collection<User> records) {
        return MyBatis3Utils.insertMultiple(this::insertMultiple, records, user, c ->
                c.map(id).toProperty("id")
                        .map(userName).toProperty("userName")
                        .map(realName).toProperty("realName")
                        .map(gender).toProperty("gender")
                        .map(password).toProperty("password")
                        .map(phone).toProperty("phone")
                        .map(email).toProperty("email")
                        .map(idCard).toProperty("idCard")
                        .map(avatarUrl).toProperty("avatarUrl")
                        .map(token).toProperty("token")
                        .map(openid).toProperty("openid")
                        .map(roleId).toProperty("roleId")
                        .map(status).toProperty("status")
                        .map(createTime).toProperty("createTime")
                        .map(updateTime).toProperty("updateTime")
                        .map(createUserId).toProperty("createUserId")
                        .map(updateUserId).toProperty("updateUserId")
        );
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.194+08:00",
            comments = "Source Table: tbl_user")
    default int insertSelective(User record) {
        return MyBatis3Utils.insert(this::insert, record, user, c ->
                c.map(id).toPropertyWhenPresent("id", record::getId)
                        .map(userName).toPropertyWhenPresent("userName", record::getUserName)
                        .map(realName).toPropertyWhenPresent("realName", record::getRealName)
                        .map(gender).toPropertyWhenPresent("gender", record::getGender)
                        .map(password).toPropertyWhenPresent("password", record::getPassword)
                        .map(phone).toPropertyWhenPresent("phone", record::getPhone)
                        .map(email).toPropertyWhenPresent("email", record::getEmail)
                        .map(idCard).toPropertyWhenPresent("idCard", record::getIdCard)
                        .map(avatarUrl).toPropertyWhenPresent("avatarUrl", record::getAvatarUrl)
                        .map(token).toPropertyWhenPresent("token", record::getToken)
                        .map(openid).toPropertyWhenPresent("openid", record::getOpenid)
                        .map(roleId).toPropertyWhenPresent("roleId", record::getRoleId)
                        .map(status).toPropertyWhenPresent("status", record::getStatus)
                        .map(createTime).toPropertyWhenPresent("createTime", record::getCreateTime)
                        .map(updateTime).toPropertyWhenPresent("updateTime", record::getUpdateTime)
                        .map(createUserId).toPropertyWhenPresent("createUserId", record::getCreateUserId)
                        .map(updateUserId).toPropertyWhenPresent("updateUserId", record::getUpdateUserId)
        );
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.198+08:00",
            comments = "Source Table: tbl_user")
    default Optional<User> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, user, completer);
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.198+08:00",
            comments = "Source Table: tbl_user")
    default List<User> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, user, completer);
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.199+08:00",
            comments = "Source Table: tbl_user")
    default List<User> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, user, completer);
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.2+08:00", comments =
            "Source Table: tbl_user")
    default Optional<User> selectByPrimaryKey(String id_) {
        return selectOne(c ->
                c.where(id, isEqualTo(id_))
        );
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.2+08:00", comments =
            "Source Table: tbl_user")
    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, user, completer);
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.201+08:00",
            comments = "Source Table: tbl_user")
    static UpdateDSL<UpdateModel> updateAllColumns(User record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(id).equalTo(record::getId)
                .set(userName).equalTo(record::getUserName)
                .set(realName).equalTo(record::getRealName)
                .set(gender).equalTo(record::getGender)
                .set(password).equalTo(record::getPassword)
                .set(phone).equalTo(record::getPhone)
                .set(email).equalTo(record::getEmail)
                .set(idCard).equalTo(record::getIdCard)
                .set(avatarUrl).equalTo(record::getAvatarUrl)
                .set(token).equalTo(record::getToken)
                .set(openid).equalTo(record::getOpenid)
                .set(roleId).equalTo(record::getRoleId)
                .set(status).equalTo(record::getStatus)
                .set(createTime).equalTo(record::getCreateTime)
                .set(updateTime).equalTo(record::getUpdateTime)
                .set(createUserId).equalTo(record::getCreateUserId)
                .set(updateUserId).equalTo(record::getUpdateUserId);
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.202+08:00",
            comments = "Source Table: tbl_user")
    static UpdateDSL<UpdateModel> updateSelectiveColumns(User record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(id).equalToWhenPresent(record::getId)
                .set(userName).equalToWhenPresent(record::getUserName)
                .set(realName).equalToWhenPresent(record::getRealName)
                .set(gender).equalToWhenPresent(record::getGender)
                .set(password).equalToWhenPresent(record::getPassword)
                .set(phone).equalToWhenPresent(record::getPhone)
                .set(email).equalToWhenPresent(record::getEmail)
                .set(idCard).equalToWhenPresent(record::getIdCard)
                .set(avatarUrl).equalToWhenPresent(record::getAvatarUrl)
                .set(token).equalToWhenPresent(record::getToken)
                .set(openid).equalToWhenPresent(record::getOpenid)
                .set(roleId).equalToWhenPresent(record::getRoleId)
                .set(status).equalToWhenPresent(record::getStatus)
                .set(createTime).equalToWhenPresent(record::getCreateTime)
                .set(updateTime).equalToWhenPresent(record::getUpdateTime)
                .set(createUserId).equalToWhenPresent(record::getCreateUserId)
                .set(updateUserId).equalToWhenPresent(record::getUpdateUserId);
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.203+08:00",
            comments = "Source Table: tbl_user")
    default int updateByPrimaryKey(User record) {
        return update(c ->
                c.set(userName).equalTo(record::getUserName)
                        .set(realName).equalTo(record::getRealName)
                        .set(gender).equalTo(record::getGender)
                        .set(password).equalTo(record::getPassword)
                        .set(phone).equalTo(record::getPhone)
                        .set(email).equalTo(record::getEmail)
                        .set(idCard).equalTo(record::getIdCard)
                        .set(avatarUrl).equalTo(record::getAvatarUrl)
                        .set(token).equalTo(record::getToken)
                        .set(openid).equalTo(record::getOpenid)
                        .set(roleId).equalTo(record::getRoleId)
                        .set(status).equalTo(record::getStatus)
                        .set(createTime).equalTo(record::getCreateTime)
                        .set(updateTime).equalTo(record::getUpdateTime)
                        .set(createUserId).equalTo(record::getCreateUserId)
                        .set(updateUserId).equalTo(record::getUpdateUserId)
                        .where(id, isEqualTo(record::getId))
        );
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.203+08:00",
            comments = "Source Table: tbl_user")
    default int updateByPrimaryKeySelective(User record) {
        return update(c ->
                c.set(userName).equalToWhenPresent(record::getUserName)
                        .set(realName).equalToWhenPresent(record::getRealName)
                        .set(gender).equalToWhenPresent(record::getGender)
                        .set(password).equalToWhenPresent(record::getPassword)
                        .set(phone).equalToWhenPresent(record::getPhone)
                        .set(email).equalToWhenPresent(record::getEmail)
                        .set(idCard).equalToWhenPresent(record::getIdCard)
                        .set(avatarUrl).equalToWhenPresent(record::getAvatarUrl)
                        .set(token).equalToWhenPresent(record::getToken)
                        .set(openid).equalToWhenPresent(record::getOpenid)
                        .set(roleId).equalToWhenPresent(record::getRoleId)
                        .set(status).equalToWhenPresent(record::getStatus)
                        .set(createTime).equalToWhenPresent(record::getCreateTime)
                        .set(updateTime).equalToWhenPresent(record::getUpdateTime)
                        .set(createUserId).equalToWhenPresent(record::getCreateUserId)
                        .set(updateUserId).equalToWhenPresent(record::getUpdateUserId)
                        .where(id, isEqualTo(record::getId))
        );
    }

    @SelectProvider(type = SqlProviderAdapter.class, method = "select")
    @Results(id = "UserVOResult", value = {
            @Result(column = "id", property = "id", jdbcType = JdbcType.VARCHAR, id = true),
            @Result(column = "user_name", property = "userName", jdbcType = JdbcType.VARCHAR),
            @Result(column = "real_name", property = "realName", jdbcType = JdbcType.VARCHAR),
            @Result(column = "token", property = "token", jdbcType = JdbcType.VARCHAR),
            @Result(column = "phone", property = "phone", jdbcType = JdbcType.VARCHAR),
            @Result(column = "gender", property = "gender", jdbcType = JdbcType.VARCHAR),
            @Result(column = "avatar_url", property = "avatarUrl", jdbcType = JdbcType.VARCHAR),
            @Result(column = "openid", property = "openid", jdbcType = JdbcType.VARCHAR),
            @Result(column = "role_id", property = "roleId", jdbcType = JdbcType.VARCHAR),
            @Result(column = "status", property = "status", jdbcType = JdbcType.SMALLINT),
            @Result(column = "create_time", property = "createTime", jdbcType = JdbcType.BIGINT),
            @Result(column = "update_time", property = "updateTime", jdbcType = JdbcType.BIGINT),
            @Result(column = "create_user_id", property = "createUserId", jdbcType = JdbcType.VARCHAR),
            @Result(column = "update_user_id", property = "updateUserId", jdbcType = JdbcType.VARCHAR),
            @Result(column = "roleName", property = "roleName", jdbcType = JdbcType.VARCHAR)
    })
    List<UserVO> selectUserVOs(SelectStatementProvider selectStatement);
}