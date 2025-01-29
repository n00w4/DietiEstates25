package it.dietiestates.data;

import jakarta.json.bind.annotation.JsonbDateFormat;

import java.sql.Timestamp;

public class Prenotazione {
	private int id;
    @JsonbDateFormat("MMM dd, yyyy HH:mm:ss")
	private Timestamp dataInizio;
    @JsonbDateFormat("MMM dd, yyyy HH:mm:ss")
	private Timestamp dataFine;
	private boolean isAccettata;
	private String emailCliente;
	private int idAnnuncio;

    public Prenotazione() {
        // Costruttore vuoto per deserializzazione
    }

	public int getID() {
		return id;
	}
	public void setID(int id) {
		this.id = id;
	}
	//Getters and Setters for dataInizio
	public Timestamp getDataInizio() {
		return dataInizio;
	}
	public void setDataInizio(Timestamp dataInizio) {
		this.dataInizio = dataInizio;
	}
	
	//Getters and Setters for dataFine
	public Timestamp getDataFine() {
		return dataFine;
	}
	public void setDataFine(Timestamp dataFine) {
		this.dataFine = dataFine;
	}
	
	//Getters and Setters for isAccettata
	public boolean isAccettata() {
		return isAccettata;
	}
	public void setAccettata(boolean isAccettata) {
		this.isAccettata = isAccettata;
	}
	
	public String getEmailCliente() {
		return emailCliente;
	}
	public void setEmailCliente(String emailCliente) {
		this.emailCliente = emailCliente;
	}
	
	public int getIdAnnuncio() {
		return idAnnuncio;
	}
	public void setIdAnnuncio(int idAnnuncio) {
		this.idAnnuncio = idAnnuncio;
	}
	
}
