package com.example.dreamwallpaper.screens.di

import com.example.dreamwallpaper.screens.image.ImageFullscreenFragment
import com.example.dreamwallpaper.screens.image_list.ImageListFragment
import com.example.dreamwallpaper.screens.image_list.ImageListFragmentViewModel
import com.example.dreamwallpaper.screens.image_list.ImageListViewModelFactory
import com.example.dreamwallpaper.screens.main.MainActivity
import com.example.dreamwallpaper.screens.main.MainFragment
import com.example.dreamwallpaper.screens.splash.SplashFragment
import dagger.Component
import javax.inject.Singleton

@Component(
    modules = [
        AppModule::class, FirebaseModule::class, ProviderModule::class,
        RemoteModule::class, RepositoryModule::class, ViewModelModule::class,
        ViewModelFactoryModule::class
    ]
)

@Singleton
interface AppComponent {

    //Activities
    fun inject(activity: MainActivity)

    //Fragments
    fun inject(fragment: MainFragment)
    fun inject(fragment: SplashFragment)
    fun inject(fragment: ImageListFragment)
    fun inject(fragment: ImageFullscreenFragment)

}