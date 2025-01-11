package com.example.myapplication.application

import android.app.Application
import android.content.Context

class MyApplicationDE : Application() {
    override fun onCreate() {
        super.onCreate()
        appContext = applicationContext
    }

    companion object {
        var appContext: Context? = null
    }
}