package it.dietiestates.data.dto;

import it.dietiestates.data.Annuncio;
import it.dietiestates.data.Prenotazione;

public class PrenotazioneConInfo {
    private Prenotazione prenotazione;
    private Annuncio annuncio;

    public PrenotazioneConInfo(Prenotazione prenotazione, Annuncio annuncio) {
        setPrenotazione(prenotazione);
        setAnnuncio(annuncio);
    }

    public Prenotazione getPrenotazione() {
        return prenotazione;
    }

    public void setPrenotazione(Prenotazione prenotazione) {
        this.prenotazione = prenotazione;
    }

    public Annuncio getAnnuncio() {
        return annuncio;
    }

    public void setAnnuncio(Annuncio annuncio) {
        this.annuncio = annuncio;
    }
}
