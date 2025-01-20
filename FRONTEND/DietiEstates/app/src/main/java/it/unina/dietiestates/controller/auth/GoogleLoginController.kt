package it.unina.dietiestates.controller.auth

import android.app.Activity
import android.content.Context
import android.util.Log
import android.widget.Toast
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task

class GoogleLoginController(private val context: Context) {
    private lateinit var googleSignInClient: GoogleSignInClient

    companion object {
        const val RC_SIGN_IN = 100 // Codice di richiesta per l'Activity Result
    }

    fun configureGoogleSignIn() {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .requestIdToken("112156935328-gd61v5j6q70h3idigvpn4v5cgnbi0id1.apps.googleusercontent.com")
            .build()

        googleSignInClient = GoogleSignIn.getClient(context, gso)
    }

    fun login(activity: Activity) {
        val signInIntent = googleSignInClient.signInIntent
        activity.startActivityForResult(signInIntent, RC_SIGN_IN)
    }

    fun handleSignInResult(task: Task<GoogleSignInAccount>) {
        try {
            val account = task.getResult(ApiException::class.java)

            val email = account?.email
            val idToken = account?.idToken
            Log.d("GoogleSignIn", "Email: $email, ID Token: $idToken")

            email?.let {
                Toast.makeText(context, "Autenticato con Google: $it", Toast.LENGTH_SHORT).show()
                // TODO: gestire istanza utente e salvare dati in SharedPreferences se utile
            }

        } catch (e: ApiException) {
            Log.e("GoogleSignIn", "Errore durante il login: ${e.message}")
            Toast.makeText(context, "Errore durante il login: ${e.message}", Toast.LENGTH_SHORT).show()
        }
    }
}

