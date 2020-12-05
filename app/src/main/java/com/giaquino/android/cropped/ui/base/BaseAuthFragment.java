package com.giaquino.android.cropped.ui.base;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.giaquino.android.cropped.NavGraphMainDirections;
import com.giaquino.android.cropped.common.Constant;
import com.giaquino.android.cropped.data.repository.SharedRepository;

import javax.inject.Inject;

import timber.log.Timber;

public abstract class BaseAuthFragment extends BaseFragment {

    @Inject
    SharedRepository sharedRepository;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setViewVisibility(false);

        if (isLoginCancelled()) {
            onLoginCancelled();
            return;
        }

        if (sharedRepository.isLoggedIn()) {
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
