package it.unina.dietiestates.data.model

import java.sql.Timestamp

data class Prenotazione(
    val dataInizio: String,
    val dataFine: String,
    var isAccettata: Boolean? = null,
    val emailCliente: String,
    val idAnnuncio: Int
)