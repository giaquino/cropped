package com.giaquino.android.cropped.data.api.request

import com.giaquino.android.cropped.common.Constant
import com.google.gson.annotations.SerializedName

data class TokenRequest(
        @SerializedName("code") val code: String,
        @SerializedName("redirect_uri") val redirectUri: String = Constant.UNSPLASH_REDIRECT_URI,
        @SerializedName("client_id") val clientId: String = Constant.UNSPLASH_CLIENT_ID,
        @SerializedName("client_secret") val clientSecret: String = Constant.UNSPLASH_CLIENT_SECRET,
        @SerializedName("grant_type") val grantType: String = "authorization_code")