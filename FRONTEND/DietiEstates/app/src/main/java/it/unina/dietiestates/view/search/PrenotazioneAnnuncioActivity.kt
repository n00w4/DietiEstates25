package it.unina.dietiestates.view.search

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import it.unina.dietiestates.R
import it.unina.dietiestates.controller.search.PrenotazioneAnnuncioController
import java.util.Calendar

class PrenotazioneAnnuncioActivity : AppCompatActivity() {

    private lateinit var dataSelected: TextView
    private lateinit var oraSelected: TextView
    private var selectedDate: Triple<Int?, Int?, Int?> = Triple(null, null, null) // DD/MM/YYYY
    private var selectedTime: Pair<Int?, Int?> = Pair(null, null) // HH:MM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_prenotazione_annuncio)
        val controller = PrenotazioneAnnuncioController(this)

        val idAnnuncio = getIntent().getStringExtra("id_annuncio")
        val titoloAnnuncio = getIntent().getStringExtra("titolo_annuncio")
        findViewById<TextView>(R.id.riepilogoTextView).text = titoloAnnuncio

        dataSelected = findViewById(R.id.dataTextView)
        dataSelected.isVisible = false
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

        val prenotaBtn = findViewById<Button>(R.id.prenotaButton)
        prenotaBtn.setOnClickListener{
            controller.gestisciPrenotazione(idAnnuncio?.toInt(), selectedDate, selectedTime)
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

        val datePickerDialog = DatePickerDialog(
            this,
            { _, year, month, day ->
                selectedDate = Triple(day, month + 1, year)
                dataSelected.text = getString(R.string.data_selezionata, year, month + 1, day)
                dataSelected.isVisible = true
            },
            calendar[Calendar.DAY_OF_MONTH], calendar[Calendar.MONTH], calendar[Calendar.YEAR])
        datePickerDialog.datePicker.minDate = tomorrow
        datePickerDialog.datePicker.maxDate = twoWeeksFromNow
        datePickerDialog.show()
    }

    private fun onTimeClicked(){
        val timePickerDialog = TimePickerDialog(
            this,
            { _, hour, minute ->
                selectedTime = Pair(hour, minute)
                oraSelected.text = getString(R.string.ora_selezionata, hour, minute)
                oraSelected.isVisible = true
            }, 8, 30, true)
        timePickerDialog.show()
    }

}