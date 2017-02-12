package com.luseen.fragmentarg;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Chatikyan on 12.02.2017.
 */

public class Phone implements Parcelable {

    private String model;
    private float androidVersion;
    private boolean isRooted;

    public Phone(String model, float androidVersion, boolean isRooted) {
        this.model = model;
        this.androidVersion = androidVersion;
        this.isRooted = isRooted;
    }

    protected Phone(Parcel in) {
        model = in.readString();
        androidVersion = in.readInt();
        isRooted = in.readByte() != 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(model);
        dest.writeFloat(androidVersion);
        dest.writeByte((byte) (isRooted ? 1 : 0));
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Phone> CREATOR = new Creator<Phone>() {
        @Override
        public Phone createFromParcel(Parcel in) {
            return new Phone(in);
        }

        @Override
        public Phone[] newArray(int size) {
            return new Phone[size];
        }
    };

    public String getModel() {
        return model;
    }

    public float getAndroidVersion() {
        return androidVersion;
    }

    public boolean isRooted() {
        return isRooted;
    }
}
