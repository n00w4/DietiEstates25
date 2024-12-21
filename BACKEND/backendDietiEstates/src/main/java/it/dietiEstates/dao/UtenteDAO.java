package it.dietiEstates.dao;

import it.dietiEstates.data.Utente;
import it.dietiEstates.exception.DataAccessException;

public interface UtenteDAO {
	Utente autenticaUtente(String email, String password) throws DataAccessException;
}
