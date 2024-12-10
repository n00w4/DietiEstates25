package dietiEstates.backendDietiEstates.DAO;

import dietiEstates.backendDietiEstates.Data.Cliente;
import dietiEstates.backendDietiEstates.Exception.DataAccessException;

public interface ClienteDAO {
	boolean insert(Cliente cliente) throws DataAccessException;
}
