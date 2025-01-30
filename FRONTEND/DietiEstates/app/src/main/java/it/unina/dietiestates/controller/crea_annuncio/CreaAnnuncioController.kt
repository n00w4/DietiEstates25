package it.unina.dietiestates.controller.crea_annuncio

import android.content.Context
import it.unina.dietiestates.data.dto.ApiResponse
import it.unina.dietiestates.data.dto.SharedPrefManager
import it.unina.dietiestates.data.model.Annuncio
import it.unina.dietiestates.data.viewmodel.AnnuncioViewModel
import it.unina.dietiestates.network.retrofit.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CreaAnnuncioController(private val context: Context) {

    fun creaAnnuncio(annuncioVM: AnnuncioViewModel, callback: (ApiResponse) -> Unit){
        val annuncio = annuncioVMToAnnuncio(annuncioVM)

        val api = RetrofitClient.instance
        api.insertAnnuncio(annuncio).enqueue(object : Callback<ApiResponse> {
            override fun onResponse(call: Call<ApiResponse>, response: Response<ApiResponse>) {
                val apiResponse = when (response.code()) {
                    200, 201 -> ApiResponse("Success", "Annuncio creato correttamente")
                    409 -> ApiResponse("Conflict", "Errore nella creazione dell'annuncio (tipo: 'Conflitto').")
                    500 -> ApiResponse("Server Error", "Errore interno del server: ${response.errorBody()?.string()}")
                    else -> ApiResponse("Error", "Errore: ${response.errorBody()?.string()}")
                }
                callback(apiResponse)
            }

            override fun onFailure(call: Call<ApiResponse>, t: Throwable) {
                val apiResponse = ApiResponse("Failure", "Connessione al server fallita: ${t.message}")
                callback(apiResponse)
            }
        })
    }

    private fun annuncioVMToAnnuncio(annuncioVM: AnnuncioViewModel) : Annuncio{
        val annuncio = Annuncio(0, annuncioVM.titolo, annuncioVM.indirizzo, annuncioVM.immagine,
            annuncioVM.descrizione, annuncioVM.dimensioni, annuncioVM.prezzo, annuncioVM.piano,
            annuncioVM.numeroStanze, annuncioVM.classeEnergetica, annuncioVM.ascensore, annuncioVM.portineria,
            annuncioVM.climatizzazione, annuncioVM.boxAuto, annuncioVM.terrazzo, annuncioVM.giardino,
            annuncioVM.tipoAnnuncio, annuncioVM.posizione, SharedPrefManager.getUserEmail(context))
        return annuncio
    }
}