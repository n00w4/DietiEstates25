package it.unina.dietiestates.data.model

import java.sql.Timestamp

data class Prenotazione(
    private var dataInizio: Timestamp,
    private var dataFine: Timestamp,
    private var isAccettata: Boolean,
    private var cliente: it.unina.dietiestates.data.model.Cliente,
    private var annuncio: it.unina.dietiestates.data.model.Annuncio
)