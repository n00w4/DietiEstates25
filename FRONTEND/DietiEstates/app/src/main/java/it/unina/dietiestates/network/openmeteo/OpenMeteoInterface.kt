package it.unina.dietiestates.network.openmeteo

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface OpenMeteoInterface {
    @GET("forecast")
    fun getWeatherForecast(@Query("latitude") latitude: Double, @Query("longitude") longitude: Double,
        @Query("hourly") hourly: String, @Query("timezone") timezone: String,
        @Query("forecast_days") forecastDays: Int): Call<WeatherResponse>
}
