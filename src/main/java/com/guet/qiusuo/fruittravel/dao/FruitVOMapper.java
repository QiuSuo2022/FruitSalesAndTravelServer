package com.guet.qiusuo.fruittravel.dao;

import com.guet.qiusuo.fruittravel.bean.vo.FruitVO;
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

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static com.guet.qiusuo.fruittravel.dao.FruitVODynamicSqlSupport.*;
import static com.guet.qiusuo.fruittravel.dao.FruitVODynamicSqlSupport.createTime;
import static com.guet.qiusuo.fruittravel.dao.FruitVODynamicSqlSupport.createUserId;
import static com.guet.qiusuo.fruittravel.dao.FruitVODynamicSqlSupport.id;
import static com.guet.qiusuo.fruittravel.dao.FruitVODynamicSqlSupport.description;
import static com.guet.qiusuo.fruittravel.dao.FruitDynamicSqlSupport.*;
import static com.guet.qiusuo.fruittravel.dao.FruitVODynamicSqlSupport.status;
import static com.guet.qiusuo.fruittravel.dao.FruitVODynamicSqlSupport.updateTime;
import static com.guet.qiusuo.fruittravel.dao.ScenicVODynamicSqlSupport.*;
import static com.guet.qiusuo.fruittravel.dao.ScenicVODynamicSqlSupport.updateUserId;
import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;
import static org.mybatis.dynamic.sql.SqlBuilder.isEqualToWhenPresent;

/**
 * @author user
 */
@Repository
@Mapper
public interface FruitVOMapper {
    BasicColumn[] selectList = BasicColumn.columnList(id,childFruitName,stock,fruitName,deliveryCost,departurePoint,
            fruitPrice,description,status,createTime,updateTime,createUserId,updateUserId);

    @SelectProvider(type = SqlProviderAdapter.class, method = "select")
    long count(SelectStatementProvider selectStatement);

    @DeleteProvider(type = SqlProviderAdapter.class, method = "delete")
    int delete(DeleteStatementProvider deleteStatement);

    @InsertProvider(type = SqlProviderAdapter.class, method = "insert")
    int insert(InsertStatementProvider<FruitVO> insertStatement);

    @InsertProvider(type = SqlProviderAdapter.class, method = "insertMultiple")
    int insertMultiple(MultiRowInsertStatementProvider<FruitVO> multipleInsertStatement);

    @SelectProvider(type = SqlProviderAdapter.class, method = "select")
    @ResultMap("ScenicTicketResult")
    Optional<FruitVO> selectOne(SelectStatementProvider selectStatement);

    @SelectProvider(type = SqlProviderAdapter.class, method = "select")
    @Results(id = "ScenicTicketResult", value = {
            @Result(column = "id", property = "id", jdbcType = JdbcType.VARCHAR, id = true),
            @Result(column = "description", property = "description", jdbcType = JdbcType.VARCHAR),
            @Result(column = "stock", property = "stock", jdbcType = JdbcType.INTEGER),
            @Result(column = "child_fruit_name", property = "childFruitName", jdbcType = JdbcType.VARCHAR),
            @Result(column = "fruit_price",property = "fruitPrice",jdbcType = JdbcType.VARCHAR),
            @Result(column = "fruit_name",property = "fruitName",jdbcType = JdbcType.VARCHAR),
            @Result(column = "delivery_cost", property = "deliveryCost", jdbcType = JdbcType.INTEGER),
            @Result(column = "departure_point", property = "departurePoint", jdbcType = JdbcType.VARCHAR),
            @Result(column = "status", property = "status", jdbcType = JdbcType.SMALLINT),
            @Result(column = "create_time", property = "createTime", jdbcType = JdbcType.BIGINT),
            @Result(column = "update_time", property = "updateTime", jdbcType = JdbcType.BIGINT),
            @Result(column = "create_user_id", property = "createUserId", jdbcType = JdbcType.VARCHAR),
            @Result(column = "update_user_id", property = "updateUserId", jdbcType = JdbcType.VARCHAR)
    })
    List<FruitVO> selectMany(SelectStatementProvider selectStatement);

