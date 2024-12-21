package it.dietiEstates.dao;

import it.dietiEstates.data.Agente;
import it.dietiEstates.exception.DataAccessException;

public interface AgenteDAO {
	boolean insert(Agente agente) throws DataAccessException;
}
