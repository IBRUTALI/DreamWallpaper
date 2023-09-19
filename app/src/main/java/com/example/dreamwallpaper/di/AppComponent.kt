package com.example.dreamwallpaper.di

import com.example.dreamwallpaper.screens.ViewModelFactory
import com.example.dreamwallpaper.screens.image_list.ImageListFragment
import com.example.dreamwallpaper.screens.image_list.ImageListFragmentViewModel
import dagger.Component
import javax.inject.Scope
import javax.inject.Singleton

@Singleton
@Component(modules = [
    NetworkModule::class,
    RepositoryModule::class
])
interface AppComponent {

    fun injectImageListFragment(fragment: ImageListFragment)
    fun imageListFragmentViewModel(): ImageListFragmentViewModel.Factory

}