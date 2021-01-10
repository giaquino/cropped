package com.giaquino.android.cropped.ui.topics

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.giaquino.android.cropped.data.model.Topic
import com.giaquino.android.cropped.databinding.TopicsItemBinding
import com.giaquino.android.cropped.ui.topics.TopicsAdapter.TopicsViewHolder
import java.util.*

class TopicsAdapter(private val listener : (Topic) -> Unit) : RecyclerView.Adapter<TopicsViewHolder>() {

    private val topics: MutableList<Topic> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopicsViewHolder {
        return TopicsViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: TopicsViewHolder, position: Int) {
        holder.bind(topics[position], listener)
    }

    override fun getItemCount(): Int {
        return topics.size
    }

    fun setTopics(topics: List<Topic>) {
        this.topics.clear()
        this.topics.addAll(topics)
        notifyDataSetChanged()
    }

    class TopicsViewHolder(private val binding: TopicsItemBinding) : RecyclerView.ViewHolder(binding.root) {
        interface OnItemClickListener {
            fun onItemClick(topic: Topic?)
        }

        fun bind(topic: Topic, listener : (Topic) -> Unit) {
            binding.apply {
                title.text = topic.title
                subtitle.text = "By ${topic.owners[0].name}"
                description.text = topic.description
                contributions.text = "${getDisplayableTotalPhotos(topic.totalPhotos)} contributions"
                card.setOnClickListener { listener(topic) }
            }
            Glide.with(binding.banner).load(topic.coverPhoto.urls.regular).into(binding.banner)
            Glide.with(binding.avatar).load(topic.owners[0].profileImage.large).circleCrop().into(binding.avatar)
        }

        private fun getDisplayableTotalPhotos(totalPhotos: Int): String {
            var total = ""
            if (totalPhotos >= 1000) {
                var temp = totalPhotos / 1000
                total += temp
                temp = (totalPhotos - temp * 1000) / 100
                if (temp > 0) {
                    total += "." + temp + "k"
                }
            } else {
                return totalPhotos.toString()
            }
            return total
        }

        companion object {
            fun create(parent: ViewGroup): TopicsViewHolder {
                return TopicsViewHolder(TopicsItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
            }
        }
    }
}