package com.guet.qiusuo.fruittravel.utils;

import java.util.regex.Pattern;

/**
 * @author: libuyan
 * @date: 2022/1/26 2:22
 */
public class RegularUtil {
    public static boolean verifyDateFormat(String dateString) {
        //校验日期的正则表达式, 形式为 yyyy/MM/dd
        Pattern datePattern = Pattern.compile("^(?:(?!0000)[0-9]{4}/(?:(?:0[1-9]|1[0-2])/" +
                "(?:0[1-9]|1[0-9]|2[0-8])|" +
                "(?:0[13-9]|1[0-2])/(?:29|30)|(?:0[13578]|1[02])/31)|(?:[0-9]{2}" +
                "(?:0[48]|[2468][048]|[13579][26])|(?:0[48]|[2468][048]|[13579][26])00)/02/29)$");
        return datePattern.matcher(dateString).matches();
    }
}
