package it.unina.dietiestates.view.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import it.unina.dietiestates.R
import it.unina.dietiestates.data.viewmodel.FiltriRicercaViewModel

class RisultatiRicercaFragment : Fragment() {

    private val filtriRicercaVM: FiltriRicercaViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_risultati_ricerca, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val latTextView = view.findViewById<TextView>(R.id.latTextView)
        val longTextView = view.findViewById<TextView>(R.id.longTextView)
        val tipoTextView = view.findViewById<TextView>(R.id.tipoTextView)

        latTextView.text = "${filtriRicercaVM.filtriRicerca.latitudine}"
        longTextView.text = "${filtriRicercaVM.filtriRicerca.longitudine}"
        tipoTextView.text = "${filtriRicercaVM.filtriRicerca.tipoAnnuncio}"
    }
}