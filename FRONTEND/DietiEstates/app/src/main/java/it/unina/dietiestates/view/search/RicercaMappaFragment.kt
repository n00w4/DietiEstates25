package it.unina.dietiestates.view.search

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import it.unina.dietiestates.R
import it.unina.dietiestates.data.viewmodel.FiltriRicercaViewModel
import it.unina.dietiestates.network.geocoding.CitiesLabelOverlay
import it.unina.dietiestates.network.geocoding.OsmdroidGeocoder
import org.osmdroid.config.Configuration
import org.osmdroid.events.MapEventsReceiver
import org.osmdroid.tileprovider.tilesource.TileSourceFactory
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.MapEventsOverlay
import org.osmdroid.views.overlay.Marker
import org.osmdroid.util.GeoPoint

class RicercaMappaFragment: Fragment() {

    private val filtriRicercaVM: FiltriRicercaViewModel by activityViewModels()
    private var selectedLatitude: Double = 41.9028
    private var selectedLongitude: Double = 12.4964 //punto iniziale di default (Roma)
    private lateinit var indirizzoTextView: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_ricerca_mappa, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val mapView = view.findViewById<MapView>(R.id.mapView)
        val btnSaveLocation = view.findViewById<Button>(R.id.btn_save_location)
        val btnAddFilters = view.findViewById<Button>(R.id.btn_add_filters)
        indirizzoTextView = view.findViewById(R.id.indirizzoTextView)

        // Inizializza la configurazione di OSMDroid
        val osmConfig = Configuration.getInstance()
        val sharedPreferences = requireContext().getSharedPreferences("osmdroid", Context.MODE_PRIVATE)
        osmConfig.load(requireContext(), sharedPreferences)
        // Configura la mappa
        mapView.setTileSource(TileSourceFactory.MAPNIK)
        mapView.setMultiTouchControls(true)
        mapView.controller.setZoom(10.0)
        mapView.overlays.add(0, CitiesLabelOverlay())
        val startPoint = GeoPoint(selectedLatitude, selectedLongitude) // Punto iniziale (Roma)
        mapView.controller.setCenter(startPoint)
        // Aggiungi un marker
        val marker = Marker(mapView)
        marker.position = startPoint
        marker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM)
        marker.icon = ContextCompat.getDrawable(requireContext(), R.drawable.map_picker)
        mapView.overlays.add(marker)
        // Gestisci i click sulla mappa
        val mapEventsOverlay = MapEventsOverlay(object : MapEventsReceiver {
            override fun singleTapConfirmedHelper(p: GeoPoint): Boolean {
                marker.position = p
                mapView.invalidate()
                selectedLatitude = p.latitude
                selectedLongitude = p.longitude
                mapView.controller.setCenter(p)
                updateLocationTextView()
                return true
            }

            override fun longPressHelper(p: GeoPoint): Boolean = false
        })
        mapView.overlays.add(mapEventsOverlay)

        btnSaveLocation.setOnClickListener {
            updateFiltriRicerca()
            findNavController().navigate(R.id.action_ricercaMappaFragment_to_risultatiRicercaFragment)
        }

        btnAddFilters.setOnClickListener{
            updateFiltriRicerca()
            val bundle = Bundle().apply {
                putString("sourceFragment", "RicercaMappa")
            }
            findNavController().navigate(R.id.action_ricercaMappaFragment_to_ricercaFiltriFragment, bundle)
        }

        val annullaText = view.findViewById<TextView>(R.id.annullaTextView)
        annullaText.setOnClickListener {
            findNavController().navigate(R.id.action_ricercaMappaFragment_to_ricercaHomeFragment)
        }

        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                findNavController().popBackStack()
            }
        })
    }

    private fun updateLocationTextView (){
        val geocoder = OsmdroidGeocoder()
        geocoder.getAddressFromCoordinates(selectedLatitude, selectedLongitude){
            address -> indirizzoTextView.text = address
        }
    }

    private fun updateFiltriRicerca(){
        filtriRicercaVM.filtriRicerca.latitudine = selectedLatitude
        filtriRicercaVM.filtriRicerca.longitudine = selectedLongitude
        Log.d("FiltriRicercaVM", "Filtri ricerca: lat=${filtriRicercaVM.filtriRicerca.latitudine}," +
                " long=${filtriRicercaVM.filtriRicerca.longitudine}")
    }

}