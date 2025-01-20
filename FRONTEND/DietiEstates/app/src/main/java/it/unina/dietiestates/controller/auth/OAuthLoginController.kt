package it.unina.dietiestates.controller.auth

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.activity.result.ActivityResultLauncher
import it.unina.dietiestates.view.auth.GitHubWebViewActivity

abstract class OAuthLoginController(protected val context: Context) {
    abstract fun login(resultLauncher: ActivityResultLauncher<Intent>)

    abstract fun handleCallback(uri: Uri)

    // Funzione per lanciare la WebView con l'URL
    protected fun commonIntentLogic(resultLauncher: ActivityResultLauncher<Intent>, url: String) {
        val intent = Intent(context, GitHubWebViewActivity::class.java).apply {
            putExtra("url", url)  // Passa l'URL alla WebView
        }
        resultLauncher.launch(intent)  // Avvia la WebViewActivity
    }
}


