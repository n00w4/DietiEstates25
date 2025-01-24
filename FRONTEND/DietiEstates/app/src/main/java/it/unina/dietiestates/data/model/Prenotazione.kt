package it.unina.dietiestates.data.model

import java.util.Date

data class Prenotazione(
    val dataInizio: Date,
    val dataFine: Date,
    val isAccettata: Boolean? = null,
    val emailCliente: String,
    val idAnnuncio: Int
)