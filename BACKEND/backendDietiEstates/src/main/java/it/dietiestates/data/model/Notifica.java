package it.dietiestates.data.model;

import java.sql.Timestamp;

public class Notifica {
	private int id;
	private Timestamp dataOra;
    private String emailAgente;
    private int idPrenotazione;

    public Notifica(int id, Timestamp dataOra, String emailAgente, int idPrenotazione) {
        setId(id);
        setDataOra(dataOra);
        setEmailAgente(emailAgente);
        setIdPrenotazione(idPrenotazione);
	}

    public int getId() {
		return id;
	}

    public void setId(int id) {
        this.id = id;
	}

	public Timestamp getDataOra() {
		return dataOra;
	}
	public void setDataOra(Timestamp dataOra) {
		this.dataOra = dataOra;
	}

    public String getEmailAgente() {
        return emailAgente;
	}

    public void setEmailAgente(String emailAgente) {
        this.emailAgente = emailAgente;
	}

    public int getIdPrenotazione() {
        return idPrenotazione;
	}

    public void setIdPrenotazione(int idPrenotazione) {
        this.idPrenotazione = idPrenotazione;
    }
}
