package it.unina.dietiestates.controller.search

import android.content.Context
import android.util.Log
import it.unina.dietiestates.data.dto.SharedPrefManager

class PrenotazioneAnnuncioController (private val context: Context) {

    fun gestisciPrenotazione(idAnnuncio : Int, data: Triple<Int, Int, Int>, ora: Pair<Int, Int> ){
        val emailUtente = SharedPrefManager.getUserEmail(context)
        Log.d("prenotazione", "$idAnnuncio, $emailUtente")
    }
}