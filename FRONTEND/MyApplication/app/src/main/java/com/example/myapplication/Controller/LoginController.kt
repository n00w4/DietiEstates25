package com.example.myapplication.controller

import android.content.Context
import android.content.Intent
import com.example.myapplication.view.HomeClienteActivity
import com.example.myapplication.view.SignUpActivity

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
