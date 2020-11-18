package com.giaquino.android.cropped.data.repository;

public interface SharedRepository {

    boolean isLoggedIn();

    void setLoggedIn(boolean isLoggedIn);

    String getAccessToken();

    void setAccessToken(String accessToken);
}
