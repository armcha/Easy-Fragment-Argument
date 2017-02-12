package com.luseen.easy_fragment_argument;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import java.lang.reflect.Field;

/**
 * Created by Chatikyan on 11.02.2017.
 */

public class ArgFragment {

    public static void onAttach(Fragment fragment) {
        receiveArguments(fragment);
    }

    public static Fragment createFragment(Activity activity, Fragment fragment) {
        Bundle arguments = new Bundle();
        for (Field field : activity.getClass().getDeclaredFields()) {
            Argument argument = field.getAnnotation(Argument.class);
            if (field.isAnnotationPresent(Argument.class) &&
                    argument.value().getName().equals(fragment.getClass().getName())) {
                setAccessIfNeeded(field);
                try {
                    Object object = field.get(activity);
                    if (object instanceof String) {
                        arguments.putString(field.getName(), (String) object);
                    } else if (object instanceof Integer) {
                        arguments.putInt(field.getName(), (Integer) object);
                    } else if (object instanceof Boolean) {
                        arguments.putBoolean(field.getName(), (Boolean) object);
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        fragment.setArguments(arguments);
        return fragment;
    }

    private static void receiveArguments(Fragment fragment) {
        Bundle bundle = fragment.getArguments();
        for (Field field : fragment.getClass().getDeclaredFields()) {
            for (String key : bundle.keySet()) {
                try {
                    if (field.getName().equals(key) && field.isAnnotationPresent(Argument.class)) {
                        setAccessIfNeeded(field);
                        if (isString(field)) {
                            field.set(fragment, bundle.getString(field.getName()));
                        } else if (isInteger(field)) {
                            field.set(fragment, bundle.getInt(field.getName()));
                        } else if (isBoolean(field)) {
                            field.set(fragment, bundle.getBoolean(field.getName()));
                        }
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static void setAccessIfNeeded(Field field) {
        if (!field.isAccessible())
            field.setAccessible(true);
    }

    private static boolean isInteger(Field field) {
        return field.getType().isAssignableFrom(int.class)
                || field.getType().isAssignableFrom(Integer.class);
    }

    private static boolean isBoolean(Field field) {
        return field.getType().isAssignableFrom(boolean.class)
                || field.getType().isAssignableFrom(Boolean.class);
    }

    private static boolean isString(Field field) {
        return field.getType().isAssignableFrom(String.class);
    }
}
