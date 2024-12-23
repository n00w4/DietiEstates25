package it.dietiestates.dao;

import it.dietiestates.data.Cliente;
import it.dietiestates.exception.DataAccessException;

public interface ClienteDAO {
	boolean insert(Cliente cliente) throws DataAccessException;
}
