package com.giaquino.android.cropped.ui.topics

import android.graphics.Rect
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration
import com.giaquino.android.cropped.data.common.Resource
import com.giaquino.android.cropped.data.model.Topic
import com.giaquino.android.cropped.databinding.TopicsFragmentBinding
import com.giaquino.android.cropped.ui.base.BaseAuthFragment
import com.giaquino.android.cropped.ui.topics.TopicsFragmentDirections.Companion.actionTopicsFragmentToPhotosFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TopicsFragment : BaseAuthFragment() {

    private lateinit var binding: TopicsFragmentBinding
    private lateinit var adapter: TopicsAdapter

    private val vm: TopicsViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = TopicsFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun initialize() {
        handleLoading(loading = false)
        adapter = TopicsAdapter { topic: Topic -> requireNavController().navigate(actionTopicsFragmentToPhotosFragment(topic.title)) }
        binding.list.adapter = adapter
        binding.list.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.list.addItemDecoration(object : ItemDecoration() {
            override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
                outRect.bottom += binding.list.paddingTop
            }
        })

        vm.topics.observe(viewLifecycleOwner, Observer { resource: Resource<List<Topic>> ->
            resource.isLoading { handleLoading(true) }
            resource.isSuccess(this::handleSuccess)
            resource.isFailure(this::handleFailure)
        })
        vm.getTopics()
    }

    private fun handleLoading(loading: Boolean) {
        binding.progress.visibility = if (loading) View.VISIBLE else View.GONE
    }

    private fun handleSuccess(topics: List<Topic>) {
        handleLoading(loading = false)
        adapter.setTopics(topics)
    }

    private fun handleFailure(throwable: Throwable) {
        handleLoading(loading = false)
        Toast.makeText(context, throwable.message, Toast.LENGTH_SHORT).show()
    }
}