package com.example.myapplication.view.info

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.MainActivity
import com.example.myapplication.R

class SignUpResult : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.signup_result) //per ora è una pagina fatta male

        val resultMessage = intent.getStringExtra("resultMessage") ?: "No message"
        val statusCode = intent.getIntExtra("responseCode", -1)
        val resultTextView = findViewById<TextView>(R.id.resultTextView)
        val statusTextView = findViewById<TextView>(R.id.statusTextView)

        val btnLogin = findViewById<Button>(R.id.loginButton)

        resultTextView.text = resultMessage
        statusTextView.text = "$statusCode"

        btnLogin.setOnClickListener(){
            onBtnLoginClicked()
        }
    }

    fun onBtnLoginClicked() {
        val loginPage = Intent(this, MainActivity::class.java)
        startActivity(loginPage)
    }
}