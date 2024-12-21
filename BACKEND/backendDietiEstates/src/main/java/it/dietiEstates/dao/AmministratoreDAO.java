package it.dietiEstates.dao;

import it.dietiEstates.data.Amministratore;
import it.dietiEstates.exception.DataAccessException;

public interface AmministratoreDAO {
	boolean insert(Amministratore amministratore) throws DataAccessException;
}
