package com.example.myapplication.view

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.R
import com.example.myapplication.controller.SignUpController

class SignUpActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.signup_page)

        val nome = findViewById<EditText>(R.id.editTextNome)
        val cognome = findViewById<EditText>(R.id.editTextCognome)
        val email = findViewById<EditText>(R.id.editTextEmail)
        val password = findViewById<EditText>(R.id.editTextPassword)

        val signUpBtn = findViewById<Button>(R.id.signUpButton)

        val signUpController = SignUpController(this)

        signUpBtn.setOnClickListener {
            signUpController.handleSignUp(nome, cognome, email, password)
        }
    }
}