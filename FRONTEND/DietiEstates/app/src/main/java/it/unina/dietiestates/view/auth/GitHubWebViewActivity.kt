package it.unina.dietiestates.view.auth

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity

class GitHubWebViewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Crea una WebView per caricare l'URL di callback
        val webView = WebView(this)
        webView.layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
        setContentView(webView)

        // Ottieni l'URL di login passato dall'intent
        val url = intent.getStringExtra("url") ?: return
        webView.loadUrl(url)  // Carica l'URL di login

        webView.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                if (url != null && url.startsWith("http://10.0.2.2:8080/api/auth/")) {
                    // Cattura l'URL del callback
                    val resultIntent = Intent()
                    resultIntent.data = Uri.parse(url)  // Imposta l'URL come risultato
                    setResult(RESULT_OK, resultIntent)  // Restituisci l'Intent con il risultato
                    finish()  // Termina questa attività
                    return true
                }
                return false
            }
        }
    }
}



