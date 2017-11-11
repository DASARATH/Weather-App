package com.example.gopu.myweather.dashboard;

import android.content.Context;

/**
 * Created by dasaratharamireddygopu on 11/10/17.
 */

public class DashboardContract {

    public interface View {
        void showLoadingSpinner();

        void hideLoadingSpinner();

        void showErrorBanner(String appErrorMessage);

        void updateView(final String cityName,
                        final String countryName,
                        final String lastUpdatedDateAndTime,
                        final String temperatureInDegreesCelsius,
                        final String temperatureInFahrenheit,
                        final String howItFeels,
                        final String airPressure,
                        final String humidity,
                        final String weatherIcon);

        void showRefreshButton();

        void hideRefreshButton();
    }

    public interface Presenter {
        void attachView(View view, Context applicationContext);

        void detachView();

        void fetchWeather(final String city);

        void fetchWeatherForSavedCity();
    }
}
