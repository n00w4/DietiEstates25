package dietiEstates.backendDietiEstates.DAO;

import dietiEstates.backendDietiEstates.Data.Annuncio;
import dietiEstates.backendDietiEstates.Exception.DataAccessException;

public interface AnnuncioDAO {
	boolean insert(Annuncio annuncio) throws DataAccessException;
}
