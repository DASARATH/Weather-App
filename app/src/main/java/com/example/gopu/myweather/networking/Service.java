package com.example.gopu.myweather.networking;

import com.example.gopu.myweather.BuildConfig;
import com.example.gopu.myweather.models.WeatherResponse;

import javax.inject.Inject;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by dasaratharamireddygopu on 11/10/17.
 */

public class Service {
    private final NetworkService mNetworkService;

    @Inject
    public Service(NetworkService networkService) {
        mNetworkService = networkService;
    }

    public Observable<WeatherResponse> getWeatherDetails(final String cityName) {

        // This return Observable, Presenter can subscribe to this observable to fetch WeatherResponse.
        return mNetworkService.getWeatherDetails(cityName, BuildConfig.APPID)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .onErrorResumeNext(new Func1<Throwable, Observable<? extends WeatherResponse>>() {
                    @Override
                    public Observable<? extends WeatherResponse> call(Throwable throwable) {
                        return rx.Observable.error(throwable);
                    }
                });
    }
}
