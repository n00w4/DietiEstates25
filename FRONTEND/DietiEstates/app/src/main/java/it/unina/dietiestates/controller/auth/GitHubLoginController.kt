package it.unina.dietiestates.controller.auth

import android.content.Context
import android.net.Uri
import android.util.Log
import android.widget.Toast
import com.google.gson.Gson
import it.unina.dietiestates.data.dto.ApiResponse
import it.unina.dietiestates.data.dto.ClienteDTO
import it.unina.dietiestates.network.retrofit.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GitHubLoginController(context: Context, private val uri: Uri): LoginController(context) {
    override fun handleLogin() {
        val code = uri.getQueryParameter("code")
        Log.d("GitHub", "Callback received: $code")

        val api = RetrofitClient.instance
        api.gitHubCallback(code).enqueue(object : Callback<ApiResponse> {
            override fun onResponse(call: Call<ApiResponse>, response: Response<ApiResponse>) {
                when (response.code()) {
                    200 -> {
                        val userData = response.body()?.message
                        val gson = Gson()
                        val clienteDTO: ClienteDTO? = gson.fromJson(userData, ClienteDTO::class.java)
                        if (clienteDTO != null) {
                            val nomeCompleto = clienteDTO.nome?.split(" ")
                            val nome = nomeCompleto?.getOrNull(0) ?: ""
                            val cognome = nomeCompleto?.getOrNull(1) ?: ""
                            val email = clienteDTO.email.toString()
                            val tipoUtente = "Cliente"

                            salvaDatiUtente(nome, cognome, email, tipoUtente)
                            scegliHomePage(tipoUtente)
                        }
                    }
                    else -> {
                        Toast.makeText(context, "Errore durante il login: codice ${response.code()}", Toast.LENGTH_SHORT).show()
                    }
                }
            }

            override fun onFailure(call: Call<ApiResponse>, t: Throwable) {
                Toast.makeText(context, "Failed to connect to server: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }
}


