package com.guet.qiusuo.fruittravel.dao;

import com.guet.qiusuo.fruittravel.model.Fruit;
import com.guet.qiusuo.fruittravel.model.Scenic;
import com.guet.qiusuo.fruittravel.model.FruitScenic;
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


import static com.guet.qiusuo.fruittravel.dao.FruitScenicDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;


@Repository
@Mapper
public interface FruitScenicMapper {
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-02-25T11:39:23.859+08:00",
            comments = "Source Table: tbl_fruit_scenic")
    BasicColumn[] selectList = BasicColumn.columnList(id, fruitId, fruitName, fruitPrice, fruitDescription,
            departurePoint, deliveryCost, scenicId, scenicName, location, openingHours, scenicDescription, type,
            status, createTime, updateTime, createUserId, updateUserId);

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-02-25T11:39:23.859+08:00",
            comments = "Source Table: tbl_fruit_scenic")
    @SelectProvider(type = SqlProviderAdapter.class, method = "select")
    long count(SelectStatementProvider selectStatement);

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-02-25T11:39:23.859+08:00",
            comments = "Source Table: tbl_fruit_scenic")
    @DeleteProvider(type = SqlProviderAdapter.class, method = "delete")
    int delete(DeleteStatementProvider deleteStatement);

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-02-25T11:39:23.859+08:00",
            comments = "Source Table: tbl_fruit_scenic")
    @InsertProvider(type = SqlProviderAdapter.class, method = "insert")
    int insert(InsertStatementProvider<FruitScenic> insertStatement);

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-02-25T11:39:23.859+08:00",
            comments = "Source Table: tbl_fruit_scenic")
    @InsertProvider(type = SqlProviderAdapter.class, method = "insertMultiple")
    int insertMultiple(MultiRowInsertStatementProvider<FruitScenic> multipleInsertStatement);

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-02-25T11:39:23.859+08:00",
            comments = "Source Table: tbl_fruit_scenic")
    @SelectProvider(type = SqlProviderAdapter.class, method = "select")
    @ResultMap("FruitScenicResult")
    Optional<Scenic> selectOne(SelectStatementProvider selectStatement);

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-02-25T11:39:23.859+08:00",
            comments = "Source Table: tbl_fruit_scenic")
    @SelectProvider(type = SqlProviderAdapter.class, method = "select")
    @Results(id = "FruitScenicResult", value = {
            @Result(column = "id", property = "id", jdbcType = JdbcType.VARCHAR, id = true),
            @Result(column = "fruit_id", property = "fruitId", jdbcType = JdbcType.VARCHAR),
            @Result(column = "fruit_name", property = "fruitName", jdbcType = JdbcType.VARCHAR),
            @Result(column = "fruit_price", property = "fruitPrice", jdbcType = JdbcType.VARCHAR),
            @Result(column = "fruit_description", property = "fruitDescription", jdbcType = JdbcType.VARCHAR),
            @Result(column = "departure_point", property = "departurePoint", jdbcType = JdbcType.VARCHAR),
            @Result(column = "delivery_cost", property = "deliveryCost", jdbcType = JdbcType.INTEGER),
            @Result(column = "scenic_id", property = "scenicId", jdbcType = JdbcType.VARCHAR),
            @Result(column = "scenic_name", property = "scenicName", jdbcType = JdbcType.VARCHAR),
            @Result(column = "location", property = "location", jdbcType = JdbcType.VARCHAR),
            @Result(column = "opening_hours", property = "openingHours", jdbcType = JdbcType.VARCHAR),
            @Result(column = "scenic_description", property = "scenicDescription", jdbcType = JdbcType.VARCHAR),
            @Result(column = "type", property = "type", jdbcType = JdbcType.SMALLINT),
            @Result(column = "status", property = "status", jdbcType = JdbcType.SMALLINT),
            @Result(column = "create_time", property = "createTime", jdbcType = JdbcType.BIGINT),
            @Result(column = "update_time", property = "updateTime", jdbcType = JdbcType.BIGINT),
            @Result(column = "create_user_id", property = "createUserId", jdbcType = JdbcType.VARCHAR),
            @Result(column = "update_user_id", property = "updateUserId", jdbcType = JdbcType.VARCHAR)
    })
    List<FruitScenic> selectMany(SelectStatementProvider selectStatement);

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-02-25T11:39:23.859+08:00",
            comments = "Source Table: tbl_fruit_scenic")
    @UpdateProvider(type = SqlProviderAdapter.class, method = "update")
    int update(UpdateStatementProvider updateStatement);

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-02-25T11:39:23.859+08:00",
            comments = "Source Table: tbl_fruit_scenic")
    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, fruitScenic, completer);
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-02-25T11:39:23.859+08:00",
            comments = "Source Table: tbl_fruit_scenic")
    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, fruitScenic, completer);
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-02-25T11:39:23.859+08:00",
            comments = "Source Table: tbl_fruit_scenic")
    default int deleteByPrimaryKey(String id_) {
        return delete(c ->
                c.where(id, isEqualTo(id_))
        );
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-02-25T11:39:23.859+08:00",
            comments = "Source Table: tbl_fruit_scenic")
    default int insert(FruitScenic record) {
        return MyBatis3Utils.insert(this::insert, record, fruitScenic, c ->
                c.map(id).toProperty("id")
                        .map(fruitId).toProperty("fruitId")
                        .map(fruitName).toProperty("fruitName")
                        .map(fruitPrice).toProperty("fruitPrice")
                        .map(fruitDescription).toProperty("fruitDescription")
                        .map(departurePoint).toProperty("departurePoint")
                        .map(deliveryCost).toProperty("deliveryCost")
                        .map(scenicId).toProperty("scenicId")
                        .map(scenicName).toProperty("scenicName")
                        .map(location).toProperty("location")
                        .map(openingHours).toProperty("openingHours")
                        .map(scenicDescription).toProperty("scenicDescription")
                        .map(type).toProperty("type")
                        .map(status).toProperty("status")
                        .map(createTime).toProperty("createTime")
                        .map(updateTime).toProperty("updateTime")
                        .map(createUserId).toProperty("createUserId")
                        .map(updateUserId).toProperty("updateUserId")
        );
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-02-25T11:39:23.859+08:00",
            comments = "Source Table: tbl_fruit_scenic")
    default int insertMultiple(Collection<FruitScenic> records) {
        return MyBatis3Utils.insertMultiple(this::insertMultiple, records, fruitScenic, c ->
                c.map(id).toProperty("id")
                        .map(fruitId).toProperty("fruitId")
                        .map(fruitName).toProperty("fruitName")
                        .map(fruitPrice).toProperty("fruitPrice")
                        .map(fruitDescription).toProperty("fruitDescription")
                        .map(departurePoint).toProperty("departurePoint")
                        .map(deliveryCost).toProperty("deliveryCost")
                        .map(scenicId).toProperty("scenicId")
                        .map(scenicName).toProperty("scenicName")
                        .map(location).toProperty("location")
                        .map(openingHours).toProperty("openingHours")
                        .map(scenicDescription).toProperty("scenicDescription")
                        .map(type).toProperty("type")
                        .map(status).toProperty("status")
                        .map(createTime).toProperty("createTime")
                        .map(updateTime).toProperty("updateTime")
                        .map(createUserId).toProperty("createUserId")
                        .map(updateUserId).toProperty("updateUserId")
        );
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-02-25T11:39:23.859+08:00",
            comments = "Source Table: tbl_fruit_scenic")
    default int insertSelective(FruitScenic record) {
        return MyBatis3Utils.insert(this::insert, record, fruitScenic, c ->
                c.map(id).toPropertyWhenPresent("id", record::getId)
                        .map(fruitId).toPropertyWhenPresent("fruitId", record::getFruitId)
                        .map(fruitName).toPropertyWhenPresent("fruitName", record::getFruitName)
                        .map(fruitPrice).toPropertyWhenPresent("fruitPrice", record::getFruitPrice)
                        .map(fruitDescription).toPropertyWhenPresent("description", record::getFruitDescription)
                        .map(departurePoint).toPropertyWhenPresent("departurePoint", record::getDeparturePoint)
                        .map(deliveryCost).toPropertyWhenPresent("deliveryCost", record::getDeliveryCost)
                        .map(scenicId).toPropertyWhenPresent("scenicId", record::getScenicId)
                        .map(scenicName).toPropertyWhenPresent("scenicName", record::getScenicName)
                        .map(location).toPropertyWhenPresent("location", record::getLocation)
                        .map(openingHours).toPropertyWhenPresent("openingHours", record::getOpeningHours)
                        .map(scenicDescription).toPropertyWhenPresent("description", record::getscenicDescription)
                        .map(type).toPropertyWhenPresent("type", record::getType)
                        .map(status).toPropertyWhenPresent("status", record::getStatus)
                        .map(createTime).toPropertyWhenPresent("createTime", record::getCreateTime)
                        .map(updateTime).toPropertyWhenPresent("updateTime", record::getUpdateTime)
                        .map(createUserId).toPropertyWhenPresent("createUserId", record::getCreateUserId)
                        .map(updateUserId).toPropertyWhenPresent("updateUserId", record::getUpdateUserId)
        );
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-02-25T11:39:23.859+08:00",
            comments = "Source Table: tbl_fruit_scenic")
    default Optional<Scenic> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, fruitScenic, completer);
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-02-25T11:39:23.859+08:00",
            comments = "Source Table: tbl_fruit_scenic")
    default List<FruitScenic> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, fruitScenic, completer);
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-02-25T11:39:23.859+08:00",
            comments = "Source Table: tbl_fruit_scenic")
    default List<FruitScenic> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, fruitScenic, completer);
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-02-25T11:39:23.859+08:00",
            comments = "Source Table: tbl_fruit_scenic")
    default Optional<Scenic> selectByPrimaryKey(String id_) {
        return selectOne(c ->
                c.where(id, isEqualTo(id_))
        );
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-02-25T11:39:23.859+08:00",
            comments = "Source Table: tbl_fruit_scenic")
    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, fruitScenic, completer);
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-02-25T11:39:23.859+08:00",
            comments = "Source Table: tbl_fruit_scenic")
    static UpdateDSL<UpdateModel> updateAllColumns(FruitScenic record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(id).equalTo(record::getId)
                .set(fruitId).equalTo(record::getFruitId)
                .set(fruitName).equalTo(record::getFruitName)
                .set(fruitPrice).equalTo(record::getFruitPrice)
                .set(fruitDescription).equalTo(record::getFruitDescription)
                .set(departurePoint).equalTo(record::getDeparturePoint)
                .set(deliveryCost).equalTo(record::getDeliveryCost)
                .set(scenicId).equalTo(record::getScenicId)
                .set(scenicName).equalTo(record::getScenicName)
                .set(location).equalTo(record::getLocation)
                .set(openingHours).equalTo(record::getOpeningHours)
                .set(scenicDescription).equalTo(record::getscenicDescription)
                .set(type).equalTo(record::getType)
                .set(status).equalTo(record::getStatus)
                .set(createTime).equalTo(record::getCreateTime)
                .set(updateTime).equalTo(record::getUpdateTime)
                .set(createUserId).equalTo(record::getCreateUserId)
                .set(updateUserId).equalTo(record::getUpdateUserId);
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-02-25T11:39:23.859+08:00",
            comments = "Source Table: tbl_fruit_scenic")
    static UpdateDSL<UpdateModel> updateSelectiveColumns(FruitScenic record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(id).equalToWhenPresent(record::getId)
                .set(fruitId).equalToWhenPresent(record::getFruitId)
                .set(fruitName).equalToWhenPresent(record::getFruitName)
                .set(fruitPrice).equalToWhenPresent(record::getFruitPrice)
                .set(fruitDescription).equalToWhenPresent(record::getFruitDescription)
                .set(departurePoint).equalToWhenPresent(record::getDeparturePoint)
                .set(deliveryCost).equalToWhenPresent(record::getDeliveryCost)
                .set(scenicId).equalToWhenPresent(record::getScenicId)
                .set(scenicName).equalToWhenPresent(record::getScenicName)
                .set(location).equalToWhenPresent(record::getLocation)
                .set(openingHours).equalToWhenPresent(record::getOpeningHours)
                .set(scenicDescription).equalToWhenPresent(record::getscenicDescription)
                .set(type).equalToWhenPresent(record::getType)
                .set(status).equalToWhenPresent(record::getStatus)
                .set(createTime).equalToWhenPresent(record::getCreateTime)
                .set(updateTime).equalToWhenPresent(record::getUpdateTime)
                .set(createUserId).equalToWhenPresent(record::getCreateUserId)
                .set(updateUserId).equalToWhenPresent(record::getUpdateUserId);
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-02-25T11:39:23.859+08:00",
            comments = "Source Table: tbl_fruit_scenic")
    default int updateByPrimaryKey(FruitScenic record) {
        return update(c ->
                c.set(fruitId).equalTo(record::getFruitId)
                        .set(fruitName).equalTo(record::getFruitName)
                        .set(fruitPrice).equalTo(record::getFruitPrice)
                        .set(fruitDescription).equalTo(record::getFruitDescription)
                        .set(departurePoint).equalTo(record::getDeparturePoint)
                        .set(deliveryCost).equalTo(record::getDeliveryCost)
                        .set(scenicId).equalTo(record::getScenicId)
                        .set(scenicName).equalTo(record::getScenicName)
                        .set(location).equalTo(record::getLocation)
                        .set(openingHours).equalTo(record::getOpeningHours)
                        .set(scenicDescription).equalTo(record::getscenicDescription)
                        .set(type).equalTo(record::getType)
                        .set(status).equalTo(record::getStatus)
                        .set(createTime).equalTo(record::getCreateTime)
                        .set(updateTime).equalTo(record::getUpdateTime)
                        .set(createUserId).equalTo(record::getCreateUserId)
                        .set(updateUserId).equalTo(record::getUpdateUserId)
                        .where(id, isEqualTo(record::getId))
        );
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-02-25T11:39:23.859+08:00",
            comments = "Source Table: tbl_fruit_scenic")
    default int updateByPrimaryKeySelective(FruitScenic record) {
        return update(c ->
                c.set(fruitId).equalToWhenPresent(record::getFruitId)
                        .set(fruitName).equalToWhenPresent(record::getFruitName)
                        .set(fruitPrice).equalToWhenPresent(record::getFruitPrice)
                        .set(fruitDescription).equalToWhenPresent(record::getFruitDescription)
                        .set(departurePoint).equalToWhenPresent(record::getDeparturePoint)
                        .set(deliveryCost).equalToWhenPresent(record::getDeliveryCost)
                        .set(scenicId).equalToWhenPresent(record::getScenicId)
                        .set(scenicName).equalToWhenPresent(record::getScenicName)
                        .set(location).equalToWhenPresent(record::getLocation)
                        .set(openingHours).equalToWhenPresent(record::getOpeningHours)
                        .set(scenicDescription).equalToWhenPresent(record::getscenicDescription)
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
    @Results(id = "FruitScenic", value = {
            @Result(column = "fruit_id", property = "fruitId",jdbcType = JdbcType.VARCHAR,id = true),
            @Result(column = "fruit_name", property = "fruitName", jdbcType = JdbcType.VARCHAR),
            @Result(column = "fruit_price", property = "fruitPrice", jdbcType = JdbcType.VARCHAR),
            @Result(column = "departure_point", property = "departurePoint", jdbcType = JdbcType.VARCHAR),
            @Result(column = "delivery_cost", property = "deliveryCost", jdbcType = JdbcType.INTEGER),
            @Result(column = "fruit_description", property = "fruitDescription", jdbcType = JdbcType.VARCHAR),
            @Result(column = "scenic_id", property = "scenicId",jdbcType = JdbcType.VARCHAR),
            @Result(column = "opening_hours", property = "openingHours", jdbcType = JdbcType.VARCHAR),
            @Result(column = "scenic_name", property = "scenicName", jdbcType = JdbcType.VARCHAR),
            @Result(column = "location", property = "location", jdbcType = JdbcType.VARCHAR),
            @Result(column = "type", property = "type", jdbcType = JdbcType.SMALLINT),
            @Result(column = "scenic_description", property = "scenicDescription", jdbcType = JdbcType.VARCHAR),
    })
    List<FruitScenic> selectFruitScenic(SelectStatementProvider selectStatement);
}
