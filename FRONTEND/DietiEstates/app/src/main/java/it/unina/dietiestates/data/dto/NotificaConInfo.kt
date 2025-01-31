package it.unina.dietiestates.data.dto

import it.unina.dietiestates.data.model.Annuncio
import it.unina.dietiestates.data.model.Notifica
import it.unina.dietiestates.data.model.Prenotazione

data class NotificaConInfo (
    val notifica : Notifica,
    val prenotazione: Prenotazione,
    val annuncio: Annuncio
)