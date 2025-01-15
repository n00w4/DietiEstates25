package com.example.myapplication.controller

import android.content.Context
import android.content.Intent
import android.widget.EditText
import android.widget.Toast
import com.example.myapplication.MainActivity
import com.example.myapplication.model.Cliente
import com.example.myapplication.retrofit.RetrofitClient
import com.example.myapplication.view.info.SignUpResult
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignUpController(private val context: Context) {

    fun handleSignUp(nomeEditText: EditText, cognomeEditText: EditText,
                     emailEditText: EditText, passwordEditText: EditText) {
        val nome = nomeEditText.text.toString().trim()
        val cognome = cognomeEditText.text.toString().trim()
        val email = emailEditText.text.toString().trim()
        val password = passwordEditText.text.toString().trim()

        if ( nome.isEmpty() || cognome.isEmpty() || email.isEmpty() || password.isEmpty()) {
            Toast.makeText(context, "Compilare tutti i campi prima di procedere.", Toast.LENGTH_SHORT).show()
        } else {
            val cliente = Cliente(nome, cognome, email, password)
            signUp(cliente)
        }
    }

    private fun signUp(cliente: Cliente) {
        val api = RetrofitClient.instance

        api.register(cliente).enqueue(object : Callback<String> {
            override fun onResponse(call: Call<String>, response: Response<String>) {
                when (response.code()) {
                    200, 201 -> {
                        val message = response.body() ?: "Unknown success response"
                        val signUpResult = Intent(context, SignUpResult::class.java)
                        signUpResult.putExtra("resultMessage", message)
                        signUpResult.putExtra("responseCode", response.code())
                        context.startActivity(signUpResult)
                    }
                    409 -> {
                        Toast.makeText(context, "Esiste già un account con quest'email. Impossibile creare un nuovo account", Toast.LENGTH_SHORT).show()
                    }
                    500 -> {
                        Toast.makeText(context, "Server error: ${response.errorBody()?.string()}", Toast.LENGTH_SHORT).show()
                    }
                    else -> {
                        Toast.makeText(context, "Error: ${response.errorBody()?.string()}", Toast.LENGTH_SHORT).show()
                    }
                    //Per ora solo in caso di successo si cambia pagina, nei casi di errore mostra un toast.
                    //Decidere approccio per i casi di errore.
                }
            }

            override fun onFailure(call: Call<String>, t: Throwable) {
                Toast.makeText(context, "Failed to connect to server: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }

    fun onLoginClicked(){
        val loginPage = Intent(context, MainActivity::class.java)
        context.startActivity(loginPage)
    }
}