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
import com.giaquino.android.cropped.R

/**
 * Base fragment without authentication
 */
abstract class BaseFragment : Fragment(), FragmentInterface {

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
        initializeToolbar()
        initialize()
    }

    private fun initializeToolbar() {
        val toolbar: Toolbar = requireView().findViewById(R.id.toolbar) ?: return
        NavigationUI.setupWithNavController(
                toolbar,
                requireNavController(),
                AppBarConfiguration.Builder(requireNavController().graph).build())
    }
}