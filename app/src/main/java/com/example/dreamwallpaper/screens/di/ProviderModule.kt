package com.example.dreamwallpaper.screens.di

import com.example.dreamwallpaper.data.retrofit.RetrofitConfig
import com.example.dreamwallpaper.data.retrofit.source.ImagesSources
import com.example.dreamwallpaper.data.retrofit.source.RetrofitSourceProvider
import com.example.dreamwallpaper.data.retrofit.source.SourcesProvider
import dagger.Module
import dagger.Provides

@Module
class ProviderModule {

    @Provides
    fun provideRetrofitSourceProvider(config: RetrofitConfig): SourcesProvider {
        return RetrofitSourceProvider(config = config)
    }

    @Provides
    fun provideImagesSource(sourcesProvider: SourcesProvider): ImagesSources {
        return sourcesProvider.getImageSource()
    }

}