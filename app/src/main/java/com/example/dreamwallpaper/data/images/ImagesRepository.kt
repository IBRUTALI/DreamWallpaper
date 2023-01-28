package com.example.dreamwallpaper.data.images

import com.example.dreamwallpaper.data.retrofit.source.BackendException
import com.example.dreamwallpaper.data.retrofit.source.ConnectionException
import com.example.dreamwallpaper.data.retrofit.source.ImagesSources
import com.example.dreamwallpaper.data.retrofit.source.ParseBackendResponseException
import com.example.dreamwallpaper.models.Image
import retrofit2.Response

class ImagesRepository(
    val imagesSource: ImagesSources
) {
    suspend fun getImagesByCategory(category: String, page: Int): Response<Image> {
        return try {
            imagesSource.getImagesByCategory(category, page)
        } catch (e: ConnectionException) {
            throw e
        } catch (e: BackendException) {
            throw e
        }
    }
}