package it.unina.dietiestates.data.model

import java.io.Serializable

data class Annuncio (
    var id: Int,
    var titolo: String,
    var indirizzo: String,
    var immagine: String,
    var descrizione: String,
    var dimensioni: Int,
    var prezzo: Float,
    var piano: String,
    var numeroStanze: Int,
    var classeEnergetica: String,
    var ascensore: Boolean,
    var portineria: Boolean,
    var climatizzazione: Boolean,
    var boxAuto: Boolean,
    var terrazzo: Boolean,
    var giardino: Boolean,
    var tipoAnnuncio: String,
    var posizione: String,
    var emailAgente: String
) : Serializable