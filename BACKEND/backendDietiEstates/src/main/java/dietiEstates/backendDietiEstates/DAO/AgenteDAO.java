package dietiEstates.backendDietiEstates.DAO;

import dietiEstates.backendDietiEstates.Data.Agente;
import dietiEstates.backendDietiEstates.Exception.DataAccessException;

public interface AgenteDAO {
	boolean insert(Agente agente) throws DataAccessException;
}
