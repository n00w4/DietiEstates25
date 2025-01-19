package it.unina.dietiestates.model.data

data class FiltriRicercaAnnunci (
    var dimensioni : Int? = null,
    var prezzoMin : Float? = null,
    var prezzoMax : Float? = null,
    var piano : String? = null,
    var numeroStanze : Int? = null,
    var classeEnergetica : String? = null,
    var ascensore : Boolean? = null,
    var portineria : Boolean? = null,
    var climatizzazione : Boolean? = null,
    var boxAuto : Boolean? = null,
    var terrazzo : Boolean? = null,
    var giardino : Boolean? = null,
    var tipoAnnuncio : String? = null,
    var latitudine : Double? = null,
    var longitudine : Double? = null
)