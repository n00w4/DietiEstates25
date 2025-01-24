package it.unina.dietiestates.network.openmeteo

interface WeatherDataCallback {
    fun onWeatherDataReceived(
        temperature: Double,
        humidity: Double,
        precipitation: Double,
        rain: Double,
        weatherCode: Int
    )

    fun onError(message: String)
}