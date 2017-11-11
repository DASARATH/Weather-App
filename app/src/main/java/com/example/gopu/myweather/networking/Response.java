package com.example.gopu.myweather.networking;

import com.google.gson.annotations.SerializedName;

/**
 * Created by dasaratharamireddygopu on 11/10/17.
 */

public class Response {

    @SerializedName("mStatus")
    public String mStatus;

    public void setStatus(String status) {
        mStatus = status;
    }

    public String getStatus() {
        return mStatus;
    }

    @SuppressWarnings({"unused", "used by Retrofit"})
    public Response() {}

    public Response(String status) {
        mStatus = status;
    }
}