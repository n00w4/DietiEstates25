package it.unina.dietiestates.network.openmeteo

data class WeatherResponse(
    val hourly: HourlyData // Maps the `hourly` object in the JSON
)

data class HourlyData(
    val time: List<String>, // List of timestamps (e.g., "2025-01-23T15:00")
    val temperature_2m: List<Double>, // List of temperatures
    val relative_humidity_2m: List<Double>, // List of relative humidity values
    val precipitation_probability: List<Double>, // List of precipitation probabilities
    val rain: List<Double>, // List of rain volumes
    val weather_code: List<Int> // List of weather codes (e.g., 1 for clear skies, etc.)
)
