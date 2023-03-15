package com.example.dreamwallpaper.screens.di

import android.app.Application
import dagger.Module
import dagger.Provides

@Module
class AppModule {

    @Provides
    fun provideApplication(): Application {
        return Application()
    }

}