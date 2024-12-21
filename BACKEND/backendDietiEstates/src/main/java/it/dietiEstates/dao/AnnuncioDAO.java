package it.dietiEstates.dao;

import it.dietiEstates.data.Annuncio;
import it.dietiEstates.exception.DataAccessException;

public interface AnnuncioDAO {
	boolean insert(Annuncio annuncio) throws DataAccessException;
}
