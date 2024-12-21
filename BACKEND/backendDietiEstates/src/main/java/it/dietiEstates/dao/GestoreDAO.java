package it.dietiEstates.dao;

import it.dietiEstates.data.Gestore;
import it.dietiEstates.exception.DataAccessException;

public interface GestoreDAO {
	boolean insert(Gestore gestore) throws DataAccessException;
}
