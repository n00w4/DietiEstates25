package it.unina.dietiestates.data.model

import java.sql.Timestamp

data class Notifica (
    private var dataOra: Timestamp,
    private var emailAgente: String,
    private var idPrenotazione: Int
)