package it.unina.dietiestates.view.search

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import it.unina.dietiestates.R
import it.unina.dietiestates.data.model.Annuncio

class AnnuncioActivity : AppCompatActivity() {

    @SuppressLint("NewApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_annuncio)

        val annuncio = intent.getSerializableExtra("ANNUNCIO") as Annuncio?

        findViewById<TextView>(R.id.titoloTextView).text = annuncio?.titolo
        findViewById<TextView>(R.id.indirizzoTextView).text = annuncio?.indirizzo
        findViewById<TextView>(R.id.descrizioneTextView).text = annuncio?.descrizione

        val annullaTextView = findViewById<TextView>(R.id.annullaTextView)
        annullaTextView.setOnClickListener {
            // Finish this activity and go back to the previous one
            finish()
        }

    }
}