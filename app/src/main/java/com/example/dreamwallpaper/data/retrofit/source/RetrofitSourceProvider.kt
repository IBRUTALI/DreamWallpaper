package com.example.dreamwallpaper.data.retrofit.source

import com.example.dreamwallpaper.data.retrofit.RetrofitConfig
import com.example.dreamwallpaper.data.retrofit.RetrofitRepository

class RetrofitSourceProvider(
    private val config: RetrofitConfig
): SourcesProvider {

    override fun getImageSource(): ImagesSources {
        return RetrofitRepository(config)
    }

}