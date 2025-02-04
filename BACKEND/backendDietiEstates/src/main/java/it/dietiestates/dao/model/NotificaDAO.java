package it.dietiestates.dao.model;

import it.dietiestates.data.model.Notifica;
import it.dietiestates.exception.DataAccessException;

import java.util.List;

public interface NotificaDAO {
	boolean insert(Notifica notifica) throws DataAccessException;

    List<Notifica> getAllNotifiche(String emailAgente) throws DataAccessException;
}
