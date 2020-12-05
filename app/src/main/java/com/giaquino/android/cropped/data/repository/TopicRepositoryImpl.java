package com.giaquino.android.cropped.data.repository;

import com.giaquino.android.cropped.data.api.UnsplashApi;
import com.giaquino.android.cropped.data.common.Resource;
import com.giaquino.android.cropped.data.model.Topic;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Single;

public class TopicRepositoryImpl implements TopicRepository {

    private UnsplashApi api;

    private List<Topic> topics = new ArrayList<>();

    @Inject
    public TopicRepositoryImpl(UnsplashApi api) {
        this.api = api;
    }

    @Override
    public Single<Resource<List<Topic>>> getTopics(String orderBy, int page, int perPage) {
        if (!topics.isEmpty()) {
            return Single.just(Resource.success(topics));
        }
        return api.getTopics(orderBy, page, perPage)
                .map(Resource::success)
                .onErrorReturn(Resource::failure)
                .doOnSuccess(resource -> resource.isSuccess(data -> {
                    topics.clear();
                    topics.addAll(data);
                }));
    }
}
