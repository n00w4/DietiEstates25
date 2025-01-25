package it.unina.dietiestates.data.model

import java.sql.Timestamp

data class Prenotazione(
    val dataInizio: Timestamp,
    val dataFine: Timestamp,
    val isAccettata: Boolean? = null,
    val emailCliente: String,
    val idAnnuncio: Int
)