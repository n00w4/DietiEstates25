package it.unina.dietiestates.data.viewmodel

import androidx.lifecycle.ViewModel

class AnnuncioViewModel : ViewModel(){
    var ID: Int? = null
    var titolo: String? = null
    var indirizzo: String? = null
    var immagine: String? = null
    var descrizione: String? = null
    var dimensioni: Int? = null
    var prezzo: Float? = null
    var piano: String? = null
    var numeroStanze: Int? = null
    var classeEnergetica: String? = null
    var ascensore: Boolean? = null
    var portineria: Boolean? = null
    var climatizzazione: Boolean? = null
    var boxAuto: Boolean? = null
    var terrazzo: Boolean? = null
    var giardino: Boolean? = null
    var tipoAnnuncio: String? = null
    var posizione: String? = null
    var emailAgente: String? = null
}