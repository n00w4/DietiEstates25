package it.unina.dietiestates.network.geocoding

import org.locationtech.jts.geom.Coordinate
import org.locationtech.jts.geom.GeometryFactory
import org.locationtech.jts.io.WKBReader
import org.locationtech.jts.geom.Point
import org.locationtech.jts.io.WKBWriter
import org.osmdroid.util.GeoPoint

class GeoPointParser {

    fun parseWKBToGeoPoint(wkbString: String?): GeoPoint? {
        try {
            // Convert hex string to byte array
            val bytes = wkbString?.let { hexStringToByteArray(it) }

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

    fun parseCoordinatesToWKB(latitude: Double, longitude: Double): String {
        return try {
            // Create a GeometryFactory instance
            val geometryFactory = GeometryFactory()

            // Create a Point object with the given latitude and longitude
            val point = geometryFactory.createPoint(Coordinate(longitude, latitude))

            // Create a WKBWriter instance
            val wkbWriter = WKBWriter()

            // Write the Point geometry to WKB
            val wkbBytes = wkbWriter.write(point)

            // Convert the byte array to a hex string
            byteArrayToHexString(wkbBytes)
        } catch (e: Exception) {
            e.printStackTrace()
            ""
        }
    }

    // Utility function to convert a byte array to a hex string
    private fun byteArrayToHexString(bytes: ByteArray): String {
        return bytes.joinToString("") { String.format("%02X", it) }
    }

}