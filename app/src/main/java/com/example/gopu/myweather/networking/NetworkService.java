package com.example.gopu.myweather.networking;

import com.example.gopu.myweather.models.WeatherResponse;

import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by dasaratharamireddygopu on 11/10/17.
 */

public interface NetworkService {
    @Headers("contentType: application/json")
    @GET("data/2.5/weather")
    Observable<WeatherResponse> getWeatherDetails(@Query("q") String query, @Query("APPID") String applicationId);
}