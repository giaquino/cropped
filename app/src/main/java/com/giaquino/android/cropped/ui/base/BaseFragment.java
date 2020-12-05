package com.giaquino.android.cropped.ui.base;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.giaquino.android.cropped.R;

public abstract class BaseFragment extends Fragment {

    private NavController navController;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(view);
        setupToolbar(view);
    }

    protected final NavController requireNavController() {
        return navController;
    }

    @SuppressWarnings("unchecked")
    protected final <T extends ViewModel> T getViewModel(Class<? extends ViewModel> clazz) {
        return (T) new ViewModelProvider(this).get(clazz);
    }

    @SuppressWarnings("unchecked")
    protected final <T extends ViewModel> T getViewModel(Class<? extends ViewModel> clazz, int graph) {
        return (T) new ViewModelProvider(requireNavController().getBackStackEntry(graph), getDefaultViewModelProviderFactory()).get(clazz);
    }

    protected final SavedStateHandle getCurrentSavedStateHandle() {
        return requireNavController().getCurrentBackStackEntry().getSavedStateHandle();
    }

    protected final SavedStateHandle getPreviousSavedStateHandle() {
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
