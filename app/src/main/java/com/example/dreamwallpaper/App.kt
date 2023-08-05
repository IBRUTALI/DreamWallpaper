package com.example.dreamwallpaper

import android.app.Application
import com.example.dreamwallpaper.di.AppComponent
import com.example.dreamwallpaper.di.DaggerAppComponent

class App: Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.create()
    }

}