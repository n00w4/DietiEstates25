package it.unina.dietiestates.application

import android.app.Application
import android.content.Context

class MyApplicationDE : Application() {
    override fun onCreate() {
        super.onCreate()
        it.unina.dietiestates.application.MyApplicationDE.Companion.appContext = applicationContext
    }

    companion object {
        var appContext: Context? = null
    }
}