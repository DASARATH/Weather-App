package com.example.gopu.myweather.dependencies;

import com.example.gopu.myweather.dashboard.DashboardContract;
import com.example.gopu.myweather.dashboard.DashboardPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by dasaratharamireddygopu on 11/10/17.
 */

@Module
public class PresenterModule {

    @Provides
    public DashboardContract.Presenter providesDashboardPresenter(DashboardPresenter presenter) {
        return presenter;
    }
}
