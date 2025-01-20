package it.unina.dietiestates.network.retrofit

import it.unina.dietiestates.data.model.Annuncio
import it.unina.dietiestates.data.model.Cliente
import it.unina.dietiestates.model.data.ApiResponse
import it.unina.dietiestates.model.data.Credenziali
import it.unina.dietiestates.model.data.FiltriRicercaAnnunci
import it.unina.dietiestates.model.data.TokenResponse
import retrofit2.Call
import retrofit2.http.*

interface ApiInterface {

    @POST("register")
    fun register(@Body cliente: Cliente): Call<ApiResponse>

    @POST("auth")
    fun login(@Body credenziali: Credenziali): Call<TokenResponse>

    @GET("auth/github/callback")
    fun gitHubCallback(@Query("code") code: String?): Call<ApiResponse>

    @GET("annunci/search")
    fun getRicercaAnnunci(@Query("lon") lon: Double,
                          @Query("lat") lat: Double): Call<List<Annuncio>>

    @POST("annunci/search")
    fun getRicercaAnnunci(@Body filters: FiltriRicercaAnnunci): Call<List<Annuncio>>

}