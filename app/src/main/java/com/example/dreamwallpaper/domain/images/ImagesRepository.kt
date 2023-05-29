package com.example.dreamwallpaper.domain.images

import com.example.dreamwallpaper.data.retrofit.source.ImagesRetrofitRepository
import com.example.dreamwallpaper.domain.models.Image
import com.example.dreamwallpaper.util.Result
import javax.inject.Inject

//class ImagesRepository @Inject constructor(
//    private val repository: ImagesRetrofitRepository
//): ImagesRetrofitRepository {
//    override suspend fun getImagesByCategory(category: String, page: Int): Result<Image> {
//        return repository.getImagesByCategory(category, page)
//    }
//}