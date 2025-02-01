package it.unina.dietiestates.view.notifiche

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import it.unina.dietiestates.R
import it.unina.dietiestates.controller.notifiche.NotificheHomeController
import it.unina.dietiestates.data.dto.NotificaConInfo

class NotificheHomeFragment : Fragment(){

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: NotificheAdapter
    private lateinit var erroreTextView: TextView
    private var notificheList: MutableList<NotificaConInfo> = mutableListOf()
    private lateinit var controller: NotificheHomeController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_notifiche_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("NotificheHomeFragment", "onViewCreated called")
        controller = NotificheHomeController(requireContext())

        erroreTextView = view.findViewById(R.id.erroreTextView)
        erroreTextView.isVisible = false

        recyclerView = view.findViewById(R.id.notificheRecyclerView)
        adapter = NotificheAdapter(notificheList, requireContext())
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = adapter

        loadNotifiche()
    }

    private fun loadNotifiche() {
        controller.getNotifiche { result ->
            if (result.isSuccess) {
                val listaResult = result.getOrNull()
                listaResult?.let {
                    notificheList.clear()
                    notificheList.addAll(it) // Update dataset reference
                    adapter.notifyItemRangeInserted(0, notificheList.size)
                }
            } else if (result.isFailure) {
                val error = result.exceptionOrNull()?.message
                erroreTextView.isVisible = true
                erroreTextView.text = "$error"
                Toast.makeText(requireContext(), "Errore: $error", Toast.LENGTH_SHORT).show()
            }
        }
    }


}