package it.unina.dietiestates.view.search

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import it.unina.dietiestates.R
import it.unina.dietiestates.controller.search.MeteoPrenotazioneController
import it.unina.dietiestates.controller.search.PrenotazioneAnnuncioController
import it.unina.dietiestates.network.geocoding.GeoPointParser
import it.unina.dietiestates.network.openmeteo.WeatherDataCallback
import java.util.Calendar
import java.util.Locale

class PrenotazioneAnnuncioActivity : AppCompatActivity(), WeatherDataCallback {

    private lateinit var dataSelected: TextView
    private lateinit var oraSelected: TextView
    private var selectedDate: Triple<Int?, Int?, Int?> = Triple(null, null, null) // DD/MM/YYYY
    private var selectedTime: Pair<Int?, Int?> = Pair(null, null) // HH:MM
    private lateinit var oraLayout: LinearLayout
    private lateinit var meteoLayout: LinearLayout
    private lateinit var temperatura: TextView
    private lateinit var umidita: TextView
    private lateinit var precipitazioni: TextView
    private lateinit var pioggia: TextView
    private lateinit var codice: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_prenotazione_annuncio)
        val controller = PrenotazioneAnnuncioController(this)

        val idAnnuncio = getIntent().getIntExtra("id_annuncio", -1)
        val titoloAnnuncio = getIntent().getStringExtra("titolo_annuncio")
        val posizioneAnnuncio = getIntent().getStringExtra("posizione_annuncio") ?: " "
        findViewById<TextView>(R.id.riepilogoTextView).text = titoloAnnuncio

        dataSelected = findViewById(R.id.dataTextView)
        dataSelected.isVisible = false
        oraLayout = findViewById(R.id.oraLayout)
        oraLayout.isVisible = false
        oraSelected = findViewById(R.id.oraTextView)
        oraSelected.isVisible = false
        val dataBtn = findViewById<Button>(R.id.dataButton)
        val oraBtn = findViewById<Button>(R.id.oraButton)
        dataBtn.setOnClickListener{
            onDateClicked()
        }
        oraBtn.setOnClickListener{
            onTimeClicked()
        }

        meteoLayout = findViewById(R.id.meteoLayout)
        meteoLayout.isVisible = false
        temperatura = findViewById(R.id.temperaturaTextView)
        umidita = findViewById(R.id.umiditaTextView)
        precipitazioni = findViewById(R.id.precipitazioniTextView)
        pioggia = findViewById(R.id.pioggiaTextView)
        codice = findViewById(R.id.codiceTextView)
        val meteoBtn = findViewById<Button>(R.id.meteoButton)
        meteoBtn.setOnClickListener{
            calcolaMeteo(posizioneAnnuncio)
        }

        val prenotaBtn = findViewById<Button>(R.id.prenotaButton)
        prenotaBtn.setOnClickListener{
            controller.gestisciPrenotazione(idAnnuncio, selectedDate, selectedTime)
        }

        val annullaTextView = findViewById<TextView>(R.id.annullaTextView)
        annullaTextView.setOnClickListener {
            finish()    // Finish this activity and go back to the previous one
        }
    }

    private fun onDateClicked(){
        val calendar = Calendar.getInstance()
        calendar.add(Calendar.DAY_OF_YEAR, 1)   //Data domani
        val tomorrow = calendar.timeInMillis
        calendar.add(Calendar.DAY_OF_YEAR, 13)  //Data max (tra 2 settimane)
        val twoWeeksFromNow = calendar.timeInMillis

        val datePickerDialog = DatePickerDialog(this, R.style.DialogTheme,
            { _, year, month, day ->
                selectedDate = Triple(day, month + 1, year)
                dataSelected.text = getString(R.string.data_selezionata, year, month + 1, day)
                dataSelected.isVisible = true
                oraLayout.isVisible = true
            },
            calendar[Calendar.DAY_OF_MONTH], calendar[Calendar.MONTH], calendar[Calendar.YEAR])
        datePickerDialog.datePicker.minDate = tomorrow
        datePickerDialog.datePicker.maxDate = twoWeeksFromNow
        datePickerDialog.show()
    }

    private fun onTimeClicked(){
        val timePickerDialog = TimePickerDialog(this, R.style.DialogTheme,
            { _, hour, minute ->
                selectedTime = Pair(hour, minute)
                oraSelected.text = getString(R.string.ora_selezionata, hour, minute)
                oraSelected.isVisible = true
                meteoLayout.isVisible = true
            }, 8, 30, true)
        timePickerDialog.show()
    }

    private fun calcolaMeteo(posizioneAnnuncio : String){
        val meteoController = MeteoPrenotazioneController()
        val geoPointParser = GeoPointParser()
        val posizione = geoPointParser.parseWKBToGeoPoint(posizioneAnnuncio)
        if (posizione != null) { meteoController.getMeteo(this, posizione, getFormattedDateTime())
        }else{temperatura.text = getString(R.string.no_info_meteo) }
    }

    private fun getFormattedDateTime(): String {
        return String.format(Locale.getDefault(), "%04d-%02d-%02dT%02d:%02d:00",
            selectedDate.third, selectedDate.second, selectedDate.first,
            selectedTime.first, selectedTime.second) // Format as ISO-8601: YYYY-MM-DDTHH:MM:00
    }

    override fun onWeatherDataReceived(temperature: Double, humidity: Double,
        precipitation: Double, rain: Double, weatherCode: Int) {
        runOnUiThread {
            temperatura.text = getString(R.string.temperatura, temperature.toFloat())
            umidita.text = getString(R.string.umidita, humidity.toFloat())
            precipitazioni.text = getString(R.string.precipitazioni, precipitation.toFloat())
            pioggia.text = getString(R.string.pioggia, rain.toFloat())
            codice.text = getString(R.string.codice, weatherCode)
        }
    }
    override fun onError(message: String) {
        runOnUiThread { temperatura.text = message }
    }

}