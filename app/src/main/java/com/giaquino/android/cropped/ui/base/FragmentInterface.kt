package com.giaquino.android.cropped.ui.base

import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavController

interface FragmentInterface {

    fun initialize()

    fun requireNavController(): NavController

    fun getCurrentSavedStateHandle(): SavedStateHandle

    fun getPreviousSavedStateHandle(): SavedStateHandle
}