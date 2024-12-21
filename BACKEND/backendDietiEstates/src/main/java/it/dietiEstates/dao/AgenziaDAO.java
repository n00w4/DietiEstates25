package it.dietiEstates.dao;

import it.dietiEstates.data.Agenzia;
import it.dietiEstates.exception.DataAccessException;

public interface AgenziaDAO {
	boolean insert(Agenzia agenzia) throws DataAccessException;
}
