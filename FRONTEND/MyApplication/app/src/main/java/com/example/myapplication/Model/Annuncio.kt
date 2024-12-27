package com.example.myapplication.model

data class Annuncio (
    private var titolo: String,
    private var indirizzo: String,
    private var descrizione: String,
    private var dimensioni: Int,
    private var prezzo: Float,
    private var numeroStanze: Int,
    private var classeEnergetica: String,
    private var ascensore: Boolean,
    private var portineria: Boolean,
    private var climatizzazione: Boolean,
    private var boxAuto: Boolean,
    private var terrazzo: Boolean,
    private var giardino: Boolean,
    private var tipoAnnuncio: String
)