package com.giaquino.android.cropped.ui.photodetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.giaquino.android.cropped.databinding.PhotoDetailsFragmentBinding
import com.giaquino.android.cropped.ui.base.BaseAuthFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PhotoDetailsFragment : BaseAuthFragment() {

    private lateinit var binding: PhotoDetailsFragmentBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = PhotoDetailsFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }
}