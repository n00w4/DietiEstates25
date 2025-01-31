package it.unina.dietiestates.view.notifiche

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import it.unina.dietiestates.R
import it.unina.dietiestates.data.dto.NotificaConInfo
import it.unina.dietiestates.data.model.Annuncio
import it.unina.dietiestates.data.model.Notifica
import it.unina.dietiestates.data.model.Prenotazione

class NotificheHomeFragment : Fragment(){

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: NotificheAdapter
    private val notificheList = mutableListOf<NotificaConInfo>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_notifiche_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("NotificheHomeFragment", "onViewCreated called")

        recyclerView = view.findViewById(R.id.notificheRecyclerView)
        adapter = NotificheAdapter(notificheList, requireContext())
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = adapter

        // Load the appointment requests (data source)
        loadNotifiche()
    }

    private fun loadNotifiche() {
        // Example: Adding some test data
        val annuncio = Annuncio(0, "Casa Blu", "via 123, Napoli, Italia", "no_image", "casa accogliente",
            100, 10000.0f, "piano terra", 3, "A", true, false, true,
            false, true, false)
        val prenotazione = Prenotazione("Feb 02, 2025 10:00:00", "Feb 02, 2025 11:00:00", null,
            "sara.verdi@gmail.it", 0)
        val notifica = Notifica(java.sql.Timestamp(System.currentTimeMillis()), "agente1@example.com", 0)

        val startPosition = notificheList.size
        notificheList.add(NotificaConInfo(notifica, prenotazione, annuncio))
        notificheList.add(NotificaConInfo(notifica, prenotazione, annuncio))
        notificheList.add(NotificaConInfo(notifica, prenotazione, annuncio))

        adapter.notifyItemRangeInserted(startPosition, 3)
    }

}