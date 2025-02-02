package it.dietiestates.dao;

import it.dietiestates.data.Prenotazione;
import it.dietiestates.exception.DataAccessException;

public interface PrenotazioneDAO {
	boolean insert(Prenotazione prenotazione) throws DataAccessException;
	boolean delete(Prenotazione prenotazione) throws DataAccessException;

    boolean updateStatusPrenotazione(Prenotazione prenotazione) throws DataAccessException;
}
