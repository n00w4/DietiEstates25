package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.myapplication.View.HomeClienteActivity
import com.example.myapplication.View.SignUpActivity

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

        textRegistrati.setOnClickListener{
            val signUpPage = Intent(this, SignUpActivity::class.java)
            startActivity(signUpPage)
        }

        btnAccedi.setOnClickListener{
            val homePage = Intent(this, HomeClienteActivity::class.java)
            startActivity(homePage)
        }
    }

}