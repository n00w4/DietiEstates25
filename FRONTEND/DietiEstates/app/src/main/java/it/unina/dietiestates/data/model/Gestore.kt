package it.unina.dietiestates.data.model

data class Gestore (
    val nome: String,
    val cognome: String,
    val email: String,
    val password: String,
    val passwordAdmin: String,
    val partitaIVA: String
)