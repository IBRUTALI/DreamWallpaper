package com.example.dreamwallpaper.di

import com.example.dreamwallpaper.data.retrofit.source.ImageRepository
import com.example.dreamwallpaper.domain.images.ImageRepositoryImpl
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
interface RepositoryModule {

    @Binds
    @Singleton
    fun bindImageRepository(
        imageRepositoryImpl: ImageRepositoryImpl
    ): ImageRepository

}