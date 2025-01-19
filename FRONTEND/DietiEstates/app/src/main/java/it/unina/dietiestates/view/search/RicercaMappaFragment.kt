package it.unina.dietiestates.view.search

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import it.unina.dietiestates.R
import it.unina.dietiestates.data.viewmodel.FiltriRicercaViewModel
import it.unina.dietiestates.network.geocoding.CitiesLabelOverlay
import org.osmdroid.config.Configuration
import org.osmdroid.events.MapEventsReceiver
import org.osmdroid.tileprovider.tilesource.TileSourceFactory
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.MapEventsOverlay
import org.osmdroid.views.overlay.Marker

class RicercaMappaFragment: Fragment() {

    private var selectedLatitude: Double = 41.9028
    private var selectedLongitude: Double = 12.4964
    private val filtriRicercaVM: FiltriRicercaViewModel by activityViewModels()
    private var mapView: MapView? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_ricerca_mappa, container, false)
        if (mapView == null) {
            mapView = MapView(requireContext())
            mapView?.id = R.id.mapView
        }
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mapView = view.findViewById(R.id.mapView)
        val btnSaveLocation = view.findViewById<Button>(R.id.btn_save_location)
        val btnAddFilters = view.findViewById<Button>(R.id.btn_add_filters)

        // Inizializza la configurazione di OSMDroid
        val osmConfig = Configuration.getInstance()
        val sharedPreferences = requireContext().getSharedPreferences("osmdroid", Context.MODE_PRIVATE)
        osmConfig.load(requireContext(), sharedPreferences)

        // Configura la mappa
        mapView?.apply {
            setTileSource(TileSourceFactory.MAPNIK)
            setMultiTouchControls(true)
            controller.setZoom(10.0)
            overlays.add(0, CitiesLabelOverlay())

            val startPoint = GeoPoint(selectedLatitude, selectedLongitude) //posizione iniziale di default: Roma
            controller.setCenter(startPoint)

            // Aggiungi il marker di default
            val marker = Marker(this)
            marker.position = startPoint
            marker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM)
            overlays.add(marker)
            invalidate()

            // Sposta il marker a ogni click sulla mappa
            val mapEventsOverlay = MapEventsOverlay(object : MapEventsReceiver {
                override fun singleTapConfirmedHelper(p: GeoPoint): Boolean {
                    marker.position = p
                    invalidate()
                    selectedLatitude = p.latitude
                    selectedLongitude = p.longitude
                    Log.d("Mappa", "Clicked position: lat=$selectedLatitude, long=$selectedLongitude")
                    return true
                }

                override fun longPressHelper(p: GeoPoint): Boolean = false
            })
            overlays.add(mapEventsOverlay)
        }

        btnSaveLocation.setOnClickListener {
            filtriRicercaVM.filtriRicerca.latitudine = selectedLatitude
            filtriRicercaVM.filtriRicerca.longitudine = selectedLongitude

            //vai a risultati.
        }

        btnAddFilters.setOnClickListener{
            filtriRicercaVM.filtriRicerca.latitudine = selectedLatitude
            filtriRicercaVM.filtriRicerca.longitudine = selectedLongitude

            findNavController().navigate(R.id.action_ricercaMappaFragment_to_ricercaFiltriFragment)
        }

        val annullaText = view.findViewById<TextView>(R.id.annullaTextView)
        annullaText.setOnClickListener {
            findNavController().navigate(R.id.action_ricercaMappaFragment_to_ricercaHomeFragment)
        }
    }

    //Funzioni per gestire il caricamento della pagina se si passa a un altro tab
    override fun onResume() {
        super.onResume()
        mapView?.onResume()
    }

    override fun onPause() {
        super.onPause()
        mapView?.onPause()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        mapView = null
    }
}