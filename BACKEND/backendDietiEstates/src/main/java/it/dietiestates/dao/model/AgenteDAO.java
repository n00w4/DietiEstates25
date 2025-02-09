package it.dietiestates.dao.model;

import it.dietiestates.data.model.Agente;
import it.dietiestates.exception.DataAccessException;

public interface AgenteDAO {
	boolean insert(Agente agente) throws DataAccessException;

	String findEmailByIdAnnuncio(int idAnnuncio) throws DataAccessException;
}
