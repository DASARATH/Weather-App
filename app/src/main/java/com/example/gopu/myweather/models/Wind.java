package com.example.gopu.myweather.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by dasaratharamireddygopu on 11/10/17.
 */

public class Wind implements Parcelable{

    public Wind() {}

    @SerializedName("speed")
    private String mSpeed;
    @SerializedName("deg")
    private String mDegree;

    public String getSpeed() {
        return mSpeed;
    }

    public void setSpeed(String speed) {
        this.mSpeed = speed;
    }

    public String getDegrees() {
        return mDegree;
    }

    public void setDegrees(String degrees) {
        this.mDegree = degrees;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.mSpeed);
        dest.writeString(this.mDegree);
    }

    protected Wind(Parcel in) {
        this.mSpeed = in.readString();
        this.mDegree = in.readString();
    }

    public static final Creator<Wind> CREATOR = new Creator<Wind>() {
        @Override
        public Wind createFromParcel(Parcel source) {
            return new Wind(source);
        }

        @Override
        public Wind[] newArray(int size) {
            return new Wind[size];
        }
    };
}
