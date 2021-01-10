package com.giaquino.android.cropped.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.navGraphViewModels
import com.giaquino.android.cropped.R
import com.giaquino.android.cropped.common.Constant
import com.giaquino.android.cropped.databinding.LoginFragmentBinding
import com.giaquino.android.cropped.ui.base.BaseFragment
import com.giaquino.android.cropped.ui.login.LoginFragmentDirections.Companion.actionLoginFragmentToLoginOAuthFragment
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class LoginFragment : BaseFragment() {

    private lateinit var binding: LoginFragmentBinding

    private val vm : LoginViewModel by navGraphViewModels(R.navigation.login)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = LoginFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialize()
    }

    private fun initialize() {
        binding.login.setOnClickListener { login() }
        Timber.d("is logged in %s", vm.isLoggedIn)
        previousSavedStateHandle.set(Constant.LOGIN_COMPLETED, vm.isLoggedIn)
        if (vm.isLoggedIn) {
            Toast.makeText(context, "Login successful", Toast.LENGTH_SHORT).show()
            requireNavController().popBackStack()
        }
    }

    private fun login() {
        requireNavController().navigate(actionLoginFragmentToLoginOAuthFragment())
    }
}