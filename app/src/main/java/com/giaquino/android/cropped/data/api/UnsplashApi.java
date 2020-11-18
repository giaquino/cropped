package com.giaquino.android.cropped.data.api;

import com.giaquino.android.cropped.data.api.request.AccessTokenRequest;
import com.giaquino.android.cropped.data.model.AccessToken;
import com.giaquino.android.cropped.data.model.Topic;

import java.util.List;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface UnsplashApi {

    @POST("oauth/token")
    Single<AccessToken> getAccessToken(@Body AccessTokenRequest request);

    @GET("topics")
    Single<List<Topic>> getTopics(@Query("order_by") String orderBy, @Query("page") int page, @Query("per_page") int perPage);
}
