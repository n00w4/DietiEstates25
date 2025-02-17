package it.unina.dietiestates.view.calendario

import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import it.unina.dietiestates.R
import it.unina.dietiestates.data.dto.PrenotazioneConInfo
import it.unina.dietiestates.view.search.AnnuncioActivity

class PrenotazioneClienteAdapter(private val prenotazioniList: MutableList<PrenotazioneConInfo>, private val context: Context) :
    RecyclerView.Adapter<PrenotazioneClienteAdapter.PrenotazioneClienteViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PrenotazioneClienteViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.fragment_prenotazione_cliente, parent, false)
        return PrenotazioneClienteViewHolder(view, context)
    }

    override fun onBindViewHolder(holder: PrenotazioneClienteViewHolder, position: Int) {
        val prenotazione = prenotazioniList[position]
        holder.bind(prenotazione)
    }

    override fun getItemCount() = prenotazioniList.size

    fun updateData(newList: List<PrenotazioneConInfo>) {
        Log.d("Prenotazione Cliente", "update data CALLED with list of ${newList.size} elements")
        prenotazioniList.clear()
        prenotazioniList.addAll(newList)
        notifyDataSetChanged()
    }

    class PrenotazioneClienteViewHolder(view: View, private val context: Context)
        : RecyclerView.ViewHolder(view) {
        private val titoloTextView: TextView = itemView.findViewById(R.id.annuncioTitolo)
        private val giornoTextView: TextView = itemView.findViewById(R.id.giornoTag)
        private val oraTextView: TextView = itemView.findViewById(R.id.oraTag)
        private val statoPrenotazioneTextView: TextView = itemView.findViewById(R.id.statoPrenotazioneTextView)

        fun bind(prenotazione: PrenotazioneConInfo) {
            Log.d("Prenotazione Cliente", "accettata == ${prenotazione.prenotazione.accettata}")
            titoloTextView.text = prenotazione.annuncio.titolo
            titoloTextView.setOnClickListener { openAnnuncioActivity(prenotazione) }

            val giorno = prenotazione.prenotazione.dataInizio.substring(0, 12)
            giornoTextView.text = context.getString(R.string.giorno_tag, giorno)
            val oraInizio = prenotazione.prenotazione.dataInizio.substring(13, 18)
            val oraFine = prenotazione.prenotazione.dataFine.substring(13, 18)
            oraTextView.text = context.getString(R.string.inizio_fine_tag, oraInizio, oraFine)

            if(prenotazione.prenotazione.accettata == null){
                statoPrenotazioneTextView.text = context.getString(R.string.non_valutata_prenotazione)
                statoPrenotazioneTextView.setTextColor(context.getColor(android.R.color.darker_gray))
            }
            if(prenotazione.prenotazione.accettata == true){
                statoPrenotazioneTextView.text = context.getString(R.string.accettata_notifica)
                statoPrenotazioneTextView.setTextColor(context.getColor(R.color.background_color_dark))
            }
            if(prenotazione.prenotazione.accettata == false){
                statoPrenotazioneTextView.text = context.getString(R.string.rifiutata_notifica)
                statoPrenotazioneTextView.setTextColor(context.getColor(R.color.button_color_red))
            }
        }

        private fun openAnnuncioActivity(prenotazione: PrenotazioneConInfo) {
            val intent = Intent(context, AnnuncioActivity::class.java)
            intent.putExtra("ANNUNCIO", prenotazione.annuncio)
            context.startActivity(intent)
        }
    }
}
