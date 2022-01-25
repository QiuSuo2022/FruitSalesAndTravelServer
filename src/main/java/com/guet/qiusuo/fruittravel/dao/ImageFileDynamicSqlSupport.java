package com.guet.qiusuo.fruittravel.dao;

import java.sql.JDBCType;
import javax.annotation.Generated;

import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class ImageFileDynamicSqlSupport {
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.241+08:00",
            comments = "Source Table: tbl_image_file")
    public static final ImageFile imageFile = new ImageFile();

    /**
     * Database Column Remarks:
     * uuid
     */
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.242+08:00",
            comments = "Source field: tbl_image_file.id")
    public static final SqlColumn<String> id = imageFile.id;

    /**
     * Database Column Remarks:
     * 图片名
     */
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.242+08:00",
            comments = "Source field: tbl_image_file.image_name")
    public static final SqlColumn<String> imageName = imageFile.imageName;

    /**
     * Database Column Remarks:
     * 图片类别: 0--轮播图, 1--水果商品图, 2--商品评价图
     */
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.242+08:00",
            comments = "Source field: tbl_image_file.type")
    public static final SqlColumn<Short> type = imageFile.type;

    /**
     * Database Column Remarks:
     * 文件大小
     */
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.242+08:00",
            comments = "Source field: tbl_image_file.image_size")
    public static final SqlColumn<Short> imageSize = imageFile.imageSize;

    /**
     * Database Column Remarks:
     * 文件存储路径
     */
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.242+08:00",
            comments = "Source field: tbl_image_file.image_url")
    public static final SqlColumn<String> imageUrl = imageFile.imageUrl;

    /**
     * Database Column Remarks:
     * 备注
     */
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.242+08:00",
            comments = "Source field: tbl_image_file.remark")
    public static final SqlColumn<String> remark = imageFile.remark;

    /**
     * Database Column Remarks:
     * 状态, 0: 无效 1: 有效
     */
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.243+08:00",
            comments = "Source field: tbl_image_file.status")
    public static final SqlColumn<Short> status = imageFile.status;

    /**
     * Database Column Remarks:
     * 创建时间
     */
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.243+08:00",
            comments = "Source field: tbl_image_file.create_time")
    public static final SqlColumn<Long> createTime = imageFile.createTime;

    /**
     * Database Column Remarks:
     * 最后更新时间
     */
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.243+08:00",
            comments = "Source field: tbl_image_file.update_time")
    public static final SqlColumn<Long> updateTime = imageFile.updateTime;

    /**
     * Database Column Remarks:
     * 增加此条数据的用户id
     */
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.243+08:00",
            comments = "Source field: tbl_image_file.create_user_id")
    public static final SqlColumn<String> createUserId = imageFile.createUserId;

    /**
     * Database Column Remarks:
     * 最后更新此条数据的用户id
     */
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.243+08:00",
            comments = "Source field: tbl_image_file.update_user_id")
    public static final SqlColumn<String> updateUserId = imageFile.updateUserId;

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.241+08:00",
            comments = "Source Table: tbl_image_file")
    public static final class ImageFile extends SqlTable {
        public final SqlColumn<String> id = column("id", JDBCType.VARCHAR);

        public final SqlColumn<String> imageName = column("image_name", JDBCType.VARCHAR);

        public final SqlColumn<Short> type = column("type", JDBCType.SMALLINT);

        public final SqlColumn<Short> imageSize = column("image_size", JDBCType.SMALLINT);

        public final SqlColumn<String> imageUrl = column("image_url", JDBCType.VARCHAR);

        public final SqlColumn<String> remark = column("remark", JDBCType.VARCHAR);

        public final SqlColumn<Short> status = column("status", JDBCType.SMALLINT);

        public final SqlColumn<Long> createTime = column("create_time", JDBCType.BIGINT);

        public final SqlColumn<Long> updateTime = column("update_time", JDBCType.BIGINT);

        public final SqlColumn<String> createUserId = column("create_user_id", JDBCType.VARCHAR);

        public final SqlColumn<String> updateUserId = column("update_user_id", JDBCType.VARCHAR);

        public ImageFile() {
            super("tbl_image_file");
        }
    }
}