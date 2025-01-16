package com.example.myapplication.network.retrofit

import com.example.myapplication.data.model.Annuncio
import com.example.myapplication.data.model.Cliente
import com.example.myapplication.model.data.ApiResponse
import com.example.myapplication.model.data.Credenziali
import com.example.myapplication.model.data.FiltriRicercaAnnunci
import com.example.myapplication.model.data.TokenResponse
import retrofit2.Call
import retrofit2.http.*

interface ApiInterface {

    @POST("register")
    fun register(@Body cliente: Cliente): Call<ApiResponse>

    @POST("auth")
    fun login(@Body credenziali: Credenziali): Call<TokenResponse>

    @GET("annunci/search")
    fun getRicercaAnnunci(@Query("lon") lon: Double,
                          @Query("lat") lat: Double): Call<List<Annuncio>>

    @POST("annunci/search")
    fun getRicercaAnnunci(@Body filters: FiltriRicercaAnnunci): Call<List<Annuncio>>

}