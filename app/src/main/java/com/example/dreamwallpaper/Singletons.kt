package com.example.dreamwallpaper

import com.example.dreamwallpaper.data.images.ImagesRepository
import com.example.dreamwallpaper.data.retrofit.api.RetrofitInstance
import com.example.dreamwallpaper.data.retrofit.source.ImagesSources
import com.example.dreamwallpaper.data.retrofit.source.SourcesProvider

object Singletons {
    private val sourcesProvider: SourcesProvider by lazy {
        RetrofitInstance.sourcesProvider
    }

    private val imagesSource: ImagesSources by lazy {
        sourcesProvider.getImageSource()
    }

    val imagesRepository: ImagesRepository by lazy {
        ImagesRepository(
            imagesSource = imagesSource
        )
    }
}