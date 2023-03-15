package com.example.dreamwallpaper.data.retrofit.source

import com.example.dreamwallpaper.data.retrofit.RetrofitConfig
import com.example.dreamwallpaper.data.retrofit.RetrofitRepository
import javax.inject.Inject

class RetrofitSourceProvider @Inject constructor(
    val config: RetrofitConfig
): SourcesProvider {

    override fun getImageSource(): ImagesSources {
        return RetrofitRepository(config)
    }

}