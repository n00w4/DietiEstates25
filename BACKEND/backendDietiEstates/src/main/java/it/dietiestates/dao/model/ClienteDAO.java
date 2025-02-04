package it.dietiestates.dao.model;

import it.dietiestates.data.model.Cliente;
import it.dietiestates.exception.DataAccessException;

public interface ClienteDAO {
	boolean insert(Cliente cliente) throws DataAccessException;
}
