package dietiEstates.backendDietiEstates.DAO;

import dietiEstates.backendDietiEstates.Data.Gestore;
import dietiEstates.backendDietiEstates.Exception.DataAccessException;

public interface GestoreDAO {
	boolean insert(Gestore gestore) throws DataAccessException;
}
