package it.unina.dietiestates.controller.calendario

import android.content.Context
import android.util.Log
import android.widget.Toast
import it.unina.dietiestates.data.dto.PrenotazioneConInfo
import it.unina.dietiestates.data.dto.SharedPrefManager
import it.unina.dietiestates.network.retrofit.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CalendarioClienteController (private val context: Context) {

    val errore: String = "Nessuna prenotazione disponibile."

    fun getPrenotazioniCliente(callback: (Result<List<PrenotazioneConInfo>>) -> Unit){
        val api = RetrofitClient.instance
        val emailCliente = SharedPrefManager.getUserEmail(context) ?: "no_email"
        Log.d("CalendarioClienteController", "getPrenotazioniCliente() called")

        api.getPrenotazioniCliente(emailCliente).enqueue(object : Callback<MutableList<PrenotazioneConInfo>> {
            override fun onResponse(call: Call<MutableList<PrenotazioneConInfo>>, response: Response<MutableList<PrenotazioneConInfo>>) {
                if (response.isSuccessful) {
                    Log.d("CalendarioClienteController", "response.isSuccessful")
                    response.body()?.let { prenotazioni ->
                        callback(Result.success(prenotazioni))
                    } ?: callback(Result.failure(Exception(errore)))
                } else {
                    Log.d("CalendarioClienteController", "response failed")
                    val errorMessage = assegnaErrore(response)
                    callback(Result.failure(Exception(errorMessage)))
                }
            }

            override fun onFailure(call: Call<MutableList<PrenotazioneConInfo>>, t: Throwable) {
                Toast.makeText(context, "Errore durante la connessione al server: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun assegnaErrore(response: Response<MutableList<PrenotazioneConInfo>>): String{
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