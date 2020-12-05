package com.giaquino.android.cropped.data.api

import com.giaquino.android.cropped.data.model.Topic
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface UnsplashApi {

    @GET("topics")
    fun getTopics(@Query("order_by") orderBy: String,
                  @Query("page") page: Int,
                  @Query("per_page") perPage: Int): Single<List<Topic>>
}