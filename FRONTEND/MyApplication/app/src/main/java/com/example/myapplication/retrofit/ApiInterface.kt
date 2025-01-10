package com.example.myapplication.retrofit

import com.example.myapplication.model.Annuncio
import retrofit2.Call
import retrofit2.http.*

interface ApiInterface {

    @GET("annunci/search")
    fun getRicercaAnnunci(@Query("lon") lon: Double,
                          @Query("lat") lat: Double
                        ): Call<List<Annuncio>>

    @GET("annunci/search")
    fun getRicercaAnnunci(@Query("lon") lon: Double,
                          @Query("lat") lat: Double,
                          @Query("prezzoMin") prezzoMin: Double
    ): Call<List<Annuncio>>

}