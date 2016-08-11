package com.allure.common.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by yang_shoulai on 8/11/2016.
 */
public class NumberUtils {

    private static final Logger logger = LoggerFactory.getLogger(NumberUtils.class);

    /**
     * parse an object into number int
     *
     * @param object
     * @return null if the given object is null or can not parse
     */
    public static Integer parseInteger(Object object) {
        if (object != null) {
            try {
                return Integer.valueOf(object.toString());
            } catch (NumberFormatException e) {
                logger.error("", e);
            }
        }
        return null;
    }
}
