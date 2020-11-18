package com.giaquino.android.cropped.data.api.request;

import com.google.gson.annotations.SerializedName;

public class AccessTokenRequest {

    @SerializedName("client_id")
    public final String clientId;

    @SerializedName("client_secret")
    public final String clientSecret;

    @SerializedName("redirect_uri")
    public final String redirectUri;

    @SerializedName("code")
    public final String code;

    @SerializedName("grant_type")
    public final String grantType = "authorization_code";

    public AccessTokenRequest(String clientId, String clientSecret, String redirectUri, String code) {
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        this.redirectUri = redirectUri;
        this.code = code;
    }
}
