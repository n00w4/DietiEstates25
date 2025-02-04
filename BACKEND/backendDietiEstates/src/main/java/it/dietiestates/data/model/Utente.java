package it.dietiestates.data.model;

import jakarta.json.bind.annotation.JsonbTypeInfo;

@JsonbTypeInfo
public abstract class Utente {
	private String nome;
	private String cognome;
	private String email;
	private String password;

	protected Utente(String nome, String cognome, String email, String password) {
		super();
		this.nome = nome;
		this.cognome = cognome;
		this.email = email;
		this.password = password;
	}

	protected Utente() {
        //Costruttore vuoto per deserializzazione
	}


    public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}

    public String getCognome() {
		return cognome;
	}
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

    public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

    public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public String getTipo() {
		return this.getClass().getSimpleName();
	}

	public String getPartitaIVA() {
		return null;
	}

	public String getNomeAgenzia() {
		return null;
	}
}
