package it.unina.dietiestates.controller.search

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.google.gson.Gson
import it.unina.dietiestates.data.dto.ApiResponse
import it.unina.dietiestates.data.dto.SharedPrefManager
import it.unina.dietiestates.data.model.Prenotazione
import it.unina.dietiestates.network.retrofit.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.sql.Timestamp
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class PrenotazioneAnnuncioController (private val context: Context) {

    fun gestisciPrenotazione(idAnnuncio : Int, data: Triple<Int?, Int?, Int?>, ora: Pair<Int?, Int?> ){
        if(isDateNull(data) || isTimeNull(ora)){
            Toast.makeText(context, "Assicurarti di aver inserito tutti i campi e riprovare.", Toast.LENGTH_SHORT).show()
        }else{
            val emailUtente = SharedPrefManager.getUserEmail(context) ?: "no_email"
            val calendar = Calendar.getInstance()
            calendar.set(data.third!!, data.second!! - 1, data.first!!, ora.first!!, ora.second!!, 0) // Month is 0-based
            val dataInizio = Timestamp(calendar.time.toInstant().toEpochMilli())
            calendar.add(Calendar.HOUR_OF_DAY, 1)
            val dataFine = Timestamp(calendar.time.toInstant().toEpochMilli())

            val formattedDataInizio = formatTimestamp(dataInizio)
            val formattedDataFine = formatTimestamp(dataFine)

            val prenotazione = Prenotazione(formattedDataInizio, formattedDataFine, null, emailUtente, idAnnuncio)
            Log.d("DEBUG_JSON", "Prenotazione JSON: ${Gson().toJson(prenotazione)}")
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
                when (response.code()) {
                    200, 201 -> {
                        val apiResponse = response.body()
                        if (apiResponse != null) {
                            showPrenotazioneEffettuataPopup()
                        } else {
                            Toast.makeText(context, "Errore: la risposta del server è incompleta.", Toast.LENGTH_LONG).show()
                        }
                    }
                    409 -> {
                        Toast.makeText(context, "Spiacenti, esiste già una prenotazione nella stessa data. Riprovare.", Toast.LENGTH_SHORT).show()
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

    private fun showPrenotazioneEffettuataPopup(){
        val builder = AlertDialog.Builder(context)
        builder.setTitle("Prenotazione effettuata con successo.")
        builder.setMessage("Potrai controllare in 'Calendario' lo stato della prenotazione.")
        builder.setPositiveButton("Ok", null)
        builder.show()
    }

    private fun formatTimestamp(timestamp: Timestamp): String {
        val sdf = SimpleDateFormat("MMM dd, yyyy HH:mm:ss", Locale.ENGLISH) // Force two-digit days
        return sdf.format(timestamp)
    }

}