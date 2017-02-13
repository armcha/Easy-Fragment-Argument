package com.luseen.easy_fragment_argument;

import android.app.Activity;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.util.Log;

import java.io.Serializable;
import java.lang.reflect.Field;

/**
 * Created by Chatikyan on 11.02.2017.
 */

public class ArgumentFactory {

    private static final String ERROR_MESSAGE = "TYPE IS UNDEFINED";
    private static final String TAG = "ArgumentFactory";

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
                getTypeAndPut(activity, field, arguments);
            }
        }
        fragment.setArguments(arguments);
        return fragment;
    }

    private static void getTypeAndPut(Activity activity, Field field, Bundle arguments) {
        try {
            Object object = field.get(activity);
            Type type = Type.getType(object);
            switch (type) {
                case STRING:
                    arguments.putString(field.getName(), (String) object);
                    break;
                case INTEGER:
                    arguments.putInt(field.getName(), (Integer) object);
                    break;
                case BOOLEAN:
                    arguments.putBoolean(field.getName(), (Boolean) object);
                    break;
                case PARCELABLE:
                    arguments.putParcelable(field.getName(), (Parcelable) object);
                    break;
                case SERIALIZABLE:
                    arguments.putSerializable(field.getName(), (Serializable) object);
                    break;
                case UNDEFINED:
                    Log.e(TAG, ERROR_MESSAGE);
                    break;
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    private static void receiveArguments(Fragment fragment) {
        Bundle bundle = fragment.getArguments();
        for (Field field : fragment.getClass().getDeclaredFields()) {
            for (String key : bundle.keySet()) {
                setFieldValues(fragment, field, bundle, key);
            }
        }
    }

    private static void setFieldValues(Fragment fragment, Field field, Bundle bundle, String key) {
        try {
            if (field.getName().equals(key) && field.isAnnotationPresent(Argument.class)) {
                setAccessIfNeeded(field);
                if (isString(field)) {
                    field.set(fragment, bundle.getString(field.getName()));
                } else if (isInteger(field)) {
                    field.set(fragment, bundle.getInt(field.getName()));
                } else if (isBoolean(field)) {
                    field.set(fragment, bundle.getBoolean(field.getName()));
                } else if (isParcelableObject(field)) {
                    field.set(fragment, bundle.getParcelable(field.getName()));
                } else if (isSerializableObject(field)) {
                    field.set(fragment, bundle.getSerializable(field.getName()));
                }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
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

    private static boolean isParcelableObject(Field field) {
        for (Class<?> clazz : field.getType().getInterfaces()) {
            if (clazz.isAssignableFrom(Parcelable.class)) {
                return true;
            }
        }
        return false;
    }

    private static boolean isSerializableObject(Field field) {
        Class<?> clazz = field.getType();
        return !clazz.isPrimitive()
                && !clazz.isAssignableFrom(Number.class)
                && isSerializableImplemented(field);
    }

    private static boolean isSerializableImplemented(Field field) {
        for (Class<?> clazz : field.getType().getInterfaces()) {
            if (clazz.isAssignableFrom(Serializable.class)) {
                return true;
            }
        }
        return false;
    }
}
