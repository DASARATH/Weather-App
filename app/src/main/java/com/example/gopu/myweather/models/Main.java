package com.example.gopu.myweather.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by dasaratharamireddygopu on 11/10/17.
 */

public class Main implements Parcelable{

    public Main() {}

    @SerializedName("humidity")
    private String mHumidity;
    @SerializedName("pressure")
    private String mPressure;
    @SerializedName("temp_max")
    private String mTemperatureMax;
    @SerializedName("temp_min")
    private String mTemperatureMin;
    @SerializedName("temp")
    private String mTemperature;

    public String getHumidity() {
        return mHumidity;
    }

    public void setHumidity(String humidity) {
        this.mHumidity = humidity;
    }

    public String getPressure() {
        return mPressure;
    }

    public void setPressure(String pressure) {
        this.mPressure = pressure;
    }

    public String getTemperatureMax() {
        return mTemperatureMax;
    }

    public void setTemperatureMax(String temperatureMax) {
        this.mTemperatureMax = temperatureMax;
    }

    public String getTemperatureMin() {
        return mTemperatureMin;
    }

    public void setTemperatureMin(String temperatureMin) {
        this.mTemperatureMin = temperatureMin;
    }

    public String getTemperature() {
        return mTemperature;
    }

    public void setTemperature(String temperature) {
        this.mTemperature = temperature;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.mHumidity);
        dest.writeString(this.mPressure);
        dest.writeString(this.mTemperatureMax);
        dest.writeString(this.mTemperatureMin);
        dest.writeString(this.mTemperature);
    }

    protected Main(Parcel in) {
        this.mHumidity = in.readString();
        this.mPressure = in.readString();
        this.mTemperatureMax = in.readString();
        this.mTemperatureMin = in.readString();
        this.mTemperature = in.readString();
    }

    public static final Creator<Main> CREATOR = new Creator<Main>() {
        @Override
        public Main createFromParcel(Parcel source) {
            return new Main(source);
        }

        @Override
        public Main[] newArray(int size) {
            return new Main[size];
        }
    };
}
