package it.dietiestates.dao.model;

import it.dietiestates.data.model.Amministratore;
import it.dietiestates.exception.DataAccessException;

public interface AmministratoreDAO {
	boolean insert(Amministratore amministratore) throws DataAccessException;
}
