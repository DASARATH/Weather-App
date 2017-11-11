package com.example.gopu.myweather.dependencies;

import com.example.gopu.myweather.dashboard.DashboardContract;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

import static org.mockito.Mockito.mock;

/**
 * Created by dasaratharamireddygopu on 11/11/17.
 */

@Module
public class MockPresenterModule {

    @Provides
    @Singleton
    DashboardContract.Presenter providesDashboardPresenter() {
        return mock(DashboardContract.Presenter.class);
    }
}
