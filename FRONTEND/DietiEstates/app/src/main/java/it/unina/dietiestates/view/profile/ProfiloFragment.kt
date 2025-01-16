package it.unina.dietiestates.view.profile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import it.unina.dietiestates.R
import it.unina.dietiestates.model.data.SharedPrefManager

class ProfiloFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_profilo, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val titolo = view.findViewById<TextView>(R.id.titoloTextView)
        val textNome = view.findViewById<TextView>(R.id.nomeTextView)
        val textCognome = view.findViewById<TextView>(R.id.cognomeTextView)
        val textEmail = view.findViewById<TextView>(R.id.emailTextView)
        val textTipoUtente = view.findViewById<TextView>(R.id.tipoUtenteTextView)

        val nome = SharedPrefManager.getUserNome(requireContext())
        val cognome = SharedPrefManager.getUserCognome(requireContext())
        val email = SharedPrefManager.getUserEmail(requireContext())
        val tipoUtente = SharedPrefManager.getUserRole(requireContext())

        titolo.text = getString(R.string.profilo_text, nome)
        textTipoUtente.text = "$tipoUtente"
        textNome.text = "$nome"
        textCognome.text = "$cognome"
        textEmail.text = "$email"

    }

}