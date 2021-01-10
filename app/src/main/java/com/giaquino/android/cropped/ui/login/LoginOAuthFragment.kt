package com.giaquino.android.cropped.ui.login

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebResourceRequest
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.navigation.navGraphViewModels
import com.giaquino.android.cropped.R
import com.giaquino.android.cropped.common.Constant
import com.giaquino.android.cropped.data.common.Resource
import com.giaquino.android.cropped.data.model.Token
import com.giaquino.android.cropped.databinding.LoginOauthFragmentBinding
import com.giaquino.android.cropped.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class LoginOAuthFragment : BaseFragment() {

    private lateinit var binding: LoginOauthFragmentBinding

    private val vm: LoginViewModel by navGraphViewModels(R.navigation.login)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = LoginOauthFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeViewModel()
        initialize()
    }

    private fun initializeViewModel() {
        vm.token.observe(viewLifecycleOwner, Observer { resource: Resource<Token> ->
            resource.isLoading { handleLoading(loading = true, showLabel = true) }
            resource.isFailure(this::handleFailure)
            resource.isSuccess(this::handleSuccess)
        })
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun initialize() {
        handleLoading(loading = true, showLabel = false)

        binding.web.settings.apply {
            textZoom = 100
            javaScriptEnabled = true
            builtInZoomControls = false
            displayZoomControls = false
            cacheMode = WebSettings.LOAD_NO_CACHE
        }

        binding.web.webViewClient = object : WebViewClient() {
            override fun onPageStarted(view: WebView, url: String, favicon: Bitmap) {
                super.onPageStarted(view, url, favicon)
                handleLoading(loading = true, showLabel = false)
            }

            override fun onPageFinished(view: WebView, url: String) {
                super.onPageFinished(view, url)
                handleLoading(loading = false, showLabel = false)
            }

            override fun shouldOverrideUrlLoading(view: WebView, request: WebResourceRequest): Boolean {
                val uri = request.url
                if (uri.toString().contains(Constant.UNSPLASH_REDIRECT_URI)) {
                    handleOauthCode(uri)
                    return true
                }
                return false
            }
        }
        binding.web.loadUrl(Constant.UNSPLASH_OAUTH_URL)
    }

    private fun handleLoading(loading: Boolean, showLabel: Boolean) {
        binding.apply {
            progress.visibility = if (loading) View.VISIBLE else View.GONE
            progressLabel.visibility = if (showLabel) View.VISIBLE else View.GONE
        }
    }

    private fun handleSuccess(token: Token) {
        Timber.d("login success : %s", token.accessToken)
        handleLoading(loading = false, showLabel = false)
        requireNavController().popBackStack()
    }

    private fun handleFailure(throwable: Throwable) {
        Timber.e(throwable, "login failure")
        handleLoading(false, showLabel = false)
        Toast.makeText(context, throwable.message, Toast.LENGTH_SHORT).show()
    }

    private fun handleOauthCode(uri: Uri) {
        Timber.d("login process oauth code : %s", uri.toString())
        vm.authorize(uri.getQueryParameter("code")!!)
    }
}