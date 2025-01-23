package it.unina.dietiestates.data.model

import java.sql.Timestamp

data class Prenotazione(
    private var dataInizio: Timestamp,
    private var dataFine: Timestamp,
    private var isAccettata: Boolean,
    private var emailCliente: String,
    private var idAnnuncio: Int
)