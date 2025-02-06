package it.unina.dietiestates.view.profile

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.fragment.app.Fragment
import it.unina.dietiestates.R
import it.unina.dietiestates.data.dto.AddUtenteForm
import it.unina.dietiestates.data.dto.ApiResponse
import it.unina.dietiestates.data.dto.SharedPrefManager
import it.unina.dietiestates.network.retrofit.RetrofitClient
import it.unina.dietiestates.utils.ValidationUtils
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AmministratoreCreaUtenteFragment : Fragment() {

    private lateinit var userType: Spinner
    private lateinit var name : EditText
    private lateinit var surname : EditText
    private lateinit var email : EditText

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_amministratore_crea_utente, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        userType = view.findViewById(R.id.spinner_gestore)
        name = view.findViewById(R.id.editTextNome)
        surname = view.findViewById(R.id.editTextCognome)
        email = view.findViewById(R.id.editTextEmailGestore)
        val buttonAdd : Button = view.findViewById(R.id.buttonAdd)

        buttonAdd.isEnabled = false

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
                addUtente(userType.selectedItem.toString(), name.text.toString(), surname.text.toString(), email.text.toString())
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

    private fun addUtente(userType: String, name: String, surname: String, email: String) {
        val partitaIVAGestore = SharedPrefManager.getPartitaIva(requireContext())

        val form = AddUtenteForm(userType, name, surname, email, partitaIVAGestore!!)

        val api = RetrofitClient.instance

        api.addUtente(form).enqueue(object : Callback<ApiResponse> {
            override fun onResponse(call: Call<ApiResponse>, response: Response<ApiResponse>) {
                when (response.code()) {
                    201 -> {
                        val message = response.body()?.message
                        if (message != null) {
                            Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
                        }
                    }
                    400 -> {
                        Toast.makeText(context, "I dati inseriti non sono al momento validi. Riprova più tardi.", Toast.LENGTH_SHORT).show()
                    }
                    409 -> {
                        Toast.makeText(context, "Esiste già un utente con questa email.", Toast.LENGTH_SHORT).show()
                    }
                    else -> {
                        Toast.makeText(context, "Errore codice ${response.code()}: riprova più tardi", Toast.LENGTH_SHORT).show()
                    }
                }
            }

            override fun onFailure(call: Call<ApiResponse>, t: Throwable) {
                Toast.makeText(context, "Errore durante la connessione al server: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun setupValidation(editText: EditText, validationFunction: (String) -> List<String>, buttonAdd: Button) {
        editText.onFocusChangeListener = View.OnFocusChangeListener { _, hasFocus ->
            if (!hasFocus) {
                val messaggiDiErrore = validationFunction(editText.text.toString())
                editText.error = if (messaggiDiErrore.isNotEmpty()) messaggiDiErrore.joinToString("\n") else null
                buttonAdd.isEnabled = messaggiDiErrore.isEmpty()
            }
        }
    }

}