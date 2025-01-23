package it.unina.dietiestates.controller.auth

import android.content.Context
import android.util.Log
import android.widget.Toast
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import it.unina.dietiestates.data.dto.SharedPrefManager

class GoogleLoginController(context: Context, private val task: Task<GoogleSignInAccount>) : LoginController(context) {
    override fun handleLogin() {
        try {
            val account = task.getResult(ApiException::class.java)
            account?.let {
                val email = it.email
                val nome = it.givenName
                val cognome = it.familyName
                val idToken = it.idToken
                val tipoUtente = "Cliente"

                if (email != null && idToken != null) {
                    Toast.makeText(context, "Autenticato con Google: $email", Toast.LENGTH_SHORT).show()
                    SharedPrefManager.saveToken(context, idToken)
                    salvaDatiUtente(nome, cognome, email, tipoUtente)
                    scegliHomePage(tipoUtente)
                }
            }
        } catch (e: ApiException) {
            Log.e("GoogleSignIn", "Errore durante il login: ${e.message}")
            Toast.makeText(context, "Errore durante il login: ${e.message}", Toast.LENGTH_SHORT).show()
        }
    }
}

