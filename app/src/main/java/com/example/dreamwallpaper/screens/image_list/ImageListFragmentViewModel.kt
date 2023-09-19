package com.example.dreamwallpaper.screens.image_list

import androidx.lifecycle.*
import com.example.dreamwallpaper.data.retrofit.models.Image
import com.example.dreamwallpaper.domain.images.ImageRepository
import com.example.dreamwallpaper.util.Result
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.launch
import javax.inject.Inject

class ImageListFragmentViewModel @AssistedInject constructor(
    private val repository: ImageRepository,
    @Assisted private val bundleCategory: String,
    @Assisted private val bundlePage: Int?
) : ViewModel() {

    private val _imageList: MutableLiveData<Result<Image>> = MutableLiveData()
    val imageList: LiveData<Result<Image>> = _imageList

    private val _page: MutableLiveData<Int> = MutableLiveData()
    val page: LiveData<Int> = _page

    private val _category: MutableLiveData<String> = MutableLiveData()
    val category: LiveData<String> = _category

    init {
        _category.value = bundleCategory
        _page.value = if (bundlePage == 0 || bundlePage == null) {
            1
        } else bundlePage

        getImagesByCategory(
            category.value!!,
            page.value!!
        )
    }

    fun getImagesByCategory(category: String, newPage: Int?) {
        viewModelScope.launch {
            _imageList.postValue(Result.Loading())
            _imageList.postValue(repository.getImagesByCategory(category = category, page = newPage ?: 1))

        }
    }

    @AssistedFactory
    interface Factory {
        fun create(
            bundleCategory: String,
            bundlePage: Int?
        ): ImageListFragmentViewModel
    }
}