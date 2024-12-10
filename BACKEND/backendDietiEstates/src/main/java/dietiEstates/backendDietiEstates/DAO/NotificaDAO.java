package dietiEstates.backendDietiEstates.DAO;

import dietiEstates.backendDietiEstates.Data.Notifica;
import dietiEstates.backendDietiEstates.Exception.DataAccessException;

public interface NotificaDAO {
	boolean insert(Notifica notifica) throws DataAccessException;
}
