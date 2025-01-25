package it.unina.dietiestates.controller.search

import it.unina.dietiestates.network.openmeteo.WeatherDataCallback
import it.unina.dietiestates.network.openmeteo.WeatherResponse
import it.unina.dietiestates.network.openmeteo.OpenMeteoInterface
import org.osmdroid.util.GeoPoint
import retrofit2.Retrofit
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.converter.gson.GsonConverterFactory

    //https://api.open-meteo.com/v1/forecast?latitude=41.8919&longitude=12.5113
    // &hourly=temperature_2m,relative_humidity_2m,precipitation_probability,rain,weather_code
    // &timezone=Europe%2FLondon
    // &forecast_days=14

class MeteoPrenotazioneController {

    fun getMeteo(callback: WeatherDataCallback, location: GeoPoint, dateTime: String) {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.open-meteo.com/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val weatherService = retrofit.create(OpenMeteoInterface::class.java)

        val call = weatherService.getWeatherForecast(
            latitude = location.latitude,
            longitude = location.longitude,
            hourly = "temperature_2m,relative_humidity_2m,precipitation_probability,rain,weather_code",
            timezone = "Europe/London",
            forecastDays = 14
        )

        call.enqueue(object : Callback<WeatherResponse> {
            override fun onResponse(call: Call<WeatherResponse>, response: Response<WeatherResponse>) {
                if (response.isSuccessful) {
                    val weatherData = response.body()
                    weatherData?.hourly?.let { hourly ->
                        val specificTime = hourly.time.indexOfFirst { it.startsWith(dateTime.substring(0, 13)) }
                        if (specificTime != -1) {
                            val temperature = hourly.temperature_2m[specificTime]
                            val humidity = hourly.relative_humidity_2m[specificTime]
                            val precipitation = hourly.precipitation_probability[specificTime]
                            val rain = hourly.rain[specificTime]
                            val weatherCode = hourly.weather_code[specificTime]

                            // Notify callback of success
                            callback.onWeatherDataReceived(temperature, humidity,
                                precipitation, rain, weatherCode)
                        } else {
                            callback.onError("Impossibile trovare informazioni per la data inserita.")
                        }
                    }
                } else {
                    callback.onError("Errore dell'API: ${response.errorBody()?.string()}")
                }
            }

            override fun onFailure(call: Call<WeatherResponse>, t: Throwable) {
                callback.onError("Errore di rete: ${t.message}")
            }
        })
    }
}
