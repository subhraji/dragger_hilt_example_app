package com.example.hiltapp

import android.app.Application
import android.content.res.Configuration
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MyApplication : Application(){

    override fun onCreate() {
        super.onCreate()
        application = this
    }

    companion object{
        lateinit var application: Application
        fun getInstance(): Application{
            return application
        }
    }
}