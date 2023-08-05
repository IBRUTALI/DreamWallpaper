package com.example.dreamwallpaper.di

import com.example.dreamwallpaper.BASE_URL
import com.example.dreamwallpaper.data.retrofit.api.ImageApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
interface NetworkModule {

    companion object {
        @Provides
        @Singleton
        fun provideImageService(): ImageApi {
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(MoshiConverterFactory.create())
                .build()
                .create()
        }
    }

}