package com.guet.qiusuo.fruittravel.common;


public class SystemConstants {
    // table
    public static final Short STATUS_NORMAL = new Short("1");

    public static final Short STATUS_NEGATIVE = new Short("0");

    public static final Short STATUS_ACTIVE = new Short("1");

    public static final Short USER_INFO_COMPLETE = new Short("1");

    public static final Short USER_INFO_NOT_COMPLETE = new Short("2");

    public static final Short SCENIC_INFO_NOT_COMPLETE = new Short("2");

    //分页
    public static final String DEFAULT_PAGE = "1";

    public static final String DEFAULT_PAGE_SIZE = "20";

    //通知类型
    public static final Short SYSTEM_NOTIFICATION = new Short("0");

    public static final Short SUBJECT_2_NOTIFICATION = new Short("1");

    public static final Short SUBJECT_3_NOTIFICATION = new Short("2");

    public static final Short NEGATIVE_EVALUATE_NOTIFICATION = new Short("3");

    public static final Short NOTIFICATION_DONT_READ = new Short("0");

    public static final Short NOTIFICATION_HAS_READ = new Short("0");

    /**
     * 用户信息不全
     */
    public static final Short USER_INFO_INCOMPLETE = new Short("3");
}