package it.dietiestates.dao.adapter;

import it.dietiestates.data.dto.AddUtenteForm;
import it.dietiestates.exception.DataAccessException;

public interface UtenteDAOAdapter {
    boolean insertUtente(AddUtenteForm form) throws DataAccessException;
}
