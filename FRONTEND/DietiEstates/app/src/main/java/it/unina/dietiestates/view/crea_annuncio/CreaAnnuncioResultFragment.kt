package it.unina.dietiestates.view.crea_annuncio

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import it.unina.dietiestates.R
import it.unina.dietiestates.controller.crea_annuncio.CreaAnnuncioController
import it.unina.dietiestates.data.viewmodel.AnnuncioViewModel

class CreaAnnuncioResultFragment : Fragment(){

    private val annuncioVM: AnnuncioViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_crea_annuncio_result, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val controller = CreaAnnuncioController(requireContext())
        controller.creaAnnuncio(annuncioVM)

        val fineBtn = view.findViewById<Button>(R.id.fineButton)
        fineBtn.setOnClickListener{
            findNavController().navigate(R.id.action_creaAnnuncioResultFragment_to_creaAnnuncioFragment)
        }
    }

}