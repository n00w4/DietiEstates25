package it.unina.dietiestates.controller.auth

import android.content.Context
import android.content.Intent
import android.widget.Toast
import it.unina.dietiestates.data.dto.SharedPrefManager
import it.unina.dietiestates.view.home.HomeAgenteActivity
import it.unina.dietiestates.view.home.HomeAmministratoreActivity
import it.unina.dietiestates.view.home.HomeClienteActivity
import it.unina.dietiestates.view.home.HomeGestoreActivity

abstract class AuthController(protected val context: Context) {
    abstract fun handleAuth()

    protected fun salvaDatiUtente(nome: String?, cognome: String?, email: String,
                                  tipoUtente: String, partitaIva: String?, nomeAgenzia: String?){
        SharedPrefManager.saveUserNome(context, nome)
        SharedPrefManager.saveUserCognome(context, cognome)
        SharedPrefManager.saveUserEmail(context, email)
        SharedPrefManager.saveUserRole(context, tipoUtente)
        SharedPrefManager.savePartitaIva(context, partitaIva)
        SharedPrefManager.saveNomeAgenzia(context, nomeAgenzia)
    }

    protected fun scegliHomePage(tipoUtente: String){
        val intent = when (tipoUtente) {
            "Cliente" -> Intent(context, HomeClienteActivity::class.java)
            "Agente" -> Intent(context, HomeAgenteActivity::class.java)
            "Amministratore" -> Intent(context, HomeAmministratoreActivity::class.java)
            "Gestore" -> Intent(context, HomeGestoreActivity::class.java)
            else -> null
        }

        if (intent != null) {
            context.startActivity(intent)
        } else {
            Toast.makeText(context, "Unknown user type", Toast.LENGTH_SHORT).show()
        }
    }
}