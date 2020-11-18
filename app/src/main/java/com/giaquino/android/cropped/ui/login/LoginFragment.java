package com.giaquino.android.cropped.ui.login;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;

import com.giaquino.android.cropped.R;
import com.giaquino.android.cropped.common.Constant;
import com.giaquino.android.cropped.databinding.LoginFragmentBinding;
import com.giaquino.android.cropped.ui.base.BaseFragment;

import timber.log.Timber;

public class LoginFragment extends BaseFragment {

    private LoginFragmentBinding binding;
    private LoginViewModel vm;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = LoginFragmentBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initializeViewModel();
        initialize();
    }

    private void initializeViewModel() {
        vm = new ViewModelProvider(requireNavController().getViewModelStoreOwner(R.id.nav_graph_login),
                new LoginViewModel.Factory(
                        getAppContainer().userRepository,
                        getAppContainer().sharedRepository)).get(LoginViewModel.class);
    }

    private void initialize() {
        binding.login.setOnClickListener(v -> login());

        Timber.d("is logged in %s", vm.isLoggedIn());

        getPreviousSavedStateHandle().set(Constant.LOGIN_COMPLETED, vm.isLoggedIn());
        if (vm.isLoggedIn()) {
            Toast.makeText(getContext(), "Login successful", Toast.LENGTH_SHORT).show();
            requireNavController().popBackStack();
        }
    }

    private void login() {
        requireNavController().navigate(LoginFragmentDirections.Companion.actionLoginFragmentToLoginOAuthFragment());
    }
}
