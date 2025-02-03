package it.dietiestates.dao;

import it.dietiestates.data.dto.PrenotazioneConInfo;
import it.dietiestates.exception.DataAccessException;

import java.util.List;

public interface PrenotazioneConInfoDAO {
    List<PrenotazioneConInfo> getPrenotazioniAccettate(String emailAgente) throws DataAccessException;
}
