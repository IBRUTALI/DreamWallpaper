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

    private val _page: MutableLiveData<Int> = MutableLiveData()
    val page: LiveData<Int> = _page

    private val _category: MutableLiveData<String> = MutableLiveData()
    val category: LiveData<String> = _category

    init {
        getImagesByCategory(
            category.value ?: "",
            page.value ?: 0
        )
    }

    fun getImagesByCategory(category: String, newPage: Int) {
        viewModelScope.launch {
            _page.value = newPage
            _imageList.postValue(Result.Loading())
            _imageList.postValue(repository.getImagesByCategory(category = category, page = newPage))

        }
    }
}