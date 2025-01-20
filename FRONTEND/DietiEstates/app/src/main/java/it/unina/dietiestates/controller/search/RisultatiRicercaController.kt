package it.unina.dietiestates.controller.search

import android.content.Context
import android.widget.Toast
import it.unina.dietiestates.data.model.Annuncio
import it.unina.dietiestates.model.data.FiltriRicercaAnnunci
import it.unina.dietiestates.network.retrofit.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RisultatiRicercaController (private val context: Context) {

    fun getRisultatiRicerca(filtri: FiltriRicercaAnnunci, callback: (Result<List<Annuncio>>) -> Unit){
        val api = RetrofitClient.instance
        api.getRicercaAnnunci(filtri).enqueue(object : Callback<List<Annuncio>> {
            override fun onResponse(call: Call<List<Annuncio>>, response: Response<List<Annuncio>>) {
                if (response.isSuccessful) {
                    response.body()?.let { annunci ->
                        callback(Result.success(annunci))
                    } ?: callback(Result.failure(Exception("Nessun annuncio trovato. Riprova.")))
                } else {
                    val errorMessage = when (response.code()) {
                        400 -> "Richiesta non valida. Riprovare con una richiesta diversa."
                        401, 403 -> "Utente non autorizzato."
                        404 -> "Nessun annuncio trovato. Riprova."
                        500 -> "Errore del server: Riprovare tra qualche minuto."
                        else -> "Errore non previsto: ${response.code()} - ${response.message()}"
                    }
                    callback(Result.failure(Exception(errorMessage)))
                }
            }

            override fun onFailure(call: Call<List<Annuncio>>, t: Throwable) {
                Toast.makeText(context, "Errore durante la connessione al server: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }
}