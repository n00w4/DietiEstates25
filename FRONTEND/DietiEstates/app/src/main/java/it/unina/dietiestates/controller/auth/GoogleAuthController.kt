package it.unina.dietiestates.controller.auth

import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import it.unina.dietiestates.view.auth.SignUpActivity

class GoogleAuthController(context: Context, private val task: Task<GoogleSignInAccount>) : AuthController(context) {
    override fun handleAuth() {
        try {
            val account = task.getResult(ApiException::class.java)
            account?.let {
                val email = it.email
                val nome = it.givenName
                val cognome = it.familyName
                val idToken = it.idToken

                if (email != null && idToken != null) {
                    val intent = Intent(context, SignUpActivity::class.java).apply {
                        putExtra("EXTRA_NOME", nome)
                        putExtra("EXTRA_COGNOME", cognome)
                        putExtra("EXTRA_EMAIL", email)
                    }
                    context.startActivity(intent)
                }
            }
        } catch (e: ApiException) {
            Log.e("GoogleSignIn", "Errore durante il login: ${e.message}")
            Toast.makeText(context, "Errore durante il login: ${e.message}", Toast.LENGTH_SHORT).show()
        }
    }
}

