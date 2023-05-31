package com.example.dreamwallpaper.domain.images

import com.example.dreamwallpaper.data.retrofit.source.ImagesSources
import com.example.dreamwallpaper.domain.models.Image
import com.example.dreamwallpaper.util.Result
import javax.inject.Inject

class ImagesRepository @Inject constructor(
    private val imagesSource: ImagesSources
): ImagesSources {
    override suspend fun getImagesByCategory(category: String, page: Int): Result<Image> {
        return imagesSource.getImagesByCategory(category, page)
    }
}