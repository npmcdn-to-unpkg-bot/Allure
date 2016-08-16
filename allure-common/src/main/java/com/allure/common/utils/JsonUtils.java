package com.allure.common.utils;

import com.google.gson.Gson;

/**
 * Created by yang_shoulai on 8/16/2016.
 */
public class JsonUtils {

    public static String toJson(Object obj) {
        if (obj == null || obj instanceof String) {
            throw new RuntimeException();
        }
        return new Gson().toJson(obj);
    }

}
