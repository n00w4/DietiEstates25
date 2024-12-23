package it.dietiestates.dao;

import it.dietiestates.data.Agenzia;
import it.dietiestates.exception.DataAccessException;

public interface AgenziaDAO {
	boolean insert(Agenzia agenzia) throws DataAccessException;
}
