package com.example.dreamwallpaper.data.retrofit.source

import com.example.dreamwallpaper.models.Image
import retrofit2.Response

interface ImagesSources {

    suspend fun getImagesByCategory(category: String, page: Int): Response<Image>

}