package it.dietiestates.data;

import java.sql.Timestamp;

public class Prenotazione {
	private int id;
	private Timestamp dataInizio;
	private Timestamp dataFine;
	private boolean isAccettata;
	private String emailCliente;
	private int idAnnuncio;
	
	public int getID() {
		return id;
	}
	public void setID(int id) {
		id = id;
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
