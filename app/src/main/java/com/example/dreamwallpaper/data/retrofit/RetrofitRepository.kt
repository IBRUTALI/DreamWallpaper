package com.example.dreamwallpaper.data.retrofit

import com.example.dreamwallpaper.data.retrofit.api.ApiService
import com.example.dreamwallpaper.data.retrofit.source.BaseRetrofitSource
import com.example.dreamwallpaper.data.retrofit.source.ImagesSources
import com.example.dreamwallpaper.domain.models.Image
import com.example.dreamwallpaper.util.Result
import javax.inject.Inject

class RetrofitRepository @Inject constructor(
    config: RetrofitConfig
) : BaseRetrofitSource(config), ImagesSources {
    @Inject lateinit var apiService: ApiService

//    private val apiService = retrofit.create(ApiService::class.java)

    override suspend fun getImagesByCategory(category: String, page: Int): Result<Image> =
        wrapRetrofitExceptions {
            apiService.getImageListByCategory(category = category, page = page)
        }


}