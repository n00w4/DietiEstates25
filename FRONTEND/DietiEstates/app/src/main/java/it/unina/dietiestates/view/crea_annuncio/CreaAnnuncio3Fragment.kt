package it.unina.dietiestates.view.crea_annuncio

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import it.unina.dietiestates.R
import it.unina.dietiestates.data.viewmodel.AnnuncioViewModel

class CreaAnnuncio3Fragment : Fragment(){

    private val annuncioVM: AnnuncioViewModel by activityViewModels()
    private lateinit var avantiBtn: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_crea_annuncio3, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val tipoAnnuncioSpinner = view.findViewById<Spinner>(R.id.spinner_tipo_annuncio)
        tipoAnnuncioSpinner.setSelection(0)
        val pianoSpinner = view.findViewById<Spinner>(R.id.spinner_piano)
        pianoSpinner.setSelection(0)
        val numeroStanzeEditText = view.findViewById<EditText>(R.id.numeroStanzeEditText)
        val classeEnergeticaSpinner = view.findViewById<Spinner>(R.id.spinner_classe_ene)
        classeEnergeticaSpinner.setSelection(0)
        val ascensoreCheckBox = view.findViewById<CheckBox>(R.id.checkBox_ascensore)
        val portineriaCheckBox = view.findViewById<CheckBox>(R.id.checkBox_portineria)
        val climatizzazioneCheckBox = view.findViewById<CheckBox>(R.id.checkBox_climatizzazione)
        val boxAutoCheckBox = view.findViewById<CheckBox>(R.id.checkBox_auto)
        val terrazzoCheckBox = view.findViewById<CheckBox>(R.id.checkBox_terrazzo)
        val giardinoCheckBox = view.findViewById<CheckBox>(R.id.checkBox_giardino)

        avantiBtn = view.findViewById(R.id.avantiButton)
        avantiBtn.setOnClickListener{
            annuncioVM.tipoAnnuncio = tipoAnnuncioSpinner.selectedItem.toString()
            annuncioVM.piano = pianoSpinner.selectedItem.toString()
            annuncioVM.numeroStanze = numeroStanzeEditText.text.trim().toString().ifBlank { "0" }.toInt()
            annuncioVM.classeEnergetica = classeEnergeticaSpinner.selectedItem.toString()
            annuncioVM.ascensore = ascensoreCheckBox.isChecked
            annuncioVM.portineria = portineriaCheckBox.isChecked
            annuncioVM.climatizzazione = climatizzazioneCheckBox.isChecked
            annuncioVM.boxAuto = boxAutoCheckBox.isChecked
            annuncioVM.terrazzo = terrazzoCheckBox.isChecked
            annuncioVM.giardino = giardinoCheckBox.isChecked

            findNavController().navigate(R.id.action_creaAnnuncio3Fragment_to_creaAnnuncioRiepilogoFragment)
        }

        val indietroBtn = view.findViewById<TextView>(R.id.annullaTextView)
        indietroBtn.setOnClickListener{
            findNavController().navigate(R.id.action_creaAnnuncio3Fragment_to_creaAnnuncio2Fragment)
        }
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                findNavController().popBackStack()
            }
        })
    }
}