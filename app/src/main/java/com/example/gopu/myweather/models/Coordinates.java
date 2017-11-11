package com.example.gopu.myweather.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by dasaratharamireddygopu on 11/10/17.
 */

public class Coordinates implements Parcelable{

    public Coordinates() {}

    @SerializedName("lon")
    private String mLongitude;
    @SerializedName("lat")
    private String mLatitude;

    public String getLongitude() {
        return mLongitude;
    }

    public void setLongitude(String longitude) {
        this.mLongitude = longitude;
    }

    public String getLatitude() {
        return mLatitude;
    }

    public void setLatitude(String latitude) {
        this.mLatitude = latitude;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.mLongitude);
        dest.writeString(this.mLatitude);
    }

    protected Coordinates(Parcel in) {
        this.mLongitude = in.readString();
        this.mLatitude = in.readString();
    }

    public static final Creator<Coordinates> CREATOR = new Creator<Coordinates>() {
        @Override
        public Coordinates createFromParcel(Parcel source) {
            return new Coordinates(source);
        }

        @Override
        public Coordinates[] newArray(int size) {
            return new Coordinates[size];
        }
    };
}
