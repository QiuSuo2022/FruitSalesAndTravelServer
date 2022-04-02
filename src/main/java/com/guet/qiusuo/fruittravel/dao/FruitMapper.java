package com.guet.qiusuo.fruittravel.dao;

import static com.guet.qiusuo.fruittravel.dao.FruitDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.*;

import com.guet.qiusuo.fruittravel.bean.vo.FruitVO;
import com.guet.qiusuo.fruittravel.model.Fruit;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import javax.annotation.Generated;

import com.guet.qiusuo.fruittravel.model.Scenic;
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

@Repository
@Mapper
public interface FruitMapper {
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.237+08:00",
            comments = "Source Table: tbl_fruit")
    BasicColumn[] selectList = BasicColumn.columnList(id, fruitName, fruitPrice, description, departurePoint,
            deliveryCost, status, createTime, updateTime, createUserId, updateUserId);

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.236+08:00",
            comments = "Source Table: tbl_fruit")
    @SelectProvider(type = SqlProviderAdapter.class, method = "select")
    long count(SelectStatementProvider selectStatement);

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.236+08:00",
            comments = "Source Table: tbl_fruit")
    @DeleteProvider(type = SqlProviderAdapter.class, method = "delete")
    int delete(DeleteStatementProvider deleteStatement);

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.236+08:00",
            comments = "Source Table: tbl_fruit")
    @InsertProvider(type = SqlProviderAdapter.class, method = "insert")
    int insert(InsertStatementProvider<Fruit> insertStatement);

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.236+08:00",
            comments = "Source Table: tbl_fruit")
    @InsertProvider(type = SqlProviderAdapter.class, method = "insertMultiple")
    int insertMultiple(MultiRowInsertStatementProvider<Fruit> multipleInsertStatement);

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.236+08:00",
            comments = "Source Table: tbl_fruit")
    @SelectProvider(type = SqlProviderAdapter.class, method = "select")
    @ResultMap("FruitResult")
    Optional<Fruit> selectOne(SelectStatementProvider selectStatement);

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.236+08:00",
            comments = "Source Table: tbl_fruit")
    @SelectProvider(type = SqlProviderAdapter.class, method = "select")
    @Results(id = "FruitResult", value = {
            @Result(column = "id", property = "id", jdbcType = JdbcType.VARCHAR, id = true),
            @Result(column = "fruit_name", property = "fruitName", jdbcType = JdbcType.VARCHAR),
            @Result(column = "fruit_price", property = "fruitPrice", jdbcType = JdbcType.VARCHAR),
            @Result(column = "description", property = "description", jdbcType = JdbcType.VARCHAR),
            @Result(column = "departure_point", property = "departurePoint", jdbcType = JdbcType.VARCHAR),
            @Result(column = "delivery_cost", property = "deliveryCost", jdbcType = JdbcType.INTEGER),
            @Result(column = "status", property = "status", jdbcType = JdbcType.SMALLINT),
            @Result(column = "create_time", property = "createTime", jdbcType = JdbcType.BIGINT),
            @Result(column = "update_time", property = "updateTime", jdbcType = JdbcType.BIGINT),
            @Result(column = "create_user_id", property = "createUserId", jdbcType = JdbcType.VARCHAR),
            @Result(column = "update_user_id", property = "updateUserId", jdbcType = JdbcType.VARCHAR)
    })
    List<Fruit> selectMany(SelectStatementProvider selectStatement);

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.236+08:00",
            comments = "Source Table: tbl_fruit")
    @UpdateProvider(type = SqlProviderAdapter.class, method = "update")
    int update(UpdateStatementProvider updateStatement);

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.236+08:00",
            comments = "Source Table: tbl_fruit")
    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, fruit, completer);
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.236+08:00",
            comments = "Source Table: tbl_fruit")
    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, fruit, completer);
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.236+08:00",
            comments = "Source Table: tbl_fruit")
    default int deleteByPrimaryKey(String id_) {
        return delete(c ->
                c.where(id, isEqualTo(id_))
        );
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.237+08:00",
            comments = "Source Table: tbl_fruit")
    default int insert(Fruit record) {
        return MyBatis3Utils.insert(this::insert, record, fruit, c ->
                c.map(id).toProperty("id")
                        .map(fruitName).toProperty("fruitName")
                        .map(fruitPrice).toProperty("fruitPrice")
                        .map(description).toProperty("description")
                        .map(departurePoint).toProperty("departurePoint")
                        .map(deliveryCost).toProperty("deliveryCost")
                        .map(status).toProperty("status")
                        .map(createTime).toProperty("createTime")
                        .map(updateTime).toProperty("updateTime")
                        .map(createUserId).toProperty("createUserId")
                        .map(updateUserId).toProperty("updateUserId")
        );
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.237+08:00",
            comments = "Source Table: tbl_fruit")
    default int insertMultiple(Collection<Fruit> records) {
        return MyBatis3Utils.insertMultiple(this::insertMultiple, records, fruit, c ->
                c.map(id).toProperty("id")
                        .map(fruitName).toProperty("fruitName")
                        .map(fruitPrice).toProperty("fruitPrice")
                        .map(description).toProperty("description")
                        .map(departurePoint).toProperty("departurePoint")
                        .map(deliveryCost).toProperty("deliveryCost")
                        .map(status).toProperty("status")
                        .map(createTime).toProperty("createTime")
                        .map(updateTime).toProperty("updateTime")
                        .map(createUserId).toProperty("createUserId")
                        .map(updateUserId).toProperty("updateUserId")
        );
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.237+08:00",
            comments = "Source Table: tbl_fruit")
    default int insertSelective(Fruit record) {
        return MyBatis3Utils.insert(this::insert, record, fruit, c ->
                c.map(id).toPropertyWhenPresent("id", record::getId)
                        .map(fruitName).toPropertyWhenPresent("fruitName", record::getFruitName)
                        .map(fruitPrice).toPropertyWhenPresent("fruitPrice", record::getFruitPrice)
                        .map(description).toPropertyWhenPresent("description", record::getDescription)
                        .map(departurePoint).toPropertyWhenPresent("departurePoint", record::getDeparturePoint)
                        .map(deliveryCost).toPropertyWhenPresent("deliveryCost", record::getDeliveryCost)
                        .map(status).toPropertyWhenPresent("status", record::getStatus)
                        .map(createTime).toPropertyWhenPresent("createTime", record::getCreateTime)
                        .map(updateTime).toPropertyWhenPresent("updateTime", record::getUpdateTime)
                        .map(createUserId).toPropertyWhenPresent("createUserId", record::getCreateUserId)
                        .map(updateUserId).toPropertyWhenPresent("updateUserId", record::getUpdateUserId)
        );
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.237+08:00",
            comments = "Source Table: tbl_fruit")
    default Optional<Fruit> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, fruit, completer);
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.237+08:00",
            comments = "Source Table: tbl_fruit")
    default List<Fruit> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, fruit, completer);
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.238+08:00",
            comments = "Source Table: tbl_fruit")
    default List<Fruit> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, fruit, completer);
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.238+08:00",
            comments = "Source Table: tbl_fruit")
    default Optional<Fruit> selectByPrimaryKey(String id_) {
        return selectOne(c ->
                c.where(id, isEqualTo(id_))
        );
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.238+08:00",
            comments = "Source Table: tbl_fruit")
    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, fruit, completer);
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.238+08:00",
            comments = "Source Table: tbl_fruit")
    static UpdateDSL<UpdateModel> updateAllColumns(Fruit record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(id).equalTo(record::getId)
                .set(fruitName).equalTo(record::getFruitName)
                .set(fruitPrice).equalTo(record::getFruitPrice)
                .set(description).equalTo(record::getDescription)
                .set(departurePoint).equalTo(record::getDeparturePoint)
                .set(deliveryCost).equalTo(record::getDeliveryCost)
                .set(status).equalTo(record::getStatus)
                .set(createTime).equalTo(record::getCreateTime)
                .set(updateTime).equalTo(record::getUpdateTime)
                .set(createUserId).equalTo(record::getCreateUserId)
                .set(updateUserId).equalTo(record::getUpdateUserId);
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.238+08:00",
            comments = "Source Table: tbl_fruit")
    static UpdateDSL<UpdateModel> updateSelectiveColumns(Fruit record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(id).equalToWhenPresent(record::getId)
                .set(fruitName).equalToWhenPresent(record::getFruitName)
                .set(fruitPrice).equalToWhenPresent(record::getFruitPrice)
                .set(description).equalToWhenPresent(record::getDescription)
                .set(departurePoint).equalToWhenPresent(record::getDeparturePoint)
                .set(deliveryCost).equalToWhenPresent(record::getDeliveryCost)
                .set(status).equalToWhenPresent(record::getStatus)
                .set(createTime).equalToWhenPresent(record::getCreateTime)
                .set(updateTime).equalToWhenPresent(record::getUpdateTime)
                .set(createUserId).equalToWhenPresent(record::getCreateUserId)
                .set(updateUserId).equalToWhenPresent(record::getUpdateUserId);
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.238+08:00",
            comments = "Source Table: tbl_fruit")
    default int updateByPrimaryKey(Fruit record) {
        return update(c ->
                c.set(fruitName).equalTo(record::getFruitName)
                        .set(fruitPrice).equalTo(record::getFruitPrice)
                        .set(description).equalTo(record::getDescription)
                        .set(departurePoint).equalTo(record::getDeparturePoint)
                        .set(deliveryCost).equalTo(record::getDeliveryCost)
                        .set(status).equalTo(record::getStatus)
                        .set(createTime).equalTo(record::getCreateTime)
                        .set(updateTime).equalTo(record::getUpdateTime)
                        .set(createUserId).equalTo(record::getCreateUserId)
                        .set(updateUserId).equalTo(record::getUpdateUserId)
                        .where(id, isEqualTo(record::getId))
        );
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.238+08:00",
            comments = "Source Table: tbl_fruit")
    default int updateByPrimaryKeySelective(Fruit record) {
        return update(c ->
                c.set(fruitName).equalToWhenPresent(record::getFruitName)
                        .set(fruitPrice).equalToWhenPresent(record::getFruitPrice)
                        .set(description).equalToWhenPresent(record::getDescription)
                        .set(departurePoint).equalToWhenPresent(record::getDeparturePoint)
                        .set(deliveryCost).equalToWhenPresent(record::getDeliveryCost)
                        .set(status).equalToWhenPresent(record::getStatus)
                        .set(createTime).equalToWhenPresent(record::getCreateTime)
                        .set(updateTime).equalToWhenPresent(record::getUpdateTime)
                        .set(createUserId).equalToWhenPresent(record::getCreateUserId)
                        .set(updateUserId).equalToWhenPresent(record::getUpdateUserId)
                        .where(id, isEqualTo(record::getId))
        );
    }

    @SelectProvider(type = SqlProviderAdapter.class, method = "select")
    @Results(id = "FruitVOResult", value = {
            @Result(column = "id", property = "id", jdbcType = JdbcType.VARCHAR, id = true),
            @Result(column = "fruit_name", property = "fruitName", jdbcType = JdbcType.VARCHAR),
            @Result(column = "fruit_price", property = "fruitPrice", jdbcType = JdbcType.VARCHAR),
            @Result(column = "description", property = "description", jdbcType = JdbcType.VARCHAR),
            @Result(column = "departure_point", property = "departurePoint", jdbcType = JdbcType.VARCHAR),
            @Result(column = "delivery_cost", property = "deliveryCost", jdbcType = JdbcType.INTEGER),
            @Result(column = "status", property = "status", jdbcType = JdbcType.SMALLINT),
            @Result(column = "create_time", property = "createTime", jdbcType = JdbcType.BIGINT),
            @Result(column = "update_time", property = "updateTime", jdbcType = JdbcType.BIGINT),
            @Result(column = "create_user_id", property = "createUserId", jdbcType = JdbcType.VARCHAR),
            @Result(column = "update_user_id", property = "updateUserId", jdbcType = JdbcType.VARCHAR),
            @Result(column = "stock", property = "stock", jdbcType = JdbcType.INTEGER),
            @Result(column = "child_fruit_name", property = "childFruitName", jdbcType = JdbcType.VARCHAR)
    })
    List<FruitVO> selectFruitVOs(SelectStatementProvider selectStatement);

    @SelectProvider(type = SqlProviderAdapter.class, method = "select")
    @Results(id = "Fruit", value = {
            @Result(column = "fruit_name", property = "fruitName", jdbcType = JdbcType.VARCHAR, id = true),
            @Result(column = "fruit_price", property = "fruitPrice", jdbcType = JdbcType.VARCHAR),
            @Result(column = "departure_point", property = "departurePoint", jdbcType = JdbcType.VARCHAR),
            @Result(column = "delivery_cost", property = "deliveryCost", jdbcType = JdbcType.INTEGER),
            @Result(column = "description", property = "description", jdbcType = JdbcType.VARCHAR),
    })
    List<Fruit> selectFruit(SelectStatementProvider selectStatement);

    @Select({
            "select tbl_fruit.fruit_name,tbl_fruit.fruit_price,description,departure_point,delivery_cost,tbl_fruit.create_time",
            "from tbl_fruit",
            "left join tbl_child_fruit",
            "on tbl_fruit.id = tbl_child_fruit.fruit_id",
            "left join tbl_cart",
            "on tbl_child_fruit.id = tbl_cart.child_fruit_id",
            "left join tbl_evaluate",
            "on tbl_child_fruit.id = tbl_evaluate.product_id",
            "where tbl_fruit.fruit_name like #{nameLike}",
            "order by (tbl_child_fruit.fruit_price*0.25 + quantity*0.5 + grade*0.25)"
    })
    @Results(id = "SortResult", value = {
            @Result(column = "fruit_name", property = "fruitName", jdbcType = JdbcType.VARCHAR),
            @Result(column = "description", property = "description", jdbcType = JdbcType.VARCHAR),
            @Result(column = "departure_point", property = "departurePoint", jdbcType = JdbcType.VARCHAR),
            @Result(column = "delivery_cost", property = "deliveryCost", jdbcType = JdbcType.INTEGER),
            @Result(column = "create_time", property = "createTime", jdbcType = JdbcType.BIGINT),
            @Result(column = "fruit_price", property = "fruitPrice", jdbcType = JdbcType.VARCHAR),
            @Result(column = "quantity", property = "quantity", jdbcType = JdbcType.INTEGER),
            @Result(column = "grade", property = "grade", jdbcType = JdbcType.SMALLINT)
    })
    /**
     * @param nameLike
     * @return
     */
    List<Fruit> selectFruitSort(@Param("nameLike") String nameLike);

    @Select({
            "select tbl_child_fruit.fruit_name,min(tbl_child_fruit.fruit_price),description,departure_point,quantity",
            "from tbl_fruit",
            "left join tbl_child_fruit",
            "on tbl_child_fruit.fruit_id = tbl_fruit.id",
            "left join tbl_cart",
            "on tbl_cart.child_fruit_id = tbl_child_fruit.id",
            "left join tbl_evaluate",
            "on tbl_child_fruit.id = tbl_evaluate.product_id",
            "where tbl_child_fruit.fruit_name like '%#{nameLike}%'",
            "group by tbl_child_fruit.fruit_name,description,departure_point,quantity,grade,tbl_child_fruit.fruit_price",
            "order by tbl_child_fruit.fruit_price*0.25 + quantity*0.5 + grade*0.25"
    })
    @Results(id = "FruitRecommend", value = {
            @Result(column = "fruit_name", property = "fruitName", jdbcType = JdbcType.VARCHAR),
            @Result(column = "fruit_price", property = "fruitPrice", jdbcType = JdbcType.INTEGER),
            @Result(column = "description", property = "description", jdbcType = JdbcType.VARCHAR),
            @Result(column = "departure_point", property = "departurePoint", jdbcType = JdbcType.VARCHAR),
            @Result(column = "quantity", property = "quantity", jdbcType = JdbcType.INTEGER)
    })
    List<Fruit> FruitRecommend(@Param("nameLike") String nameLike);
}