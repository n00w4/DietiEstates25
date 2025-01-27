package it.unina.dietiestates.view.crea_annuncio

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import it.unina.dietiestates.R
import it.unina.dietiestates.data.viewmodel.AnnuncioViewModel
import it.unina.dietiestates.network.geocoding.GeoPointParser
import it.unina.dietiestates.network.geocoding.OsmdroidGeocoder

class CreaAnnuncioFragment : Fragment() {

    private val annuncioVM: AnnuncioViewModel by activityViewModels()
    private var latitudine: Double? = null
    private var longitudine: Double? = null
    private val geocoder = OsmdroidGeocoder()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_crea_annuncio, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val titoloEditText = view.findViewById<EditText>(R.id.titoloEditText)
        val descrizioneEditText = view.findViewById<EditText>(R.id.descrizioneEditText)
        val indirizzoEditText = view.findViewById<EditText>(R.id.indirizzoEditText)
        val erroriTextView = view.findViewById<TextView>(R.id.indirizzoErrori)
        erroriTextView.isVisible = false
        val prezzoEditText = view.findViewById<EditText>(R.id.prezzoEditText)
        val dimensioniEditText = view.findViewById<EditText>(R.id.dimensioniEditText)

        indirizzoEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) { /*empty*/ }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) { /*empty*/ }

            override fun afterTextChanged(s: Editable?) {
                val address = s.toString()
                if (address.isNotBlank()) {
                    geocoder.getCoordinatesFromAddress(address, requireContext()) { lat, lon ->
                        activity?.runOnUiThread {
                            if (lat != null && lon != null) {
                                latitudine = lat
                                longitudine = lon
                                erroriTextView.isVisible = false// Hide error message
                            } else {
                                latitudine = null
                                longitudine = null
                                erroriTextView.text = getString(R.string.indirizzo_non_valido)
                                erroriTextView.isVisible = true
                            }
                        }
                    }
                } else {
                    latitudine = null
                    longitudine = null    // Clear coordinates if input is empty
                    erroriTextView.isVisible = false
                }
            }
        })

        val avantiBtn = view.findViewById<Button>(R.id.avantiButton)
        avantiBtn.setOnClickListener{
            val titolo = titoloEditText.text.trim().toString()
            val descrizione = descrizioneEditText.text.trim().toString()
            val indirizzo = indirizzoEditText.text.trim().toString()
            val prezzo = prezzoEditText.text.trim().toString().toFloat()
            val dimensioni = dimensioniEditText.text.trim().toString().toInt()
            if(isAnyFieldEmpty(titolo, descrizione, indirizzo, prezzo, dimensioni)){
                Toast.makeText(requireContext(), "Compilare tutti i campi prima di procedere.", Toast.LENGTH_SHORT).show()
            }else{
                saveDataInVM(titolo, descrizione, indirizzo, prezzo, dimensioni)
            }
            findNavController().navigate(R.id.action_creaAnnuncioFragment_to_creaAnnuncio2Fragment)
        }

    }

    private fun isAnyFieldEmpty(titolo: String?, descrizione: String?,
                                indirizzo: String?, prezzo: Float?, dimensioni: Int?) : Boolean{
        if(titolo.isNullOrEmpty()) return false
        if(descrizione.isNullOrEmpty()) return false
        if(indirizzo.isNullOrEmpty() || latitudine == null || longitudine == null) return false
        if(prezzo == null || prezzo.toDouble() == 0.0) return false
        if(dimensioni == null || dimensioni == 0) return false
        return true
    }

    private fun saveDataInVM(titolo: String?, descrizione: String?,
                             indirizzo: String?, prezzo: Float?, dimensioni: Int?){
        annuncioVM.titolo = titolo
        annuncioVM.descrizione = descrizione
        annuncioVM.indirizzo = indirizzo
        annuncioVM.posizione =
            latitudine?.let { longitudine?.let { it1 ->
                GeoPointParser().parseCoordinatesToWKB(it, it1)
            } }
        annuncioVM.prezzo = prezzo
        annuncioVM.dimensioni = dimensioni
    }
}