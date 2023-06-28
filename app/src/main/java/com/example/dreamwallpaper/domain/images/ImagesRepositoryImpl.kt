package com.example.dreamwallpaper.domain.images

import com.example.dreamwallpaper.data.retrofit.api.RetrofitInstance
import com.example.dreamwallpaper.data.retrofit.source.ImagesRepository
import com.example.dreamwallpaper.data.retrofit.models.Image
import com.example.dreamwallpaper.domain.error_handling.ResponseWrapper
import com.example.dreamwallpaper.util.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ImagesRepositoryImpl: ResponseWrapper() ,ImagesRepository {
    override suspend fun getImagesByCategory(category: String, page: Int): Result<Image>  = responseWrap {
        RetrofitInstance.api.getImageListByCategory(category, page)
    }
}