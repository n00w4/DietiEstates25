package it.unina.dietiestates.controller.search

import android.app.Activity
import android.content.Intent
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import it.unina.dietiestates.R
import it.unina.dietiestates.view.search.RicercaFiltriFragment
import it.unina.dietiestates.view.search.RicercaMappaActivity

class RicercaController(
    private val fragment: Fragment,
    private val fragmentManager: FragmentManager
) {

    fun onMapButtonClicked(resultLauncher: (Intent) -> Unit) {
        val intent = Intent(fragment.activity, RicercaMappaActivity::class.java)
        resultLauncher(intent)
    }

    fun handleActivityResult(resultCode: Int, data: Intent?, updateUI: (Double, Double) -> Unit) {
        if (resultCode == Activity.RESULT_OK) {
            val latitude = data?.getDoubleExtra("latitude", 0.0) ?: 0.0
            val longitude = data?.getDoubleExtra("longitude", 0.0) ?: 0.0

            Log.d("RicercaController", "Latitudine: $latitude, Longitudine: $longitude")

            updateUI(latitude, longitude)
        }
    }

    fun onFilterButtonClicked() {
        val ricercaFiltriFragment = it.unina.dietiestates.view.search.RicercaFiltriFragment()
        fragmentManager.beginTransaction()
            .replace(R.id.fragment_container_client, ricercaFiltriFragment)
            .addToBackStack(null)
            .commit()
    }
}
