package com.giaquino.android.cropped.data.repository;

import com.giaquino.android.cropped.data.api.request.AccessTokenRequest;
import com.giaquino.android.cropped.data.common.Resource;
import com.giaquino.android.cropped.data.model.AccessToken;

import io.reactivex.rxjava3.core.Single;

public interface UserRepository {

    Single<Resource<AccessToken>> authorize(AccessTokenRequest request);
}
