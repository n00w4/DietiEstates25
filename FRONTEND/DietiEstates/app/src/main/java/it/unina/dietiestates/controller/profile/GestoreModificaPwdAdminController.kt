package it.unina.dietiestates.controller.profile

import android.content.Context
import android.widget.Toast
import it.unina.dietiestates.data.dto.ApiResponse
import it.unina.dietiestates.data.dto.ChangeAdminPwdForm
import it.unina.dietiestates.data.dto.SharedPrefManager
import it.unina.dietiestates.network.retrofit.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GestoreModificaPwdAdminController (private val context: Context){

    fun changeAdminPwd(oldPwd: String, newPwd: String) {
        val emailGestore = SharedPrefManager.getUserEmail(context)
        val partitaIVAGestore = SharedPrefManager.getPartitaIva(context)

        val form = ChangeAdminPwdForm(oldPwd, newPwd, emailGestore!!, partitaIVAGestore!!)

        val api = RetrofitClient.instance

        api.updateAdminPassword(form).enqueue(object : Callback<ApiResponse> {
            override fun onResponse(call: Call<ApiResponse>, response: Response<ApiResponse>) {
                when (response.code()) {
                    200 -> {
                        val message = response.body()?.message
                        if (message != null) {
                            Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
                        }else{
                            Toast.makeText(context, "Password modificata con successo.", Toast.LENGTH_SHORT).show()
                        }
                    }
                    400 -> {
                        Toast.makeText(context, "I dati inseriti non sono al momento validi. Riprova più tardi.", Toast.LENGTH_SHORT).show()
                    }
                    else -> {
                        Toast.makeText(context, "Errore codice ${response.code()}: riprova più tardi", Toast.LENGTH_SHORT).show()
                    }
                }
            }

            override fun onFailure(call: Call<ApiResponse>, t: Throwable) {
                Toast.makeText(context, "Errore durante la connessione al server: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }
}