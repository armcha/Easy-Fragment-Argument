package com.luseen.fragmentarg;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;

import java.lang.reflect.Field;

/**
 * Created by Chatikyan on 11.02.2017.
 */

public class ArgFragment {

    public static Fragment createFragment(Activity activity, Fragment fragment) {
        Bundle arguments = new Bundle();
        for (Field field : activity.getClass().getDeclaredFields()) {
            Argument argument = field.getAnnotation(Argument.class);
            if (field.isAnnotationPresent(Argument.class) && argument.value().getSimpleName().equals(fragment.getClass().getSimpleName())) {
                if (!field.isAccessible())
                    field.setAccessible(true);
                try {
                    Object object = field.get(activity);
                    if (object instanceof String) {
                        arguments.putString(field.getName(), (String) field.get(activity));
                    }
                    if (object instanceof Integer) {
                        arguments.putInt(field.getName(), (Integer) field.get(activity));
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        fragment.setArguments(arguments);
        return fragment;
    }

    public static void receiveArguments(Fragment fragment) {
        for (Field field : fragment.getClass().getDeclaredFields()) {
            for (String key : fragment.getArguments().keySet()) {
                if (!field.isAccessible())
                    field.setAccessible(true);
                try {
                    if (field.getName().equals(key) && field.isAnnotationPresent(Argument.class)) {
                        if (field.getType().isAssignableFrom(String.class)) {
                            field.set(fragment, fragment.getArguments().getString(field.getName()));
                        }
                        if (field.getType().isAssignableFrom(int.class)) {
                            field.set(fragment, fragment.getArguments().getInt(field.getName()));
                        }
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
