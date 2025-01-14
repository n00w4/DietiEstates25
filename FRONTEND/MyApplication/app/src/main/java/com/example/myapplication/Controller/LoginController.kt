package com.example.myapplication.controller

import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast
import com.example.myapplication.model.data.Credenziali
import com.example.myapplication.retrofit.RetrofitClient
import com.example.myapplication.view.HomeClienteActivity
import com.example.myapplication.view.SignUpActivity
import com.example.myapplication.view.info.SignUpResult
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginController(private val context: Context) {

    fun onTextRegistratiClicked() {
        val signUpPage = Intent(context, SignUpActivity::class.java)
        context.startActivity(signUpPage)
    }

    fun onBtnAccediClicked(credenziali: Credenziali) {
        Log.d("Utente generico", "Gestisci Login")
        val api = RetrofitClient.instance

        api.login(credenziali).enqueue(object : Callback<String> {
            override fun onResponse(call: Call<String>, response: Response<String>) {
                when (response.code()) {
                    200, 201 -> {
                        val message = response.body() ?: "Unknown success response"
                        val signUpResult = Intent(context, SignUpResult::class.java)
                        signUpResult.putExtra("resultMessage", message)
                        signUpResult.putExtra("responseCode", response.code())
                        context.startActivity(signUpResult) }
                    409 -> {
                        Toast.makeText(context, "Conflict: ${response.errorBody()?.string()}", Toast.LENGTH_SHORT).show() }
                    500 -> {
                        Toast.makeText(context, "Server error: ${response.errorBody()?.string()}", Toast.LENGTH_SHORT).show() }
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

    private fun recuperaDatiUtente(response: Response<String>) : String{
        return "ruolo"
    }

    private fun scegliHomePage(tipoUtente: String){
        when(tipoUtente){
            "Cliente" -> {
                Log.d("Cliente", "Carica Cliente")
                goToClienteHomePage()
            }
            "Agente" -> {
                Log.d("Agente", "Carica Agente")
                //goToAgenteHomePage()
            }
            "Amministratore" -> {
                Log.d("Amministratore", "Carica Amministratore")
                //goToAmministratoreHomePage()
            }
            "Gestore" -> {
                Log.d("gestore", "Carica gestore")
                //goToGestoreHomePage()
            }
        }
    }

    private fun goToClienteHomePage(){
        val homePage = Intent(context, HomeClienteActivity::class.java)
        context.startActivity(homePage)
    }

}
