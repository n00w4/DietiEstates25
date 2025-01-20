package it.unina.dietiestates.controller.auth

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import it.unina.dietiestates.model.data.ApiResponse
import it.unina.dietiestates.network.retrofit.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GitHubLoginController(context: Context) : OAuthLoginController(context) {
    override fun login(resultLauncher: ActivityResultLauncher<Intent>) {
        val url = "http://10.0.2.2:8080/api/auth/github/"
        commonIntentLogic(resultLauncher, url)
    }

    override fun handleCallback(uri: Uri) {
        val code = uri.getQueryParameter("code")
        Log.d("GitHub", "Callback received: $code")

        val api = RetrofitClient.instance
        api.gitHubCallback(code).enqueue(object : Callback<ApiResponse> {
            override fun onResponse(call: Call<ApiResponse>, response: Response<ApiResponse>) {
                when (response.code()) {
                    200 -> {
                        val userData = response.body()?.message
                        Toast.makeText(context, userData, Toast.LENGTH_SHORT).show()
                        // TODO: gestire istanza utente e salvare dati in SharedPreferences se utile
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


