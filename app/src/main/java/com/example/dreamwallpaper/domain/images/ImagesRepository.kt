package com.example.dreamwallpaper.domain.images

import com.example.dreamwallpaper.data.retrofit.source.ImagesSources
import com.example.dreamwallpaper.domain.error_handling.BackendException
import com.example.dreamwallpaper.domain.models.Image
import com.example.dreamwallpaper.util.Result

class ImagesRepository(
    val imagesSource: ImagesSources
) {
    suspend fun getImagesByCategory(category: String, page: Int): Result<Image> {
        return imagesSource.getImagesByCategory(category, page)
    }
}