package it.dietiestates.dao.dto;

import it.dietiestates.data.dto.NotificaConInfo;
import it.dietiestates.exception.DataAccessException;

import java.util.List;

public interface NotificaConInfoDAO {
    List<NotificaConInfo> getAll(String emailAgente) throws DataAccessException;
}
