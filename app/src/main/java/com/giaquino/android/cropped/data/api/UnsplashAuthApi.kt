package com.giaquino.android.cropped.data.api

import com.giaquino.android.cropped.data.api.request.AccessTokenRequest
import com.giaquino.android.cropped.data.model.AccessToken
import io.reactivex.rxjava3.core.Single
import retrofit2.http.Body
import retrofit2.http.POST

interface UnsplashAuthApi {

    @POST("oauth/token")
    fun getAccessToken(@Body request: AccessTokenRequest): Single<AccessToken>
}