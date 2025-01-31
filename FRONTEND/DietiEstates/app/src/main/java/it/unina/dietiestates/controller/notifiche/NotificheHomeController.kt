package it.unina.dietiestates.controller.notifiche

import android.content.Context
import android.util.Log
import android.widget.Toast
import it.unina.dietiestates.data.dto.NotificaConInfo
import it.unina.dietiestates.data.dto.SharedPrefManager
import it.unina.dietiestates.network.retrofit.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NotificheHomeController (private val context: Context) {

    val errore: String = "Nessuna notifica disponibile."

    fun getNotifiche(callback: (Result<List<NotificaConInfo>>) -> Unit){
        val api = RetrofitClient.instance
        val emailAgente = SharedPrefManager.getUserEmail(context) ?: "no_email"
        Log.d("NotificheHomeController", "getNotifiche() called")

        api.getNotifiche(emailAgente).enqueue(object : Callback<List<NotificaConInfo>> {
            override fun onResponse(call: Call<List<NotificaConInfo>>, response: Response<List<NotificaConInfo>>) {
                if (response.isSuccessful) {
                    Log.d("NotificheHomeController", "response.isSuccessful")
                    response.body()?.let { notifiche ->
                        callback(Result.success(notifiche))
                    } ?: callback(Result.failure(Exception(errore)))
                } else {
                    Log.d("NotificheHomeController", "response failed")
                    val errorMessage = assegnaErrore(response)
                    callback(Result.failure(Exception(errorMessage)))
                }
            }

            override fun onFailure(call: Call<List<NotificaConInfo>>, t: Throwable) {
                Toast.makeText(context, "Errore durante la connessione al server: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun assegnaErrore(response: Response<List<NotificaConInfo>>): String{
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