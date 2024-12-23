package it.dietiestates.dao;

import it.dietiestates.data.Notifica;
import it.dietiestates.exception.DataAccessException;

public interface NotificaDAO {
	boolean insert(Notifica notifica) throws DataAccessException;
}
