package dietiEstates.backendDietiEstates.Data;

import java.sql.Timestamp;

public class Prenotazione {
	private int ID;
	private Timestamp dataInizio;
	private Timestamp dataFine;
	private boolean isAccettata;
	private Cliente cliente;
	private Annuncio annuncio;
	
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
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
	
	//Getters and Setters for Cliente
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	//Getters and Setters for Annuncio
	public Annuncio getAnnuncio() {
		return annuncio;
	}
	public void setAnnuncio(Annuncio annuncio) {
		this.annuncio = annuncio;
	}
	
	
	
}
