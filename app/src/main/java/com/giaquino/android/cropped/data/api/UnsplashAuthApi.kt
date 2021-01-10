package com.giaquino.android.cropped.data.api

import com.giaquino.android.cropped.data.api.request.TokenRequest
import com.giaquino.android.cropped.data.model.Token
import io.reactivex.rxjava3.core.Single
import retrofit2.http.Body
import retrofit2.http.POST

interface UnsplashAuthApi {

    @POST("oauth/token")
    fun getAccessToken(@Body request: TokenRequest): Single<Token>
}