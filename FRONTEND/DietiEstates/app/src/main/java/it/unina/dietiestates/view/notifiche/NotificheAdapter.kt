package it.unina.dietiestates.view.notifiche

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import it.unina.dietiestates.R
import it.unina.dietiestates.controller.notifiche.NotificaController
import it.unina.dietiestates.data.dto.NotificaConInfo
import it.unina.dietiestates.view.search.AnnuncioActivity
class NotificheAdapter (private val notificheList: MutableList<NotificaConInfo>, private val context: Context) :
    RecyclerView.Adapter<NotificheAdapter.NotificaViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotificaViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.fragment_notifica, parent, false)
        return NotificaViewHolder(view, context){ position ->
            notifyItemChanged(position)
        }
    }

    override fun onBindViewHolder(holder: NotificaViewHolder, position: Int) {
        val notifica = notificheList[position]
        holder.bind(notifica)
    }

    override fun getItemCount(): Int {
        return notificheList.size
    }

    // ViewHolder to display each item in the list
    class NotificaViewHolder(
        itemView: View,
        private val context: Context,
        private val notifyChange: (Int) -> Unit
    ) : RecyclerView.ViewHolder(itemView) {
        private val titoloTextView: TextView = itemView.findViewById(R.id.annuncioTitolo)
        private val emailTextView: TextView = itemView.findViewById(R.id.utenteTag)
        private val giornoTextView: TextView = itemView.findViewById(R.id.giornoTag)
        private val oraTextView: TextView = itemView.findViewById(R.id.oraTag)
        private val accettaBtn: Button = itemView.findViewById(R.id.accettaButton)
        private val rifiutaBtn: Button = itemView.findViewById(R.id.rifiutaButton)
        private val controller = NotificaController(context)

        fun bind(notifica: NotificaConInfo) {
            Log.d("NotifichaViewHolder", "bind called")
            titoloTextView.text = notifica.annuncio.titolo
            emailTextView.text = context.getString(R.string.titolo_tag, notifica.prenotazione.emailCliente)
            val giorno = notifica.prenotazione.dataInizio.substring(0, 12)
            giornoTextView.text = context.getString(R.string.giorno_tag, giorno)
            val oraInizio = notifica.prenotazione.dataInizio.substring(13, 18)
            val oraFine = notifica.prenotazione.dataFine.substring(13, 18)
            oraTextView.text = context.getString(R.string.inizio_fine_tag, oraInizio, oraFine)

            if (notifica.prenotazione.isAccettata == true) {
                accettaBtn.text = context.getString(R.string.accettata_notifica)
                accettaBtn.isEnabled = false
                rifiutaBtn.isVisible = false
            }
            if (notifica.prenotazione.isAccettata == false){
                rifiutaBtn.text = context.getString(R.string.rifiutata_notifica)
                rifiutaBtn.isEnabled = false
                accettaBtn.isVisible = false
            }

            titoloTextView.setOnClickListener{
                val intent = Intent(context, AnnuncioActivity::class.java)
                intent.putExtra("ANNUNCIO", notifica.annuncio)
                context.startActivity(intent)
            }

            accettaBtn.setOnClickListener {
                val position = bindingAdapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    //richiesta al server di accettare la prenotazione, poi chiama notifyChange per modificare i button del fragment
                    notifica.prenotazione.isAccettata = true
                    notifyChange(position)
                }
            }

            rifiutaBtn.setOnClickListener {
                val position = bindingAdapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    //richiesta al server di rifiutare la prenotazione, poi chiama notifyChange per modificare i button del fragment
                    notifica.prenotazione.isAccettata = false
                    notifyChange(position)
                }
            }
        }
    }
}