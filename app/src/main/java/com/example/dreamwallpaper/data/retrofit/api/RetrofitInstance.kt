package com.example.dreamwallpaper.data.retrofit.api

import com.example.dreamwallpaper.BASE_URL
import com.example.dreamwallpaper.data.retrofit.RetrofitConfig
import com.example.dreamwallpaper.data.retrofit.source.RetrofitSourceProvider
import com.example.dreamwallpaper.data.retrofit.source.SourcesProvider
import com.squareup.moshi.Moshi
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object RetrofitInstance {

    val sourcesProvider: SourcesProvider by lazy<SourcesProvider> {
        val moshi = Moshi.Builder().build()
        val config = RetrofitConfig(
            createRetrofit(moshi),
            moshi
        )
        RetrofitSourceProvider(config)
    }

    private fun createRetrofit(moshi: Moshi): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
    }
}