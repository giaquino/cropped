package com.giaquino.android.cropped.data.repository;

public class SharedRepositoryImpl implements SharedRepository {

    private boolean isLoggedIn;
    private String accessToken;

    @Override
    public boolean isLoggedIn() {
        return isLoggedIn;
    }

    @Override
    public void setLoggedIn(boolean isLoggedIn) {
        this.isLoggedIn = isLoggedIn;
    }

    @Override
    public String getAccessToken() {
        return accessToken;
    }

    @Override
    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
}
