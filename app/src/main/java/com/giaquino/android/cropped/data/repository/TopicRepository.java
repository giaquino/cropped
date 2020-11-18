package com.giaquino.android.cropped.data.repository;

import com.giaquino.android.cropped.data.common.Resource;
import com.giaquino.android.cropped.data.model.Topic;

import java.util.List;

import io.reactivex.rxjava3.core.Single;

public interface TopicRepository {

    Single<Resource<List<Topic>>> getTopics(String orderBy, int page, int perPage);
}
