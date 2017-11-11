package com.example.gopu.myweather.dashboard;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;

import com.example.gopu.myweather.BuildConfig;
import com.example.gopu.myweather.models.WeatherResponse;
import com.example.gopu.myweather.networking.NetworkError;
import com.example.gopu.myweather.networking.Service;

import java.text.DateFormat;
import java.util.Date;

import javax.inject.Inject;

import rx.Subscriber;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by dasaratharamireddygopu on 11/10/17.
 */

public class DashboardPresenter implements DashboardContract.Presenter {
    private static final String PREVIOUS_SEARCH = "PREVIOUS_SEARCH";
    private static final String CITY_NAME = "CITY_NAME";
    private static final double KELVIN_IN_DEGREES_CELSIUS = 273.15;

    private final Service mService;
    private DashboardContract.View mView;
    private Context mContext;
    private SharedPreferences mSharedPreferences;

    @NonNull
    private final CompositeSubscription mCompositeSubscription = new CompositeSubscription();

    @Inject
    public DashboardPresenter(Service service) {
        mService = service;
    }

    @Override
    public void attachView(DashboardContract.View view, Context applicationContext) {
        mView = view;
        mContext = applicationContext;
        initSharedPreference();

        //Every time when the view is attached to presenter we fetch the current weather details for saved city.
        fetchWeatherForSavedCity();
    }

    @Override
    public void fetchWeatherForSavedCity() {
        final String searchedCity = getSearchedCity();
        if (!searchedCity.equals("") && searchedCity.length() > 0) {
            fetchWeatherForCity(searchedCity);
        }
    }

    @Override
    public void detachView() {
        // This represents a group of Subscriptions that are unsubscribed together.
        mCompositeSubscription.clear();
    }

    @Override
    public void fetchWeather(String city) {
        fetchWeatherForCity(city);
    }

    private void fetchWeatherForCity(final String city) {
        mView.showLoadingSpinner();

        //Subscribe to mService.getWeatherDetails(city) (Observable) to fetch  WeatherResponse for city.
        Subscription subscription = mService.getWeatherDetails(city).subscribe(new Subscriber<WeatherResponse>() {
            @Override
            public void onCompleted() {
                setSearchedCity(city);
                mView.showRefreshButton();
            }

            @Override
            public void onError(Throwable e) {
                final NetworkError networkError = new NetworkError(e);
                final String mErrorMessage = networkError.getAppErrorMessage();

                mView.showErrorBanner(mErrorMessage);
                mView.hideLoadingSpinner();
            }

            @Override
            public void onNext(WeatherResponse weatherResponse) {
                final String cityName = weatherResponse.getName();
                final String countryName = weatherResponse.getCountry().getCountry();
                final String updateDateAndTime = getUpdateDateAndTime(weatherResponse.getDate());
                final String degreesCelsiusFromKelvin = getDegreesCelsiusFromKelvin(weatherResponse.getMain().getTemperature());
                final String fahrenheitCelsiusFromKelvin = getFahrenheitCelsiusFromKelvin(weatherResponse.getMain().getTemperature());
                final String description = weatherResponse.getWeather().get(0).getDescription();
                final String pressure = weatherResponse.getMain().getPressure();
                final String humidity = weatherResponse.getMain().getHumidity();
                final String weatherIconURL = getWeatherIconURL(weatherResponse.getWeather().get(0).getIcon());

                mView.updateView(cityName,
                        countryName,
                        updateDateAndTime,
                        degreesCelsiusFromKelvin,
                        fahrenheitCelsiusFromKelvin,
                        description,
                        pressure,
                        humidity,
                        weatherIconURL);
                mView.hideLoadingSpinner();
            }
        });

        mCompositeSubscription.add(subscription);
    }

    //region private

    private void initSharedPreference() {
        mSharedPreferences = mContext.getSharedPreferences(PREVIOUS_SEARCH,
                mContext.MODE_PRIVATE);
    }

    private void setSearchedCity(String cityName) {
        SharedPreferences.Editor editor = getSharedPreferenceEditor();
        editor.putString(CITY_NAME, cityName)
                .apply();
    }

    private String getSearchedCity() {
        return mSharedPreferences.getString(CITY_NAME, "");
    }

    private SharedPreferences.Editor getSharedPreferenceEditor() {
        return mSharedPreferences.edit();
    }

    private String getDegreesCelsiusFromKelvin(String temperatureInKelvin) {
        return String.valueOf(Math.round(Double.valueOf(temperatureInKelvin) - KELVIN_IN_DEGREES_CELSIUS));
    }

    private String getFahrenheitCelsiusFromKelvin(String temperatureInKelvin) {
        return String.valueOf(Math.round((9.0 / 5.0 * (Double.valueOf(temperatureInKelvin) - KELVIN_IN_DEGREES_CELSIUS)) + 32));
    }

    private String getUpdateDateAndTime(String date) {
        DateFormat dateFormat = DateFormat.getDateTimeInstance();
        return dateFormat.format(new Date(Long.valueOf(date) * 1000));
    }

    private String getWeatherIconURL(String weatherIcon) {
        StringBuilder imageUrl = new StringBuilder(20);
        imageUrl.append(BuildConfig.IMAGE_URL).append(weatherIcon).append(".png");

        return imageUrl.toString();
    }

    //end region
}
