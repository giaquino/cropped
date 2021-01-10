package com.giaquino.android.cropped.ui.base

import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.giaquino.android.cropped.R

abstract class BaseFragment : Fragment() {

    private lateinit var navController: NavController

    val currentSavedStateHandle: SavedStateHandle
        get() = requireNavController().currentBackStackEntry!!.savedStateHandle

    val previousSavedStateHandle: SavedStateHandle
        get() = requireNavController().previousBackStackEntry!!.savedStateHandle

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        setupToolbar(view)
    }

    protected fun requireNavController(): NavController {
        return navController
    }

    private fun setupToolbar(view: View) {
        val toolbar: Toolbar = view.findViewById(R.id.toolbar) ?: return
        val configuration = AppBarConfiguration.Builder(requireNavController().graph).build()
        NavigationUI.setupWithNavController(toolbar, requireNavController(), configuration)
    }
}