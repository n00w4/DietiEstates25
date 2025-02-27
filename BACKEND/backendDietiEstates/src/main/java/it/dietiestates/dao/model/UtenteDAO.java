package it.dietiestates.dao.model;

import it.dietiestates.data.dto.AddUtenteForm;
import it.dietiestates.data.model.Utente;
import it.dietiestates.exception.DataAccessException;

public interface UtenteDAO {
	Utente autenticaUtente(String email, String password) throws DataAccessException;
	boolean insertUtente(AddUtenteForm form) throws DataAccessException;
}
