package com.luseen.easy_fragment_argument;

import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by Chatikyan on 13.02.2017.
 */

enum Type {

    STRING,
    BOOLEAN,
    INTEGER,
    SERIALIZABLE,
    PARCELABLE,
    UNDEFINED;

    static Type getType(Object object) {
        if (object instanceof String) {
            return STRING;
        } else if (object instanceof Integer) {
            return INTEGER;
        } else if (object instanceof Boolean) {
            return BOOLEAN;
        } else if (object instanceof Parcelable) {
            return PARCELABLE;
        } else if (object instanceof Serializable) {
            return SERIALIZABLE;
        }
        return UNDEFINED;
    }
}
