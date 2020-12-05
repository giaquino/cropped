package com.giaquino.android.cropped.data.repository;

import com.giaquino.android.cropped.data.api.UnsplashAuthApi;
import com.giaquino.android.cropped.data.api.request.AccessTokenRequest;
import com.giaquino.android.cropped.data.common.Resource;
import com.giaquino.android.cropped.data.model.AccessToken;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Single;

public class UserRepositoryImpl implements UserRepository {

    private final UnsplashAuthApi api;
    private final SharedRepository sharedRepository;

    @Inject
    public UserRepositoryImpl(UnsplashAuthApi api, SharedRepository sharedRepository) {
        this.api = api;
        this.sharedRepository = sharedRepository;
    }

    @Override
    public Single<Resource<AccessToken>> authorize(AccessTokenRequest request) {
        return api.getAccessToken(request)
                .map(Resource::success)
                .onErrorReturn(Resource::failure)
                .doOnSuccess(resource -> {
                    resource.isSuccess(it -> {
                        sharedRepository.setLoggedIn(true);
                        sharedRepository.setAccessToken(it.accessToken);
                    });
                });
    }
}
