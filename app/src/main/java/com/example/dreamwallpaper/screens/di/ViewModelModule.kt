package com.example.dreamwallpaper.screens.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.dreamwallpaper.screens.image_list.ImageListFragmentViewModel
import com.example.dreamwallpaper.screens.image_list.ImageListViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(ImageListFragmentViewModel::class)
    internal abstract fun bindImageListFragmentViewModel(
        imageListFragmentViewModel: ImageListFragmentViewModel
    ): ViewModel

}