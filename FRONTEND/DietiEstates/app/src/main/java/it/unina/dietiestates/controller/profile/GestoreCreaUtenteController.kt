package it.unina.dietiestates.controller.profile

import android.content.Context
import android.widget.Toast
import it.unina.dietiestates.data.dto.AddUtenteForm
import it.unina.dietiestates.data.dto.ApiResponse
import it.unina.dietiestates.data.dto.SharedPrefManager
import it.unina.dietiestates.network.retrofit.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GestoreCreaUtenteController (private val context: Context){

    fun addUtente(userType: String, name: String, surname: String, email: String) {
        val partitaIVAGestore = SharedPrefManager.getPartitaIva(context)

        val form = AddUtenteForm(userType, name, surname, email, partitaIVAGestore!!)

        val api = RetrofitClient.instance

        api.addUtente(form).enqueue(object : Callback<ApiResponse> {
            override fun onResponse(call: Call<ApiResponse>, response: Response<ApiResponse>) {
                when (response.code()) {
                    201 -> {
                        val message = response.body()?.message
                        if (message != null) {
                            Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
                        }else{
                            Toast.makeText(context, "Utente creato con successo.", Toast.LENGTH_SHORT).show()
                        }
                    }
                    400 -> {
                        Toast.makeText(context, "I dati inseriti non sono al momento validi. Riprova più tardi.", Toast.LENGTH_SHORT).show()
                    }
                    409 -> {
                        Toast.makeText(context, "Esiste già un utente con questa email.", Toast.LENGTH_SHORT).show()
                    }
                    else -> {
                        Toast.makeText(context, "Errore codice ${response.code()}: riprova più tardi", Toast.LENGTH_SHORT).show()
                    }
                }
            }

            override fun onFailure(call: Call<ApiResponse>, t: Throwable) {
                Toast.makeText(context, "Errore durante la connessione al server: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }
}