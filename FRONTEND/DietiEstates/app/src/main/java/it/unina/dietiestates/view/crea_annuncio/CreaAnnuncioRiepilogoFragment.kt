package it.unina.dietiestates.view.crea_annuncio

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import it.unina.dietiestates.R
import it.unina.dietiestates.data.viewmodel.AnnuncioViewModel

class CreaAnnuncioRiepilogoFragment : Fragment(){

    private val annuncioVM: AnnuncioViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_crea_annuncio_riepilogo, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<TextView>(R.id.titoloTextView).text = annuncioVM.titolo.toString()
        view.findViewById<TextView>(R.id.descrizioneTextView).text = annuncioVM.descrizione.toString()
        view.findViewById<TextView>(R.id.indirizzoTextView).text = annuncioVM.indirizzo.toString()
        view.findViewById<TextView>(R.id.tipoImmobileTextView).text = annuncioVM.tipoAnnuncio.toString()
        view.findViewById<TextView>(R.id.dimensioniTextView).text = getString(R.string.dimensioni_riepilogo, annuncioVM.dimensioni.toString())
        view.findViewById<TextView>(R.id.prezzoTextView).text = getString(R.string.prezzo_riepilogo, annuncioVM.prezzo.toString())
        view.findViewById<TextView>(R.id.pianoTextView).text = annuncioVM.piano.toString()
        view.findViewById<TextView>(R.id.numeroStanzeTextView).text = annuncioVM.numeroStanze.toString()
        view.findViewById<TextView>(R.id.classeEnergeticaTextView).text = annuncioVM.classeEnergetica.toString()
        val ascensore = view.findViewById<TextView>(R.id.ascensoreTextView)
        ascensore.isVisible = (annuncioVM.ascensore == true)
        val portineria = view.findViewById<TextView>(R.id.portineriaTextView)
        portineria.isVisible = (annuncioVM.portineria == true)
        val climatizzazione = view.findViewById<TextView>(R.id.climatizzazioneTextView)
        climatizzazione.isVisible = (annuncioVM.climatizzazione == true)
        val boxAuto = view.findViewById<TextView>(R.id.boxAutoTextView)
        boxAuto.isVisible = (annuncioVM.boxAuto == true)
        val terrazzo = view.findViewById<TextView>(R.id.terrazzoTextView)
        terrazzo.isVisible = (annuncioVM.terrazzo == true)
        val giardino = view.findViewById<TextView>(R.id.giardinoTextView)
        giardino.isVisible = (annuncioVM.giardino == true)

        val avantiBtn = view.findViewById<Button>(R.id.avantiButton)
        avantiBtn.setOnClickListener{
            findNavController().navigate(R.id.action_creaAnnuncioRiepilogoFragment_to_creaAnnuncioResultFragment)
        }

        val annullaBtn = view.findViewById<TextView>(R.id.annullaButton)
        annullaBtn.setOnClickListener{
            findNavController().navigate(R.id.action_creaAnnuncioRiepilogoFragment_to_creaAnnuncio3Fragment)
        }
        val indietroBtn = view.findViewById<TextView>(R.id.annullaTextView)
        indietroBtn.setOnClickListener{
            findNavController().navigate(R.id.action_creaAnnuncioRiepilogoFragment_to_creaAnnuncio3Fragment)
        }
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                findNavController().popBackStack()
            }
        })
    }
}