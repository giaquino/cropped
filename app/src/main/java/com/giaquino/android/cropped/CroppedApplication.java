package com.giaquino.android.cropped;

import android.app.Application;

import com.giaquino.android.cropped.di.AppContainer;

import timber.log.Timber;

public class CroppedApplication extends Application {

    public AppContainer appContainer;

    @Override
    public void onCreate() {
        super.onCreate();
        Timber.plant(new Timber.DebugTree());
        initializeContainers();
    }

    private void initializeContainers() {
        appContainer = new AppContainer();
    }
}
