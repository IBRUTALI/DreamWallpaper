package com.example.dreamwallpaper.screens.image_list

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.dreamwallpaper.R
import com.example.dreamwallpaper.Singletons
import com.example.dreamwallpaper.data.retrofit.RetrofitRepositoryImpl
import com.example.dreamwallpaper.data.retrofit.source.ImagesRetrofitRepository
import com.example.dreamwallpaper.domain.ResourcesProvider
import com.example.dreamwallpaper.domain.models.Image
import com.example.dreamwallpaper.util.Result
import kotlinx.coroutines.launch
import javax.inject.Inject

class ImageListFragmentViewModel @Inject constructor(
    var repository: RetrofitRepositoryImpl,
    application: Application
) : AndroidViewModel(application) {

    val imageList: MutableLiveData<Result<Image>> = MutableLiveData()
    val pageLiveData: MutableLiveData<Int> = MutableLiveData()
    val errorState = MutableLiveData<String>(null)
    private val resourcesProvider = ResourcesProvider(application)

    fun getImagesByCategory(category: String, page: Int) {
        viewModelScope.launch {
            when(val result = repository.getImagesByCategory(category = category, page = page)) {
                is Result.Success -> {
                    imageList.value = result
                    pageLiveData.value = page
                }
                is Result.Error -> {
                    errorState.value = resourcesProvider.getString(R.string.no_internet_connection)
                }
            }
        }
    }
}