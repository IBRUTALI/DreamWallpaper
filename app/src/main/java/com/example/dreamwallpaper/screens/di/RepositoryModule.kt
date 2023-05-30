package com.example.dreamwallpaper.screens.di

import com.example.dreamwallpaper.data.retrofit.RetrofitConfig
import com.example.dreamwallpaper.data.retrofit.RetrofitRepositoryImpl
import com.example.dreamwallpaper.data.retrofit.source.ImagesRetrofitRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Provides
    @Singleton
    fun provideRetrofitRepository(config: RetrofitConfig): ImagesRetrofitRepository {
        return RetrofitRepositoryImpl(config = config)
    }

//    @Provides
//    @Singleton
//    fun provideImagesRepository(imagesSources: ImagesRetrofitRepository): ImagesRetrofitRepository {
//        return ImagesRepository(imagesSources)
//    }





}