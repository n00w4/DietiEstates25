package it.unina.dietiestates.data.dto

data class AddUtenteForm(
    val userType: String,
    val name: String,
    val surname: String,
    val email: String,
    val partitaIVA: String
)
