package com.giaquino.android.cropped.data.repository

import com.giaquino.android.cropped.data.api.UnsplashApi
import com.giaquino.android.cropped.data.common.Resource
import com.giaquino.android.cropped.data.model.Topic
import io.reactivex.rxjava3.core.Single
import java.util.*
import java.util.function.Consumer
import javax.inject.Inject

class TopicRepositoryImpl @Inject constructor(private val api: UnsplashApi) : TopicRepository {

    private val topics: MutableList<Topic> = ArrayList()

    override fun getTopics(orderBy: String, page: Int, perPage: Int): Single<Resource<List<Topic>>> {
        return when {
            topics.isNotEmpty() -> {
                Single.just(Resource.success<List<Topic>>(topics))
            }
            else -> {
                api.getTopics(orderBy, page, perPage)
                        .map<Resource<List<Topic>>> { data: List<Topic> -> Resource.success(data) }
                        .onErrorReturn { error: Throwable -> Resource.failure(error) }
                        .doOnSuccess { resource: Resource<List<Topic>> ->

                            resource.isSuccess { data ->
                                topics.clear()
                                topics.addAll(data)
                            }
                        }
            }
        }
    }
}