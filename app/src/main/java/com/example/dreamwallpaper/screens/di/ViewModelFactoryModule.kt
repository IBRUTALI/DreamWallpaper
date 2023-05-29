package com.example.dreamwallpaper.screens.di

import androidx.lifecycle.ViewModelProvider
import com.example.dreamwallpaper.screens.image_list.ImageListViewModelFactory
import dagger.Binds
import dagger.Module

@Module
abstract class ViewModelFactoryModule {

    @Binds
    internal abstract fun bindImageListViewModelFactory(
        factory: ImageListViewModelFactory
    ): ViewModelProvider.Factory

}