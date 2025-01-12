package com.example.myapplication.retrofit

import com.example.myapplication.model.Annuncio
import com.example.myapplication.model.Cliente
import com.example.myapplication.model.data.FiltriRicercaAnnunci
import retrofit2.Call
import retrofit2.http.*

interface ApiInterface {

    @GET("annunci/search")
    fun getRicercaAnnunci(@Query("lon") lon: Double,
                          @Query("lat") lat: Double): Call<List<Annuncio>>

    @POST("annunci/search")
    fun getRicercaAnnunci(@Body filters: FiltriRicercaAnnunci): Call<List<Annuncio>>

    @POST("register")
    fun register(@Body cliente: Cliente): Call<String>

}