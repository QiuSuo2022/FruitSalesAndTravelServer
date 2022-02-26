package com.guet.qiusuo.fruittravel.dao;

import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

import javax.annotation.Generated;
import java.sql.JDBCType;

public final class ScenicVODynamicSqlSupport {

    public static final ScenicVO scenicVO= new ScenicVO();

    /**
     * Database Column Remarks:
     * uuid
     */
    public static final SqlColumn<String> id = scenicVO.id;

    /**
     * Database Column Remarks:
     * 景区描述
     */
    public static final SqlColumn<String> description = scenicVO.description;

    /**
     * Database Column Remarks:
     * 景区门票id
     */
    public static final SqlColumn<String> ticketId = scenicVO.ticketId;

    /**
     * Database Column Remarks:
     * 景区门票名称
     */
    public static final SqlColumn<String> ticketName = scenicVO.ticketName;

    /**
     * Database Column Remarks:
     * 景区门票价格
     */
    public static final SqlColumn<Integer> price = scenicVO.price;

    /**
     * Database Column Remarks:
     * 景区门票类型
     */
    public static final SqlColumn<Short> type = scenicVO.type;

    /**
     * Database Column Remarks:
     * 状态,0:禁用 1:启用
     */
    public static final SqlColumn<Short> status = scenicVO.status;

    /**
     * Database Column Remarks:
     * 创建时间
     */
    public static final SqlColumn<Long> createTime = scenicVO.createTime;

    /**
     * Database Column Remarks:
     * 更新时间
     */
    public static final SqlColumn<Long> updateTime = scenicVO.updateTime;

    /**
     * Database Column Remarks:
     * 记录创建人ID
     */
    public static final SqlColumn<String> createUserId = scenicVO.createUserId;

    /**
     * Database Column Remarks:
     * 记录修改人ID
     */
    public static final SqlColumn<String> updateUserId = scenicVO.updateUserId;

    public static final class ScenicVO extends SqlTable {
        public final SqlColumn<String> id = column("id", JDBCType.VARCHAR);

        public final SqlColumn<String> description = column("description", JDBCType.VARCHAR);

        public final SqlColumn<String> ticketId = column("ticket_id", JDBCType.VARCHAR);

        public final SqlColumn<String> ticketName = column("ticket_name", JDBCType.VARCHAR);

        public final SqlColumn<Integer> price = column("price", JDBCType.INTEGER);

        public final SqlColumn<Short> type = column("type", JDBCType.SMALLINT);

        public final SqlColumn<Short> status = column("status", JDBCType.SMALLINT);

        public final SqlColumn<Long> createTime = column("create_time", JDBCType.BIGINT);

        public final SqlColumn<Long> updateTime = column("update_time", JDBCType.BIGINT);

        public final SqlColumn<String> createUserId = column("create_user_id", JDBCType.VARCHAR);

        public final SqlColumn<String> updateUserId = column("update_user_id", JDBCType.VARCHAR);

        protected ScenicVO() {
            super("tbl_scenic_ticket");
        }
    }
}
