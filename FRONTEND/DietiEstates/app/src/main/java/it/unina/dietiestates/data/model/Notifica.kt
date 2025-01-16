package it.unina.dietiestates.data.model

import java.sql.Timestamp

data class Notifica (
    private var dataOra: Timestamp,
    private var agente: Agente,
    private var prenotazione: it.unina.dietiestates.data.model.Prenotazione
)