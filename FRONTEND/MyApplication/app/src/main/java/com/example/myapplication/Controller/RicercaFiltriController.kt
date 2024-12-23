package com.example.myapplication.Controller

import android.util.Log

class RicercaFiltriController {

    fun handleApplyFilters(tipoImmobile: String, prezzoMin: String, prezzoMax: String,
        stanze: Int, classeEnergetica: String, conPortineria: Boolean, conTerrazzo: Boolean) {

        Log.d(
            "RicercaController", """
            Posizione: da definire
            Tipo di Immobile: $tipoImmobile
            Prezzo Minimo: ${prezzoMin}€
            Prezzo Massimo: ${prezzoMax}€
            Numero di Stanze: $stanze
            Classe Energetica: $classeEnergetica
            Portineria: $conPortineria
            Con terrazzo: $conTerrazzo
            """.trimIndent()
        )
    }

}
