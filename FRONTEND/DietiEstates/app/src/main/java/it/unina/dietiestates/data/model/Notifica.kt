package it.unina.dietiestates.data.model

import java.sql.Timestamp

data class Notifica (
    val dataOra: Timestamp,
    val emailAgente: String,
    val idPrenotazione: Int
)