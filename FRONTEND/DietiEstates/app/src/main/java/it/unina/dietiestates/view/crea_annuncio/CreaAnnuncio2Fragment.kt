package it.unina.dietiestates.view.crea_annuncio

import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import it.unina.dietiestates.R
import it.unina.dietiestates.data.viewmodel.AnnuncioViewModel
import it.unina.dietiestates.utils.ImageUtils

class CreaAnnuncio2Fragment : Fragment(){

    private val annuncioVM: AnnuncioViewModel by activityViewModels()
    private lateinit var immagineView: ImageView
    private var selectedImageBitmap: Bitmap? = null
    private val imageContract = registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
        uri?.let {
            immagineView.setImageURI(it)
            convertUriToBitmap(it)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_crea_annuncio2, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        immagineView = view.findViewById(R.id.imageView)
        if (annuncioVM.immagine.isNullOrEmpty()) {
            immagineView.setImageResource(R.drawable.no_image) // Immagine vuota
        } else {
            val bitmap = ImageUtils.decodeBase64ToBitmap(annuncioVM.immagine!!)
            immagineView.setImageBitmap(bitmap) // Ripristina l'immagine se è stata già selezionata in precedenza
        }

        val caricaBtn = view.findViewById<Button>(R.id.caricaButton)
        caricaBtn.setOnClickListener{
            imageContract.launch("image/*")
        }

        val avantiBtn = view.findViewById<Button>(R.id.avantiButton)
        avantiBtn.setOnClickListener{
            saveImage()
            findNavController().navigate(R.id.action_creaAnnuncio2Fragment_to_creaAnnuncio3Fragment)
        }

        val indietroBtn = view.findViewById<TextView>(R.id.annullaTextView)
        indietroBtn.setOnClickListener{
            findNavController().navigate(R.id.action_creaAnnuncio2Fragment_to_creaAnnuncioFragment)
        }
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                findNavController().popBackStack()
            }
        })
    }

    private fun convertUriToBitmap(uri: Uri) {
        try {
            val source = ImageDecoder.createSource(requireActivity().contentResolver, uri)
            selectedImageBitmap = ImageDecoder.decodeBitmap(source)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun saveImage(){
        selectedImageBitmap?.let { bitmap ->
            val immagineBase64 = ImageUtils.encodeBitmapToBase64(bitmap)
            annuncioVM.immagine = immagineBase64
        } ?: Log.e("Base64Image", "No image selected!")
    }

}