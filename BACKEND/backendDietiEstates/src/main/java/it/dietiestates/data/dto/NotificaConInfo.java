package it.dietiestates.data.dto;

import it.dietiestates.data.Annuncio;
import it.dietiestates.data.Notifica;
import it.dietiestates.data.Prenotazione;

public class NotificaConInfo {
    private Notifica notifica;
    private Prenotazione prenotazione;
    private Annuncio annuncio;

    public NotificaConInfo(Notifica notifica, Prenotazione prenotazione, Annuncio annuncio) {
        setNotifica(notifica);
        setPrenotazione(prenotazione);
        setAnnuncio(annuncio);
    }

    public Notifica getNotifica() {
        return notifica;
    }

    public void setNotifica(Notifica notifica) {
        this.notifica = notifica;
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
