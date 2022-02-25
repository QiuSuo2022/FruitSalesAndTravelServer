package com.guet.qiusuo.fruittravel.dao;

import com.guet.qiusuo.fruittravel.model.FruitScenic;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

import javax.annotation.Generated;
import java.sql.JDBCType;

public class FruitScenicDynamicSqlSupport {
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-02-25T11:39:23.859+08:00",
            comments = "Source Table: tbl_fruit_scenic")
    public static final FruitScenic fruitScenic = new FruitScenic();

    /**
     * Database Column Remarks:
     * uuid
     */
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-02-25T11:39:23.859+08:00",
            comments = "Source Table: tbl_fruit_scenic.id")
    public static final SqlColumn<String> id = fruitScenic.id;

    /**
     * Database Column Remarks:
     * 水果id
     */
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-02-25T11:39:23.859+08:00",
            comments = "Source Table: tbl_fruit_scenic.fruit_id")
    public static final SqlColumn<String> fruitId = fruitScenic.fruitId;

    /**
     * Database Column Remarks:
     * 水果名称
     */
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-02-25T11:39:23.859+08:00",
            comments = "Source field: tbl_fruit_scenic.fruit_name")
    public static final SqlColumn<String> fruitName = fruitScenic.fruitName;

    /**
     * Database Column Remarks:
     * 水果价钱(一个范围)
     */
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-02-25T11:39:23.859+08:00",
            comments = "Source field: tbl_fruit_scenic.fruit_price")
    public static final SqlColumn<String> fruitPrice = fruitScenic.fruitPrice;

    /**
     * Database Column Remarks:
     * 水果描述
     */
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-02-25T11:39:23.859+08:00",
            comments = "Source field: tbl_fruit_scenic.fruit_description")
    public static final SqlColumn<String> fruitDescription = fruitScenic.fruitDescription;

    /**
     * Database Column Remarks:
     * 发货地点
     */
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-02-25T11:39:23.859+08:00",
            comments = "Source field: tbl_fruit_scenic.departure_point")
    public static final SqlColumn<String> departurePoint = fruitScenic.departurePoint;

    /**
     * Database Column Remarks:
     * 快递费
     */
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-02-25T11:39:23.859+08:00",
            comments = "Source field: tbl_fruit_scenic.delivery_cost")
    public static final SqlColumn<Integer> deliveryCost = fruitScenic.deliveryCost;

    /**
     * Database Column Remarks:
     * 景点id
     */
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-02-25T11:39:23.859+08:00",
            comments = "Source field: tbl_fruit_scenic.scenic_id")
    public static final SqlColumn<String> scenicId = fruitScenic.scenicId;

    /**
     * Database Column Remarks:
     * 景点名称
     */
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-02-25T11:39:23.859+08:00",
            comments = "Source field: tbl_fruit_scenic.scenic_name")
    public static final SqlColumn<String> scenicName = fruitScenic.scenicName;

    /**
     * Database Column Remarks:
     * 地理位置
     */
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-02-25T11:39:23.859+08:00",
            comments = "Source field: tbl_fruit_scenic.location")
    public static final SqlColumn<String> location = fruitScenic.location;

    /**
     * Database Column Remarks:
     * 开放时间
     */
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-02-25T11:39:23.859+08:00",
            comments = "Source field: tbl_fruit_scenic.opening_hours")
    public static final SqlColumn<String> openingHours = fruitScenic.openingHours;

    /**
     * Database Column Remarks:
     * 景区介绍
     */
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-02-25T11:39:23.859+08:00",
            comments = "Source field: tbl_fruit_scenic.description")
    public static final SqlColumn<String> scenicDescription = fruitScenic.scenicDescription;

    /**
     * Database Column Remarks:
     * 景区类型, 1-文化古迹, 2-园林花园, 3-古镇古村, 4-城堡教堂
     */
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-02-25T11:39:23.859+08:00",
            comments = "Source field: tbl_fruit_scenic.type")
    public static final SqlColumn<Short> type = fruitScenic.type;

    /**
     * Database Column Remarks:
     * 状态,0:禁用 1:启用
     */
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-02-25T11:39:23.859+08:00",
            comments = "Source field: tbl_fruit_scenic.status")
    public static final SqlColumn<Short> status = fruitScenic.status;

    /**
     * Database Column Remarks:
     * 创建时间
     */
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-02-25T11:39:23.859+08:00",
            comments = "Source field: tbl_fruit_scenic.create_time")
    public static final SqlColumn<Long> createTime = fruitScenic.createTime;

    /**
     * Database Column Remarks:
     * 更新时间
     */
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-02-25T11:39:23.859+08:00",
            comments = "Source field: tbl_fruit_scenic.update_time")
    public static final SqlColumn<Long> updateTime = fruitScenic.updateTime;

    /**
     * Database Column Remarks:
     * 记录创建人ID
     */
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-02-25T11:39:23.859+08:00",
            comments = "Source field: tbl_fruit_scenic.create_user_id")
    public static final SqlColumn<String> createUserId = fruitScenic.createUserId;

    /**
     * Database Column Remarks:
     * 记录修改人ID
     */
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-02-25T11:39:23.859+08:00",
            comments = "Source field: tbl_fruit_scenic.update_user_id")
    public static final SqlColumn<String> updateUserId = fruitScenic.updateUserId;

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-02-25T11:39:23.859+08:00",
            comments = "Source Table: tbl_fruit_scenic")
    public static final class FruitScenic extends SqlTable {
        public final SqlColumn<String> id = column("id", JDBCType.VARCHAR);

        public final SqlColumn<String> fruitId = column("fruit_id", JDBCType.VARCHAR);

        public final SqlColumn<String> fruitName = column("fruit_name", JDBCType.VARCHAR);

        public final SqlColumn<String> fruitPrice = column("fruit_price", JDBCType.VARCHAR);

        public final SqlColumn<String> fruitDescription = column("fruit_description", JDBCType.VARCHAR);

        public final SqlColumn<String> departurePoint = column("departure_point", JDBCType.VARCHAR);

        public final SqlColumn<Integer> deliveryCost = column("delivery_cost", JDBCType.INTEGER);

        public final SqlColumn<String> scenicId = column("scenic_id", JDBCType.VARCHAR);

        public final SqlColumn<String> scenicName = column("scenic_name", JDBCType.VARCHAR);

        public final SqlColumn<String> location = column("location", JDBCType.VARCHAR);

        public final SqlColumn<String> openingHours = column("opening_hours", JDBCType.VARCHAR);

        public final SqlColumn<String> scenicDescription = column("scenic_description", JDBCType.VARCHAR);

        public final SqlColumn<Short> type = column("type", JDBCType.SMALLINT);

        public final SqlColumn<Short> status = column("status", JDBCType.SMALLINT);

        public final SqlColumn<Long> createTime = column("create_time", JDBCType.BIGINT);

        public final SqlColumn<Long> updateTime = column("update_time", JDBCType.BIGINT);

        public final SqlColumn<String> createUserId = column("create_user_id", JDBCType.VARCHAR);

        public final SqlColumn<String> updateUserId = column("update_user_id", JDBCType.VARCHAR);

        public FruitScenic() {
            super("tbl_fruit_scenic");
        }
    }
}
