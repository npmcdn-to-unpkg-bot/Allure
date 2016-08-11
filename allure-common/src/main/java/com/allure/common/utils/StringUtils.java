package com.allure.common.utils;

import org.springframework.security.authentication.encoding.Md5PasswordEncoder;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by yang_shoulai on 2015/10/12.
 */
public class StringUtils {

    public static boolean isEmpty(String str) {
        return str == null || str.trim().isEmpty();
    }


    public static String toMD5(String source) {
        return new Md5PasswordEncoder().encodePassword(source, null);
    }

    /**
     * 判断号码是不是手机号
     *
     * @param number
     * @return
     */
    public static boolean isPhoneNumber(String number) {
        Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");
        Matcher m = p.matcher(number);
        return m.matches();
    }

    /**
     * 判断邮箱地址是否正确
     *
     * @param email
     * @return
     */
    public static boolean isEmail(String email) {
        Pattern p = Pattern.compile("^([a-z0-9A-Z]+[-|_|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$");
        Matcher m = p.matcher(email);
        return m.matches();
    }


    /**
     * 分隔字符串，返回int[]
     *
     * @param str
     * @param splitor
     * @return
     */
    public static int[] splitToIntArray(String str, String splitor) {
        if (isEmpty(str) || splitor == null) return new int[0];
        String[] strs = str.split(splitor);
        int[] array = new int[strs.length];
        for (int i = 0; i < strs.length; i++) {
            array[i] = Integer.valueOf(strs[i]);
        }
        return array;
    }

}
