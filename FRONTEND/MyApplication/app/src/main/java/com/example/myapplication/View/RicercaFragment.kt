package com.example.myapplication.View

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
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
        val btnFiltri: Button = view.findViewById(R.id.btn_filtri)

        val resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK) {
                val data = result.data
                val latitude = data?.getDoubleExtra("latitude", 0.0) ?: 0.0
                val longitude = data?.getDoubleExtra("longitude", 0.0) ?: 0.0

                // Usa le coordinate
                Log.d("PrimaActivity", "Latitudine: $latitude, Longitudine: $longitude")
                val textLatitudine = view.findViewById<TextView>(R.id.text_latitudine)
                val textLongitudine = view.findViewById<TextView>(R.id.text_longitudine)
                textLatitudine.text = "Latitudine: $latitude"
                textLongitudine.text = "Longitudine: $longitude"
            }
        }

        btnMappa.setOnClickListener {
            val intent = Intent(activity, RicercaMappaActivity::class.java)
            resultLauncher.launch(intent)
        }

        btnFiltri.setOnClickListener {
            val searchResultsFragment = RicercaFiltriFragment()

            parentFragmentManager.beginTransaction()
                .replace(R.id.root_layout, searchResultsFragment)
                .addToBackStack(null) // Optional: Keeps the back stack to return to SearchFragment
                .commit()
        }



    }

}