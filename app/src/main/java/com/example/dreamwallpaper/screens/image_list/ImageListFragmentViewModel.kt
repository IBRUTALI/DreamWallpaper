package com.example.dreamwallpaper.screens.image_list

import androidx.lifecycle.*
import com.example.dreamwallpaper.data.retrofit.models.Image
import com.example.dreamwallpaper.domain.images.ImageRepository
import com.example.dreamwallpaper.util.Result
import kotlinx.coroutines.launch
import javax.inject.Inject

class ImageListFragmentViewModel @Inject constructor(
    private val repository: ImageRepository
) : ViewModel() {

    private val _imageList: MutableLiveData<Result<Image>> = MutableLiveData()
    val imageList: LiveData<Result<Image>> = _imageList
    val pageLiveData: MutableLiveData<Int> = MutableLiveData()


    fun getImagesByCategory(category: String, page: Int) {
        viewModelScope.launch {
            pageLiveData.value = page
            _imageList.postValue(Result.Loading())
            _imageList.postValue(repository.getImagesByCategory(category = category, page = page))

        }
    }
}