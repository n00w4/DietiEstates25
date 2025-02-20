package it.unina.dietiestates.view.auth

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import android.view.ViewGroup
import android.webkit.WebResourceRequest
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
        webView.loadUrl(url)

        webView.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
                val requestUrl = request?.url?.toString()
                if (requestUrl != null && requestUrl.startsWith("http://dietiestates.htc5a0feg2g0bnem.italynorth.azurecontainer.io:8080/api/v1/auth/")) {
                    // Capture the callback URL
                    val resultIntent = Intent()
                    resultIntent.data = Uri.parse(requestUrl)
                    setResult(RESULT_OK, resultIntent)
                    finish()
                    return true
                }
                return false
            }
        }
    }
}



