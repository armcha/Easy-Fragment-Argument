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
                    argument.value().getSimpleName().equals(fragment.getClass().getSimpleName())) {
                setAccessIfNeeded(field);
                try {
                    Object object = field.get(activity);
                    if (object instanceof String) {
                        arguments.putString(field.getName(), (String) field.get(activity));
                    } else if (object instanceof Integer) {
                        arguments.putInt(field.getName(), (Integer) field.get(activity));
                    } else if (object instanceof Boolean) {
                        arguments.putBoolean(field.getName(), (Boolean) field.get(activity));
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
        for (Field field : fragment.getClass().getDeclaredFields()) {
            for (String key : fragment.getArguments().keySet()) {
                try {
                    if (field.getName().equals(key) && field.isAnnotationPresent(Argument.class)) {
                        setAccessIfNeeded(field);
                        if (field.getType().isAssignableFrom(String.class)) {
                            field.set(fragment, fragment.getArguments().getString(field.getName()));
                        } else if (Utils.isInteger(field)) {
                            field.set(fragment, fragment.getArguments().getInt(field.getName()));
                        } else if (Utils.isBoolean(field)) {
                            field.set(fragment, fragment.getArguments().getBoolean(field.getName()));
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
}
