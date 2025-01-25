package it.unina.dietiestates.view.search

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.bumptech.glide.Glide
import it.unina.dietiestates.R
import it.unina.dietiestates.data.dto.SharedPrefManager
import it.unina.dietiestates.data.model.Annuncio

class AnnuncioActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_annuncio)

        val annuncio = intent.getSerializableExtra("ANNUNCIO") as Annuncio?
        Log.d("DEBUG_ANNUNCIO", "id annuncio: ${annuncio?.id}")

        val immagine = findViewById<ImageView>(R.id.imageView)
        //Recupera immagine (LOCALE)
        val baseUrl = "http://192.168.1.108:8080/images/"
        val imageUrl = baseUrl + (annuncio?.immagine ?: "")
        Glide.with(this).load(imageUrl)
            .placeholder(R.drawable.no_image).error(R.drawable.no_image).into(immagine)

        findViewById<TextView>(R.id.titoloTextView).text = annuncio?.titolo
        findViewById<TextView>(R.id.tipoAnnuncioTextView).text = getString(R.string.tipo_annuncio, annuncio?.tipoAnnuncio)
        findViewById<TextView>(R.id.indirizzoTextView).text = annuncio?.indirizzo
        findViewById<TextView>(R.id.descrizioneTextView).text = annuncio?.descrizione
        findViewById<TextView>(R.id.dimensioniTextView).text = annuncio?.dimensioni.toString()
        findViewById<TextView>(R.id.prezzoTextView).text = annuncio?.prezzo.toString()
        findViewById<TextView>(R.id.pianoTextView).text = annuncio?.piano
        findViewById<TextView>(R.id.stanzeTextView).text = annuncio?.numeroStanze.toString()
        findViewById<TextView>(R.id.classeTextView).text = annuncio?.classeEnergetica
        findViewById<TextView>(R.id.ascensoreTextView).text = if (annuncio?.ascensore == true) " Si" else " No"
        findViewById<TextView>(R.id.portineriaTextView).text = if (annuncio?.portineria == true) " Si" else " No"
        findViewById<TextView>(R.id.climatizzazioneTextView).text = if (annuncio?.climatizzazione == true) " Si" else " No"
        findViewById<TextView>(R.id.boxAutoTextView).text = if (annuncio?.boxAuto == true) " Si" else " No"
        findViewById<TextView>(R.id.terrazzoTextView).text = if (annuncio?.terrazzo == true) " Si" else " No"
        findViewById<TextView>(R.id.giardinoTextView).text = if (annuncio?.giardino == true) " Si" else " No"
        findViewById<TextView>(R.id.emailAgenteTextView).text = getString(R.string.more_info, annuncio?.emailAgente)

        val prenotaBtn = findViewById<Button>(R.id.prenotaButton)
        if (SharedPrefManager.getUserRole(this) != "Cliente" ) { prenotaBtn.isVisible = false}
        prenotaBtn.setOnClickListener(){
            val intent = Intent(this, PrenotazioneAnnuncioActivity::class.java)
            intent.putExtra("id_annuncio", annuncio?.id)
            intent.putExtra("titolo_annuncio", annuncio?.titolo)
            intent.putExtra("posizione_annuncio", annuncio?.posizione)
            startActivity(intent)
        }

        val annullaTextView = findViewById<TextView>(R.id.annullaTextView)
        annullaTextView.setOnClickListener {
            finish()    // Finish this activity and go back to the previous one
        }
    }
}