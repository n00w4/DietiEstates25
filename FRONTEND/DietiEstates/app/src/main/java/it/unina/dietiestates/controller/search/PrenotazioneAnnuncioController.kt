package it.unina.dietiestates.controller.search

import android.content.Context
import android.content.Intent
import android.widget.Toast
import it.unina.dietiestates.data.dto.ApiResponse
import it.unina.dietiestates.data.dto.SharedPrefManager
import it.unina.dietiestates.data.model.Prenotazione
import it.unina.dietiestates.network.retrofit.RetrofitClient
import it.unina.dietiestates.view.auth.SignUpResult
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.Calendar
import java.util.Date

class PrenotazioneAnnuncioController (private val context: Context) {

    fun gestisciPrenotazione(idAnnuncio : Int?, data: Triple<Int?, Int?, Int?>, ora: Pair<Int?, Int?> ){
        if(isDateNull(data) || isTimeNull(ora) || idAnnuncio == null){
            Toast.makeText(context, "Assicurarsi di aver inserito tutti i campi e riprovare.", Toast.LENGTH_SHORT).show()
        }else{
            val emailUtente = SharedPrefManager.getUserEmail(context) ?: "no_email"
            val calendar = Calendar.getInstance()
            calendar.set(data.third!!, data.second!! - 1, data.first!!, ora.first!!, ora.second!!, 0) // Month is 0-based
            val dataInizio: Date = calendar.time
            calendar.add(Calendar.HOUR_OF_DAY, 1)
            val dataFine: Date = calendar.time
            val prenotazione = Prenotazione(dataInizio, dataFine, null, emailUtente, idAnnuncio)
            inserisciPrenotazione(prenotazione)
        }
    }

    private fun isDateNull(date: Triple<Int?, Int?, Int?>): Boolean{
        return date.first == null || date.second == null || date.third == null
    }
    private fun isTimeNull(time: Pair<Int?, Int?>): Boolean{
        return time.first == null || time.second == null
    }

    private fun inserisciPrenotazione(prenotazione: Prenotazione){
        val api = RetrofitClient.instance

        api.insertPrenotazione(prenotazione).enqueue(object : Callback<ApiResponse> {
            override fun onResponse(call: Call<ApiResponse>, response: Response<ApiResponse>) {
                when (response.code()) { //TODO: Vedere gestione appropriata della response dal backend
                    200, 201 -> {
                        val apiResponse = response.body()
                        if (apiResponse != null) {
                            val signUpResult = Intent(context, SignUpResult::class.java)
                            signUpResult.putExtra("resultMessage", apiResponse.message)
                            signUpResult.putExtra("responseCode", response.code())
                            context.startActivity(signUpResult)
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

}