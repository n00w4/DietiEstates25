package it.dietiEstates.dao;

import it.dietiEstates.data.Cliente;
import it.dietiEstates.exception.DataAccessException;

public interface ClienteDAO {
	boolean insert(Cliente cliente) throws DataAccessException;
}
