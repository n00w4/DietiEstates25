package it.unina.dietiestates.controller.auth

import android.content.Context
import android.util.Log
import android.widget.Toast
import it.unina.dietiestates.data.dto.Credenziali
import it.unina.dietiestates.network.retrofit.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import com.auth0.android.jwt.JWT
import it.unina.dietiestates.data.dto.SharedPrefManager
import it.unina.dietiestates.data.dto.TokenResponse

class DietiLoginController(context: Context, private val credenziali: Credenziali): LoginController(context) {
    override fun handleLogin() {
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
                Toast.makeText(context, "Errore durante la connessione al server: ${t.message}", Toast.LENGTH_SHORT).show()
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
                Log.e("DietiLoginController", "Invalid token claims")
                Toast.makeText(context, "Login failed: invalid token claims.", Toast.LENGTH_SHORT).show()
            }
        } catch (e: Exception) {
            Log.e("DietiLoginController", "Token processing error: ${e.message}")
            Toast.makeText(context, "Invalid token: token processing error.", Toast.LENGTH_SHORT).show()
        }
    }
}
