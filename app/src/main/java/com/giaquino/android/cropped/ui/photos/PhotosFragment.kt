package com.giaquino.android.cropped.ui.photos

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.giaquino.android.cropped.databinding.PhotosFragmentBinding
import com.giaquino.android.cropped.ui.base.BaseAuthFragment
import com.giaquino.android.cropped.ui.photos.PhotosFragmentDirections.Companion.actionPhotosFragmentToPhotoDetailsFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PhotosFragment : BaseAuthFragment() {

    private lateinit var binding: PhotosFragmentBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = PhotosFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun initialize() {
        binding.previewButton.setOnClickListener {
            requireNavController().navigate(actionPhotosFragmentToPhotoDetailsFragment())
        }
    }
}