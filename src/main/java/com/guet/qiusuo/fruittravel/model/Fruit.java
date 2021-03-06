package com.guet.qiusuo.fruittravel.model;

import javax.annotation.Generated;

/**
 * Database Table Remarks:
 * 水果商品表
 * <p>
 * This class was generated by MyBatis Generator.
 * This class corresponds to the database table tbl_fruit
 */
public class Fruit {
    /**
     * Database Column Remarks:
     * uuid
     */
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.232+08:00",
            comments = "Source field: tbl_fruit.id")
    private String id;

    /**
     * Database Column Remarks:
     * 水果名称
     */
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.232+08:00",
            comments = "Source field: tbl_fruit.fruit_name")
    private String fruitName;

    /**
     * Database Column Remarks:
     * 水果价钱(一个范围)
     */
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.233+08:00",
            comments = "Source field: tbl_fruit.fruit_price")
    private String fruitPrice;

    /**
     * Database Column Remarks:
     * 水果描述
     */
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.233+08:00",
            comments = "Source field: tbl_fruit.description")
    private String description;

    /**
     * Database Column Remarks:
     * 发货地点
     */
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.233+08:00",
            comments = "Source field: tbl_fruit.departure_point")
    private String departurePoint;

    /**
     * Database Column Remarks:
     * 快递费
     */
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.233+08:00",
            comments = "Source field: tbl_fruit.delivery_cost")
    private Integer deliveryCost;

    /**
     * Database Column Remarks:
     * 状态,0: 无效 1: 有效
     */
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.233+08:00",
            comments = "Source field: tbl_fruit.status")
    private Short status;

    /**
     * Database Column Remarks:
     * 创建时间
     */
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.233+08:00",
            comments = "Source field: tbl_fruit.create_time")
    private Long createTime;

    /**
     * Database Column Remarks:
     * 最后更新时间
     */
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.233+08:00",
            comments = "Source field: tbl_fruit.update_time")
    private Long updateTime;

    /**
     * Database Column Remarks:
     * 增加此条数据的用户id
     */
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.234+08:00",
            comments = "Source field: tbl_fruit.create_user_id")
    private String createUserId;

    /**
     * Database Column Remarks:
     * 最后更新此条数据的用户id
     */
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.234+08:00",
            comments = "Source field: tbl_fruit.update_user_id")
    private String updateUserId;

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.232+08:00",
            comments = "Source field: tbl_fruit.id")
    public String getId() {
        return id;
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.232+08:00",
            comments = "Source field: tbl_fruit.id")
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.233+08:00",
            comments = "Source field: tbl_fruit.fruit_name")
    public String getFruitName() {
        return fruitName;
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.233+08:00",
            comments = "Source field: tbl_fruit.fruit_name")
    public void setFruitName(String fruitName) {
        this.fruitName = fruitName == null ? null : fruitName.trim();
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.233+08:00",
            comments = "Source field: tbl_fruit.fruit_price")
    public String getFruitPrice() {
        return fruitPrice;
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.233+08:00",
            comments = "Source field: tbl_fruit.fruit_price")
    public void setFruitPrice(String fruitPrice) {
        this.fruitPrice = fruitPrice == null ? null : fruitPrice.trim();
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.233+08:00",
            comments = "Source field: tbl_fruit.description")
    public String getDescription() {
        return description;
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.233+08:00",
            comments = "Source field: tbl_fruit.description")
    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.233+08:00",
            comments = "Source field: tbl_fruit.departure_point")
    public String getDeparturePoint() {
        return departurePoint;
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.233+08:00",
            comments = "Source field: tbl_fruit.departure_point")
    public void setDeparturePoint(String departurePoint) {
        this.departurePoint = departurePoint == null ? null : departurePoint.trim();
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.233+08:00",
            comments = "Source field: tbl_fruit.delivery_cost")
    public Integer getDeliveryCost() {
        return deliveryCost;
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.233+08:00",
            comments = "Source field: tbl_fruit.delivery_cost")
    public void setDeliveryCost(Integer deliveryCost) {
        this.deliveryCost = deliveryCost;
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.233+08:00",
            comments = "Source field: tbl_fruit.status")
    public Short getStatus() {
        return status;
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.233+08:00",
            comments = "Source field: tbl_fruit.status")
    public void setStatus(Short status) {
        this.status = status;
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.233+08:00",
            comments = "Source field: tbl_fruit.create_time")
    public Long getCreateTime() {
        return createTime;
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.233+08:00",
            comments = "Source field: tbl_fruit.create_time")
    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.234+08:00",
            comments = "Source field: tbl_fruit.update_time")
    public Long getUpdateTime() {
        return updateTime;
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.234+08:00",
            comments = "Source field: tbl_fruit.update_time")
    public void setUpdateTime(Long updateTime) {
        this.updateTime = updateTime;
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.234+08:00",
            comments = "Source field: tbl_fruit.create_user_id")
    public String getCreateUserId() {
        return createUserId;
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.234+08:00",
            comments = "Source field: tbl_fruit.create_user_id")
    public void setCreateUserId(String createUserId) {
        this.createUserId = createUserId == null ? null : createUserId.trim();
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.234+08:00",
            comments = "Source field: tbl_fruit.update_user_id")
    public String getUpdateUserId() {
        return updateUserId;
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2022-01-26T02:15:00.234+08:00", comments = "Source field: tbl_fruit.update_user_id")
    public void setUpdateUserId(String updateUserId) {
        this.updateUserId = updateUserId == null ? null : updateUserId.trim();
    }
}