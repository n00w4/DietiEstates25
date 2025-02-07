package it.dietiestates.dao.dto;

import it.dietiestates.data.dto.PrenotazioneConInfo;
import it.dietiestates.exception.DataAccessException;

import java.util.List;

public interface PrenotazioneConInfoDAO {
    List<PrenotazioneConInfo> getPrenotazioniAccettate(String emailAgente) throws DataAccessException;

    List<PrenotazioneConInfo> getPrenotazioniAccettateCliente(String emailCliente) throws DataAccessException;
}
