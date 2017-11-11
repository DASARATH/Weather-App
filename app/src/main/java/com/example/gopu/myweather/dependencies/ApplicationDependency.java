package com.example.gopu.myweather.dependencies;

import com.example.gopu.myweather.dashboard.DashboardActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by dasaratharamireddygopu on 11/10/17.
 */

@Singleton
@Component(modules = {ApplicationModule.class, PresenterModule.class})
public interface ApplicationDependency {
    void inject(DashboardActivity dashBoardActivity);
}