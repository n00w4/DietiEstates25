package it.unina.dietiestates.data.model

import java.sql.Timestamp

data class Prenotazione(
    private var dataInizio: Date,
    private var dataFine: Date,
    private var isAccettata: Boolean? = null,
    private var emailCliente: String,
    private var idAnnuncio: Int
)