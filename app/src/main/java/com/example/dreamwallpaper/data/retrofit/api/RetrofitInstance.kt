package com.example.dreamwallpaper.data.retrofit.api

import com.example.dreamwallpaper.BASE_URL
import com.example.dreamwallpaper.data.retrofit.RetrofitConfig
import com.squareup.moshi.Moshi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object RetrofitInstance {

//    val sourcesProvider: SourcesProvider by lazy<SourcesProvider> {
//        val moshi = Moshi.Builder().build()
//        val config = RetrofitConfig(
//            createRetrofit(moshi),
//            moshi
//        )
//        RetrofitSourceProvider(config)
//    }

//    private val interceptor = HttpLoggingInterceptor().apply {
//        this.level = HttpLoggingInterceptor.Level.BODY
//    }
//
//    private val client = OkHttpClient.Builder().apply {
//        this.addInterceptor(interceptor)
//    }.build()
//
//    private fun createRetrofit(moshi: Moshi): Retrofit {
//        return Retrofit.Builder()
//            .baseUrl(BASE_URL)
//            .client(client)
//            .addConverterFactory(MoshiConverterFactory.create(moshi))
//            .build()
//    }
}