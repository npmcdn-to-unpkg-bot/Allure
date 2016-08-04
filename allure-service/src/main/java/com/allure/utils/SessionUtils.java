package com.allure.utils;

import com.allure.config.UserService;
import com.allure.domain.model.Account;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Administrator on 8/3/2016.
 */
public class SessionUtils {

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
