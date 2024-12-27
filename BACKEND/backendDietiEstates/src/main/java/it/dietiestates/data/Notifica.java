package it.dietiestates.data;

import java.sql.Timestamp;

public class Notifica {
	private int id;
	private Timestamp dataOra;
	private Agente agente;
	private Prenotazione prenotazione;
	
	public Notifica(int id, Timestamp dataOra, Agente agente, Prenotazione prenotazione) {
		super();
		id = id;
		this.dataOra = dataOra;
		this.agente = agente;
		this.prenotazione = prenotazione;
	}
	
	public int getID() {
		return id;
	}
	public void setID(int id) {
		id = id;
	}
	public Timestamp getDataOra() {
		return dataOra;
	}
	public void setDataOra(Timestamp dataOra) {
		this.dataOra = dataOra;
	}
	public Agente getAgente() {
		return agente;
	}
	public void setAgente(Agente agente) {
		this.agente = agente;
	}
	public Prenotazione getPrenotazione() {
		return prenotazione;
	}
	public void setPrenotazione(Prenotazione prenotazione) {
		this.prenotazione = prenotazione;
	}
	
}
