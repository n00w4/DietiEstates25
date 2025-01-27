package it.unina.dietiestates.view.crea_annuncio

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import it.unina.dietiestates.R

class CreaAnnuncio3Fragment : Fragment(){
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_crea_annuncio3, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val avantiBtn = view.findViewById<Button>(R.id.avantiButton)
        avantiBtn.setOnClickListener{
            findNavController().navigate(R.id.action_creaAnnuncio3Fragment_to_creaAnnuncioRiepilogoFragment)
        }

        val indietroBtn = view.findViewById<TextView>(R.id.annullaTextView)
        indietroBtn.setOnClickListener{
            findNavController().navigate(R.id.action_creaAnnuncio3Fragment_to_creaAnnuncio2Fragment)
        }
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                findNavController().popBackStack()
            }
        })
    }
}