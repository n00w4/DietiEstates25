package it.unina.dietiestates.network.retrofit

import it.unina.dietiestates.data.dto.SharedPrefManager
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    //private const val BASE_URL = "http://10.0.2.2:8080/api/" //"http://192.168.1.108:8080/api/"
    private val BASE_URL = System.getenv("SERVER_ADDRESS") ?: "http://192.168.1.108:8080/api/"

    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor { chain ->
            val token = SharedPrefManager.getToken(it.unina.dietiestates.application.MyApplicationDE.appContext!!) ?: ""
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
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiInterface::class.java)
    }

}