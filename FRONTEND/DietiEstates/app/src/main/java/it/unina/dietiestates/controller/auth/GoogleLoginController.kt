package it.unina.dietiestates.controller.auth

import android.content.Context
import android.content.Intent
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

    fun configureGoogleSignIn() {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestProfile()
            .requestEmail()
            .requestIdToken("112156935328-gd61v5j6q70h3idigvpn4v5cgnbi0id1.apps.googleusercontent.com")
            .build()

        googleSignInClient = GoogleSignIn.getClient(context, gso)
    }

    fun getSignInIntent(): Intent {
        return googleSignInClient.signInIntent
    }

    fun handleSignInResult(task: Task<GoogleSignInAccount>) {
        try {
            val account = task.getResult(ApiException::class.java)
            account?.let {
                val email = it.email
                val idToken = it.idToken

                if (email != null && idToken != null) {
                    Toast.makeText(context, "Autenticato con Google: $email", Toast.LENGTH_SHORT).show()
                }
            }
        } catch (e: ApiException) {
            Log.e("GoogleSignIn", "Errore durante il login: ${e.message}")
            Toast.makeText(context, "Errore durante il login: ${e.message}", Toast.LENGTH_SHORT).show()
        }
    }
}

