package it.dietiestates.data;

import jakarta.json.bind.annotation.JsonbDateFormat;

import java.time.LocalDateTime;

public class Prenotazione {
	private int id;
    @JsonbDateFormat("MMM dd, yyyy HH:mm:ss")
    private LocalDateTime dataInizio;
    @JsonbDateFormat("MMM dd, yyyy HH:mm:ss")
    private LocalDateTime dataFine;
	private boolean isAccettata;
	private int idAnnuncio;
    private String emailCliente;

    public Prenotazione() {
        // Costruttore vuoto per deserializzazione
    }

    public Prenotazione(int idPrenotazione, LocalDateTime dataInizio, LocalDateTime dataFine, boolean isAccettata, int idAnnuncio, String emailCliente) {
        setID(idPrenotazione);
        setDataInizio(dataInizio);
        setDataFine(dataFine);
        setAccettata(isAccettata);
        setIdAnnuncio(idAnnuncio);
        setEmailCliente(emailCliente);
    }

    public int getID() {
		return id;
	}
	public void setID(int id) {
		this.id = id;
	}
	//Getters and Setters for dataInizio
    public LocalDateTime getDataInizio() {
		return dataInizio;
	}

    public void setDataInizio(LocalDateTime dataInizio) {
		this.dataInizio = dataInizio;
	}
	
	//Getters and Setters for dataFine
    public LocalDateTime getDataFine() {
		return dataFine;
	}

    public void setDataFine(LocalDateTime dataFine) {
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
