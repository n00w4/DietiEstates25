package it.unina.dietiestates.data.dto

import android.content.Context
import android.content.SharedPreferences

object SharedPrefManager {
    private const val PREF_NAME = "my_app_prefs"

    private const val KEY_TOKEN = "jwt_token"
    private const val USER_ROLE = "user_role"
    private const val USER_EMAIL = "user_email"
    private const val USER_NOME = "user_nome"
    private const val USER_COGNOME = "user_cognome"

    private const val PARTITA_IVA = "partita_iva"
    private const val NOME_AGENZIA = "nome_agenzia"

    private fun getPreferences(context: Context): SharedPreferences {
        return context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
    }

    fun saveToken(context: Context, token: String) {
        val editor = getPreferences(context).edit()
        editor.putString(KEY_TOKEN, token)
        editor.apply()
    }

    fun getToken(context: Context): String? {
        return getPreferences(context).getString(KEY_TOKEN, null)
    }

    fun saveUserRole(context: Context, role: String) {
        val editor = getPreferences(context).edit()
        editor.putString(USER_ROLE, role)
        editor.apply()
    }

    fun getUserRole(context: Context): String? {
        return getPreferences(context).getString(USER_ROLE, null)
    }

    fun saveUserEmail(context: Context, email: String) {
        val editor = getPreferences(context).edit()
        editor.putString(USER_EMAIL, email)
        editor.apply()
    }

    fun getUserEmail(context: Context): String? {
        return getPreferences(context).getString(USER_EMAIL, null)
    }

    fun saveUserNome(context: Context, nome: String?) {
        val editor = getPreferences(context).edit()
        editor.putString(USER_NOME, nome)
        editor.apply()
    }

    fun getUserNome(context: Context): String? {
        return getPreferences(context).getString(USER_NOME, null)
    }

    fun saveUserCognome(context: Context, cognome: String?) {
        val editor = getPreferences(context).edit()
        editor.putString(USER_COGNOME, cognome)
        editor.apply()
    }

    fun getUserCognome(context: Context): String? {
        return getPreferences(context).getString(USER_COGNOME, null)
    }

    fun savePartitaIva(context: Context, partitaIva: String?) {
        val editor = getPreferences(context).edit()
        editor.putString(PARTITA_IVA, partitaIva)
        editor.apply()
    }

    fun getPartitaIva(context: Context): String? {
        return getPreferences(context).getString(PARTITA_IVA, null)
    }

    fun saveNomeAgenzia(context: Context, nomeAgenzia: String?) {
        val editor = getPreferences(context).edit()
        editor.putString(NOME_AGENZIA, nomeAgenzia)
        editor.apply()
    }

    fun getNomeAgenzia(context: Context): String? {
        return getPreferences(context).getString(NOME_AGENZIA, null)
    }

    fun clear(context: Context) {
        val editor = getPreferences(context).edit()
        editor.clear()
        editor.apply()
    }
}
