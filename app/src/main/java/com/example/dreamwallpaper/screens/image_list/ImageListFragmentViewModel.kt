package com.example.dreamwallpaper.screens.image_list

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.dreamwallpaper.R
import com.example.dreamwallpaper.Singletons
import com.example.dreamwallpaper.domain.ResourcesProvider
import com.example.dreamwallpaper.data.retrofit.models.Image
import com.example.dreamwallpaper.util.Result
import kotlinx.coroutines.launch

class ImageListFragmentViewModel(application: Application) : AndroidViewModel(application) {

    private val _imageList: MutableLiveData<Result<Image>> = MutableLiveData()
    val imageList: LiveData<Result<Image>> = _imageList
    val pageLiveData: MutableLiveData<Int> = MutableLiveData()
    private val repository = Singletons.imagesRepository


    fun getImagesByCategory(category: String, page: Int) {
        viewModelScope.launch {
            pageLiveData.value = page
            _imageList.postValue(Result.Loading())
            _imageList.postValue(repository.getImagesByCategory(category = category, page = page))

        }
    }
}