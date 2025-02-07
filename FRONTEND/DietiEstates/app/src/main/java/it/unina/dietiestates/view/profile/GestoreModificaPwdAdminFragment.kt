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
import android.widget.Toast
import androidx.fragment.app.Fragment
import it.unina.dietiestates.R
import it.unina.dietiestates.controller.profile.GestoreModificaPwdAdminController
import it.unina.dietiestates.utils.ValidationUtils

class GestoreModificaPwdAdminFragment : Fragment() {

    private lateinit var oldPwd : EditText
    private lateinit var newPwd : EditText
    private lateinit var confirmNewPwd : EditText
    private lateinit var controller : GestoreModificaPwdAdminController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_gestore_modifica_pwd_admin, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        controller = GestoreModificaPwdAdminController(requireContext())

        oldPwd = view.findViewById(R.id.editTextVecchiaPassword)
        newPwd = view.findViewById(R.id.editTextNuovaPassword)
        confirmNewPwd = view.findViewById(R.id.editTextConfermaNuovaPassword)
        val buttonChange: Button = view.findViewById(R.id.buttonChange)

        setupValidation(newPwd, ValidationUtils::verificaPassword, buttonChange)
        setupValidation(confirmNewPwd, ValidationUtils::verificaPassword, buttonChange)

        buttonChange.setOnClickListener {
            onButtonChangeCliked()
        }
    }

    private fun onButtonChangeCliked(){
        if(checkEmptyEditText()){
            Toast.makeText(context, "Compilare tutti i campi prima di procedere.", Toast.LENGTH_SHORT).show()
        }else{
            val builder = AlertDialog.Builder(requireContext())
            builder.setTitle("Conferma Operazione")
            builder.setMessage("Sei sicuro di voler procedere?")
            builder.setPositiveButton("Conferma") { dialog, _ ->
                controller.changeAdminPwd(oldPwd.text.toString(), newPwd.text.toString())
                dialog.dismiss()
            }
            builder.setNegativeButton("Annulla") { dialog, _ ->
                dialog.dismiss()
            }
            builder.create().show()
        }
    }

    private fun checkEmptyEditText() : Boolean{
        if(oldPwd.text.toString().trim().isEmpty()) return true
        if(newPwd.text.toString().trim().isEmpty()) return true
        if(confirmNewPwd.text.toString().trim().isEmpty()) return true
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
                    buttonAdd.backgroundTintList = requireContext().getColorStateList(R.color.button_color_light)
                } else {
                    editText.error = messaggiDiErrore.joinToString("\n")
                    buttonAdd.isEnabled = false
                    buttonAdd.backgroundTintList = requireContext().getColorStateList(android.R.color.darker_gray)
                }
            }
        })
    }

}