package it.dietiestates.dao;

import it.dietiestates.data.Annuncio;
import it.dietiestates.dto.RicercaAnnuncio;
import it.dietiestates.exception.DataAccessException;

import java.util.List;

public interface AnnuncioDAO {
	boolean insert(Annuncio annuncio) throws DataAccessException;
	boolean delete(Annuncio annuncio) throws DataAccessException;
	List<Annuncio> getAnnunciFromSearch(RicercaAnnuncio ricerca) throws DataAccessException;
	List<Annuncio> getAllAnnunci() throws DataAccessException;
}
