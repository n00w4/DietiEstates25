package it.unina.dietiestates.data.model

data class Prenotazione(
    val ID: Int,
    val dataInizio: String,
    val dataFine: String,
    var accettata: Boolean?,
    val emailCliente: String,
    val idAnnuncio: Int
)