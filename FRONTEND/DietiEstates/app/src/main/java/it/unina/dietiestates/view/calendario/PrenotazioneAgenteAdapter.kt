package it.unina.dietiestates.view.calendario

import android.content.Context
import android.content.Intent
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import it.unina.dietiestates.R
import it.unina.dietiestates.data.dto.PrenotazioneConInfo
import it.unina.dietiestates.view.search.AnnuncioActivity

class PrenotazioneAgenteAdapter(private val prenotazioniList: MutableList<PrenotazioneConInfo>, private val context: Context) :
    RecyclerView.Adapter<PrenotazioneAgenteAdapter.PrenotazioneAgenteViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PrenotazioneAgenteViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.fragment_prenotazione_agente, parent, false)
        return PrenotazioneAgenteViewHolder(view, context)
    }

    override fun onBindViewHolder(holder: PrenotazioneAgenteViewHolder, position: Int) {
        val prenotazione = prenotazioniList[position]
        holder.bind(prenotazione)
    }

    override fun getItemCount() = prenotazioniList.size

    fun updateData(newList: List<PrenotazioneConInfo>) {
        prenotazioniList.clear()
        prenotazioniList.addAll(newList)
        notifyDataSetChanged()
    }

    class PrenotazioneAgenteViewHolder(view: View, private val context: Context)
        : RecyclerView.ViewHolder(view) {
        private val titoloTextView: TextView = itemView.findViewById(R.id.annuncioTitolo)
        private val emailTextView: TextView = itemView.findViewById(R.id.utenteTag)
        private val giornoTextView: TextView = itemView.findViewById(R.id.giornoTag)
        private val oraTextView: TextView = itemView.findViewById(R.id.oraTag)

        fun bind(prenotazione: PrenotazioneConInfo) {
            titoloTextView.text = prenotazione.annuncio.titolo
            titoloTextView.setOnClickListener { openAnnuncioActivity(prenotazione) }
            emailTextView.text = context.getString(R.string.utente_tag, prenotazione.prenotazione.emailCliente)

            val giorno = prenotazione.prenotazione.dataInizio.substring(0, 12)
            giornoTextView.text = context.getString(R.string.giorno_tag, giorno)
            val oraInizio = prenotazione.prenotazione.dataInizio.substring(13, 18)
            val oraFine = prenotazione.prenotazione.dataFine.substring(13, 18)
            oraTextView.text = context.getString(R.string.inizio_fine_tag, oraInizio, oraFine)

        }

        private fun openAnnuncioActivity(prenotazione: PrenotazioneConInfo) {
            val intent = Intent(context, AnnuncioActivity::class.java)
            intent.putExtra("ANNUNCIO", prenotazione.annuncio)
            context.startActivity(intent)
        }
    }
}
