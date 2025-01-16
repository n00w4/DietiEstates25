package com.example.myapplication.controller

import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast
import com.example.myapplication.model.data.Credenziali
import com.example.myapplication.retrofit.RetrofitClient
import com.example.myapplication.view.HomeClienteActivity
import com.example.myapplication.view.SignUpActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import com.auth0.android.jwt.JWT
import com.example.myapplication.model.data.SharedPrefManager
import com.example.myapplication.model.data.TokenResponse
import com.example.myapplication.view.HomeAgenteActivity
import com.example.myapplication.view.HomeAmministratoreActivity
import com.example.myapplication.view.HomeGestoreActivity

class LoginController(private val context: Context) {

    fun onTextRegistratiClicked() {
        val signUpPage = Intent(context, SignUpActivity::class.java)
        context.startActivity(signUpPage)
    }

    fun onBtnAccediClicked(email: String, password: String){
        if ( email.isEmpty() || password.isEmpty()) {
            Toast.makeText(context, "Compilare tutti i campi prima di procedere.", Toast.LENGTH_SHORT).show()
        } else {
            val credenziali = Credenziali(email, password)
            handleLogin(credenziali)
        }
    }

    private fun handleLogin(credenziali: Credenziali) {
        val api = RetrofitClient.instance

        api.login(credenziali).enqueue(object : Callback<TokenResponse> {
            override fun onResponse(call: Call<TokenResponse>, response: Response<TokenResponse>) {
                when (response.code()) {
                    200 -> {
                        val token = response.body()?.token
                        if (token != null) {
                            analizzaToken(token)
                        } else {
                            Toast.makeText(context, "Accesso non riuscito: il token è NULL.", Toast.LENGTH_SHORT).show()
                        }
                    }
                    401 -> {
                        Toast.makeText(context, "Le credenziali sono errate, riprovare.", Toast.LENGTH_SHORT).show()
                    }
                    else -> {
                        Toast.makeText(context, "Errore durante il login: codice ${response.code()}", Toast.LENGTH_SHORT).show()
                    }
                }
            }

            override fun onFailure(call: Call<TokenResponse>, t: Throwable) {
                Toast.makeText(context, "Failed to connect to server: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun analizzaToken(token: String){
        try {
            val jwt = JWT(token)
            val nome = jwt.getClaim("nome").asString()
            val cognome = jwt.getClaim("cognome").asString()
            val email = jwt.getClaim("email").asString()
            val tipoUtente = jwt.getClaim("tipoUtente").asString()

            if (nome != null && cognome != null && email != null && tipoUtente != null) {
                SharedPrefManager.saveToken(context, token)
                salvaDatiUtente(nome, cognome, email, tipoUtente)
                scegliHomePage(tipoUtente)
            } else {
                Log.e("LoginController", "Invalid token claims")
                Toast.makeText(context, "Login failed: invalid token claims.", Toast.LENGTH_SHORT).show()
            }
        } catch (e: Exception) {
            Log.e("LoginController", "Token processing error: ${e.message}")
            Toast.makeText(context, "Invalid token: token processing error.", Toast.LENGTH_SHORT).show()
        }
    }

    private fun salvaDatiUtente(nome: String, cognome: String, email: String, tipoUtente: String){
        SharedPrefManager.saveUserNome(context, nome)
        SharedPrefManager.saveUserCognome(context, cognome)
        SharedPrefManager.saveUserEmail(context, email)
        SharedPrefManager.saveUserRole(context, tipoUtente)
    }



    private fun scegliHomePage(tipoUtente: String){
        val intent = when (tipoUtente) {
            "Cliente" -> Intent(context, HomeClienteActivity::class.java)
            "Agente" -> Intent(context, HomeAgenteActivity::class.java)
            "Amministratore" -> Intent(context, HomeAmministratoreActivity::class.java)
            "Gestore" -> Intent(context, HomeGestoreActivity::class.java)
            else -> null
        }

        if (intent != null) {
            context.startActivity(intent)
        } else {
            Toast.makeText(context, "Unknown user type", Toast.LENGTH_SHORT).show()
        }
    }

}
