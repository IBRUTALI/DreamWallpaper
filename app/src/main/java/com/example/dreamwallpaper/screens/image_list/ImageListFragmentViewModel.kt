package com.example.dreamwallpaper.screens.image_list

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.dreamwallpaper.Singletons
import com.example.dreamwallpaper.data.retrofit.RetrofitRepository
import com.example.dreamwallpaper.data.retrofit.source.BackendException
import com.example.dreamwallpaper.data.retrofit.source.ConnectionException
import com.example.dreamwallpaper.data.retrofit.source.ParseBackendResponseException
import com.example.dreamwallpaper.models.Hit
import com.example.dreamwallpaper.models.Image
import kotlinx.coroutines.launch
import retrofit2.HttpException
import retrofit2.Response

class ImageListFragmentViewModel(application: Application) : AndroidViewModel(application) {

    val imageList: MutableLiveData<Response<Image>> = MutableLiveData()
    val pageLiveData: MutableLiveData<Int> = MutableLiveData()
    val state = MutableLiveData(String())
    private val repository = Singletons.imagesRepository

    fun getImagesByCategory(category: String, page: Int) {
        Log.d("!@#", "Request")
        viewModelScope.launch {
            try {
                pageLiveData.value = page
                imageList.value = repository.getImagesByCategory(category = category, page = page)
                state.value = null
            } catch (e: BackendException) {
                state.value = "Ошибка сервера"
            } catch (e: ConnectionException) {
                state.value = "Нет подключения к интернету"
            }

        }
    }

}