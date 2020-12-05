package com.giaquino.android.cropped

import dagger.hilt.android.HiltAndroidApp
import android.app.Application
import timber.log.Timber
import timber.log.Timber.DebugTree

@HiltAndroidApp
class CroppedApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        Timber.plant(DebugTree())
    }
}