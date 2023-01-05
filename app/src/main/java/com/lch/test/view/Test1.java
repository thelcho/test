package com.lch.test.view;

import android.os.Parcel;
import android.os.Parcelable;

public class Test1 implements Parcelable {
    protected Test1(Parcel in) {
    }

    public static final Creator<Test1> CREATOR = new Creator<Test1>() {
        @Override
        public Test1 createFromParcel(Parcel in) {
            return new Test1(in);
        }

        @Override
        public Test1[] newArray(int size) {
            return new Test1[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
    }
}
