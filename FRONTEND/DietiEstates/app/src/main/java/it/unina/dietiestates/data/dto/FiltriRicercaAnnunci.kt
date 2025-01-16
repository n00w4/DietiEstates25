package it.unina.dietiestates.model.data

data class FiltriRicercaAnnunci (
    val dimensioni : Int,
    val prezzoMin : Float,
    val prezzoMax : Float,
    val piano : String,
    val numeroStanze : Int,
    val classeEnergetica : String,
    val ascensore : Boolean,
    val portineria : Boolean,
    val climatizzazione : Boolean,
    val boxAuto : Boolean,
    val terrazzo : Boolean,
    val giardino : Boolean,
    val tipoAnnuncio : String,
    val latitudine : Double,
    val longitudine : Double
    //val sortOrder: String
)