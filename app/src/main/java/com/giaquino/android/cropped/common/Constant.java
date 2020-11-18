package com.giaquino.android.cropped.common;

public interface Constant {

    /**
     * Create developer account on https://unsplash.com/developers
     *
     * Add your client-id and client-secret
     *
     * Set cropped://oauth_callback as your redirect url on unsplash developer account
     *
     * Get oauth url from unsplash and set it here
     *
     */
    String UNSPLASH_CLIENT_ID = "";
    String UNSPLASH_CLIENT_SECRET = "";
    String UNSPLASH_OAUTH_URL = "";
    String UNSPLASH_REDIRECT_URI = "cropped://oauth_callback";

    String LOGIN_COMPLETED = "LOGIN_COMPLETED";
}
