package com.example.gopu.myweather.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by dasaratharamireddygopu on 11/10/17.
 */

public class WeatherResponse implements Parcelable{

    public WeatherResponse() {}

    @SerializedName("id")
    private String mId;
    @SerializedName("dt")
    private String mDate;
    @SerializedName("clouds")
    private Clouds mClouds;
    @SerializedName("coord")
    private Coordinates mCoordinates;
    @SerializedName("wind")
    private Wind mWind;
    @SerializedName("cod")
    private String mStatusCode;
    @SerializedName("visibility")
    private String mVisibility;
    @SerializedName("sys")
    private Country mCountry;
    @SerializedName("name")
    private String mName;
    @SerializedName("base")
    private String mBase;
    @SerializedName("weather")
    private List<Weather> mWeather;
    @SerializedName("main")
    private Main mMain;

    public String getId() {
        return mId;
    }

    public void setId(String id) {
        this.mId = id;
    }

    public String getDate() {
        return mDate;
    }

    public void setDate(String date) {
        this.mDate = date;
    }

    public Clouds getClouds() {
        return mClouds;
    }

    public void setClouds(Clouds clouds) {
        this.mClouds = clouds;
    }

    public Coordinates getCoordinates() {
        return mCoordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.mCoordinates = coordinates;
    }

    public Wind getWind() {
        return mWind;
    }

    public void setWind(Wind wind) {
        this.mWind = wind;
    }

    public String getStatusCode() {
        return mStatusCode;
    }

    public void setStatusCode(String statusCode) {
        this.mStatusCode = statusCode;
    }

    public String getVisibility() {
        return mVisibility;
    }

    public void setVisibility(String visibility) {
        this.mVisibility = visibility;
    }

    public Country getCountry() {
        return mCountry;
    }

    public void setCountry(Country country) {
        this.mCountry = country;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        this.mName = name;
    }

    public String getBase() {
        return mBase;
    }

    public void setBase(String base) {
        this.mBase = base;
    }

    public Main getMain() {
        return mMain;
    }

    public void setMain(Main main) {
        this.mMain = main;
    }

    public List<Weather> getWeather() {
        return mWeather;
    }

    public void setWeather(List<Weather> weather) {
        this.mWeather = weather;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.mId);
        dest.writeString(this.mDate);
        dest.writeParcelable(this.mClouds, flags);
        dest.writeParcelable(this.mCoordinates, flags);
        dest.writeParcelable(this.mWind, flags);
        dest.writeString(this.mStatusCode);
        dest.writeString(this.mVisibility);
        dest.writeParcelable(this.mCountry, flags);
        dest.writeString(this.mName);
        dest.writeString(this.mBase);
        dest.writeTypedList(this.mWeather);
        dest.writeParcelable(this.mMain, flags);
    }

    protected WeatherResponse(Parcel in) {
        this.mId = in.readString();
        this.mDate = in.readString();
        this.mClouds = in.readParcelable(Clouds.class.getClassLoader());
        this.mCoordinates = in.readParcelable(Coordinates.class.getClassLoader());
        this.mWind = in.readParcelable(Wind.class.getClassLoader());
        this.mStatusCode = in.readString();
        this.mVisibility = in.readString();
        this.mCountry = in.readParcelable(Country.class.getClassLoader());
        this.mName = in.readString();
        this.mBase = in.readString();
        this.mWeather = in.createTypedArrayList(Weather.CREATOR);
        this.mMain = in.readParcelable(Main.class.getClassLoader());
    }

    public static final Creator<WeatherResponse> CREATOR = new Creator<WeatherResponse>() {
        @Override
        public WeatherResponse createFromParcel(Parcel source) {
            return new WeatherResponse(source);
        }

        @Override
        public WeatherResponse[] newArray(int size) {
            return new WeatherResponse[size];
        }
    };
}
