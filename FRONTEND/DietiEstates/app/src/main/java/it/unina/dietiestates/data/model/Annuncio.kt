package it.unina.dietiestates.data.model

import java.io.Serializable

data class Annuncio (
    val id: Int,
    val titolo: String,
    val indirizzo: String,
    val immagine: String,
    val descrizione: String,
    val dimensioni: Int,
    val prezzo: Float,
    val piano: String,
    val numeroStanze: Int,
    val classeEnergetica: String,
    val ascensore: Boolean,
    val portineria: Boolean,
    val climatizzazione: Boolean,
    val boxAuto: Boolean,
    val terrazzo: Boolean,
    val giardino: Boolean,
    val tipoAnnuncio: String,
    val posizione: String,
    val emailAgente: String
) : Serializable