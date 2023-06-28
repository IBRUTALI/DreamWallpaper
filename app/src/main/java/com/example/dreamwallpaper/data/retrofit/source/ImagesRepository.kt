package com.example.dreamwallpaper.data.retrofit.source

import com.example.dreamwallpaper.data.retrofit.models.Image
import com.example.dreamwallpaper.util.Result

interface ImagesRepository {
    suspend fun getImagesByCategory(category: String, page: Int): Result<Image>
}