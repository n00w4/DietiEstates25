package it.unina.dietiestates.controller.crea_annuncio

import android.util.Log
import android.widget.EditText
import it.unina.dietiestates.data.viewmodel.AnnuncioViewModel
import it.unina.dietiestates.data.viewmodel.CoordinateViewModel
import it.unina.dietiestates.network.geocoding.GeoPointParser

class CreaAnnuncioController {

    fun setEditTextData(titoloText : EditText, descrizioneText: EditText,
                        prezzoText: EditText, dimensioniText: EditText,
                        annuncioVM: AnnuncioViewModel){
        if(!annuncioVM.titolo.isNullOrEmpty()) titoloText.setText(annuncioVM.titolo.toString())
        if(!annuncioVM.descrizione.isNullOrEmpty()) descrizioneText.setText(annuncioVM.descrizione.toString())
        if(!(annuncioVM.prezzo == null || annuncioVM.prezzo?.toDouble() == 0.0)) prezzoText.setText(annuncioVM.prezzo.toString())
        if(!(annuncioVM.dimensioni == null || annuncioVM.dimensioni == 0)) dimensioniText.setText(annuncioVM.dimensioni.toString())
    }

    fun isAnyFieldEmpty(titolo: String?, descrizione: String?,
                        indirizzo: String?, latitudine: Double?, longitudine: Double?,
                        prezzo: Float?, dimensioni: Int?) : Boolean{
        if(titolo.isNullOrEmpty()) return true
        if(descrizione.isNullOrEmpty()) return true
        if(indirizzo.isNullOrEmpty() || latitudine == null || longitudine == null) return true
        if(prezzo == null || prezzo.toDouble() == 0.0) return true
        if(dimensioni == null || dimensioni == 0) return true
        return false
    }

    fun isCoordinateEmpty(latitudine: Double?, longitudine: Double?) :Boolean{
        if(latitudine == null || longitudine == null) return true
        return false
    }

    fun saveDataInVM(titolo: String?, descrizione: String?,
                     prezzo: Float?, dimensioni: Int?,
                     annuncioVM: AnnuncioViewModel){
        annuncioVM.titolo = titolo
        annuncioVM.descrizione = descrizione
        annuncioVM.prezzo = prezzo
        annuncioVM.dimensioni = dimensioni
        Log.d("CREA_ANNUNCIO", "${annuncioVM.titolo}, ${annuncioVM.descrizione}, ${annuncioVM.indirizzo}," +
                "${annuncioVM.posizione}, ${annuncioVM.prezzo}, ${annuncioVM.dimensioni},")
    }

    fun savePositionInVM(indirizzo: String?, latitudine: Double?, longitudine: Double?,
                         annuncioVM: AnnuncioViewModel){
        annuncioVM.indirizzo = indirizzo
        annuncioVM.posizione =
            latitudine?.let { longitudine?.let { it1 ->
                GeoPointParser().parseCoordinatesToWKB(it, it1)
            } }
        Log.d("CREA_ANNUNCIO", "${annuncioVM.titolo}, ${annuncioVM.descrizione}, ${annuncioVM.indirizzo}," +
                "${annuncioVM.posizione}, ${annuncioVM.prezzo}, ${annuncioVM.dimensioni},")
    }

    fun setPosizioneAnnuncioToNull(coordinateVM : CoordinateViewModel, annuncioVM : AnnuncioViewModel){
        coordinateVM.latitudine = null
        coordinateVM.longitudine = null
        annuncioVM.indirizzo = null
        annuncioVM.posizione = null
    }
}