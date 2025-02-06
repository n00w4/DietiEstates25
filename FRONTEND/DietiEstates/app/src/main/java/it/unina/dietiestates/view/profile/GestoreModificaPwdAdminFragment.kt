package it.unina.dietiestates.view.profile

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import it.unina.dietiestates.R
import it.unina.dietiestates.data.dto.ApiResponse
import it.unina.dietiestates.data.dto.ChangeAdminPwdForm
import it.unina.dietiestates.data.dto.SharedPrefManager
import it.unina.dietiestates.network.retrofit.RetrofitClient
import it.unina.dietiestates.utils.ValidationUtils
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GestoreModificaPwdAdminFragment : Fragment() {

    private lateinit var oldPwd : EditText
    private lateinit var newPwd : EditText
    private lateinit var confirmNewPwd: EditText

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_gestore_modifica_pwd_admin, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        oldPwd = view.findViewById(R.id.editTextVecchiaPassword)
        newPwd = view.findViewById(R.id.editTextNuovaPassword)
        confirmNewPwd = view.findViewById(R.id.editTextConfermaNuovaPassword)
        val buttonChange: Button = view.findViewById(R.id.buttonChange)

        newPwd.onFocusChangeListener = View.OnFocusChangeListener { _, hasFocus ->
            if (!hasFocus) {
                var messaggiDiErrore = ValidationUtils.verificaPassword(newPwd.text.toString())
                if (newPwd.text.toString() == oldPwd.text.toString()) {
                    messaggiDiErrore = messaggiDiErrore.plus("La password vecchia è uguale alla nuova")
                }
                if (messaggiDiErrore.isNotEmpty()) {
                    newPwd.error = messaggiDiErrore.joinToString("\n")
                    buttonChange.isEnabled = false
                } else {
                    newPwd.error = null
                    buttonChange.isEnabled = true
                }
            }
        }

        confirmNewPwd.onFocusChangeListener = View.OnFocusChangeListener { _, hasFocus ->
            if (!hasFocus) {
                if (newPwd.text.toString() != confirmNewPwd.text.toString()) {
                    confirmNewPwd.error = "La password non è uguale alla nuova password"
                    buttonChange.isEnabled = false
                } else {
                    confirmNewPwd.error = null
                    buttonChange.isEnabled = true
                }
            }
        }

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
                changeAdminPwd(oldPwd.text.toString(), newPwd.text.toString())
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

    private fun changeAdminPwd(oldPwd: String, newPwd: String) {
        val emailGestore = SharedPrefManager.getUserEmail(requireContext())
        val partitaIVAGestore = SharedPrefManager.getPartitaIva(requireContext())

        val form = ChangeAdminPwdForm(oldPwd, newPwd, emailGestore!!, partitaIVAGestore!!)

        val api = RetrofitClient.instance

        api.updateAdminPassword(form).enqueue(object : Callback<ApiResponse> {
            override fun onResponse(call: Call<ApiResponse>, response: Response<ApiResponse>) {
                when (response.code()) {
                    200 -> {
                        val message = response.body()?.message
                        if (message != null) {
                            Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
                        }
                    }
                    400 -> {
                        Toast.makeText(context, "I dati inseriti non sono al momento validi. Riprova più tardi.", Toast.LENGTH_SHORT).show()
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
}