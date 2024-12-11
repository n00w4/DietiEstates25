package dietiEstates.backendDietiEstates.Data;

import java.sql.Timestamp;

public class Notifica {
	private int ID;
	private Timestamp dataOra;
	private Agente agente;
	private Prenotazione prenotazione;
	
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
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
