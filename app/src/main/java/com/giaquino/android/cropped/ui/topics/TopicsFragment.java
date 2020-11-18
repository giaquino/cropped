package com.giaquino.android.cropped.ui.topics;

import android.graphics.Rect;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.giaquino.android.cropped.databinding.TopicsFragmentBinding;
import com.giaquino.android.cropped.data.model.Topic;
import com.giaquino.android.cropped.ui.base.BaseAuthFragment;

import java.util.List;

public class TopicsFragment extends BaseAuthFragment {

    private TopicsFragmentBinding binding;
    private TopicsViewModel vm;

    private TopicsAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = TopicsFragmentBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    protected void onLoginCompleted() {
        super.onLoginCompleted();
        initializeViewModel();
        initialize();
    }

    private void initializeViewModel() {
        vm = new TopicsViewModel(getAppContainer().topicRepository);
        vm.topics().observe(getViewLifecycleOwner(), resource -> {
            resource.isLoading(it -> handleLoading(it, true));
            resource.isSuccess(TopicsFragment.this::handleSuccess);
            resource.isFailure(TopicsFragment.this::handleFailure);
        });
    }

    private void initialize() {
        handleLoading(null, false);
        adapter = new TopicsAdapter(topic -> requireNavController().navigate(TopicsFragmentDirections.Companion.actionTopicsFragmentToPhotosFragment(topic.title)));
        binding.list.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        binding.list.setAdapter(adapter);
        binding.list.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent,
                                       @NonNull RecyclerView.State state) {
                outRect.bottom += binding.list.getPaddingTop();
            }
        });
        vm.getTopics();
    }

    private void handleLoading(List<Topic> topics, boolean loading) {
        binding.progress.setVisibility(loading ? View.VISIBLE : View.GONE);
    }

    private void handleSuccess(List<Topic> topics) {
        handleLoading(null, false);
        adapter.setTopics(topics);
    }

    private void handleFailure(Throwable throwable) {
        handleLoading(null, false);
        Toast.makeText(getContext(), throwable.getMessage(), Toast.LENGTH_SHORT).show();
    }
}
