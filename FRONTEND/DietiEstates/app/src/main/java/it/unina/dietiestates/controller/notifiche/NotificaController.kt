package it.unina.dietiestates.controller.notifiche

import android.content.Context
import android.widget.Toast
import it.unina.dietiestates.data.dto.ApiResponse
import it.unina.dietiestates.data.model.Prenotazione
import it.unina.dietiestates.network.retrofit.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NotificaController (private val context: Context){

    val errore: String = "Impossibile effettuare la richiesta, riprovare."

    fun valutaPrenotazione(prenotazione: Prenotazione, callback: (Result<ApiResponse>) -> Unit){
        val api = RetrofitClient.instance

        api.valutaPrenotazione(prenotazione).enqueue(object : Callback<ApiResponse> {
            override fun onResponse(call: Call<ApiResponse>, response: Response<ApiResponse>) {
                if (response.isSuccessful) {
                    response.body()?.let { apiResponse ->
                        callback(Result.success(apiResponse))
                    } ?: callback(Result.failure(Exception(errore)))
                } else {
                    val errorMessage = assegnaErrore(response)
                    callback(Result.failure(Exception(errorMessage)))
                }
            }

            override fun onFailure(call: Call<ApiResponse>, t: Throwable) {
                Toast.makeText(context, "Errore durante la connessione al server: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun assegnaErrore(response: Response<ApiResponse>): String{
        val messaggio= when (response.code()) {
            400 -> "Richiesta non valida. Riprovare con una richiesta diversa."
            401, 403 -> "Utente non autorizzato."
            404 -> errore
            500 -> "Errore del server: Riprovare tra qualche minuto."
            else -> "Errore non previsto: ${response.code()} - ${response.message()}"
        }
        return messaggio
    }
}