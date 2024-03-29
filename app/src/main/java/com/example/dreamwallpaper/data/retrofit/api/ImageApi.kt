package com.example.dreamwallpaper.data.retrofit.api

import com.example.dreamwallpaper.data.retrofit.models.Image
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ImageApi {
    @GET("?key=33106230-b104905cd7ff74ed17e2229af&lang=ru")
    suspend fun getImageListByCategory(
        @Query("category") category: String,
        @Query("page") page: Int
    ): Response<Image>
}

