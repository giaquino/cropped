package com.giaquino.android.cropped.data.repository

import com.giaquino.android.cropped.data.common.Resource
import com.giaquino.android.cropped.data.model.Topic
import io.reactivex.rxjava3.core.Single

interface TopicRepository {

    fun getTopics(orderBy: String, page: Int, perPage: Int): Single<Resource<List<Topic>>>
}