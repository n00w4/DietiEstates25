package it.unina.dietiestates.view.search

import androidx.appcompat.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import it.unina.dietiestates.R
import it.unina.dietiestates.controller.search.RisultatiRicercaController
import it.unina.dietiestates.data.model.Annuncio
import it.unina.dietiestates.data.viewmodel.FiltriRicercaViewModel
import it.unina.dietiestates.network.geocoding.GeoPointParser
import org.osmdroid.config.Configuration
import org.osmdroid.views.MapView
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.overlay.Marker
import java.util.Locale

class RisultatiRicercaFragment : Fragment() {

    private val filtriRicercaVM: FiltriRicercaViewModel by activityViewModels()
    private lateinit var controller: RisultatiRicercaController
    private lateinit var listaAnnunci: List<Annuncio>
    private lateinit var mapView : MapView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_risultati_ricerca, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        controller = RisultatiRicercaController(requireContext())

        mapView = view.findViewById(R.id.mapView)
        mapView.isVisible = false
        val erroreTextView = view.findViewById<TextView>(R.id.erroreTextView)
        erroreTextView.isVisible = false
        val numAnnunci = view.findViewById<TextView>(R.id.numRisultatiTextView)

        controller.getRisultatiRicerca(filtriRicercaVM.filtriRicerca){ result ->
            if (result.isSuccess) {
                val annunci = result.getOrNull()
                annunci?.let {
                    listaAnnunci = annunci
                    mapView.isVisible = true
                    numAnnunci.setText(String.format(Locale.getDefault(), "%d",annunci.size))
                    initializeMap(mapView)
                }
            }else if (result.isFailure) {
                val error = result.exceptionOrNull()?.message
                mapView.isVisible = false
                erroreTextView.isVisible = true
                erroreTextView.text = "$error"
            }
        }

        val homeTextView = view.findViewById<TextView>(R.id.homeTextView)

        homeTextView.setOnClickListener{
            findNavController().navigate(R.id.action_risultatiRicercaFragment_to_ricercaHomeFragment)
        }

        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                findNavController().popBackStack()
            }
        })
    }

    private fun initializeMap(map: MapView){
        // Inizializza la configurazione di OSMDroid
        val osmConfig = Configuration.getInstance()
        val sharedPreferences = requireContext().getSharedPreferences("osmdroid", Context.MODE_PRIVATE)
        osmConfig.load(requireContext(), sharedPreferences)
        // Configura la mappa
        map.setMultiTouchControls(true)
        map.controller.setZoom(10.0)
        val lat = filtriRicercaVM.filtriRicerca.latitudine ?: 41.9028
        val long = filtriRicercaVM.filtriRicerca.longitudine ?: 12.4964 //Default (Roma)
        map.controller.setCenter(GeoPoint(lat, long))
        val parser = GeoPointParser()
        listaAnnunci.forEach { annuncio ->
            val marker = Marker(map)
            val geopoint = parser.parseWKBToGeoPoint(annuncio.posizione)
            if (geopoint != null){
                marker.position = geopoint
                marker.title = annuncio.titolo
                marker.icon = ContextCompat.getDrawable(requireContext(), R.drawable.map_picker)
                Log.d("DEBUG_MAPPA", "id annuncio: ${annuncio.ID}")
                marker.setOnMarkerClickListener { _, _ ->
                    showAnnuncioPopup(annuncio)
                    true
                }
                map.overlays.add(marker)
            }
        }
    }

    private fun showAnnuncioPopup(annuncio: Annuncio) {
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle(annuncio.titolo)
        builder.setMessage("Descrizione: ${annuncio.descrizione}")
        Log.d("DEBUG_POPUP", "id annuncio: ${annuncio.ID}")
        builder.setPositiveButton("Dettagli") { _, _ ->
            val intent = Intent(requireContext(), AnnuncioActivity::class.java)
            intent.putExtra("ANNUNCIO", annuncio)
            startActivity(intent)
        }
        builder.setNegativeButton("Indietro", null)
        builder.show()
    }

}