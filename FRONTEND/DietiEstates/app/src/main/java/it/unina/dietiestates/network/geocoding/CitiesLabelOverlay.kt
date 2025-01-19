package it.unina.dietiestates.network.geocoding

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Point
import it.unina.dietiestates.data.dto.cities
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.Overlay

class CitiesLabelOverlay : Overlay() {

    private val paint = Paint().apply {
        color = Color.BLACK          // Text color
        textSize = 40f               // Desired text size
        isAntiAlias = true
    }

    override fun draw(canvas: Canvas, mapView: MapView, shadow: Boolean) {
        val projection = mapView.projection

        for ((geoPoint, name) in cities) {
            val point = Point()
            projection.toPixels(geoPoint, point)

            canvas.drawText(name, point.x.toFloat(), point.y.toFloat(), paint)
        }
    }
}
