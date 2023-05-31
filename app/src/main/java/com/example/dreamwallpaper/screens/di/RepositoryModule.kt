package com.example.dreamwallpaper.screens.di

import com.example.dreamwallpaper.data.retrofit.RetrofitConfig
import com.example.dreamwallpaper.data.retrofit.RetrofitRepository
import com.example.dreamwallpaper.data.retrofit.source.ImagesSources
import com.example.dreamwallpaper.domain.images.ImagesRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Provides
    @Singleton
    fun provideRetrofitRepository(config: RetrofitConfig): ImagesSources {
        return RetrofitRepository(config = config)
    }

    @Provides
    @Singleton
    fun provideImagesRepository(imagesSources: ImagesSources): ImagesSources {
        return ImagesRepository(imagesSources)
    }





}