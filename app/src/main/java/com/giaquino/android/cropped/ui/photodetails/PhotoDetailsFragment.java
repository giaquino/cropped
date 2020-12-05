package com.giaquino.android.cropped.ui.photodetails;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.giaquino.android.cropped.databinding.PhotoDetailsFragmentBinding;
import com.giaquino.android.cropped.ui.base.BaseAuthFragment;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class PhotoDetailsFragment extends BaseAuthFragment {

    private PhotoDetailsFragmentBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = PhotoDetailsFragmentBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }
}
