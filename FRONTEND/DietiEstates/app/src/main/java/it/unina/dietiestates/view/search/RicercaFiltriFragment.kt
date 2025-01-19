package it.unina.dietiestates.view.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import it.unina.dietiestates.controller.search.RicercaFiltriController
import it.unina.dietiestates.R
import androidx.navigation.fragment.findNavController
import it.unina.dietiestates.data.viewmodel.FiltriRicercaViewModel

class RicercaFiltriFragment : Fragment() {

    private val filtriRicercaVM: FiltriRicercaViewModel by activityViewModels()
    private lateinit var controller: RicercaFiltriController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_ricerca_filtri, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        controller = RicercaFiltriController()

        val spinnerTipoAnnuncio: Spinner = view.findViewById(R.id.spinner_tipo_annuncio)
        spinnerTipoAnnuncio.setSelection(0)

        val spinnerClasseEnergetica: Spinner = view.findViewById(R.id.spinner_classe_ene)
        spinnerClasseEnergetica.setSelection(0)

        val spinnerPiano: Spinner = view.findViewById(R.id.spinner_piano)
        spinnerPiano.setSelection(0)

        val seekBarStanze: SeekBar = view.findViewById(R.id.seekBar_stanze)
        val textStanze: TextView = view.findViewById(R.id.text_stanze)
        val textPrezzoMin: EditText = view.findViewById(R.id.text_prezzo_min)
        val textPrezzoMax: EditText = view.findViewById(R.id.text_prezzo_max)
        val btnFiltri: Button = view.findViewById(R.id.btn_applica_filtri)
        val checkBoxPortineria: CheckBox = view.findViewById(R.id.checkBox_portineria)
        val checkBoxTerrazzo: CheckBox = view.findViewById(R.id.checkBox_terrazzo)

        textStanze.text = "${seekBarStanze.progress}"  // Default value for rooms

        seekBarStanze.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                textStanze.text = "$progress"
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {   // Add implementation if needed
                }
            override fun onStopTrackingTouch(seekBar: SeekBar) { // Add implementation if needed
                }
        })

        btnFiltri.setOnClickListener {
            val tipoImmobile = spinnerTipoAnnuncio.selectedItem.toString()
            val prezzoMin = textPrezzoMin.text.toString().ifBlank { "0" }
            val prezzoMax = textPrezzoMax.text.toString().ifBlank { "1000000" }
            val stanze = seekBarStanze.progress
            val classeEnergetica = spinnerClasseEnergetica.selectedItem.toString()
            val conPortineria = checkBoxPortineria.isChecked
            val conTerrazzo = checkBoxTerrazzo.isChecked

            controller.handleApplyFilters(tipoImmobile, prezzoMin, prezzoMax, stanze,
                classeEnergetica, conPortineria, conTerrazzo
            )

            findNavController().navigate(R.id.action_ricercaFiltriFragment_to_risultatiRicercaFragment)
        }

        val precFragment = arguments?.getString("sourceFragment") ?: ""
        val annullaText = view.findViewById<TextView>(R.id.annullaTextView)
        annullaText.setOnClickListener{
            when(precFragment){
                "RicercaMappa" -> findNavController().navigate(R.id.action_ricercaFiltriFragment_to_ricercaMappaFragment)
                "RicercaCittà" -> findNavController().navigate(R.id.action_ricercaFiltriFragment_to_ricercaCittaFragment)
                else -> findNavController().navigate(R.id.action_ricercaFiltriFragment_to_ricercaHomeFragment)
            }
        }
    }
}
