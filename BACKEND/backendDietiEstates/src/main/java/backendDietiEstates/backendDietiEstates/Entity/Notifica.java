package backendDietiEstates.backendDietiEstates.Entity;

import java.sql.Timestamp;

public class Notifica {
	private Timestamp dataOra;
	private Agente agente;
	private Prenotazione prenotazione;
	
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
