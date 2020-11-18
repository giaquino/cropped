package com.giaquino.android.cropped.di;

import com.giaquino.android.cropped.data.api.UnsplashApi;
import com.giaquino.android.cropped.data.repository.SharedRepository;
import com.giaquino.android.cropped.data.repository.SharedRepositoryImpl;
import com.giaquino.android.cropped.data.repository.TopicRepository;
import com.giaquino.android.cropped.data.repository.TopicRepositoryImpl;
import com.giaquino.android.cropped.data.repository.UserRepository;
import com.giaquino.android.cropped.data.repository.UserRepositoryImpl;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import timber.log.Timber;

/**
 * TODO: use dependency injection (Hilt)
 */
public class AppContainer {

    public final SharedRepository sharedRepository;
    public final TopicRepository topicRepository;
    public final UserRepository userRepository;

    public AppContainer() {
        sharedRepository = new SharedRepositoryImpl();

        OkHttpClient client = new OkHttpClient.Builder()
                .addNetworkInterceptor(chain -> {
                    if (sharedRepository.isLoggedIn()) {
                        return chain.proceed(chain.request().newBuilder().addHeader("Authorization", "Bearer " + sharedRepository.getAccessToken()).build());
                    }
                    return chain.proceed(chain.request());
                })
                .addInterceptor(new HttpLoggingInterceptor(s -> Timber.d(s)).setLevel(HttpLoggingInterceptor.Level.BODY))
                .build();

        UnsplashApi api = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .baseUrl("https://unsplash.com/")
                .client(client)
                .build()
                .create(UnsplashApi.class);
        userRepository = new UserRepositoryImpl(api, sharedRepository);

        api = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .baseUrl("https://api.unsplash.com/")
                .client(client)
                .build()
                .create(UnsplashApi.class);
        topicRepository = new TopicRepositoryImpl(api);
    }
}
