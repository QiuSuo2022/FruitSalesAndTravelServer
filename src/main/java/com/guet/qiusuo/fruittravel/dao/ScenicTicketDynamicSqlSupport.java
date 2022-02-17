package com.guet.qiusuo.fruittravel.dao;

import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

import javax.annotation.Generated;
import java.sql.JDBCType;

public final class ScenicTicketDynamicSqlSupport {
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-02-17T17:03:23.866+08:00",
            comments = "Source Table: tbl_scenic_ticket")
    public static final ScenicVO scenicVO= new ScenicVO();

    /**
     * Database Column Remarks:
     * uuid
     */
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-02-17T17:03:23.866+08:00",
            comments = "Source field: tbl_scenic_ticket.id")
    public static final SqlColumn<String> id = scenicVO.id;

    /**
     * Database Column Remarks:
     * 景区描述
     */
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-02-17T17:03:23.866+08:00",
            comments = "Source field: tbl_scenic_ticket.description")
    public static final SqlColumn<String> description = scenicVO.description;

    /**
     * Database Column Remarks:
     * 景区门票id
     */
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-02-17T17:03:23.866+08:00",
            comments = "Source field: tbl_scenic_ticket.ticket_id")
    public static final SqlColumn<String> ticketId = scenicVO.ticketId;

    /**
     * Database Column Remarks:
     * 景区门票名称
     */
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-02-17T17:03:23.866+08:00",
            comments = "Source field: tbl_scenic_ticket.ticket_name")
    public static final SqlColumn<String> ticketName = scenicVO.ticketName;

    /**
     * Database Column Remarks:
     * 景区门票价格
     */
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-02-17T17:03:23.866+08:00",
            comments = "Source field: tbl_scenic_ticket.price")
    public static final SqlColumn<Integer> price = scenicVO.price;

    /**
     * Database Column Remarks:
     * 景区门票类型
     */
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-02-17T17:03:23.866+08:00",
            comments = "Source field: tbl_scenic_ticket.type")
    public static final SqlColumn<Short> type = scenicVO.type;

    /**
     * Database Column Remarks:
     * 状态,0:禁用 1:启用
     */
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-02-17T17:03:23.866+08:00",
            comments = "Source field: tbl_scenic_ticket.status")
    public static final SqlColumn<Short> status = scenicVO.status;

    /**
     * Database Column Remarks:
     * 创建时间
     */
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-02-17T17:03:23.866+08:00",
            comments = "Source field: tbl_scenic_ticket.create_time")
    public static final SqlColumn<Long> createTime = scenicVO.createTime;

    /**
     * Database Column Remarks:
     * 更新时间
     */
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-02-17T17:03:23.866+08:00",
            comments = "Source field: tbl_scenic_ticket.update_time")
    public static final SqlColumn<Long> updateTime = scenicVO.updateTime;

    /**
     * Database Column Remarks:
     * 记录创建人ID
     */
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-02-17T17:03:23.866+08:00",
            comments = "Source field: tbl_scenic_ticket.create_user_id")
    public static final SqlColumn<String> createUserId = scenicVO.createUserId;

    /**
     * Database Column Remarks:
     * 记录修改人ID
     */
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-02-17T17:03:23.866+08:00",
            comments = "Source field: tbl_scenic_ticket.update_user_id")
    public static final SqlColumn<String> updateUserId = scenicVO.updateUserId;

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-02-17T17:03:23.866+08:00",
            comments = "Source Table: tbl_scenic_ticket")
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
