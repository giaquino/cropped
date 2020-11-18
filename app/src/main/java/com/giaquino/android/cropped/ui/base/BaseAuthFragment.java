package com.giaquino.android.cropped.ui.base;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;

import com.giaquino.android.cropped.NavGraphMainDirections;
import com.giaquino.android.cropped.R;
import com.giaquino.android.cropped.common.Constant;

import timber.log.Timber;

public abstract class BaseAuthFragment extends BaseFragment {

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setViewVisibility(false);

        if (isLoginCancelled()) {
            onLoginCancelled();
            return;
        }

        if (getAppContainer().sharedRepository.isLoggedIn()) {
            onLoginCompleted();
        } else {
            startLogin();
        }
    }

    /**
     * saved state handle will have value once login is cancelled from LoginOauthFragment
     */
    private boolean isLoginCancelled() {
        Boolean completed = getCurrentSavedStateHandle().get(Constant.LOGIN_COMPLETED);
        return completed != null && !completed;
    }

    private void startLogin() {
        Timber.d("start login");
        requireNavController().navigate(NavGraphMainDirections.Companion.actionGlobalLogin());
    }

    private void setViewVisibility(boolean visible) {
        getView().setVisibility(visible ? View.VISIBLE : View.GONE);
    }

    protected void onLoginCompleted() {
        Timber.d("login completed");
        setViewVisibility(true);
    }

    protected void onLoginCancelled() {
        Timber.d("login cancelled");
        getActivity().finish();
    }
}
