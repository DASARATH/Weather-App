package com.example.gopu.myweather;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.gopu.myweather.dependencies.ApplicationDependency;
import com.example.gopu.myweather.dependencies.ApplicationModule;
import com.example.gopu.myweather.dependencies.DaggerApplicationDependency;

import java.io.File;

/**
 * Created by dasaratharamireddygopu on 11/10/17.
 */

public class BaseApplication extends AppCompatActivity {
    ApplicationDependency mApplicationDependency;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        File cacheFile = new File(getCacheDir(), "responses");
        mApplicationDependency = DaggerApplicationDependency.builder().applicationModule(new ApplicationModule(cacheFile)).build();
    }

    public ApplicationDependency getApplicationDependency() {
        return mApplicationDependency;
    }
}