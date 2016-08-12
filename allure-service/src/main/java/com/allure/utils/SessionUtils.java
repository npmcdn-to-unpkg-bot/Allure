package com.allure.utils;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Administrator on 8/3/2016.
 */
public class SessionUtils {


    public static final String KEY_VERIFY_CODE = "key_verify_code";


    /**
     * add attribute in session
     *
     * @param request http servlet request
     * @param key     attribute key
     * @param value   attribute value
     */
    public static void setAttribute(HttpServletRequest request, String key, Object value) {
        request.getSession().setAttribute(key, value);
    }

    /**
     * get attribute from session
     *
     * @param request http servlet request
     * @param key     attribute key
     * @return
     */
    public static Object getAttribute(HttpServletRequest request, String key) {
        return request.getSession().getAttribute(key);
    }


}
