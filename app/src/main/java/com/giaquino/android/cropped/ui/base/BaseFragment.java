package com.giaquino.android.cropped.ui.base;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.SavedStateHandle;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.giaquino.android.cropped.CroppedApplication;
import com.giaquino.android.cropped.R;
import com.giaquino.android.cropped.di.AppContainer;

public abstract class BaseFragment extends Fragment {

    public NavController navController;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(view);
        setupToolbar(view);
    }

    protected AppContainer getAppContainer() {
        return ((CroppedApplication) getContext().getApplicationContext()).appContainer;
    }

    protected NavController requireNavController() {
        return navController;
    }

    protected SavedStateHandle getCurrentSavedStateHandle() {
        return requireNavController().getCurrentBackStackEntry().getSavedStateHandle();
    }

    protected SavedStateHandle getPreviousSavedStateHandle() {
        return requireNavController().getPreviousBackStackEntry().getSavedStateHandle();
    }

    private void setupToolbar(View view) {
        Toolbar toolbar = view.findViewById(R.id.toolbar);
        if (toolbar == null) {
            return;
        }
        AppBarConfiguration configuration = new AppBarConfiguration.Builder(requireNavController().getGraph()).build();
        NavigationUI.setupWithNavController(toolbar, requireNavController(), configuration);
    }
}
