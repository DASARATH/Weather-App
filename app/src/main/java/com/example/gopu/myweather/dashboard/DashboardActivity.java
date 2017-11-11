package com.example.gopu.myweather.dashboard;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.gopu.myweather.BaseApplication;
import com.example.gopu.myweather.R;
import com.example.gopu.myweather.widgets.ClearableEditText;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DashboardActivity extends BaseApplication
        implements NavigationView.OnNavigationItemSelectedListener, DashboardContract.View {

    @Inject
    DashboardContract.Presenter mPresenter;

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @BindView(R.id.nav_view)
    NavigationView mNavigationView;

    @BindView(R.id.app_bar_layout)
    CoordinatorLayout mCoordinatorLayout;

    @BindView(R.id.content_main_loading_spinner)
    ProgressBar mLoadingSpinner;

    @BindView(R.id.content_main_search_field_text)
    ClearableEditText mCitySearchText;

    @BindView(R.id.content_main_layout)
    RelativeLayout mDashboardLayout;

    @BindView(R.id.content_main_city_name)
    TextView mCityName;

    @BindView(R.id.content_main_last_updated)
    TextView mWeatherLastUpdatedDateAndTime;

    @BindView(R.id.content_main_weather_icon)
    ImageView mWeatherRepresentationImage;

    @BindView(R.id.content_main_temperature)
    TextView mTemperature;

    @BindView(R.id.content_main_how_it_feels)
    TextView mHowItFeels;

    @BindView(R.id.content_main_pressure)
    TextView mAirPressure;

    @BindView(R.id.content_main_humidity)
    TextView mHumidity;

    @BindView(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;

    @BindView(R.id.content_main_refresh_icon)
    ImageView mRefreshButton;

    //region lifecycle methods

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        //Application Dependencies
        getApplicationDependency().inject(this);
        ButterKnife.bind(this);

        setSupportActionBar(mToolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, mDrawerLayout, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawerLayout.setDrawerListener(toggle);
        toggle.syncState();

        mNavigationView.setNavigationItemSelectedListener(this);

        onSearchCityClicked();
    }

    @Override
    protected void onResume() {
        super.onResume();

        mPresenter.attachView(this, getApplicationContext());
    }

    @Override
    protected void onPause() {
        super.onPause();

        mPresenter.detachView();
    }

    @Override
    public void showLoadingSpinner() {
        mLoadingSpinner.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoadingSpinner() {
        mLoadingSpinner.setVisibility(View.GONE);
    }

    @Override
    public void showErrorBanner(String appErrorMessage) {
        updateSnackBarView(appErrorMessage);
    }

    @Override
    public void updateView(final String cityName,
                           final String countryName,
                           final String lastUpdatedDateAndTime,
                           final String temperatureInDegreesCelsius,
                           final String temperatureInFahrenheit,
                           final String howItFeels,
                           final String airPressure,
                           final String humidity,
                           final String weatherIconURL) {

        Glide.with(this)
                .load(weatherIconURL)
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .skipMemoryCache(true)
                .into(mWeatherRepresentationImage);

        mCityName.setText(getString(R.string.dashboard_view_city_with_country, cityName, countryName));
        mWeatherLastUpdatedDateAndTime.setText(getString(R.string.dashboard_view_last_updated, lastUpdatedDateAndTime));
        mTemperature.setText(getString(R.string.dashboard_view_temperature, temperatureInDegreesCelsius, temperatureInFahrenheit));
        mHowItFeels.setText(getString(R.string.dashboard_view_how_it_feels, howItFeels));
        mAirPressure.setText(getString(R.string.dashboard_view_air_pressure, airPressure));
        mHumidity.setText(getString(R.string.dashboard_view_humidity, humidity, "%"));
    }

    @Override
    public void showRefreshButton() {
        mRefreshButton.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideRefreshButton() {
        mRefreshButton.setVisibility(View.GONE);
    }

    //Refreshes the weather for current/saved city
    @OnClick(R.id.content_main_refresh_icon)
    protected void onRefreshButtonClicked() {
        mPresenter.fetchWeatherForSavedCity();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.dashboard, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camara) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    //end of region

    //region private

    private void updateSnackBarView(String string) {
        Snackbar.make(mCoordinatorLayout, string, Snackbar.LENGTH_LONG).show();
    }

    private void hideKeyBoardIfVisible() {
        InputMethodManager imm = (InputMethodManager) this.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(mDashboardLayout.getWindowToken(), 0);
    }

    private void onSearchCityClicked() {
        mCitySearchText.getClearableEditText().setHint(getString(R.string.content_main_search_bar_hint));
        mCitySearchText.getClearableEditText().setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    String city = mCitySearchText.getClearableEditText().getText().toString();

                    if (mCitySearchText.getClearableEditText().getText().length() != 0 &&
                            !city.equals("")) {
                        mPresenter.fetchWeather(city);
                    } else {
                        updateSnackBarView(getString(R.string.dashboard_view_enter_valid_city));
                    }

                    hideKeyBoardIfVisible();

                    return true;
                } else {
                    return false;
                }
            }
        });
    }

    //end of region
}
