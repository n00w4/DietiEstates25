package com.example.myapplication.Entity

import java.sql.Timestamp

class Prenotazione {
    private var dataInizio: Timestamp? = null
    private var dataFine: Timestamp? = null
    private var isAccettata: Boolean? = null
    private var cliente: Cliente? = null
    private var annuncio: Annuncio? = null
}