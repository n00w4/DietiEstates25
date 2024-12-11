package dietiEstates.backendDietiEstates.DAO;

import dietiEstates.backendDietiEstates.Data.Agenzia;
import dietiEstates.backendDietiEstates.Exception.DataAccessException;

public interface AgenziaDAO {
	boolean insert(Agenzia agenzia) throws DataAccessException;
}
