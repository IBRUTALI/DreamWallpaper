package com.example.dreamwallpaper.data.retrofit

import com.example.dreamwallpaper.data.retrofit.api.ApiService
import com.example.dreamwallpaper.data.retrofit.source.BaseRetrofitSource
import com.example.dreamwallpaper.data.retrofit.source.ImagesSources
import com.example.dreamwallpaper.models.Image
import retrofit2.Response

class RetrofitRepository(config: RetrofitConfig
): BaseRetrofitSource(config), ImagesSources {

    private val apiService = retrofit.create(ApiService::class.java)

    override suspend fun getImagesByCategory(category: String, page: Int): Response<Image>  = wrapRetrofitExceptions {
        apiService.getImageListByCategory(category = category, page = page)
    }

}