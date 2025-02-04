package it.dietiestates.dao.model;

import it.dietiestates.data.model.Prenotazione;
import it.dietiestates.exception.DataAccessException;

public interface PrenotazioneDAO {
	boolean insert(Prenotazione prenotazione) throws DataAccessException;
	boolean delete(Prenotazione prenotazione) throws DataAccessException;
    boolean updateStatusPrenotazione(Prenotazione prenotazione) throws DataAccessException;
}
