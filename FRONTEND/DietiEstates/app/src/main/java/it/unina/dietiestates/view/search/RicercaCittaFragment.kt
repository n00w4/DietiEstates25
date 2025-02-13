package it.unina.dietiestates.view.search

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import androidx.core.content.ContextCompat.getColorStateList
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import it.unina.dietiestates.R
import it.unina.dietiestates.data.viewmodel.FiltriRicercaViewModel
import it.unina.dietiestates.data.dto.cities

class RicercaCittaFragment : Fragment() {

    private val filtriRicercaVM: FiltriRicercaViewModel by activityViewModels()
    private lateinit var btnSearch: Button
    private lateinit var btnAddFilters: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_ricerca_citta, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnSearch = view.findViewById(R.id.btn_cerca)
        btnSearch.isEnabled = false
        btnSearch.backgroundTintList = getColorStateList(requireContext(), android.R.color.darker_gray)
        btnAddFilters = view.findViewById(R.id.btn_add_filters)
        val annullaText = view.findViewById<TextView>(R.id.annullaTextView)

        val cittaInput = view.findViewById<AutoCompleteTextView>(R.id.cittaInput)
        val cityNames = cities.map { it.second }
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_dropdown_item_1line, cityNames)
        cittaInput.setAdapter(adapter)
        cittaInput.threshold = 0

        cittaInput.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) { // Vuoto
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val input = s.toString()
                if(cityNames.contains(input)){
                    btnSearch.isEnabled = true
                    btnSearch.backgroundTintList = getColorStateList(requireContext(), R.color.button_color_light)
                    btnAddFilters.isEnabled = true
                }else{
                    btnSearch.isEnabled = false
                    btnSearch.backgroundTintList = getColorStateList(requireContext(), android.R.color.darker_gray)
                    btnAddFilters.isEnabled = false
                }

            }

            override fun afterTextChanged(s: Editable?) { // Vuoto
            }
        })

        cittaInput.setOnItemClickListener { _, _, position, _ ->
            val selectedCity = adapter.getItem(position)
            if (selectedCity != null) {
                btnSearch.isEnabled = true
                btnSearch.backgroundTintList = getColorStateList(requireContext(), R.color.background_color_dark)
                btnAddFilters.isEnabled = true
            }
        }

        btnSearch.setOnClickListener{
            updateFiltriRicerca(cittaInput)
            findNavController().navigate(R.id.action_ricercaCittaFragment_to_risultatiRicercaFragment)
        }

        btnAddFilters.setOnClickListener{
            updateFiltriRicerca(cittaInput)
            val bundle = Bundle().apply {
                putString("sourceFragment", "RicercaCitta")
            }
            findNavController().navigate(R.id.action_ricercaCittaFragment_to_ricercaFiltriFragment, bundle)
        }

        annullaText.setOnClickListener {
            findNavController().navigate(R.id.action_ricercaCittaFragment_to_ricercaHomeFragment)
        }

        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                findNavController().popBackStack()
            }
        })

    }

    private fun updateFiltriRicerca(cittaInput: AutoCompleteTextView){
        val input = cittaInput.text.toString()
        val selectedCity = cities.find { it.second.equals(input, ignoreCase = true) }
        if (selectedCity != null) {
            val cityGeoPoint = selectedCity.first
            filtriRicercaVM.filtriRicerca.latitudine = cityGeoPoint.latitude
            filtriRicercaVM.filtriRicerca.longitudine = cityGeoPoint.longitude
            Log.d("RicercaCitta", "Città: $input, Latitude: ${cityGeoPoint.latitude}, Longitude: ${cityGeoPoint.longitude}")
        }
    }
}