package it.unina.dietiestates.view.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import it.unina.dietiestates.R
import androidx.navigation.fragment.findNavController

class RicercaHomeFragment : Fragment(){

    private lateinit var btnCitta: Button
    private lateinit var btnMappa: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_ricerca_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnCitta = view.findViewById(R.id.btn_citta)
        btnMappa = view.findViewById(R.id.btn_mappa)

        val navController = findNavController()

        btnCitta.setOnClickListener(){
            navController.navigate(R.id.action_ricercaHomeFragment_to_ricercaCittaFragment)
        }

        btnMappa.setOnClickListener(){
            navController.navigate(R.id.action_ricercaHomeFragment_to_ricercaMappaFragment)
        }

        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                //Non fare niente (impossibile andare indietro alla pagina di login)
            }
        })
    }
}