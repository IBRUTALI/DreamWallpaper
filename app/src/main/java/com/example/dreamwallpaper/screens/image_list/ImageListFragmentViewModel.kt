package com.example.dreamwallpaper.screens.image_list

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.dreamwallpaper.Singletons
import com.example.dreamwallpaper.domain.models.Image
import com.example.dreamwallpaper.util.Result
import kotlinx.coroutines.launch

class ImageListFragmentViewModel(application: Application) : AndroidViewModel(application) {

    val imageList: MutableLiveData<Result<Image>> = MutableLiveData()
    val pageLiveData: MutableLiveData<Int> = MutableLiveData()
    val errorState = MutableLiveData<String>(null)
    private val repository = Singletons.imagesRepository

    fun getImagesByCategory(category: String, page: Int) {
        viewModelScope.launch {
            when(val result = repository.getImagesByCategory(category = category, page = page)) {
                is Result.Success -> {
                    imageList.value = result
                    pageLiveData.value = page
                }
                is Result.Error -> {
                    errorState.value = "Нет подключения к интернету"
                }
            }
        }
    }
}