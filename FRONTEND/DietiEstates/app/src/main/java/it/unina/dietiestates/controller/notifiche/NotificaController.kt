package it.unina.dietiestates.controller.notifiche

import android.content.Context
import it.unina.dietiestates.data.model.Prenotazione
import it.unina.dietiestates.network.retrofit.RetrofitClient

class NotificaController (private val context: Context){

    fun accettaPrenotazione(prenotazione: Prenotazione){//Aggiungi callback?
        val api = RetrofitClient.instance

    }
}