package com.example.dreamwallpaper.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.dreamwallpaper.screens.image_list.ImageListFragmentViewModel
import javax.inject.Inject
import javax.inject.Provider

class ViewModelFactory @Inject constructor (
myViewModelProvider: Provider<ImageListFragmentViewModel>
) : ViewModelProvider.Factory {
    private val providers = mapOf<Class<*>, Provider<out ViewModel>>(
        ImageListFragmentViewModel::class.java to myViewModelProvider
    )

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return providers[modelClass]!!.get() as T
    }
}