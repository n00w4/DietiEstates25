package it.unina.dietiestates.view.crea_annuncio

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import it.unina.dietiestates.R
import it.unina.dietiestates.controller.crea_annuncio.CreaAnnuncioController
import it.unina.dietiestates.data.viewmodel.AnnuncioViewModel
import it.unina.dietiestates.data.viewmodel.CoordinateViewModel
import it.unina.dietiestates.network.geocoding.OsmdroidGeocoder

class CreaAnnuncioFragment : Fragment() {

    private val annuncioVM: AnnuncioViewModel by activityViewModels()
    private val coordinateVM: CoordinateViewModel by activityViewModels()
    private val geocoder = OsmdroidGeocoder()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_crea_annuncio, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val controller = CreaAnnuncioController()

        val indirizzoTextView = view.findViewById<AutoCompleteTextView>(R.id.indirizzoEditText)
        indirizzoTextView.threshold = 0
        val erroriTextView = view.findViewById<TextView>(R.id.indirizzoErrori)
        erroriTextView.isVisible = false

        val adapter = ArrayAdapter<String>(requireContext(), android.R.layout.simple_dropdown_item_1line)
        indirizzoTextView.setAdapter(adapter)

        indirizzoTextView.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {/*Vuoto*/}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val address = s.toString()
                if (address.isNotBlank()) {
                    geocoder.getPossibleAddresses(address) { addresses ->
                        activity?.runOnUiThread {
                            adapter.clear()
                            adapter.addAll(addresses)
                            adapter.notifyDataSetChanged() } }
                    erroriTextView.isVisible = false
                }else{
                    erroriTextView.isVisible = true
                }
            }
            override fun afterTextChanged(s: Editable?) {/*Vuoto*/}
        })

        indirizzoTextView.setOnItemClickListener { _, _, position, _ ->
            val selectedAddress = adapter.getItem(position)
            if (selectedAddress != null) {
                geocoder.getCoordinatesFromAddress(selectedAddress) { lat, lon ->
                    activity?.runOnUiThread {
                        if (lat != null && lon != null) {
                            coordinateVM.latitudine = lat
                            coordinateVM.longitudine = lon
                        } else {
                            coordinateVM.latitudine = null
                            coordinateVM.longitudine = null
                            erroriTextView.text = getString(R.string.indirizzo_non_valido)
                            erroriTextView.isVisible = true
                        }
                    }
                }
            }
        }

        annuncioVM.indirizzo.let { indirizzo ->
            indirizzoTextView.setText(indirizzo)
        }

        val mappaBtn = view.findViewById<Button>(R.id.mappaButton)
        mappaBtn.setOnClickListener{
            findNavController().navigate(R.id.action_creaAnnuncioFragment_to_creaAnnuncioMappaFragment)
        }

        val avantiBtn = view.findViewById<Button>(R.id.avantiButton)
        avantiBtn.setOnClickListener{
            val titolo = view.findViewById<EditText>(R.id.titoloEditText).text.trim().toString()
            val descrizione = view.findViewById<EditText>(R.id.descrizioneEditText).text.trim().toString()
            val indirizzo = indirizzoTextView.text.trim().toString()
            val prezzo = view.findViewById<EditText>(R.id.prezzoEditText).text.trim().toString().ifBlank{ "0.0" }.toFloat()
            val dimensioni = view.findViewById<EditText>(R.id.dimensioniEditText).text.trim().toString().ifBlank{ "0" }.toInt()

            if(controller.isAnyFieldEmpty(titolo, descrizione, indirizzo, coordinateVM.latitudine, coordinateVM.longitudine, prezzo, dimensioni)){
                Toast.makeText(requireContext(), "Compilare tutti i campi prima di procedere.", Toast.LENGTH_SHORT).show()
            }else{
                controller.saveDataInVM(titolo, descrizione, prezzo, dimensioni, annuncioVM)
                controller.savePositionInVM(indirizzo, coordinateVM.latitudine, coordinateVM.longitudine, annuncioVM)
                findNavController().navigate(R.id.action_creaAnnuncioFragment_to_creaAnnuncio2Fragment)
            }
        }

    }

}