package it.unina.dietiestates.view.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import it.unina.dietiestates.R
import androidx.navigation.fragment.findNavController
import it.unina.dietiestates.data.viewmodel.FiltriRicercaViewModel

class RicercaFiltriFragment : Fragment() {

    private val filtriRicercaVM: FiltriRicercaViewModel by activityViewModels()

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

        val textStanze: TextView = view.findViewById(R.id.text_stanze)

        val seekBarStanze: SeekBar = view.findViewById(R.id.seekBar_stanze)
        val textDimensioniMin: EditText = view.findViewById(R.id.text_dim_min)
        val textPrezzoMin: EditText = view.findViewById(R.id.text_prezzo_min)
        val textPrezzoMax: EditText = view.findViewById(R.id.text_prezzo_max)
        val btnFiltri: Button = view.findViewById(R.id.btn_applica_filtri)
        val checkBoxAscensore : CheckBox = view.findViewById(R.id.checkBox_ascensore)
        val checkBoxPortineria: CheckBox = view.findViewById(R.id.checkBox_portineria)
        val checkBoxClimatizzazione : CheckBox = view.findViewById(R.id.checkBox_climatizzazione)
        val checkBoxBoxAuto : CheckBox = view.findViewById(R.id.checkBox_auto)
        val checkBoxTerrazzo: CheckBox = view.findViewById(R.id.checkBox_terrazzo)
        val checkBoxGiardino: CheckBox = view.findViewById(R.id.checkBox_giardino)

        textStanze.text = "${seekBarStanze.progress}"  // Valore minimo di default per stanze

        seekBarStanze.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                textStanze.text = "$progress"
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {   // Vuoto
                }
            override fun onStopTrackingTouch(seekBar: SeekBar) { // Vuoto
                }
        })

        btnFiltri.setOnClickListener {
            filtriRicercaVM.filtriRicerca.tipoAnnuncio = spinnerTipoAnnuncio.selectedItem.toString()
            filtriRicercaVM.filtriRicerca.piano = spinnerPiano.selectedItem.toString()
            filtriRicercaVM.filtriRicerca.dimensioni = textDimensioniMin.text.toString().ifBlank { "0" }.toInt()
            filtriRicercaVM.filtriRicerca.prezzoMin = textPrezzoMin.text.toString().ifBlank { "0" }.toFloat()
            filtriRicercaVM.filtriRicerca.prezzoMax = textPrezzoMax.text.toString().ifBlank { "1000000" }.toFloat()
            filtriRicercaVM.filtriRicerca.numeroStanze = seekBarStanze.progress
            filtriRicercaVM.filtriRicerca.classeEnergetica = spinnerClasseEnergetica.selectedItem.toString()
            filtriRicercaVM.filtriRicerca.ascensore = checkBoxAscensore.isChecked
            filtriRicercaVM.filtriRicerca.portineria = checkBoxPortineria.isChecked
            filtriRicercaVM.filtriRicerca.climatizzazione = checkBoxClimatizzazione.isChecked
            filtriRicercaVM.filtriRicerca.boxAuto = checkBoxBoxAuto.isChecked
            filtriRicercaVM.filtriRicerca.terrazzo = checkBoxTerrazzo.isChecked
            filtriRicercaVM.filtriRicerca.giardino = checkBoxGiardino.isChecked

            findNavController().navigate(R.id.action_ricercaFiltriFragment_to_risultatiRicercaFragment)
        }

        val precFragment = arguments?.getString("sourceFragment") ?: ""
        val annullaText = view.findViewById<TextView>(R.id.annullaTextView)
        annullaText.setOnClickListener{
            when(precFragment){
                "RicercaMappa" -> findNavController().navigate(R.id.action_ricercaFiltriFragment_to_ricercaMappaFragment)
                "RicercaCitta" -> findNavController().navigate(R.id.action_ricercaFiltriFragment_to_ricercaCittaFragment)
                else -> findNavController().navigate(R.id.action_ricercaFiltriFragment_to_ricercaHomeFragment)
            }
        }

        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                findNavController().popBackStack()
            }
        })
    }
}
