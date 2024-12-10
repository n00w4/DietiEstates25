package com.example.myapplication.Model

import java.sql.Timestamp

data class Notifica (
    private var dataOra: Timestamp,
    private var agente: Agente,
    private var prenotazione: Prenotazione
)