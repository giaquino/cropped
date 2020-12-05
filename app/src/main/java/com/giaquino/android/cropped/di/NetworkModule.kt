package com.giaquino.android.cropped.di

import com.giaquino.android.cropped.data.api.UnsplashApi
import com.giaquino.android.cropped.data.api.UnsplashAuthApi
import com.giaquino.android.cropped.data.repository.SharedRepository
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber

@Module
@InstallIn(ApplicationComponent::class)
object NetworkModule {

    @Provides
    fun provideGson(): Gson {
        return Gson()
    }

    @Provides
    fun provideGsonConverterFactory(gson : Gson): GsonConverterFactory {
        return GsonConverterFactory.create(gson)
    }

    @Provides
    fun provideRxJava3CallAdapterFactory(): RxJava3CallAdapterFactory {
        return RxJava3CallAdapterFactory.create()
    }

    @Suppress("ObjectLiteralToLambda")
    @Provides
    fun provideOkHttpClient(sharedRepository: SharedRepository): OkHttpClient {
        val logger = object : HttpLoggingInterceptor.Logger {
            override fun log(message: String) = Timber.d(message)
        }
        val interceptor = Interceptor {
            if (sharedRepository.isLoggedIn) {
                it.proceed(it.request().newBuilder().addHeader("Authorization", "Bearer " + sharedRepository.accessToken).build())
            } else {
                it.proceed(it.request())
            }
        }
        return OkHttpClient.Builder()
                .addNetworkInterceptor(interceptor)
                .addInterceptor(HttpLoggingInterceptor(logger).setLevel(HttpLoggingInterceptor.Level.BODY))
                .build()
    }

    @Provides
    fun provideUnsplashApi(client : OkHttpClient, converterFactory : GsonConverterFactory, callAdapterFactory : RxJava3CallAdapterFactory) : UnsplashApi {
        return Retrofit.Builder()
                .client(client)
                .baseUrl("https://api.unsplash.com/")
                .addConverterFactory(converterFactory)
                .addCallAdapterFactory(callAdapterFactory)
                .build()
                .create(UnsplashApi::class.java)
    }

    @Provides
    fun provideUnsplashAuthApi(client : OkHttpClient, converterFactory : GsonConverterFactory, callAdapterFactory : RxJava3CallAdapterFactory) : UnsplashAuthApi {
        return Retrofit.Builder()
                .client(client)
                .baseUrl("https://unsplash.com/")
                .addConverterFactory(converterFactory)
                .addCallAdapterFactory(callAdapterFactory)
                .build()
                .create(UnsplashAuthApi::class.java)
    }
}