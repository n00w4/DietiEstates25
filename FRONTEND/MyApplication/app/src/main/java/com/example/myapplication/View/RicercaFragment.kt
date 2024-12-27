package com.example.myapplication.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import com.example.myapplication.controller.RicercaController
import com.example.myapplication.R
import com.example.myapplication.controller.OsmdroidGeocoder

class RicercaFragment : Fragment() {

    private lateinit var controller: RicercaController
    private var geocoder = OsmdroidGeocoder()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_ricerca, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        controller = RicercaController(this, parentFragmentManager)

        val btnMappa: Button = view.findViewById(R.id.btn_map)
        val btnFiltri: Button = view.findViewById(R.id.btn_filtri)
        val textLatitudine = view.findViewById<TextView>(R.id.text_latitudine)
        val textLongitudine = view.findViewById<TextView>(R.id.text_longitudine)
        val textAddress = view.findViewById<TextView>(R.id.text_address)


        val resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            controller.handleActivityResult(result.resultCode, result.data) { latitude, longitude ->
                textLatitudine.text = getString(R.string.latitude_text, latitude)
                textLongitudine.text = getString(R.string.longitude_text, longitude)

                geocoder.getAddressFromCoordinates(latitude, longitude, requireContext()){
                    address -> textAddress.text = getString(R.string.address_text, address)
                    Log.d("Mappa", "Posizione cliccata: lat=${latitude}, long=${longitude}. address ${address}")
                }
            }
        }

        btnMappa.setOnClickListener {
            controller.onMapButtonClicked { intent -> resultLauncher.launch(intent) }
        }

        btnFiltri.setOnClickListener {
            controller.onFilterButtonClicked()
        }
    }
}
