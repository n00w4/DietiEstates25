package it.unina.dietiestates.view.profile

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import androidx.core.view.isVisible
import it.unina.dietiestates.MainActivity
import it.unina.dietiestates.R
import it.unina.dietiestates.data.dto.SharedPrefManager

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
        val agenziaLayout = view.findViewById<LinearLayout>(R.id.agenziaLayout)
        val textNomeAgenzia = view.findViewById<TextView>(R.id.agenziaTextView)
            agenziaLayout.isVisible = false
        val ivaLayout = view.findViewById<LinearLayout>(R.id.ivaLayout)
        val textPartitaIva = view.findViewById<TextView>(R.id.ivaTextView)
            ivaLayout.isVisible = false

        val nome = SharedPrefManager.getUserNome(requireContext())
        val cognome = SharedPrefManager.getUserCognome(requireContext())
        val email = SharedPrefManager.getUserEmail(requireContext())
        val tipoUtente = SharedPrefManager.getUserRole(requireContext())

        val nonDisponibileText = "Non Disponibile"
        if(nome==null){
            titolo.text = getString(R.string.profilo_text, " ")
        } else titolo.text = getString(R.string.profilo_text, nome)
        textTipoUtente.text = "$tipoUtente"
        textNome.text = "$nome"
        if(nome==null) textNome.text = nonDisponibileText
        textCognome.text = "$cognome"
        if(cognome==null) textCognome.text = nonDisponibileText
        textEmail.text = "$email"
        if(email==null) textEmail.text = nonDisponibileText

        if(!tipoUtente.equals("Cliente") ){
            val nomeAgenzia = SharedPrefManager.getNomeAgenzia(requireContext())
            textNomeAgenzia.text = "$nomeAgenzia"
            agenziaLayout.isVisible = true
            val partitaIva = SharedPrefManager.getPartitaIva(requireContext())
            textPartitaIva.text = "$partitaIva"
            ivaLayout.isVisible = true
        }

        val logoutBtn = view.findViewById<Button>(R.id.logoutButton)
        logoutBtn.setOnClickListener{
            SharedPrefManager.clear(requireContext())
            val loginPage = Intent(requireContext(), MainActivity::class.java)
            startActivity(loginPage)
        }

        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() { /*Non fare niente (impossibile andare indietro alla pagina di login)*/ }
        })

    }

}