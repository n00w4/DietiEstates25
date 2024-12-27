package com.example.myapplication.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.R
import org.osmdroid.config.Configuration
import org.osmdroid.tileprovider.tilesource.TileSourceFactory
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.Marker
import org.osmdroid.views.overlay.MapEventsOverlay
import org.osmdroid.events.MapEventsReceiver

class RicercaMappaActivity : AppCompatActivity() {
    private lateinit var mapView: MapView
    private lateinit var btnSaveLocation: Button
    private var selectedLatitude: Double = 41.9028
    private var selectedLongitude: Double = 12.4964

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inizializza la configurazione di OSMDroid
        val osmConfig = Configuration.getInstance()
        val sharedPreferences = getSharedPreferences("osmdroid", Context.MODE_PRIVATE)
        osmConfig.load(applicationContext, sharedPreferences)

        setContentView(R.layout.ricerca_mappa)

        mapView = findViewById(R.id.mapView)
        btnSaveLocation = findViewById(R.id.btn_save_location)

        // Configura la mappa
        mapView.setTileSource(TileSourceFactory.MAPNIK)
        mapView.setMultiTouchControls(true)
        mapView.controller.setZoom(10.0)

        val startPoint = GeoPoint(selectedLatitude, selectedLongitude) // Punto iniziale (Roma)
        mapView.controller.setCenter(startPoint)

        // Aggiungi un marker
        val marker = Marker(mapView)
        marker.position = startPoint
        marker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM)
        mapView.overlays.add(marker)

        // Gestisci i click sulla mappa
        val mapEventsOverlay = MapEventsOverlay(object : MapEventsReceiver {
            override fun singleTapConfirmedHelper(p: GeoPoint): Boolean {
                marker.position = p
                mapView.invalidate()
                selectedLatitude = p.latitude
                selectedLongitude = p.longitude
                Log.d("Mappa", "Posizione cliccata: lat=${p.latitude}, long=${p.longitude}")
                return true
            }

            override fun longPressHelper(p: GeoPoint): Boolean = false
        })
        mapView.overlays.add(mapEventsOverlay)

        // Salva la posizione
        btnSaveLocation.setOnClickListener {
            val resultIntent = Intent()
            resultIntent.putExtra("latitude", selectedLatitude)
            resultIntent.putExtra("longitude", selectedLongitude)
            setResult(RESULT_OK, resultIntent)
            finish()
        }
    }
}
