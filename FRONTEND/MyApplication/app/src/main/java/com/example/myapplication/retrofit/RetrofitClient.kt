package com.example.myapplication.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

//Client senza Authentication Token!!! Da aggiungere Interceptor.
//Ricorda: all'iniziazione del RetrofitClient l'interceptor avrà bisogno di un context,
// da dare all'inizio della MainActivity.
object RetrofitClient {
    private const val BASE_URL = "http://10.0.2.2:8080/api/"

    val instance: ApiInterface by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiInterface::class.java)
    }

}