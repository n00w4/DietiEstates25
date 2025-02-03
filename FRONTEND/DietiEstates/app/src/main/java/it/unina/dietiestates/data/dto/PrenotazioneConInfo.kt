package it.unina.dietiestates.data.dto

import it.unina.dietiestates.data.model.Annuncio
import it.unina.dietiestates.data.model.Prenotazione

data class PrenotazioneConInfo (
    val prenotazione: Prenotazione,
    val annuncio: Annuncio
)
