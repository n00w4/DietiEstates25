package it.dietiestates.dao;

import it.dietiestates.data.Agente;
import it.dietiestates.exception.DataAccessException;

public interface AgenteDAO {
	boolean insert(Agente agente) throws DataAccessException;
}
