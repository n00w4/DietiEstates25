package it.unina.dietiestates.view.crea_annuncio

import android.content.Context
import android.os.Bundle
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
import it.unina.dietiestates.controller.crea_annuncio.CreaAnnuncioDettagliController
import it.unina.dietiestates.data.viewmodel.AnnuncioViewModel
import it.unina.dietiestates.data.viewmodel.CoordinateViewModel
import it.unina.dietiestates.network.geocoding.OsmdroidGeocoder
import org.osmdroid.config.Configuration
import org.osmdroid.events.MapEventsReceiver
import org.osmdroid.tileprovider.tilesource.TileSourceFactory
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.MapEventsOverlay
import org.osmdroid.views.overlay.Marker

class CreaAnnuncioMappaFragment : Fragment(){

    private val controller = CreaAnnuncioDettagliController()
    private val annuncioVM: AnnuncioViewModel by activityViewModels()
    private val coordinateVM: CoordinateViewModel by activityViewModels()
    private lateinit var indirizzoTextView: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_crea_annuncio_mappa, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val mapView = view.findViewById<MapView>(R.id.mapView)
        val selezionaBtn = view.findViewById<Button>(R.id.seleziona_button)
        indirizzoTextView = view.findViewById(R.id.indirizzoTextView)

        // Inizializza la configurazione di OSMDroid
        val osmConfig = Configuration.getInstance()
        val sharedPreferences = requireContext().getSharedPreferences("osmdroid", Context.MODE_PRIVATE)
        osmConfig.load(requireContext(), sharedPreferences)
        // Configura la mappa
        mapView.setTileSource(TileSourceFactory.MAPNIK)
        mapView.setMultiTouchControls(true)
        mapView.controller.setZoom(10.0)
        val startPoint = GeoPoint(41.9028, 12.4964) // Punto iniziale (Roma)
        mapView.controller.setCenter(startPoint)
        // Aggiungi un marker
        val marker = Marker(mapView)
        marker.position = startPoint
        marker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM)
        marker.icon = ContextCompat.getDrawable(requireContext(), R.drawable.transparent_img) //Invisibile fino a quando l'user non clicca sulla mappa per la prima volta
        mapView.overlays.add(marker)
        // Gestisci i click sulla mappa
        val mapEventsOverlay = MapEventsOverlay(object : MapEventsReceiver {
            override fun singleTapConfirmedHelper(p: GeoPoint): Boolean {
                marker.position = p
                marker.icon = ContextCompat.getDrawable(requireContext(), R.drawable.map_picker)
                mapView.invalidate()
                coordinateVM.latitudine = p.latitude
                coordinateVM.longitudine = p.longitude
                mapView.controller.setCenter(p)
                updateIndirizzoTextView()
                return true
            }

            override fun longPressHelper(p: GeoPoint): Boolean = false
        })
        mapView.overlays.add(mapEventsOverlay)

        selezionaBtn.setOnClickListener {
            updatePosizioneAnnuncio()
            findNavController().navigate(R.id.action_creaAnnuncioMappaFragment_to_creaAnnuncioFragment)
        }


        val annullaBtn = view.findViewById<Button>(R.id.annullaButton)
        annullaBtn.setOnClickListener{
            controller.setPosizioneAnnuncioToNull(coordinateVM, annuncioVM)
            findNavController().navigate(R.id.action_creaAnnuncioMappaFragment_to_creaAnnuncioFragment)
        }
        val annullaText = view.findViewById<TextView>(R.id.annullaTextView)
        annullaText.setOnClickListener {
            controller.setPosizioneAnnuncioToNull(coordinateVM, annuncioVM)
            findNavController().navigate(R.id.action_creaAnnuncioMappaFragment_to_creaAnnuncioFragment)
        }
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                findNavController().popBackStack()
            }
        })
    }

    private fun updateIndirizzoTextView() {
        val geocoder = OsmdroidGeocoder()
        coordinateVM.latitudine?.let { lat ->
            coordinateVM.longitudine?.let { lon ->
                geocoder.getAddressFromCoordinates(lat, lon) { address ->
                    activity?.runOnUiThread { indirizzoTextView.text = address }
                }
            }
        }
    }

    private fun updatePosizioneAnnuncio(){
        if(!controller.isCoordinateEmpty(coordinateVM.latitudine, coordinateVM.longitudine)){
            val indirizzo = indirizzoTextView.text.trim().toString()
            controller.savePositionInVM(indirizzo, coordinateVM.latitudine, coordinateVM.longitudine, annuncioVM)
        }else{
            controller.setPosizioneAnnuncioToNull(coordinateVM, annuncioVM)
        }
    }

}