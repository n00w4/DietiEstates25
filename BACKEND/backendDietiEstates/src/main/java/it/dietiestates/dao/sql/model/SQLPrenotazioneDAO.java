package it.dietiestates.dao.sql.model;

import it.dietiestates.dao.model.PrenotazioneDAO;
import it.dietiestates.data.model.Prenotazione;
import it.dietiestates.exception.DataAccessException;
import it.dietiestates.exception.ForeignKeyConstraintViolationException;
import it.dietiestates.exception.OverlappingBookingException;
import it.dietiestates.exception.ValidBookingException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

public class SQLPrenotazioneDAO implements PrenotazioneDAO {
    private final Connection connection;

    public SQLPrenotazioneDAO(Connection dbConnection) {
        this.connection = dbConnection;
    }

    @Override
    public boolean insert(Prenotazione prenotazione) throws DataAccessException {
        String query = "INSERT INTO est.Prenotazione (dataInizio, dataFine, isAccettata, idAnnuncio, email) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setTimestamp(1, Timestamp.valueOf(prenotazione.getDataInizio()));
            statement.setTimestamp(2, Timestamp.valueOf(prenotazione.getDataFine()));
            statement.setObject(3, prenotazione.isAccettata());
            statement.setInt(4, prenotazione.getIdAnnuncio());
            statement.setString(5, prenotazione.getEmailCliente());

            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            if (e.getMessage().contains("Sovrapposizione di prenotazioni")) {
                throw new OverlappingBookingException(e.getMessage());
            }
            if (e.getMessage().contains("Non puoi prenotare lo stesso annuncio")) {
                throw new ValidBookingException(e.getMessage());
            }
            if ("23503".equals(e.getSQLState())) {
                throw new ForeignKeyConstraintViolationException("Nessun annuncio trovato da prenotare");
            }
            throw new DataAccessException("Errore durante l'inserimento della prenotazione", e);
        }
    }

    @Override
    public boolean delete(Prenotazione prenotazione) throws DataAccessException {
        String query = "DELETE FROM est.Prenotazione WHERE idPrenotazione = ? AND email = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, prenotazione.getID());
            statement.setString(2, prenotazione.getEmailCliente());

            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DataAccessException("Errore durante l'eliminazione della prenotazione", e);
        }
    }

    @Override
    public boolean updateStatusPrenotazione(Prenotazione prenotazione) throws DataAccessException {
        String query = "UPDATE est.Prenotazione SET isAccettata = ? WHERE idPrenotazione = ? AND idAnnuncio = ? AND email = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setBoolean(1, prenotazione.isAccettata());
            statement.setInt(2, prenotazione.getID());
            statement.setInt(3, prenotazione.getIdAnnuncio());
            statement.setString(4, prenotazione.getEmailCliente());

            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DataAccessException("Errore durante l'aggiornamento dello stato della prenotazione. Si prega di verificare i dati inseriti e riprovare.", e);
        }
    }
}
