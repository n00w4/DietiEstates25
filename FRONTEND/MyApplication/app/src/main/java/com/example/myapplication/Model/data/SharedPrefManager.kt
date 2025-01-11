package com.example.myapplication.model.data

import android.content.Context
import android.content.SharedPreferences

object SharedPrefManager {
    private const val PREF_NAME = "my_app_prefs"
    private const val KEY_TOKEN = "jwt_token"
    private const val KEY_CLIENT_ID = "client_id"

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

    fun saveClientId(context: Context, clientId: String) {
        val editor = getPreferences(context).edit()
        editor.putString(KEY_CLIENT_ID, clientId)
        editor.commit()
    }

    fun getClientId(context: Context): String? {
        return getPreferences(context).getString(KEY_CLIENT_ID, null)
    }

    fun clear(context: Context) {
        val editor = getPreferences(context).edit()
        editor.clear()
        editor.commit()
    }
}
