package it.unina.dietiestates.view.profile

import android.app.AlertDialog
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.fragment.app.Fragment
import it.unina.dietiestates.R
import it.unina.dietiestates.controller.profile.GestoreCreaUtenteController
import it.unina.dietiestates.utils.ValidationUtils

class AmministratoreCreaUtenteFragment : Fragment() {

    private lateinit var userType: Spinner
    private lateinit var name : EditText
    private lateinit var surname : EditText
    private lateinit var email : EditText
    private lateinit var controller : GestoreCreaUtenteController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_amministratore_crea_utente, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        controller = GestoreCreaUtenteController(requireContext())

        userType = view.findViewById(R.id.spinner_gestore)
        name = view.findViewById(R.id.editTextNome)
        surname = view.findViewById(R.id.editTextCognome)
        email = view.findViewById(R.id.editTextEmailGestore)
        val buttonAdd : Button = view.findViewById(R.id.buttonAdd)

        setupValidation(name, ValidationUtils::verificaNome, buttonAdd)
        setupValidation(surname, ValidationUtils::verificaNome, buttonAdd)
        setupValidation(email, ValidationUtils::verificaEmail, buttonAdd)

        buttonAdd.setOnClickListener {
            onButtonAddClicked()
        }

    }

    private fun onButtonAddClicked(){
        if(checkEmptyEditText()){
            Toast.makeText(context, "Compilare tutti i campi prima di procedere.", Toast.LENGTH_SHORT).show()
        }else{
            val builder = AlertDialog.Builder(requireContext())
            builder.setTitle("Conferma Operazione")
            builder.setMessage("Sei sicuro di voler procedere?")
            builder.setPositiveButton("Conferma") { dialog, _ ->
                controller.addUtente(userType.selectedItem.toString(), name.text.toString(), surname.text.toString(), email.text.toString())
                dialog.dismiss()
            }
            builder.setNegativeButton("Annulla") { dialog, _ ->
                dialog.dismiss()
            }
            builder.create().show()
        }
    }

    private fun checkEmptyEditText() : Boolean{
        if(name.text.toString().trim().isEmpty()) return true
        if(surname.text.toString().trim().isEmpty()) return true
        if(email.text.toString().trim().isEmpty()) return true
        return false
    }

    private fun setupValidation(editText: EditText, validationFunction: (String) -> List<String>, buttonAdd: Button) {
        editText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) { /*empty*/ }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) { /*empty*/ }
            override fun afterTextChanged(s: Editable?) {
                val messaggiDiErrore = validationFunction(editText.text.toString())

                if (messaggiDiErrore.isEmpty()) {
                    buttonAdd.isEnabled = true
                    buttonAdd.backgroundTintList = requireContext().getColorStateList(R.color.button_color_dark)
                } else {
                    editText.error = messaggiDiErrore.joinToString("\n")
                    buttonAdd.isEnabled = false
                    buttonAdd.backgroundTintList = requireContext().getColorStateList(android.R.color.darker_gray)
                }
            }
        })
    }

}