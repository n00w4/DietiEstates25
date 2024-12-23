package it.dietiestates.dao;

import it.dietiestates.data.Amministratore;
import it.dietiestates.exception.DataAccessException;

public interface AmministratoreDAO {
	boolean insert(Amministratore amministratore) throws DataAccessException;
}
