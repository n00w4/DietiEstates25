package it.unina.dietiestates.data.model

import java.util.Date

data class Prenotazione(
    private var dataInizio: Date,
    private var dataFine: Date,
    private var isAccettata: Boolean? = null,
    private var emailCliente: String,
    private var idAnnuncio: Int
)