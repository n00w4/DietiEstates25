package it.unina.dietiestates.view.notifiche

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
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
            Log.d("NotificaDebugger", "${notifica.prenotazione}")
            setupTextViews(notifica)
            setupButtons(notifica)

            titoloTextView.setOnClickListener { openAnnuncioActivity(notifica) }
            accettaBtn.setOnClickListener { handleButtonClick(notifica, true) }
            rifiutaBtn.setOnClickListener { handleButtonClick(notifica, false) }
        }

        private fun setupTextViews(notifica: NotificaConInfo) {
            titoloTextView.text = notifica.annuncio.titolo
            emailTextView.text = context.getString(R.string.titolo_tag, notifica.prenotazione.emailCliente)

            val giorno = notifica.prenotazione.dataInizio.substring(0, 12)
            giornoTextView.text = context.getString(R.string.giorno_tag, giorno)

            val oraInizio = notifica.prenotazione.dataInizio.substring(13, 18)
            val oraFine = notifica.prenotazione.dataFine.substring(13, 18)
            oraTextView.text = context.getString(R.string.inizio_fine_tag, oraInizio, oraFine)
        }

        private fun setupButtons(notifica: NotificaConInfo) {
            when (notifica.prenotazione.accettata) {
                true -> {
                    accettaBtn.text = context.getString(R.string.accettata_notifica)
                    accettaBtn.isEnabled = false
                    rifiutaBtn.isVisible = false
                }
                false -> {
                    rifiutaBtn.text = context.getString(R.string.rifiutata_notifica)
                    rifiutaBtn.isEnabled = false
                    accettaBtn.isVisible = false
                }
                else -> {
                    accettaBtn.isEnabled = true
                    rifiutaBtn.isEnabled = true
                    accettaBtn.isVisible = true
                    rifiutaBtn.isVisible = true
                }
            }
        }

        private fun openAnnuncioActivity(notifica: NotificaConInfo) {
            val intent = Intent(context, AnnuncioActivity::class.java)
            intent.putExtra("ANNUNCIO", notifica.annuncio)
            context.startActivity(intent)
        }

        private fun handleButtonClick(notifica: NotificaConInfo, isAccepted: Boolean) {
            val position = bindingAdapterPosition
            if (position != RecyclerView.NO_POSITION) {
                notifica.prenotazione.accettata = isAccepted
                controller.valutaPrenotazione(notifica.prenotazione) { result ->
                    if (result.isSuccess) {
                        notifyChange(position)
                    } else if (result.isFailure){
                        notifica.prenotazione.accettata = null
                        val error = result.exceptionOrNull()?.message
                        Toast.makeText(context, "Errore: $error", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }

    }
}