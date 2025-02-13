package it.unina.dietiestates.controller.profile

import android.content.Context
import android.content.Intent
import it.unina.dietiestates.MainActivity
import it.unina.dietiestates.data.dto.SharedPrefManager

class ProfiloController (private val context: Context) {

    fun handleLogout(){
        SharedPrefManager.clear(context)
        val loginPage = Intent(context, MainActivity::class.java)
        context.startActivity(loginPage)
    }
}