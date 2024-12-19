package com.example.myapplication.View

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.SeekBar
import android.widget.Spinner
import android.widget.TextView
import com.example.myapplication.R

class RicercaFiltriFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_ricerca_filtri, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val spinnerTipoAnnuncio: Spinner = view.findViewById(R.id.spinner_tipo_annuncio)
        spinnerTipoAnnuncio.setSelection(0)

        val spinnerClasseEnergetica: Spinner = view.findViewById(R.id.spinner_classe_ene)
        spinnerClasseEnergetica.setSelection(0)

        val spinnerPiano: Spinner = view.findViewById(R.id.spinner_piano)
        spinnerPiano.setSelection(0)

        val seekBarStanze: SeekBar = view.findViewById(R.id.seekBar_stanze)
        val textStanze: TextView = view.findViewById(R.id.text_stanze)
        val defaultStanze = seekBarStanze.progress
        textStanze.text = "$defaultStanze"

        seekBarStanze.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                textStanze.text = "$progress"
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) { // Optional: Do something when the user starts moving the SeekBar
            }

            override fun onStopTrackingTouch(seekBar: SeekBar) { // Optional: Do something when the user stops moving the SeekBar
            }
        })

        val textPrezzoMin: EditText = view.findViewById(R.id.text_prezzo_min)
        val textPrezzoMax: EditText = view.findViewById(R.id.text_prezzo_max)
        val btnFiltri: Button = view.findViewById(R.id.btn_applica_filtri)
        val checkBoxPortineria: CheckBox = view.findViewById(R.id.checkBox_portineria)
        val checkBoxTerrazzo: CheckBox = view.findViewById(R.id.checkBox_terrazzo)

        btnFiltri.setOnClickListener {
            val tipoImmobile = spinnerTipoAnnuncio.selectedItem.toString()
            val prezzoMin = if (textPrezzoMin.text.toString().isBlank()) { "0"
                            } else { textPrezzoMin.text.toString() }
            val prezzoMax = if (textPrezzoMax.text.toString().isBlank()) { "1,000,000"
            } else { textPrezzoMin.text.toString() }
            val stanze = seekBarStanze.progress
            val classeEnergetica = spinnerClasseEnergetica.selectedItem.toString()
            val conPortineria = checkBoxPortineria.isChecked
            val conTerrazzo = checkBoxTerrazzo.isChecked

            Log.d(
                "RicercaFragment", """
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

}