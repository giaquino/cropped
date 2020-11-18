package com.giaquino.android.cropped.data.model;

import com.google.gson.annotations.SerializedName;

public class AccessToken {

    @SerializedName("access_token")
    public final String accessToken;

    @SerializedName("token_type")
    public final String tokenType;

    @SerializedName("scope")
    public final String scope;

    @SerializedName("created_at")
    public final String createdAt;

    public AccessToken(String accessToken, String tokenType, String scope, String createdAt) {
        this.accessToken = accessToken;
        this.tokenType = tokenType;
        this.scope = scope;
        this.createdAt = createdAt;
    }
}
