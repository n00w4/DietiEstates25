package it.unina.dietiestates

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import it.unina.dietiestates.controller.auth.GitHubAuthController
import it.unina.dietiestates.controller.auth.GoogleAuthController
import it.unina.dietiestates.controller.auth.DietiAuthController
import it.unina.dietiestates.data.dto.Credenziali
import it.unina.dietiestates.view.auth.GitHubWebViewActivity
import it.unina.dietiestates.view.auth.SignUpActivity

class MainActivity : AppCompatActivity() {
    private lateinit var gitHubResultLauncher: ActivityResultLauncher<Intent>
    private lateinit var googleSignInLauncher: ActivityResultLauncher<Intent>
    private lateinit var googleSignInClient: GoogleSignInClient
    private lateinit var dietiAuthController: DietiAuthController
    private lateinit var gitHubAuthController: GitHubAuthController
    private lateinit var googleAuthController: GoogleAuthController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        configureGoogleSignIn()

        val textRegistrati = findViewById<TextView>(R.id.textRegistrati)
        val btnAccedi = findViewById<Button>(R.id.accedi_btn)
        val emailEditText = findViewById<EditText>(R.id.editTextEmail)
        val passwordEditText = findViewById<EditText>(R.id.editTextPassword)

        textRegistrati.setOnClickListener {
            val signUpPage = Intent(this, SignUpActivity::class.java)
            startActivity(signUpPage)
        }

        btnAccedi.setOnClickListener {
            val email = emailEditText.text.toString().trim()
            val password = passwordEditText.text.toString().trim()

            if ( email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Compilare tutti i campi prima di procedere.", Toast.LENGTH_SHORT).show()
            } else {
                val credenziali = Credenziali(email, password)
                dietiAuthController = DietiAuthController(this, credenziali)
                dietiAuthController.handleAuth()
            }
        }

        val gitHubLoginBtn = findViewById<ImageView>(R.id.imageViewGitHub)
        val googleLoginBtn = findViewById<ImageView>(R.id.imageViewGoogle)

        gitHubResultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
            if (result.resultCode == RESULT_OK) {
                val uri = result.data?.data
                uri?.let {
                    gitHubAuthController = GitHubAuthController(this, uri)
                    gitHubAuthController.handleAuth()
                }
            }
        }
        googleSignInLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
            googleAuthController = GoogleAuthController(this, task)
            googleAuthController.handleAuth()
        }

        gitHubLoginBtn.setOnClickListener{
            launchGitHubIntent(gitHubResultLauncher)
        }

        googleLoginBtn.setOnClickListener{
            val signInIntent = googleSignInClient.signInIntent
            googleSignInLauncher.launch(signInIntent)
        }
    }
    private fun configureGoogleSignIn() {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestProfile()
            .requestEmail()
            .requestIdToken("112156935328-gd61v5j6q70h3idigvpn4v5cgnbi0id1.apps.googleusercontent.com")
            .build()

        googleSignInClient = GoogleSignIn.getClient(this, gso)
    }
    private fun launchGitHubIntent(resultLauncher: ActivityResultLauncher<Intent>) {
        val url = "http://10.0.2.2:8080/api/v1/auth/github/"
        val intent = Intent(this, GitHubWebViewActivity::class.java).apply {
            putExtra("url", url)  // Passa l'URL alla WebView
        }
        resultLauncher.launch(intent)  // Avvia la WebViewActivity
    }
}