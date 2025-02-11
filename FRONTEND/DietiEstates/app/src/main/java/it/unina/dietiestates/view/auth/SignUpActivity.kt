package it.unina.dietiestates.view.auth

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import it.unina.dietiestates.R
import it.unina.dietiestates.controller.auth.SignUpController
import it.unina.dietiestates.utils.ValidationUtils

class SignUpActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.signup_page)

        val nome = findViewById<EditText>(R.id.editTextNome)
        val cognome = findViewById<EditText>(R.id.editTextCognome)
        val email = findViewById<EditText>(R.id.editTextEmail)
        val password = findViewById<EditText>(R.id.editTextPassword)

        // Recupero dati da Google Sign-In se presenti
        val nomeProviderEsterno = intent.getStringExtra("EXTRA_NOME")
        val cognomeProviderEsterno = intent.getStringExtra("EXTRA_COGNOME")
        val emailProviderEsterno = intent.getStringExtra("EXTRA_EMAIL")

        nome.setText(nomeProviderEsterno ?: "")
        cognome.setText(cognomeProviderEsterno ?: "")
        email.setText(emailProviderEsterno ?: "")

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
                val messaggiDiErrore = ValidationUtils.verificaPassword(psw)

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
                val messaggiDiErrore = ValidationUtils.verificaEmail(mail)

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
}