package com.example.myapplication.retrofit

import com.example.myapplication.model.Annuncio
import com.example.myapplication.model.Cliente
import com.example.myapplication.model.data.Credenziali
import com.example.myapplication.model.data.FiltriRicercaAnnunci
import com.example.myapplication.model.data.TokenResponse
import retrofit2.Call
import retrofit2.http.*

interface ApiInterface {

    @POST("register")
    fun register(@Body cliente: Cliente): Call<String>

    @POST("auth")
    fun login(@Body credenziali: Credenziali): Call<TokenResponse>

    @GET("annunci/search")
    fun getRicercaAnnunci(@Query("lon") lon: Double,
                          @Query("lat") lat: Double): Call<List<Annuncio>>

    @POST("annunci/search")
    fun getRicercaAnnunci(@Body filters: FiltriRicercaAnnunci): Call<List<Annuncio>>

}