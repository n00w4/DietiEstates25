package it.dietiEstates.dao;

import it.dietiEstates.data.Notifica;
import it.dietiEstates.exception.DataAccessException;

public interface NotificaDAO {
	boolean insert(Notifica notifica) throws DataAccessException;
}
