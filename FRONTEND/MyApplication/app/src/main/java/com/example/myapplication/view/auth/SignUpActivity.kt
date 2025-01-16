package com.example.myapplication.view.auth

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.R
import com.example.myapplication.controller.auth.SignUpController

class SignUpActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.signup_page)

        val nome = findViewById<EditText>(R.id.editTextNome)
        val cognome = findViewById<EditText>(R.id.editTextCognome)
        val email = findViewById<EditText>(R.id.editTextEmail)
        val password = findViewById<EditText>(R.id.editTextPassword)

        val loginText = findViewById<TextView>(R.id.loginText)
        val erroriPwd = findViewById<TextView>(R.id.erroriPwdTextView)
            erroriPwd.visibility = TextView.GONE
        val erroriEmail = findViewById<TextView>(R.id.erroriEmailTextView)
            erroriEmail.visibility = TextView.GONE
        val signUpBtn = findViewById<Button>(R.id.signUpButton)

        val signUpController = SignUpController(this)

        password.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) { /*empty*/ }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) { /*empty*/ }

            override fun afterTextChanged(s: Editable?) {
                val psw = s.toString()
                val messaggiDiErrore = verificaPassword(psw)

                if (messaggiDiErrore.isEmpty()) {
                    erroriPwd.visibility = TextView.GONE
                    signUpBtn.isEnabled = true
                    signUpBtn.backgroundTintList = getColorStateList(R.color.button_color_light)
                } else {
                    erroriPwd.visibility = TextView.VISIBLE
                    erroriPwd.text = messaggiDiErrore.joinToString("\n")
                    signUpBtn.isEnabled = false
                    signUpBtn.backgroundTintList = getColorStateList(android.R.color.darker_gray)
                }
            }
        })

        email.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) { /*empty*/ }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) { /*empty*/ }

            override fun afterTextChanged(s: Editable?) {
                val mail = s.toString()
                val messaggiDiErrore = verificaEmail(mail)

                if (messaggiDiErrore.isEmpty()) {
                    erroriEmail.visibility = TextView.GONE
                    signUpBtn.isEnabled = true
                    signUpBtn.backgroundTintList = getColorStateList(R.color.button_color_light)
                } else {
                    erroriEmail.visibility = TextView.VISIBLE
                    erroriEmail.text = messaggiDiErrore.joinToString("\n")
                    signUpBtn.isEnabled = false
                    signUpBtn.backgroundTintList = getColorStateList(android.R.color.darker_gray)
                }
            }
        })

        signUpBtn.setOnClickListener {
            signUpController.handleSignUp(nome, cognome, email, password)
        }

        loginText.setOnClickListener {
            signUpController.onLoginClicked()
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

    private fun verificaEmail(email: String): List<String> {
        val errori = mutableListOf<String>()
        val emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$".toRegex()

        if(email.isEmpty()){
            errori.add("L'email non può essere vuota.")
        }
        if (!email.contains('@')) {
            errori.add("L'email deve contenere il simbolo '@'.")
        }
        if (!email.contains('.')) {
            errori.add("L'email deve contenere un punto ('.') nel dominio.")
        }
        if (!emailRegex.matches(email)) {
            errori.add("L'email non è valida.")
        }

        return errori
    }
}