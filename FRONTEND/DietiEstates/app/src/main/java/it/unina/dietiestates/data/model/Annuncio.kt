package it.unina.dietiestates.data.model

import java.io.Serializable

data class Annuncio (
    val ID: Int? = null,
    val titolo: String? = null,
    val indirizzo: String? = null,
    val immagine: String? = null,
    val descrizione: String? = null,
    val dimensioni: Int? = null,
    val prezzo: Float? = null,
    val piano: String? = null,
    val numeroStanze: Int? = null,
    val classeEnergetica: String? = null,
    val ascensore: Boolean? = null,
    val portineria: Boolean? = null,
    val climatizzazione: Boolean? = null,
    val boxAuto: Boolean? = null,
    val terrazzo: Boolean? = null,
    val giardino: Boolean? = null,
    val tipoAnnuncio: String? = null,
    val posizione: String? = null,
    val emailAgente: String? = null
) : Serializable