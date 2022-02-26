package com.guet.qiusuo.fruittravel.dao;

import com.guet.qiusuo.fruittravel.bean.vo.FruitVO;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

import java.sql.JDBCType;

public class FruitVODynamicSqlSupport {
    public static final FruitVO fruitVO = new FruitVO();

    /**
     * Database Column Remarks:
     * uuid
     */
    public static final SqlColumn<String> id = fruitVO.id;

    /**
     * Database Column Remarks:
     * 水果描述
     */
    public static final SqlColumn<String> description = fruitVO.description;

    /**
     * Database Column Remarks:
     * 水果库存
     */
    public static final SqlColumn<Integer> stock = fruitVO.stock;

    /**
     * Database Column Remarks:
     * 水果子类名称
     */
    public static final SqlColumn<String> childFruitName = fruitVO.childFruitName;
    /**
     * Database Column Remarks:
     * 快递费
     */
    public static final SqlColumn<Integer> ticketName = fruitVO.deliveryCost;

    /**
     * Database Column Remarks:
     * 水果价格
     */
    public static final SqlColumn<String> price = fruitVO.fruitPrice;

    /**
     * Database Column Remarks:
     * 状态,0:禁用 1:启用
     */
    public static final SqlColumn<Short> status = fruitVO.status;

    /**
     * Database Column Remarks:
     * 创建时间
     */
    public static final SqlColumn<Long> createTime = fruitVO.createTime;

    /**
     * Database Column Remarks:
     * 更新时间
     */
    public static final SqlColumn<Long> updateTime = fruitVO.updateTime;

    /**
     * Database Column Remarks:
     * 记录创建人ID
     */
    public static final SqlColumn<String> createUserId = fruitVO.createUserId;

    /**
     * Database Column Remarks:
     * 记录修改人ID
     */
    public static final SqlColumn<String> updateUserId = fruitVO.updateUserId;

    public static final class FruitVO extends SqlTable {
        public final SqlColumn<String> id = column("id", JDBCType.VARCHAR);

        public final SqlColumn<Integer> stock = column("stock", JDBCType.INTEGER);
        public final SqlColumn<String> childFruitName = column("child_fruit_name", JDBCType.VARCHAR);

        public final SqlColumn<String> fruitName = column("fruit_name", JDBCType.VARCHAR);

        public final SqlColumn<String> description = column("description", JDBCType.VARCHAR);

        public final SqlColumn<String> departurePoint = column("departure_point", JDBCType.VARCHAR);

        public final SqlColumn<Integer> deliveryCost = column("delivery_cost", JDBCType.INTEGER);

        public final SqlColumn<String> fruitPrice = column("fruit_price", JDBCType.VARCHAR);

        public final SqlColumn<Short> status = column("status", JDBCType.SMALLINT);

        public final SqlColumn<Long> createTime = column("create_time", JDBCType.BIGINT);

        public final SqlColumn<Long> updateTime = column("update_time", JDBCType.BIGINT);

        public final SqlColumn<String> createUserId = column("create_user_id", JDBCType.VARCHAR);

        public final SqlColumn<String> updateUserId = column("update_user_id", JDBCType.VARCHAR);

        protected FruitVO() {
            super("tbl_fruit_childFruit");
        }
    }
}
