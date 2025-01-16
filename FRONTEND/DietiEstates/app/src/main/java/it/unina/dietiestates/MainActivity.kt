package it.unina.dietiestates

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import it.unina.dietiestates.controller.auth.GitHubLoginController
import it.unina.dietiestates.controller.auth.GoogleLoginController
import it.unina.dietiestates.controller.auth.LoginController

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val textRegistrati = findViewById<TextView>(R.id.textRegistrati)
        val btnAccedi = findViewById<Button>(R.id.accedi_btn)
        val emailEditText = findViewById<EditText>(R.id.editTextEmail)
        val passwordEditText = findViewById<EditText>(R.id.editTextPassword)

        val controller = LoginController(this)

        textRegistrati.setOnClickListener {
            controller.onTextRegistratiClicked()
        }

        btnAccedi.setOnClickListener {
            val email = emailEditText.text.toString().trim()
            val pwd = passwordEditText.text.toString().trim()

            controller.onBtnAccediClicked(email, pwd)
        }

        val gitHubLoginBtn = findViewById<ImageView>(R.id.imageViewGitHub)
        val googleLoginBtn = findViewById<ImageView>(R.id.imageViewGoogle)
        val gitHubController = GitHubLoginController(this)
        val googleController = GoogleLoginController(this)

        gitHubLoginBtn.setOnClickListener{
            gitHubController.gitHubLogin()
        }

        googleLoginBtn.setOnClickListener{
            googleController.googleLogin()
        }
    }

}