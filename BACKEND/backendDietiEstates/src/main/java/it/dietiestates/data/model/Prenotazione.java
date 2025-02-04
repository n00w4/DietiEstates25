package it.dietiestates.data.model;

import jakarta.json.bind.annotation.JsonbCreator;
import jakarta.json.bind.annotation.JsonbDateFormat;
import jakarta.json.bind.annotation.JsonbProperty;

import java.time.LocalDateTime;

public class Prenotazione {
	private int id;
    @JsonbDateFormat("MMM dd, yyyy HH:mm:ss")
    private LocalDateTime dataInizio;
    @JsonbDateFormat("MMM dd, yyyy HH:mm:ss")
    private LocalDateTime dataFine;
	private Boolean isAccettata;
	private int idAnnuncio;
    private String emailCliente;

    @JsonbCreator
    public Prenotazione(
            @JsonbProperty("idPrenotazione") int id,
            @JsonbProperty("dataInizio") LocalDateTime dataInizio,
            @JsonbProperty("dataFine") LocalDateTime dataFine,
			@JsonbProperty("isAccettata") Boolean isAccettata,
            @JsonbProperty("idAnnuncio") int idAnnuncio,
            @JsonbProperty("emailCliente") String emailCliente
    ) {
        setID(id);
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

    public LocalDateTime getDataInizio() {
		return dataInizio;
	}

    public void setDataInizio(LocalDateTime dataInizio) {
		this.dataInizio = dataInizio;
	}

    public LocalDateTime getDataFine() {
		return dataFine;
	}

    public void setDataFine(LocalDateTime dataFine) {
		this.dataFine = dataFine;
	}

    public Boolean isAccettata() {
		return isAccettata;
	}

    public void setAccettata(Boolean isAccettata) {
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
