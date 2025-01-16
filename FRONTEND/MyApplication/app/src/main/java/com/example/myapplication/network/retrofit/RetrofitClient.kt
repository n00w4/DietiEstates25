package com.example.myapplication.network.retrofit

import com.example.myapplication.application.MyApplicationDE
import com.example.myapplication.model.data.SharedPrefManager
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private const val BASE_URL = "http://10.0.2.2:8080/api/"

    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor { chain ->
            val token = SharedPrefManager.getToken(MyApplicationDE.appContext!!) ?: ""
            val newRequest = chain.request().newBuilder()
                .apply {
                    if (token.isNotEmpty()) {
                        addHeader("Authorization", "Bearer $token")
                    }
                }
                .build()
            chain.proceed(newRequest)
        }
        .build()

    val instance: ApiInterface by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiInterface::class.java)
    }

}