package it.unina.dietiestates.data.model

class Cliente(
    nome: String? = null,
    cognome: String? = null,
    email: String? = null,
    password: String? = null
) : Utente(nome, cognome, email, password)