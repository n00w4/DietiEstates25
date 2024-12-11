package dietiEstates.backendDietiEstates.DAO;

import dietiEstates.backendDietiEstates.Data.Amministratore;
import dietiEstates.backendDietiEstates.Exception.DataAccessException;

public interface AmministratoreDAO {
	boolean insert(Amministratore amministratore) throws DataAccessException;
}
