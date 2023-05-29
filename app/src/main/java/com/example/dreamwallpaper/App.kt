package com.example.dreamwallpaper

import android.app.Application
import com.example.dreamwallpaper.screens.di.AppComponent
import com.example.dreamwallpaper.screens.di.DaggerAppComponent

class App: Application() {

    companion object {

        lateinit var  appComponent: AppComponent

    }

    override fun onCreate() {
        super.onCreate()
        initializeDagger()
    }

    private fun initializeDagger() {
        appComponent = DaggerAppComponent.builder()
            .build()
    }

}