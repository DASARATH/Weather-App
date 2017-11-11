package com.example.gopu.myweather.dashboard;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.gopu.myweather.BaseTest;
import com.example.gopu.myweather.models.Country;
import com.example.gopu.myweather.models.Main;
import com.example.gopu.myweather.models.Weather;
import com.example.gopu.myweather.models.WeatherResponse;
import com.example.gopu.myweather.networking.NetworkError;
import com.example.gopu.myweather.networking.Service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

import rx.observers.TestObserver;

import static junit.framework.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by dasaratharamireddygopu on 11/10/17.
 */
@RunWith(MockitoJUnitRunner.class)
public class DashboardPresenterTest extends BaseTest {

    @Mock
    Service mServices;

    @Mock
    DashboardContract.View mView;

    @Mock
    Context mContext;

    @Mock
    SharedPreferences mSharedPreferences;

    private DashboardPresenter mDashboardPresenter;
    private TestObserver<WeatherResponse> mTestObserverRestaurants;

    @Before
    public void setup() {
        super.setup();

        mDashboardPresenter = new DashboardPresenter(mServices);
        mDashboardPresenter.attachView(mView, mContext);
        mTestObserverRestaurants = new TestObserver<>();
    }

    @Test
    public void onAttachView_ifAlreadySearchedCityAvailable_updateView() {
        when(mSharedPreferences.getString("CITY_NAME", "")).thenReturn("Detroit");

        onFetchWeatherUpdateView();
    }

    @Test
    public void onAttach_onFetchWeatherForSavedCityError_shouldUpdateView() {
        NetworkError networkError = mock(NetworkError.class);

        when(networkError.getAppErrorMessage()).thenReturn(NetworkError.DEFAULT_ERROR_MESSAGE);

        mTestObserverRestaurants.onError(networkError);

        verify(mView).showErrorBanner(NetworkError.DEFAULT_ERROR_MESSAGE);
        verify(mView).hideLoadingSpinner();
    }

    @Test
    public void onFetchWeather_shouldUpdateView() {
        mDashboardPresenter.fetchWeather("Detroit");

        onFetchWeatherUpdateView();
    }

    @Test
    public void onFetchWeatherComplete_shouldWriteCityToSharedPref() {
        mDashboardPresenter.fetchWeather("Detroit");

        WeatherResponse weatherResponse = mock(WeatherResponse.class);

        when(weatherResponse.getName()).thenReturn("Detroit");

        mTestObserverRestaurants.onNext(weatherResponse);
        mTestObserverRestaurants.onCompleted();

        assertEquals(mSharedPreferences.getString("CITY_NAME", "").toString(), "Detroit");
        verify(mView).showRefreshButton();
    }

    //region private

    private void onFetchWeatherUpdateView() {
        WeatherResponse weatherResponse = mock(WeatherResponse.class);
        Country country = mock(Country.class);
        Main main = mock(Main.class);
        Weather weather = mock(Weather.class);

        List<Weather> weatherList = Arrays.asList(weather);

        when(weatherResponse.getName()).thenReturn("Detroit");
        when(weatherResponse.getCountry()).thenReturn(country);
        when(country.getCountry()).thenReturn("US");
        when(weatherResponse.getDate()).thenReturn("1510349700");
        when(weatherResponse.getMain()).thenReturn(main);
        when(main.getTemperature()).thenReturn("267");
        when(main.getPressure()).thenReturn("1024");
        when(main.getHumidity()).thenReturn("46");
        when(weatherResponse.getWeather()).thenReturn(weatherList);
        when(weatherList.get(0).getDescription()).thenReturn("overcast clouds");
        when(weatherList.get(0).getIcon()).thenReturn("http://openweathermap.org/img/w/04n.png");

        verify(mView).showLoadingSpinner();

        mTestObserverRestaurants.onNext(weatherResponse);

        verify(mView).updateView("Detroit", "US", "Nov 10, 2017 4:35:00PM", "-6", "21", "overcast clouds", "1024", "46", "http://openweathermap.org/img/w/04n.png");
        verify(mView).hideLoadingSpinner();
    }

    //end region
}