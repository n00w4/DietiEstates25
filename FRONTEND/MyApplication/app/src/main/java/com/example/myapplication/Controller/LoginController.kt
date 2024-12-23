package com.example.myapplication.controllers

import android.content.Context
import android.content.Intent
import com.example.myapplication.View.HomeClienteActivity
import com.example.myapplication.View.SignUpActivity

class LoginController(private val context: Context) {

    fun onTextRegistratiClicked() {
        val signUpPage = Intent(context, SignUpActivity::class.java)
        context.startActivity(signUpPage)
    }

    fun onBtnAccediClicked() {
        val homePage = Intent(context, HomeClienteActivity::class.java)
        context.startActivity(homePage)
    }
}
