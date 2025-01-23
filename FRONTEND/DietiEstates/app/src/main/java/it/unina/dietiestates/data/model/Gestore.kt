package it.unina.dietiestates.data.model

data class Gestore (
    private var nome: String,
    private var cognome: String,
    private var email: String,
    private var password: String,
    private var passwordAdmin: String,
    private var partitaIVA: String
)