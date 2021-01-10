package com.giaquino.android.cropped.ui.base

import android.os.Bundle
import android.view.View
import com.giaquino.android.cropped.NavGraphMainDirections.Companion.actionGlobalLogin
import com.giaquino.android.cropped.common.Constant
import com.giaquino.android.cropped.data.repository.SharedRepository
import timber.log.Timber
import javax.inject.Inject

abstract class BaseAuthFragment : BaseFragment() {

    @Inject
    lateinit var sharedRepository: SharedRepository

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setViewVisibility(false)
        when {
            isLoginCancelled() -> onLoginCancelled()
            sharedRepository.isLoggedIn -> onLoginCompleted()
            else -> startLogin()
        }
    }

    /**
     * saved state handle will have value once login is cancelled from LoginOauthFragment
     */
    private fun isLoginCancelled(): Boolean {
        return currentSavedStateHandle.get<Boolean>(Constant.LOGIN_COMPLETED) == true
    }

    private fun startLogin() {
        Timber.d("start login")
        requireNavController().navigate(actionGlobalLogin())
    }

    private fun setViewVisibility(visible: Boolean) {
        requireView().visibility = if (visible) View.VISIBLE else View.GONE
    }

    protected open fun onLoginCompleted() {
        Timber.d("login completed")
        setViewVisibility(true)
    }

    protected open fun onLoginCancelled() {
        Timber.d("login cancelled")
        requireActivity().finish()
    }
}