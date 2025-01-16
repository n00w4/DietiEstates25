package it.unina.dietiestates.model.data

import android.content.Context
import android.content.SharedPreferences

object SharedPrefManager {
    private const val PREF_NAME = "my_app_prefs"

    private const val KEY_TOKEN = "jwt_token"
    private const val USER_ROLE = "user_role"
    private const val USER_EMAIL = "user_email"
    private const val USER_NOME = "user_nome"
    private const val USER_COGNOME = "user_cognome"

    private fun getPreferences(context: Context): SharedPreferences {
        return context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
    }

    fun saveToken(context: Context, token: String) {
        val editor = getPreferences(context).edit()
        editor.putString(KEY_TOKEN, token)
        editor.commit()
    }

    fun getToken(context: Context): String? {
        return getPreferences(context).getString(KEY_TOKEN, null)
    }

    fun saveUserRole(context: Context, role: String) {
        val editor = getPreferences(context).edit()
        editor.putString(USER_ROLE, role)
        editor.commit()
    }

    fun getUserRole(context: Context): String? {
        return getPreferences(context).getString(USER_ROLE, null)
    }

    fun saveUserEmail(context: Context, email: String) {
        val editor = getPreferences(context).edit()
        editor.putString(USER_EMAIL, email)
        editor.commit()
    }

    fun getUserEmail(context: Context): String? {
        return getPreferences(context).getString(USER_EMAIL, null)
    }

    fun saveUserNome(context: Context, nome: String) {
        val editor = getPreferences(context).edit()
        editor.putString(USER_NOME, nome)
        editor.commit()
    }

    fun getUserNome(context: Context): String? {
        return getPreferences(context).getString(USER_NOME, null)
    }

    fun saveUserCognome(context: Context, cognome: String) {
        val editor = getPreferences(context).edit()
        editor.putString(USER_COGNOME, cognome)
        editor.commit()
    }

    fun getUserCognome(context: Context): String? {
        return getPreferences(context).getString(USER_COGNOME, null)
    }

    fun clear(context: Context) {
        val editor = getPreferences(context).edit()
        editor.clear()
        editor.commit()
    }
}
