package com.giaquino.android.cropped.ui.topics;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.LiveDataReactiveStreams;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.ViewModel;

import com.giaquino.android.cropped.data.common.Resource;
import com.giaquino.android.cropped.data.model.Topic;
import com.giaquino.android.cropped.data.repository.TopicRepository;

import java.util.List;

public class TopicsViewModel extends ViewModel {

    private TopicRepository topicRepository;

    private MediatorLiveData<Resource<List<Topic>>> topics = new MediatorLiveData<>();

    public TopicsViewModel(TopicRepository topicRepository) {
        this.topicRepository = topicRepository;
    }

    public LiveData<Resource<List<Topic>>> topics() {
        return topics;
    }

    public void getTopics() {
        LiveData<Resource<List<Topic>>> source = LiveDataReactiveStreams
                .fromPublisher(topicRepository.getTopics("featured", 1, 10).toFlowable());

        topics.addSource(source, resource -> {
            if (resource.status != Resource.Status.LOADING) {
                topics.removeSource(source);
            }
            topics.setValue(resource);
        });
    }
}
