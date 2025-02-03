package it.unina.dietiestates.data.model

data class Prenotazione(
    val dataInizio: String,
    val dataFine: String,
    var isAccettata: Boolean?,
    val emailCliente: String,
    val idAnnuncio: Int
)