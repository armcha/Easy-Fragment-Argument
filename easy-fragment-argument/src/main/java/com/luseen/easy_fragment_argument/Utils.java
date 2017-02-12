package com.luseen.easy_fragment_argument;

import java.lang.reflect.Field;

/**
 * Created by Chatikyan on 11.02.2017.
 */

class Utils {

    private Utils() {
    }

    static boolean isInteger(Field field) {
        return field.getType().isAssignableFrom(int.class)
                || field.getType().isAssignableFrom(Integer.class);
    }

    static boolean isBoolean(Field field) {
        return field.getType().isAssignableFrom(boolean.class)
                || field.getType().isAssignableFrom(Boolean.class);
    }

    static boolean isString(Field field) {
        return field.getType().isAssignableFrom(String.class);
    }
}
