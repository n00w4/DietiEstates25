package it.dietiestates.dao;

import it.dietiestates.data.Prenotazione;
import it.dietiestates.exception.DataAccessException;

public interface PrenotazioneDAO {
	boolean insert(Prenotazione prenotazione) throws DataAccessException;
}
