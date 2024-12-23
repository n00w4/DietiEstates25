package it.dietiestates.dao;

import it.dietiestates.data.Annuncio;
import it.dietiestates.exception.DataAccessException;

public interface AnnuncioDAO {
	boolean insert(Annuncio annuncio) throws DataAccessException;
}
