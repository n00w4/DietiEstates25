package dietiEstates.backendDietiEstates.DAO;

import dietiEstates.backendDietiEstates.Data.Utente;
import dietiEstates.backendDietiEstates.Exception.DataAccessException;

public interface UtenteDAO {
	Utente autenticaUtente(String email, String password) throws DataAccessException;
}
