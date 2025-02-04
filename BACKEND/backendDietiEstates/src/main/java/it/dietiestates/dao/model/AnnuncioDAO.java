package it.dietiestates.dao.model;

import it.dietiestates.data.dto.RicercaAnnuncio;
import it.dietiestates.data.model.Annuncio;
import it.dietiestates.exception.DataAccessException;

import java.util.List;

public interface AnnuncioDAO {
	boolean insert(Annuncio annuncio) throws DataAccessException;
	boolean delete(Annuncio annuncio) throws DataAccessException;
	List<Annuncio> getAnnunciFromSearch(RicercaAnnuncio ricerca) throws DataAccessException;
	List<Annuncio> getAllAnnunci() throws DataAccessException;
	List<Annuncio> getAnnunciFromPosition(double longitude, double latitude) throws DataAccessException;
}
