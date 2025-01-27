package it.unina.dietiestates.network.geocoding

import android.content.Context
import android.widget.Toast
import org.json.JSONArray
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

class OsmdroidGeocoder {

    fun getAddressFromCoordinates(lat: Double, lon: Double,
                                  context: Context, callback: (String) -> Unit) {
        val url = "https://nominatim.openstreetmap.org/reverse?lat=$lat&lon=$lon&format=json"

        Thread {
            try {
                val connection = URL(url).openConnection() as HttpURLConnection
                connection.requestMethod = "GET"
                connection.connect()

                val streamReader = BufferedReader(InputStreamReader(connection.inputStream))
                val response = streamReader.readText()
                streamReader.close()

                val jsonObject = JSONObject(response)
                val address = jsonObject.getString("display_name")
                callback(address) // Returns the address
            } catch (e: Exception) {
                val address = "Nessun indirizzo corrispondente."
                callback(address)
                e.printStackTrace()
            }
        }.start()
    }


    fun getCoordinatesFromAddress(address: String,
                                  context: Context, callback: (Double?, Double?) -> Unit) {
        val url = "https://nominatim.openstreetmap.org/search?q=${address.replace(" ", "+")}&format=json"

        Thread {
            try {
                val connection = URL(url).openConnection() as HttpURLConnection
                connection.requestMethod = "GET"
                connection.connect()

                val streamReader = BufferedReader(InputStreamReader(connection.inputStream))
                val response = streamReader.readText()
                streamReader.close()

                val jsonArray = JSONArray(response)
                if (jsonArray.length() > 0) {
                    val obj = jsonArray.getJSONObject(0)
                    val lat = obj.getDouble("lat")
                    val lon = obj.getDouble("lon")
                    callback(lat, lon) // Returns latitude and longitude
                } else {
                    callback(null, null)
                }

            } catch (e: Exception) {
                e.printStackTrace()
                callback(null, null)
            }
        }.start()
    }

}