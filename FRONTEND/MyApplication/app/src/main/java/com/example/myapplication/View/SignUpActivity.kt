package com.example.myapplication.view

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
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

        val errori = findViewById<TextView>(R.id.erroriTextView)
        val signUpBtn = findViewById<Button>(R.id.signUpButton)

        val signUpController = SignUpController(this)

        password.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) { /*empty*/ }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) { /*empty*/ }

            override fun afterTextChanged(s: Editable?) {
                val psw = s.toString()
                val messaggiDiErrore = verificaPassword(psw)

                if (messaggiDiErrore.isEmpty()) {
                    errori.visibility = TextView.GONE
                    signUpBtn.isEnabled = true
                } else {
                    errori.visibility = TextView.VISIBLE
                    errori.text = messaggiDiErrore.joinToString("\n")
                    signUpBtn.isEnabled = false
                    signUpBtn.backgroundTintList = getColorStateList(android.R.color.darker_gray)
                }
            }
        })

        signUpBtn.setOnClickListener {
            signUpController.handleSignUp(nome, cognome, email, password)
        }
    }

    private fun verificaPassword(password: String): List<String> {
        val errori = mutableListOf<String>()

        if (password.length < 8) {
            errori.add("La password deve essere di almeno 8 caratteri.") }
        if (!password.any { it.isUpperCase() }) {
            errori.add("La password deve contenere almeno un carattere maiuscolo.") }
        if (!password.any { it.isLowerCase() }) {
            errori.add("La password deve contenere almeno un carattere minuscolo.") }
        if (!password.any { it.isDigit() }) {
            errori.add("La password deve contenere almeno un numero.") }
        if (!password.any { "!@#$%^&*()_+-=[]{}|;:',.<>?/".contains(it) }) {
            errori.add("La password deve contenere almeno un carattere speciale.") }

        return errori
    }
}