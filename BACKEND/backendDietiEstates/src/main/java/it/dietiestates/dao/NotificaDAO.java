package it.dietiestates.dao;

import it.dietiestates.data.Notifica;
import it.dietiestates.exception.DataAccessException;

import java.util.List;

public interface NotificaDAO {
	boolean insert(Notifica notifica) throws DataAccessException;

    List<Notifica> getAllNotifiche(String emailAgente) throws DataAccessException;
}
