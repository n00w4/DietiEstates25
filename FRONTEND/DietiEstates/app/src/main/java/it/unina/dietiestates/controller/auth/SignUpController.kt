package it.unina.dietiestates.controller.auth

import android.content.Context
import android.content.Intent
import android.widget.EditText
import android.widget.Toast
import it.unina.dietiestates.MainActivity
import it.unina.dietiestates.data.model.Cliente
import it.unina.dietiestates.model.data.ApiResponse
import it.unina.dietiestates.network.retrofit.RetrofitClient
import it.unina.dietiestates.view.auth.SignUpResult
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

        api.register(cliente).enqueue(object : Callback<ApiResponse> {
            override fun onResponse(call: Call<ApiResponse>, response: Response<ApiResponse>) {
                when (response.code()) {
                    200, 201 -> {
                        val apiResponse = response.body()
                        if (apiResponse != null) {
                            val signUpResult = Intent(context, SignUpResult::class.java)
                            signUpResult.putExtra("resultMessage", apiResponse.message)
                            signUpResult.putExtra("responseCode", response.code())
                            context.startActivity(signUpResult)
                        } else {
                            Toast.makeText(context, "Errore: la risposta del server è incompleta", Toast.LENGTH_LONG).show()
                        }
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
                }
            }

            override fun onFailure(call: Call<ApiResponse>, t: Throwable) {
                Toast.makeText(context, "Failed to connect to server: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }

    fun onLoginClicked(){
        val loginPage = Intent(context, MainActivity::class.java)
        context.startActivity(loginPage)
    }
}