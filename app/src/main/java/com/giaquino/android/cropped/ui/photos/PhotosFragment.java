package com.giaquino.android.cropped.ui.photos;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.giaquino.android.cropped.databinding.PhotosFragmentBinding;
import com.giaquino.android.cropped.ui.base.BaseAuthFragment;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class PhotosFragment extends BaseAuthFragment {

    private PhotosFragmentBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = PhotosFragmentBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.previewButton.setOnClickListener(v -> requireNavController().navigate(PhotosFragmentDirections.Companion.actionPhotosFragmentToPhotoDetailsFragment()));
    }
}
