package it.unina.dietiestates.network.retrofit

import it.unina.dietiestates.data.dto.AddUtenteForm
import it.unina.dietiestates.data.model.Annuncio
import it.unina.dietiestates.data.model.Cliente
import it.unina.dietiestates.data.dto.ApiResponse
import it.unina.dietiestates.data.dto.ChangeAdminPwdForm
import it.unina.dietiestates.data.dto.Credenziali
import it.unina.dietiestates.data.dto.FiltriRicercaAnnunci
import it.unina.dietiestates.data.dto.NotificaConInfo
import it.unina.dietiestates.data.dto.PrenotazioneConInfo
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

    @PATCH("prenotazione/update-status")
    fun valutaPrenotazione(@Body prenotazione: Prenotazione): Call<ApiResponse>

    @GET("prenotazione/get-prenotazioni-accettate")
    fun getPrenotazioniAccettate(@Query("emailAgente") emailAgente: String): Call<MutableList<PrenotazioneConInfo>>

    @GET("prenotazione/get-prenotazioni-utente")
    fun getPrenotazioniCliente(@Query("emailUtente") emailUtente: String): Call<MutableList<PrenotazioneConInfo>>

    @GET("notifiche/get-all")
    fun getNotifiche(@Query("emailAgente") emailAgente: String): Call<MutableList<NotificaConInfo>>

    @POST("gestore/update-admin-password")
    fun updateAdminPassword(@Body form: ChangeAdminPwdForm): Call<ApiResponse>

    @POST("gestore/add-utente")
    fun addUtente(@Body form: AddUtenteForm): Call<ApiResponse>

    @POST("annunci/insert")
    fun insertAnnuncio(@Body annuncio: Annuncio): Call<ApiResponse>
}