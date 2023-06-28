package com.example.dreamwallpaper

import com.example.dreamwallpaper.domain.images.ImagesRepositoryImpl
import com.example.dreamwallpaper.data.retrofit.source.ImagesRepository

object Singletons {

    val imagesRepository: ImagesRepository by lazy {
        ImagesRepositoryImpl()
    }

}