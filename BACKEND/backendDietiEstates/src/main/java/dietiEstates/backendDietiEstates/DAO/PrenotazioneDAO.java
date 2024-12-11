package dietiEstates.backendDietiEstates.DAO;

import dietiEstates.backendDietiEstates.Data.Prenotazione;
import dietiEstates.backendDietiEstates.Exception.DataAccessException;

public interface PrenotazioneDAO {
	boolean insert(Prenotazione prenotazione) throws DataAccessException;
}
