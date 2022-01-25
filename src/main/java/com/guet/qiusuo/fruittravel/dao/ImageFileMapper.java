package com.guet.qiusuo.fruittravel.dao;

import static com.guet.qiusuo.fruittravel.dao.ImageFileDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.*;

import com.guet.qiusuo.fruittravel.model.ImageFile;

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
public interface ImageFileMapper {
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.245+08:00",
            comments = "Source Table: tbl_image_file")
    BasicColumn[] selectList = BasicColumn.columnList(id, imageName, type, imageSize, imageUrl, remark, status,
            createTime, updateTime, createUserId, updateUserId);

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.243+08:00",
            comments = "Source Table: tbl_image_file")
    @SelectProvider(type = SqlProviderAdapter.class, method = "select")
    long count(SelectStatementProvider selectStatement);

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.243+08:00",
            comments = "Source Table: tbl_image_file")
    @DeleteProvider(type = SqlProviderAdapter.class, method = "delete")
    int delete(DeleteStatementProvider deleteStatement);

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.243+08:00",
            comments = "Source Table: tbl_image_file")
    @InsertProvider(type = SqlProviderAdapter.class, method = "insert")
    int insert(InsertStatementProvider<ImageFile> insertStatement);

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.244+08:00",
            comments = "Source Table: tbl_image_file")
    @InsertProvider(type = SqlProviderAdapter.class, method = "insertMultiple")
    int insertMultiple(MultiRowInsertStatementProvider<ImageFile> multipleInsertStatement);

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.244+08:00",
            comments = "Source Table: tbl_image_file")
    @SelectProvider(type = SqlProviderAdapter.class, method = "select")
    @ResultMap("ImageFileResult")
    Optional<ImageFile> selectOne(SelectStatementProvider selectStatement);

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.244+08:00",
            comments = "Source Table: tbl_image_file")
    @SelectProvider(type = SqlProviderAdapter.class, method = "select")
    @Results(id = "ImageFileResult", value = {
            @Result(column = "id", property = "id", jdbcType = JdbcType.VARCHAR, id = true),
            @Result(column = "image_name", property = "imageName", jdbcType = JdbcType.VARCHAR),
            @Result(column = "type", property = "type", jdbcType = JdbcType.SMALLINT),
            @Result(column = "image_size", property = "imageSize", jdbcType = JdbcType.SMALLINT),
            @Result(column = "image_url", property = "imageUrl", jdbcType = JdbcType.VARCHAR),
            @Result(column = "remark", property = "remark", jdbcType = JdbcType.VARCHAR),
            @Result(column = "status", property = "status", jdbcType = JdbcType.SMALLINT),
            @Result(column = "create_time", property = "createTime", jdbcType = JdbcType.BIGINT),
            @Result(column = "update_time", property = "updateTime", jdbcType = JdbcType.BIGINT),
            @Result(column = "create_user_id", property = "createUserId", jdbcType = JdbcType.VARCHAR),
            @Result(column = "update_user_id", property = "updateUserId", jdbcType = JdbcType.VARCHAR)
    })
    List<ImageFile> selectMany(SelectStatementProvider selectStatement);

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.244+08:00",
            comments = "Source Table: tbl_image_file")
    @UpdateProvider(type = SqlProviderAdapter.class, method = "update")
    int update(UpdateStatementProvider updateStatement);

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.244+08:00",
            comments = "Source Table: tbl_image_file")
    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, imageFile, completer);
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.244+08:00",
            comments = "Source Table: tbl_image_file")
    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, imageFile, completer);
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.244+08:00",
            comments = "Source Table: tbl_image_file")
    default int deleteByPrimaryKey(String id_) {
        return delete(c ->
                c.where(id, isEqualTo(id_))
        );
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.244+08:00",
            comments = "Source Table: tbl_image_file")
    default int insert(ImageFile record) {
        return MyBatis3Utils.insert(this::insert, record, imageFile, c ->
                c.map(id).toProperty("id")
                        .map(imageName).toProperty("imageName")
                        .map(type).toProperty("type")
                        .map(imageSize).toProperty("imageSize")
                        .map(imageUrl).toProperty("imageUrl")
                        .map(remark).toProperty("remark")
                        .map(status).toProperty("status")
                        .map(createTime).toProperty("createTime")
                        .map(updateTime).toProperty("updateTime")
                        .map(createUserId).toProperty("createUserId")
                        .map(updateUserId).toProperty("updateUserId")
        );
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.245+08:00",
            comments = "Source Table: tbl_image_file")
    default int insertMultiple(Collection<ImageFile> records) {
        return MyBatis3Utils.insertMultiple(this::insertMultiple, records, imageFile, c ->
                c.map(id).toProperty("id")
                        .map(imageName).toProperty("imageName")
                        .map(type).toProperty("type")
                        .map(imageSize).toProperty("imageSize")
                        .map(imageUrl).toProperty("imageUrl")
                        .map(remark).toProperty("remark")
                        .map(status).toProperty("status")
                        .map(createTime).toProperty("createTime")
                        .map(updateTime).toProperty("updateTime")
                        .map(createUserId).toProperty("createUserId")
                        .map(updateUserId).toProperty("updateUserId")
        );
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.245+08:00",
            comments = "Source Table: tbl_image_file")
    default int insertSelective(ImageFile record) {
        return MyBatis3Utils.insert(this::insert, record, imageFile, c ->
                c.map(id).toPropertyWhenPresent("id", record::getId)
                        .map(imageName).toPropertyWhenPresent("imageName", record::getImageName)
                        .map(type).toPropertyWhenPresent("type", record::getType)
                        .map(imageSize).toPropertyWhenPresent("imageSize", record::getImageSize)
                        .map(imageUrl).toPropertyWhenPresent("imageUrl", record::getImageUrl)
                        .map(remark).toPropertyWhenPresent("remark", record::getRemark)
                        .map(status).toPropertyWhenPresent("status", record::getStatus)
                        .map(createTime).toPropertyWhenPresent("createTime", record::getCreateTime)
                        .map(updateTime).toPropertyWhenPresent("updateTime", record::getUpdateTime)
                        .map(createUserId).toPropertyWhenPresent("createUserId", record::getCreateUserId)
                        .map(updateUserId).toPropertyWhenPresent("updateUserId", record::getUpdateUserId)
        );
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.245+08:00",
            comments = "Source Table: tbl_image_file")
    default Optional<ImageFile> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, imageFile, completer);
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.245+08:00",
            comments = "Source Table: tbl_image_file")
    default List<ImageFile> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, imageFile, completer);
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.245+08:00",
            comments = "Source Table: tbl_image_file")
    default List<ImageFile> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, imageFile, completer);
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.245+08:00",
            comments = "Source Table: tbl_image_file")
    default Optional<ImageFile> selectByPrimaryKey(String id_) {
        return selectOne(c ->
                c.where(id, isEqualTo(id_))
        );
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.246+08:00",
            comments = "Source Table: tbl_image_file")
    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, imageFile, completer);
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.246+08:00",
            comments = "Source Table: tbl_image_file")
    static UpdateDSL<UpdateModel> updateAllColumns(ImageFile record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(id).equalTo(record::getId)
                .set(imageName).equalTo(record::getImageName)
                .set(type).equalTo(record::getType)
                .set(imageSize).equalTo(record::getImageSize)
                .set(imageUrl).equalTo(record::getImageUrl)
                .set(remark).equalTo(record::getRemark)
                .set(status).equalTo(record::getStatus)
                .set(createTime).equalTo(record::getCreateTime)
                .set(updateTime).equalTo(record::getUpdateTime)
                .set(createUserId).equalTo(record::getCreateUserId)
                .set(updateUserId).equalTo(record::getUpdateUserId);
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.246+08:00",
            comments = "Source Table: tbl_image_file")
    static UpdateDSL<UpdateModel> updateSelectiveColumns(ImageFile record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(id).equalToWhenPresent(record::getId)
                .set(imageName).equalToWhenPresent(record::getImageName)
                .set(type).equalToWhenPresent(record::getType)
                .set(imageSize).equalToWhenPresent(record::getImageSize)
                .set(imageUrl).equalToWhenPresent(record::getImageUrl)
                .set(remark).equalToWhenPresent(record::getRemark)
                .set(status).equalToWhenPresent(record::getStatus)
                .set(createTime).equalToWhenPresent(record::getCreateTime)
                .set(updateTime).equalToWhenPresent(record::getUpdateTime)
                .set(createUserId).equalToWhenPresent(record::getCreateUserId)
                .set(updateUserId).equalToWhenPresent(record::getUpdateUserId);
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.246+08:00",
            comments = "Source Table: tbl_image_file")
    default int updateByPrimaryKey(ImageFile record) {
        return update(c ->
                c.set(imageName).equalTo(record::getImageName)
                        .set(type).equalTo(record::getType)
                        .set(imageSize).equalTo(record::getImageSize)
                        .set(imageUrl).equalTo(record::getImageUrl)
                        .set(remark).equalTo(record::getRemark)
                        .set(status).equalTo(record::getStatus)
                        .set(createTime).equalTo(record::getCreateTime)
                        .set(updateTime).equalTo(record::getUpdateTime)
                        .set(createUserId).equalTo(record::getCreateUserId)
                        .set(updateUserId).equalTo(record::getUpdateUserId)
                        .where(id, isEqualTo(record::getId))
        );
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.246+08:00",
            comments = "Source Table: tbl_image_file")
    default int updateByPrimaryKeySelective(ImageFile record) {
        return update(c ->
                c.set(imageName).equalToWhenPresent(record::getImageName)
                        .set(type).equalToWhenPresent(record::getType)
                        .set(imageSize).equalToWhenPresent(record::getImageSize)
                        .set(imageUrl).equalToWhenPresent(record::getImageUrl)
                        .set(remark).equalToWhenPresent(record::getRemark)
                        .set(status).equalToWhenPresent(record::getStatus)
                        .set(createTime).equalToWhenPresent(record::getCreateTime)
                        .set(updateTime).equalToWhenPresent(record::getUpdateTime)
                        .set(createUserId).equalToWhenPresent(record::getCreateUserId)
                        .set(updateUserId).equalToWhenPresent(record::getUpdateUserId)
                        .where(id, isEqualTo(record::getId))
        );
    }
}