    @UpdateProvider(type = SqlProviderAdapter.class, method = "update")
    int update(UpdateStatementProvider updateStatement);

    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, fruitVO, completer);
    }

    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, fruitVO, completer);
    }

    default int deleteByPrimaryKey(String id_) {
        return delete(c ->
                c.where(id, isEqualTo(id_))
        );
    }

    default int insert(FruitVO record) {
        return MyBatis3Utils.insert(this::insert, record, fruitVO, c ->
                c.map(id).toProperty("id")
                        .map(fruitPrice).toProperty("fruitPrice")
                        .map(fruitName).toProperty("ticketId")
                        .map(childFruitName).toProperty("childFruitName")
                        .map(description).toProperty("description")
                        .map(departurePoint).toProperty("departurePoint")
                        .map(deliveryCost).toProperty("deliveryCost")
                        .map(stock).toProperty("stock")
                        .map(status).toProperty("status")
                        .map(createTime).toProperty("createTime")
                        .map(updateTime).toProperty("updateTime")
                        .map(createUserId).toProperty("createUserId")
                        .map(updateUserId).toProperty("updateUserId")
        );
    }

    default int insertMultiple(Collection<FruitVO> records) {
        return MyBatis3Utils.insertMultiple(this::insertMultiple, records, fruitVO, c ->
                c.map(id).toProperty("id")
                        .map(fruitPrice).toProperty("fruitPrice")
                        .map(fruitName).toProperty("ticketId")
                        .map(childFruitName).toProperty("childFruitName")
                        .map(description).toProperty("description")
                        .map(departurePoint).toProperty("departurePoint")
                        .map(deliveryCost).toProperty("deliveryCost")
                        .map(stock).toProperty("stock")
                        .map(status).toProperty("status")
                        .map(createTime).toProperty("createTime")
                        .map(updateTime).toProperty("updateTime")
                        .map(createUserId).toProperty("createUserId")
                        .map(updateUserId).toProperty("updateUserId")
        );
    }

    default int insertSelective(FruitVO record) {
        return MyBatis3Utils.insert(this::insert, record, fruitVO, c ->
                c.map(id).toPropertyWhenPresent("id", record::getId)
                        .map(fruitPrice).toPropertyWhenPresent("description", record::getFruitPrice)
                        .map(fruitName).toPropertyWhenPresent("ticketId", record::getFruitName)
                        .map(childFruitName).toPropertyWhenPresent("childFruitName", record::getChildFruitName)
                        .map(description).toPropertyWhenPresent("description", record::getDescription)
                        .map(departurePoint).toPropertyWhenPresent("departurePoint", record::getDeparturePoint)
                        .map(deliveryCost).toPropertyWhenPresent("deliveryCost", record::getDeliveryCost)
                        .map(stock).toPropertyWhenPresent("stock", record::getStock)
                        .map(status).toPropertyWhenPresent("status", record::getStatus)
                        .map(createTime).toPropertyWhenPresent("createTime", record::getCreateTime)
                        .map(updateTime).toPropertyWhenPresent("updateTime", record::getUpdateTime)
                        .map(createUserId).toPropertyWhenPresent("createUserId", record::getCreateUserId)
                        .map(updateUserId).toPropertyWhenPresent("updateUserId", record::getUpdateUserId)
        );
    }

    default Optional<FruitVO> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, fruitVO, completer);
    }

    default List<FruitVO> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, fruitVO, completer);
    }

    default List<FruitVO> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, fruitVO, completer);
    }

    default Optional<FruitVO> selectByPrimaryKey(String id_) {
        return selectOne(c ->
                c.where(id, isEqualTo(id_))
        );
    }

    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, fruitVO, completer);
    }

    static UpdateDSL<UpdateModel> updateAllColumns(FruitVO record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(id).equalTo(record::getId)
                .set(description).equalTo(record::getDescription)
                .set(fruitPrice).equalTo(record::getFruitPrice)
                .set(childFruitName).equalTo(record::getChildFruitName)
                .set(stock).equalTo(record::getStock)
                .set(fruitName).equalTo(record::getFruitName)
                .set(deliveryCost).equalTo(record::getDeliveryCost)
                .set(departurePoint).equalTo(record::getDeparturePoint)
                .set(status).equalTo(record::getStatus)
                .set(createTime).equalTo(record::getCreateTime)
                .set(updateTime).equalTo(record::getUpdateTime)
                .set(createUserId).equalTo(record::getCreateUserId)
                .set(updateUserId).equalTo(record::getUpdateUserId);
    }

    static UpdateDSL<UpdateModel> updateSelectiveColumns(FruitVO record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(id).equalToWhenPresent(record::getId)
                .set(description).equalToWhenPresent(record::getDescription)
                .set(fruitPrice).equalToWhenPresent(record::getFruitPrice)
                .set(childFruitName).equalToWhenPresent(record::getChildFruitName)
                .set(stock).equalToWhenPresent(record::getStock)
                .set(fruitName).equalToWhenPresent(record::getFruitName)
                .set(deliveryCost).equalToWhenPresent(record::getDeliveryCost)
                .set(departurePoint).equalToWhenPresent(record::getDeparturePoint)
                .set(status).equalToWhenPresent(record::getStatus)
                .set(createTime).equalToWhenPresent(record::getCreateTime)
                .set(updateTime).equalToWhenPresent(record::getUpdateTime)
                .set(createUserId).equalToWhenPresent(record::getCreateUserId)
                .set(updateUserId).equalToWhenPresent(record::getUpdateUserId);
    }

    default int updateByPrimaryKey(FruitVO record) {
        return update(c ->
                c.set(description).equalTo(record::getDescription)
                        .set(fruitPrice).equalTo(record::getFruitPrice)
                        .set(childFruitName).equalTo(record::getChildFruitName)
                        .set(stock).equalTo(record::getStock)
                        .set(fruitName).equalTo(record::getFruitName)
                        .set(deliveryCost).equalTo(record::getDeliveryCost)
                        .set(departurePoint).equalTo(record::getDeparturePoint)
                        .set(status).equalTo(record::getStatus)
                        .set(createTime).equalTo(record::getCreateTime)
                        .set(updateTime).equalTo(record::getUpdateTime)
                        .set(createUserId).equalTo(record::getCreateUserId)
                        .set(updateUserId).equalTo(record::getUpdateUserId)
                        .where(id, isEqualTo(record::getId))
        );
    }

    default int updateByPrimaryKeySelective(FruitVO record) {
        return update(c ->
                c.set(description).equalToWhenPresent(record::getDescription)
                        .set(fruitPrice).equalToWhenPresent(record::getFruitPrice)
                        .set(childFruitName).equalToWhenPresent(record::getChildFruitName)
                        .set(stock).equalToWhenPresent(record::getStock)
                        .set(fruitName).equalToWhenPresent(record::getFruitName)
                        .set(deliveryCost).equalToWhenPresent(record::getDeliveryCost)
                        .set(departurePoint).equalToWhenPresent(record::getDeparturePoint)
                        .set(status).equalToWhenPresent(record::getStatus)
                        .set(createTime).equalToWhenPresent(record::getCreateTime)
                        .set(updateTime).equalToWhenPresent(record::getUpdateTime)
                        .set(createUserId).equalToWhenPresent(record::getCreateUserId)
                        .set(updateUserId).equalToWhenPresent(record::getUpdateUserId)
                        .where(id, isEqualToWhenPresent(record::getId))
        );
    }
}
