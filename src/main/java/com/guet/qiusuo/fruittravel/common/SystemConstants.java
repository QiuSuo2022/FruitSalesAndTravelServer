package com.guet.qiusuo.fruittravel.common;


import java.math.BigInteger;

public class SystemConstants {
    //排序
    public static final Short PRICE_ASC = new Short("0");

    public static final Short PRICE_DESC = new Short("1");

    public static final Short SALE_ASC = new Short("2");

    public static final Short SALE_DESC = new Short("3");

    public static final Short GRADE_ASC = new Short("4");

    public static final Short GRADE_DESC = new Short("5");

    public static final Short SORT_ALL = new Short("6");
    // table
    public static final Short STATUS_NORMAL = new Short("1");

    public static final Short STATUS_NEGATIVE = new Short("0");

    public static final Short STATUS_ACTIVE = new Short("1");

    public static final Short USER_INFO_COMPLETE = new Short("1");

    public static final Short USER_INFO_NOT_COMPLETE = new Short("2");

    public static final Short SCENIC_INFO_NOT_COMPLETE = new Short("2");

    //评价类型
    public static final Short FRUIT_EVALUATE_TYPE = new Short("0");

    public static final Short SCENIC_EVALUATE_TYPE = new Short("1");

    public static final Short REEVALUATE_TYPE = new Short("2");

    //分页
    public static final String DEFAULT_PAGE = "1";

    public static final String DEFAULT_PAGE_SIZE = "20";

    //通知类型
    public static final Short SYSTEM_NOTIFICATION = new Short("0");

    public static final Short NOTIFICATION_DONT_READ = new Short("0");

    public static final Short NOTIFICATION_HAS_READ = new Short("0");

    //门票类型
    public static final Short ADULT = new Short("1");

    public static final Short CHILD = new Short("2");

    public static final Short OLD = new Short("3");

    /**
     * 用户信息不全
     */
    public static final Short USER_INFO_INCOMPLETE = new Short("3");


    /**
     * 日期时间戳
     */
    public static final short WEEK  = 1;
    public static final long WEEK_MILLIS  = new BigInteger("604800017").longValue();

    public static final short MONTH = 2;
    public static final long MONTH_MILLIS = new BigInteger("2629800000").longValue();

    public static final short YEAH  = 3;
    public static final long YEAH_MILLIS  = new BigInteger("31557600000").longValue();

    /**
     * 评价星级
     */
    public static final short STAR_1 = 1;
    public static final short STAR_2 = 2;
    public static final short STAR_3 = 3;
    public static final short STAR_4 = 4;
    public static final short STAR_5 = 5;

    /**
     * 支付状态
     */
    public static final short UNPAID = 0;//待付款
    public static final short PAID = 1;//待发货
    public static final short UNFINISHED = 2;//待收货
    public static final short WAIT_FOR_EVAL = 3;//待评价
    public static final short FINISHED = 4;//已完成
    public static final short REFUND = 4;//售后


    /**
     * 评价类型
     */
    public static final short GOOD_EVALUATION   = 1;
    public static final short COMMON_EVALUATION = 2;
    public static final short BAD_EVALUATION    = 3;


    public static final String nullFlag = "-1";

    public static final short defaultInfo = 3;

    /**
     * 图片类型
     */
    public static final short IMG_COMMON = 0;
    public static final short IMG_EVAL = 1;
    public static final short IMG_SHOW= 2;

    /**
     * 评价状态
     */
    public static final short EVAL = 1;
    public static final short UNEVAL = 0;
}