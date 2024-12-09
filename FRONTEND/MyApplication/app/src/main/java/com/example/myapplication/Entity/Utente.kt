package com.example.myapplication.Entity

open class Utente {
    private var nome: String? = null
    private var cognome: String? = null
    private var email: String? = null
    private var password: String? = null

    // Getter and Setter for nome
    fun getNome(): String? {
        return nome
    }
    fun setNome(nome: String?) {
        this.nome = nome
    }

    // Getter and Setter for cognome
    fun getCognome(): String? {
        return cognome
    }
    fun setCognome(cognome: String?) {
        this.cognome = cognome
    }

    // Getter and Setter for email
    fun getEmail(): String? {
        return email
    }
    fun setEmail(email: String?) {
        this.email = email
    }

    // Getter and Setter for password
    fun getPassword(): String? {
        return password
    }
    fun setPassword(password: String?) {
        this.password = password
    }
}
