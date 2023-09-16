package com.example.dreamwallpaper.data.retrofit.source

import com.example.dreamwallpaper.data.retrofit.api.ImageApi
import com.example.dreamwallpaper.data.retrofit.models.Image
import com.example.dreamwallpaper.domain.error_handling.ResponseWrapper
import com.example.dreamwallpaper.domain.images.ImageRepository
import com.example.dreamwallpaper.util.Result
import javax.inject.Inject

class ImageRepositoryImpl @Inject constructor(
    private val api: ImageApi
): ResponseWrapper() , ImageRepository {
    override suspend fun getImagesByCategory(category: String, page: Int): Result<Image>  = responseWrap {
        api.getImageListByCategory(category, page)
    }
}