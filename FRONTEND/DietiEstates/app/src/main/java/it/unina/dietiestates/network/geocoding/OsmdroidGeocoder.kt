package it.unina.dietiestates.network.geocoding

import org.json.JSONArray
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

class OsmdroidGeocoder {

    fun getAddressFromCoordinates(lat: Double, lon: Double,
                                  callback: (String) -> Unit) {
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
                                  callback: (Double?, Double?) -> Unit) {
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

    fun getPossibleAddresses(address: String,
                             callback: (List<String>) -> Unit) {
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
                val addresses = mutableListOf<String>()

                for (i in 0 until jsonArray.length()) {
                    val obj = jsonArray.getJSONObject(i)
                    val displayName = obj.getString("display_name")
                    addresses.add(displayName)
                }

                callback(addresses) // Return the list of addresses
            } catch (e: Exception) {
                e.printStackTrace()
                callback(emptyList()) // Return an empty list in case of error
            }
        }.start()
    }


}