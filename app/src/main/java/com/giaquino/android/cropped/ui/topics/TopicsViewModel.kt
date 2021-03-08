package com.giaquino.android.cropped.ui.topics

import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import com.giaquino.android.cropped.data.common.Resource
import com.giaquino.android.cropped.data.model.Topic
import com.giaquino.android.cropped.data.repository.TopicRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TopicsViewModel @Inject constructor(private val topicRepository: TopicRepository) : ViewModel() {

    private val _topics = MediatorLiveData<Resource<List<Topic>>>()
    val topics : LiveData<Resource<List<Topic>>> = _topics

    fun getTopics() {
        val source: LiveData<Resource<List<Topic>>> = LiveDataReactiveStreams
                .fromPublisher(topicRepository.getTopics("featured", 1, 10).toFlowable())

        _topics.addSource(source) { resource: Resource<List<Topic>> ->
            resource.isSuccess { _topics.removeSource(source) }
            resource.isFailure { _topics.removeSource(source) }
            _topics.setValue(resource)
        }
    }
}