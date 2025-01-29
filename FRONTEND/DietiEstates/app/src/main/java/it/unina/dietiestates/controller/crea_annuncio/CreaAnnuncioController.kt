package it.unina.dietiestates.controller.crea_annuncio

import android.content.Context
import android.widget.Toast
import it.unina.dietiestates.data.dto.ApiResponse
import it.unina.dietiestates.data.dto.SharedPrefManager
import it.unina.dietiestates.data.model.Annuncio
import it.unina.dietiestates.data.viewmodel.AnnuncioViewModel
import it.unina.dietiestates.network.retrofit.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CreaAnnuncioController(private val context: Context) {

    fun creaAnnuncio(annuncioVM: AnnuncioViewModel){
        val annuncio = annuncioVMToAnnuncio(annuncioVM)

        val api = RetrofitClient.instance
        api.insertAnnuncio(annuncio).enqueue(object : Callback<ApiResponse> {
            override fun onResponse(call: Call<ApiResponse>, response: Response<ApiResponse>) {
                when (response.code()) {
                    200, 201 -> {
                        val apiResponse = response.body()
                        if (apiResponse != null) {
                            Toast.makeText(context, "Annuncio creato correttamente", Toast.LENGTH_LONG).show()
                        } else {
                            Toast.makeText(context, "Errore: la risposta del server è incompleta", Toast.LENGTH_LONG).show()
                        }
                    }
                    409 -> {
                        Toast.makeText(context, "Esiste già un account con quest'email. Impossibile creare un nuovo account", Toast.LENGTH_SHORT).show()
                    }
                    500 -> {
                        Toast.makeText(context, "Server error: ${response.errorBody()?.string()}", Toast.LENGTH_SHORT).show()
                    }
                    else -> {
                        Toast.makeText(context, "Error: ${response.errorBody()?.string()}", Toast.LENGTH_SHORT).show()
                    }
                }
            }

            override fun onFailure(call: Call<ApiResponse>, t: Throwable) {
                Toast.makeText(context, "Failed to connect to server: ${t.message}", Toast.LENGTH_SHORT).show()
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