package com.giaquino.android.cropped

import dagger.hilt.android.HiltAndroidApp
import android.app.Application
import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import timber.log.Timber
import timber.log.Timber.DebugTree

val Context.accountDataStore : DataStore<Preferences> by preferencesDataStore(name = "account.ds")

@HiltAndroidApp
class CroppedApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        Timber.plant(DebugTree())
    }
}