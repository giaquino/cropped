package com.giaquino.android.cropped.ui.login;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;

import com.giaquino.android.cropped.R;
import com.giaquino.android.cropped.common.Constant;
import com.giaquino.android.cropped.data.repository.SharedRepository;
import com.giaquino.android.cropped.data.repository.UserRepository;
import com.giaquino.android.cropped.databinding.LoginOauthFragmentBinding;
import com.giaquino.android.cropped.data.model.AccessToken;
import com.giaquino.android.cropped.ui.base.BaseFragment;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;
import timber.log.Timber;

@AndroidEntryPoint
public class LoginOAuthFragment extends BaseFragment {

    private LoginOauthFragmentBinding binding;
    private LoginViewModel vm;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = LoginOauthFragmentBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initializeViewModel();
        initialize();
    }

    private void initializeViewModel() {
        vm = getViewModel(LoginViewModel.class, R.id.nav_graph_login);
        vm.accessToken().observe(getViewLifecycleOwner(), resource -> {
            resource.isLoading(it -> handleLoading(true, true));
            resource.isFailure(this::handleFailure);
            resource.isSuccess(this::handleSuccess);
        });
    }

    @SuppressLint("SetJavaScriptEnabled")
    private void initialize() {
        handleLoading(true, false);

        WebSettings settings = binding.web.getSettings();
        settings.setTextZoom(100);
        settings.setJavaScriptEnabled(true);
        settings.setBuiltInZoomControls(false);
        settings.setDisplayZoomControls(false);
        settings.setDisplayZoomControls(false);
        settings.setBuiltInZoomControls(false);
        settings.setCacheMode(WebSettings.LOAD_NO_CACHE);

        binding.web.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                handleLoading(true, false);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                handleLoading(false, false);
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                Uri uri = request.getUrl();
                if (uri.toString().contains(Constant.UNSPLASH_REDIRECT_URI)) {
                    handleOauthCode(uri);
                    return true;
                }
                return false;
            }
        });
        binding.web.loadUrl(Constant.UNSPLASH_OAUTH_URL);
    }

    private void handleLoading(boolean loading, boolean showLabel) {
        binding.progress.setVisibility(loading ? View.VISIBLE : View.GONE);
        binding.progressLabel.setVisibility(showLabel ? View.VISIBLE : View.GONE);
    }

    private void handleSuccess(AccessToken accessToken) {
        Timber.d("login success : %s", accessToken.accessToken);
        handleLoading(false, false);
        requireNavController().popBackStack();
    }

    private void handleFailure(Throwable throwable) {
        Timber.e(throwable, "login failure");
        handleLoading(false, false);
        Toast.makeText(getContext(), throwable.getMessage(), Toast.LENGTH_SHORT).show();
    }

    private void handleOauthCode(Uri uri) {
        Timber.d("login process oauth code : %s", uri.toString());
        vm.authorize(uri.getQueryParameter("code"));
    }
}
