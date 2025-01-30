package it.unina.dietiestates.view.auth

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import it.unina.dietiestates.MainActivity
import it.unina.dietiestates.R

class SignUpResult : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.signup_result)

        val resultMessage = intent.getStringExtra("resultMessage") ?: "Nessun messaggio"
        val statusCode = intent.getIntExtra("responseCode", -1)
        val resultTextView = findViewById<TextView>(R.id.resultTextView)
        val statusTextView = findViewById<TextView>(R.id.statusTextView)
        val immagine = findViewById<ImageView>(R.id.imageView)

        val btnLogin = findViewById<Button>(R.id.loginButton)
        if(statusCode == 200 || statusCode == 201){
            immagine.setImageResource(R.drawable.happy_dark)
            statusTextView.text = getString(R.string.ok_signup)
            resultTextView.text = getString(R.string.ok_messaggio_signup)
        }else{
            immagine.setImageResource(R.drawable.sad_dark)
            statusTextView.text = getString(R.string.ops_signup)
            resultTextView.text = resultMessage
        }

        btnLogin.setOnClickListener(){
            onBtnLoginClicked()
        }
    }

    private fun onBtnLoginClicked() {
        val loginPage = Intent(this, MainActivity::class.java)
        startActivity(loginPage)
    }
}