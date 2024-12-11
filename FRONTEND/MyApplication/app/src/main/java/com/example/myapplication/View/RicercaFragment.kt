package com.example.myapplication.View

import RicercaMappaActivity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.SeekBar
import android.widget.Spinner
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.myapplication.R


class RicercaFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_ricerca, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val btnMappa: Button = view.findViewById(R.id.btn_map)

        val spinnerTipoAnnuncio: Spinner = view.findViewById(R.id.spinner_tipo_annuncio)
        spinnerTipoAnnuncio.setSelection(0)

        val spinnerClasseEnergetica: Spinner = view.findViewById(R.id.spinner_classe_ene)
        spinnerClasseEnergetica.setSelection(0)

        val spinnerPiano: Spinner = view.findViewById(R.id.spinner_piano)
        spinnerPiano.setSelection(0)

        val seekBarPrezzoMin: SeekBar = view.findViewById(R.id.seekBar_prezzo_min)
        val textPrezzoMin: TextView = view.findViewById(R.id.text_prezzo_min)
        val defaultPrezzoMin = seekBarPrezzoMin.progress
        textPrezzoMin.text = formatAsCurrency(defaultPrezzoMin)

        seekBarPrezzoMin.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                textPrezzoMin.text = formatAsCurrency(progress)
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) { // Optional: Do something when the user starts moving the SeekBar
            }

            override fun onStopTrackingTouch(seekBar: SeekBar) { // Optional: Do something when the user stops moving the SeekBar
            }
        })

        val seekBarPrezzoMax: SeekBar = view.findViewById(R.id.seekBar_prezzo_max)
        val textPrezzoMax: TextView = view.findViewById(R.id.text_prezzo_max)
        val defaultPrezzoMax = seekBarPrezzoMax.progress
        textPrezzoMax.text = formatAsCurrency(defaultPrezzoMax)

        seekBarPrezzoMax.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                textPrezzoMax.text = formatAsCurrency(progress)
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) { // Optional: Do something when the user starts moving the SeekBar
            }

            override fun onStopTrackingTouch(seekBar: SeekBar) { // Optional: Do something when the user stops moving the SeekBar
            }
        })

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


        btnMappa.setOnClickListener {
            val intent = Intent(activity, RicercaMappaActivity::class.java)
            startActivity(intent)
        }

        val btnFiltri: Button = view.findViewById(R.id.btn_applica_filtri)
        val checkBoxPortineria: CheckBox = view.findViewById(R.id.checkBox_portineria)
        val checkBoxTerrazzo: CheckBox = view.findViewById(R.id.checkBox_terrazzo)

        btnFiltri.setOnClickListener {
            val tipoImmobile = spinnerTipoAnnuncio.selectedItem.toString()
            val prezzoMin = seekBarPrezzoMin.progress
            val prezzoMax = seekBarPrezzoMax.progress
            val stanze = seekBarStanze.progress
            val classeEnergetica = spinnerClasseEnergetica.selectedItem.toString()
            val conPortineria = checkBoxPortineria.isChecked
            val conTerrazzo = checkBoxTerrazzo.isChecked

            // Stampiamo tutte le informazioni
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

    private fun formatAsCurrency(value: Int): String {
        return "€ ${"%,d".format(value)}"
    }
}