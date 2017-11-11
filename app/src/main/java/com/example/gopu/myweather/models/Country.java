package com.example.gopu.myweather.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by dasaratharamireddygopu on 11/10/17.
 */

public class Country implements Parcelable{

    public Country() {}

    @SerializedName("message")
    private String mMessage;
    @SerializedName("id")
    private String mId;
    @SerializedName("sunset")
    private String mSunset;
    @SerializedName("sunrise")
    private String mSunrise;
    @SerializedName("type")
    private String mType;
    @SerializedName("country")
    private String mCountry;

    public String getMessage() {
        return mMessage;
    }

    public void setMessage(String message) {
        this.mMessage = message;
    }

    public String getmId() {
        return mId;
    }

    public void setmId(String mId) {
        this.mId = mId;
    }

    public String getSunset() {
        return mSunset;
    }

    public void setSunset(String sunset) {
        this.mSunset = sunset;
    }

    public String getSunrise() {
        return mSunrise;
    }

    public void setSunrise(String sunrise) {
        this.mSunrise = sunrise;
    }

    public String getType() {
        return mType;
    }

    public void setType(String type) {
        this.mType = type;
    }

    public String getCountry() {
        return mCountry;
    }

    public void setCountry(String country) {
        this.mCountry = country;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.mMessage);
        dest.writeString(this.mId);
        dest.writeString(this.mSunset);
        dest.writeString(this.mSunrise);
        dest.writeString(this.mType);
        dest.writeString(this.mCountry);
    }

    protected Country(Parcel in) {
        this.mMessage = in.readString();
        this.mId = in.readString();
        this.mSunset = in.readString();
        this.mSunrise = in.readString();
        this.mType = in.readString();
        this.mCountry = in.readString();
    }

    public static final Creator<Country> CREATOR = new Creator<Country>() {
        @Override
        public Country createFromParcel(Parcel source) {
            return new Country(source);
        }

        @Override
        public Country[] newArray(int size) {
            return new Country[size];
        }
    };
}
