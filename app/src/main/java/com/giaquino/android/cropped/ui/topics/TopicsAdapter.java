package com.giaquino.android.cropped.ui.topics;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.giaquino.android.cropped.databinding.TopicsItemBinding;
import com.giaquino.android.cropped.data.model.Topic;

import java.util.ArrayList;
import java.util.List;

public class TopicsAdapter extends RecyclerView.Adapter<TopicsAdapter.TopicsViewHolder> {

    private List<Topic> topics = new ArrayList<>();
    private TopicsViewHolder.OnItemClickListener itemClickListener;

    public TopicsAdapter(TopicsViewHolder.OnItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    @NonNull
    @Override
    public TopicsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return TopicsViewHolder.create(parent);
    }

    @Override
    public void onBindViewHolder(@NonNull TopicsViewHolder holder, int position) {
        holder.bind(topics.get(position), itemClickListener);
    }

    @Override
    public int getItemCount() {
        return topics.size();
    }

    public void setTopics(List<Topic> topics) {
        this.topics.clear();
        this.topics.addAll(topics);
        this.notifyDataSetChanged();
    }

    public static class TopicsViewHolder extends RecyclerView.ViewHolder {

        public interface OnItemClickListener {
            void onItemClick(Topic topic);
        }

        private TopicsItemBinding binding;

        public static TopicsViewHolder create(ViewGroup parent) {
            TopicsItemBinding binding = TopicsItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
            return new TopicsViewHolder(binding);
        }

        public TopicsViewHolder(TopicsItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(Topic topic, OnItemClickListener listener) {
            binding.title.setText(topic.title);
            binding.subtitle.setText("By " + topic.owners.get(0).name);
            binding.description.setText(topic.description);
            binding.contributions.setText(getDisplayableTotalPhotos(topic.totalPhotos) + " contributions");

            Glide.with(binding.banner).load(topic.coverPhoto.urls.regular).into(binding.banner);
            Glide.with(binding.avatar).load(topic.owners.get(0).profileImage.large).circleCrop().into(binding.avatar);

            binding.card.setOnClickListener(v -> listener.onItemClick(topic));
        }

        private String getDisplayableTotalPhotos(int totalPhotos) {
            String total = "";
            if (totalPhotos >= 1000) {
                int temp = totalPhotos / 1000;
                total += temp;
                temp = (totalPhotos - (temp * 1000)) / 100;
                if (temp > 0) {
                    total += "." + temp + "k";
                }
            } else {
                return String.valueOf(totalPhotos);
            }
            return total;
        }
    }
}
