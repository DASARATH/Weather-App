package com.example.gopu.myweather.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by dasaratharamireddygopu on 11/10/17.
 */

public class Weather implements Parcelable{

    public Weather() {}

    @SerializedName("id")
    private String mId;
    @SerializedName("icon")
    private String mIcon;
    @SerializedName("description")
    private String mDescription;
    @SerializedName("main")
    private String mMain;

    public String getId() {
        return mId;
    }

    public void setId(String id) {
        this.mId = id;
    }

    public String getIcon() {
        return mIcon;
    }

    public void setIcon(String icon) {
        this.mIcon = icon;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        this.mDescription = description;
    }

    public String getMain() {
        return mMain;
    }

    public void setMain(String main) {
        this.mMain = main;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.mId);
        dest.writeString(this.mIcon);
        dest.writeString(this.mDescription);
        dest.writeString(this.mMain);
    }

    protected Weather(Parcel in) {
        this.mId = in.readString();
        this.mIcon = in.readString();
        this.mDescription = in.readString();
        this.mMain = in.readString();
    }

    public static final Creator<Weather> CREATOR = new Creator<Weather>() {
        @Override
        public Weather createFromParcel(Parcel source) {
            return new Weather(source);
        }

        @Override
        public Weather[] newArray(int size) {
            return new Weather[size];
        }
    };
}
