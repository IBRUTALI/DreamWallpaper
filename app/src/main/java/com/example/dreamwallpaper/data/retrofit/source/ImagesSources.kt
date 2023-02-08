package com.example.dreamwallpaper.data.retrofit.source

import com.example.dreamwallpaper.domain.models.Image
import com.example.dreamwallpaper.util.Result

interface ImagesSources {
    suspend fun getImagesByCategory(category: String, page: Int): Result<Image>
}