package it.unina.dietiestates.network.geocoding

import org.locationtech.jts.io.WKBReader
import org.locationtech.jts.geom.Point
import org.osmdroid.util.GeoPoint

class GeoPointParser {

    fun parseWKBToGeoPoint(wkbString: String): GeoPoint? {
        try {
            // Convert hex string to byte array
            val bytes = hexStringToByteArray(wkbString)

            // Create a WKBReader instance
            val wkbReader = WKBReader()

            // Read the WKB bytes into a Point object
            val geometry = wkbReader.read(bytes) as Point

            // Extract latitude (y) and longitude (x)
            val latitude = geometry.y
            val longitude = geometry.x

            // Return GeoPoint
            return GeoPoint(latitude, longitude)
        } catch (e: Exception) {
            e.printStackTrace() // Log the error
            return null
        }
    }

    // Helper function to convert hex string to byte array
    private fun hexStringToByteArray(s: String): ByteArray {
        val len = s.length
        val data = ByteArray(len / 2)
        for (i in 0 until len step 2) {
            data[i / 2] = ((Character.digit(s[i], 16) shl 4)
                    + Character.digit(s[i + 1], 16)).toByte()
        }
        return data
    }

    private fun parseWNTToGeoPoint(positionString: String): GeoPoint? {
        val regex = Regex("""POINT\(([-\d.]+) ([-\d.]+)\)""")
        val matchResult = regex.find(positionString)
        return if (matchResult != null) {
            val (longitude, latitude) = matchResult.destructured
            GeoPoint(latitude.toDouble(), longitude.toDouble())
        } else {
            null // Return null if the format is invalid
        }
    }

}