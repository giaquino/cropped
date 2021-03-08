package com.giaquino.android.cropped.ui.base

import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.giaquino.android.cropped.NavGraphMainDirections
import com.giaquino.android.cropped.R
import com.giaquino.android.cropped.common.Constant
import com.giaquino.android.cropped.data.repository.SharedRepository
import timber.log.Timber
import javax.inject.Inject

/**
 * Base fragment for handling authentication
 */
abstract class BaseAuthFragment : Fragment(), FragmentInterface {

    @Inject
    lateinit var sharedRepository: SharedRepository

    private val navController by lazy {
        NavHostFragment.findNavController(this)
    }

    override fun requireNavController(): NavController {
        return navController
    }

    override fun getCurrentSavedStateHandle(): SavedStateHandle {
        return requireNavController().currentBackStackEntry!!.savedStateHandle
    }

    override fun getPreviousSavedStateHandle(): SavedStateHandle {
        return requireNavController().previousBackStackEntry!!.savedStateHandle
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        when {
            sharedRepository.getUnsplashToken() != null -> {
                Timber.d("login successful")
                setContentVisibility(true)
                initialize()
                initializeToolbar()
            }
            getCurrentSavedStateHandle().get<Boolean>(Constant.LOGIN_CANCELLED) == true -> {
                Timber.d("login cancelled")
                requireNavController().popBackStack()
            }
            else -> {
                Timber.d("start login")
                setContentVisibility(false)
                requireNavController().navigate(NavGraphMainDirections.actionGlobalNavGraphLogin())
            }
        }
    }

    private fun setContentVisibility(visible: Boolean) {
        requireView().visibility = if (visible) View.VISIBLE else View.GONE
    }

    private fun initializeToolbar() {
        val toolbar: Toolbar = requireView().findViewById(R.id.toolbar) ?: return
        NavigationUI.setupWithNavController(
                toolbar,
                requireNavController(),
                AppBarConfiguration.Builder(requireNavController().graph).build())
    }
}