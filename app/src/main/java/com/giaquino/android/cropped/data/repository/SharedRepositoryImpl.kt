package com.giaquino.android.cropped.data.repository

import android.app.Application
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.giaquino.android.cropped.accountDataStore
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

class SharedRepositoryImpl @Inject constructor(private val application : Application) : SharedRepository {

    override fun getUnsplashToken(): String? = runBlocking {
        application.accountDataStore.data.map {
            it[UNSPLASH_TOKEN]
        }.first()
    }

    override fun setUnsplashToken(token: String) {
        runBlocking {
            application.accountDataStore.edit {
                it[UNSPLASH_TOKEN] = token
            }
        }
    }

    companion object {
        val UNSPLASH_TOKEN = stringPreferencesKey("unsplash_token")
    }
}