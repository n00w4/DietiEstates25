package it.unina.dietiestates.controller.search

import android.util.Log
import it.unina.dietiestates.data.viewmodel.FiltriRicercaViewModel

class RicercaFiltriController {

    fun handleApplyFilters(filtri: FiltriRicercaViewModel, tipoImmobile: String, prezzoMin: String, prezzoMax: String,
                           stanze: Int, classeEnergetica: String, conPortineria: Boolean, conTerrazzo: Boolean) {

        filtri.filtriRicerca.tipoAnnuncio = tipoImmobile

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
