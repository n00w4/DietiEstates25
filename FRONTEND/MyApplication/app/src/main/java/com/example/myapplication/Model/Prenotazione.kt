package com.example.myapplication.model

import java.sql.Timestamp

data class Prenotazione(
    private var dataInizio: Timestamp,
    private var dataFine: Timestamp,
    private var isAccettata: Boolean,
    private var cliente: Cliente,
    private var annuncio: Annuncio
)