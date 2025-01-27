package it.unina.dietiestates.network.retrofit

import it.unina.dietiestates.data.model.Annuncio
import it.unina.dietiestates.data.model.Cliente
import it.unina.dietiestates.data.dto.ApiResponse
import it.unina.dietiestates.data.dto.ChangeAdminPwdForm
import it.unina.dietiestates.data.dto.Credenziali
import it.unina.dietiestates.data.dto.FiltriRicercaAnnunci
import it.unina.dietiestates.data.dto.TokenResponse
import it.unina.dietiestates.data.model.Prenotazione
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
    fun getRicercaAnnunci(@Query("longitude") lon: Double?,
                          @Query("latitude") lat: Double?): Call<List<Annuncio>>

    @POST("annunci/search")
    fun getRicercaAnnunci(@Body filters: FiltriRicercaAnnunci): Call<List<Annuncio>>

    @POST("prenotazione/insert")
    fun insertPrenotazione(@Body prenotazione: Prenotazione): Call<ApiResponse>

    @POST("gestore/updateAdminPassword")
    fun updateAdminPassword(@Body form: ChangeAdminPwdForm): Call<ApiResponse>

    @POST("annunci/insert")
    fun insertAnnuncio(@Body annuncio: Annuncio): Call<ApiResponse>
}