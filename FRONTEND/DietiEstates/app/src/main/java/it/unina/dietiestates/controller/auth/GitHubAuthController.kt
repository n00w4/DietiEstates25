package it.unina.dietiestates.controller.auth

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import com.google.gson.Gson
import it.unina.dietiestates.data.dto.ApiResponse
import it.unina.dietiestates.data.model.Cliente
import it.unina.dietiestates.network.retrofit.RetrofitClient
import it.unina.dietiestates.view.auth.SignUpActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GitHubAuthController(context: Context, private val uri: Uri): AuthController(context) {
    override fun handleAuth() {
        val code = uri.getQueryParameter("code")

        val api = RetrofitClient.instance
        api.gitHubCallback(code).enqueue(object : Callback<ApiResponse> {
            override fun onResponse(call: Call<ApiResponse>, response: Response<ApiResponse>) {
                when (response.code()) {
                    200 -> {
                        val userData = response.body()?.message
                        val gson = Gson()
                        val cliente: Cliente? = gson.fromJson(userData, Cliente::class.java)
                        if (cliente != null) {
                            val nomeCompleto = cliente.nome?.split(" ")
                            val nome = nomeCompleto?.getOrNull(0) ?: ""
                            val cognome = nomeCompleto?.getOrNull(1) ?: ""
                            val email = cliente.email

                            val intent = Intent(context, SignUpActivity::class.java).apply {
                                putExtra("EXTRA_NOME", nome)
                                putExtra("EXTRA_COGNOME", cognome)
                                putExtra("EXTRA_EMAIL", email)
                            }
                            context.startActivity(intent)
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


