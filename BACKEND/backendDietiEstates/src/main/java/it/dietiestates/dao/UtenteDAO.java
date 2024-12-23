package it.dietiestates.dao;

import it.dietiestates.data.Utente;
import it.dietiestates.exception.DataAccessException;

public interface UtenteDAO {
	Utente autenticaUtente(String email, String password) throws DataAccessException;
}
