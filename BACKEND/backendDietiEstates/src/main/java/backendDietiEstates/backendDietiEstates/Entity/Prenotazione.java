package backendDietiEstates.backendDietiEstates.Entity;

import java.sql.Timestamp;

public class Prenotazione {
	private Timestamp dataInizio;
	private Timestamp dataFine;
	private boolean isAccettata;
	Cliente cliente;
	Annuncio annuncio;
	
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
	
}
