package it.dietiestates.dao.model;

import it.dietiestates.data.model.Agenzia;
import it.dietiestates.exception.DataAccessException;

public interface AgenziaDAO {
	boolean insert(Agenzia agenzia) throws DataAccessException;
}
