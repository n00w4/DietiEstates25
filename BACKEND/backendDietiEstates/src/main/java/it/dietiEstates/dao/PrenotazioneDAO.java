package it.dietiEstates.dao;

import it.dietiEstates.data.Prenotazione;
import it.dietiEstates.exception.DataAccessException;

public interface PrenotazioneDAO {
	boolean insert(Prenotazione prenotazione) throws DataAccessException;
}
