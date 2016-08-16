package com.allure.common.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by yang_shoulai on 8/16/2016.
 */
public class DateUtils {

    public static final String DATE_SHORT = "yyyy-MM-dd";

    public static String format(Date date, String pattern) {
        if (null == pattern || pattern.trim().length() == 0) {
            pattern = DATE_SHORT;
        }
        if (date != null) {
            DateFormat format = new SimpleDateFormat(pattern);
            return format.format(date);
        }
        return null;
    }
}
