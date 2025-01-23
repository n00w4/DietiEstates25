package it.dietiestates.dao.sql;

import it.dietiestates.dao.PrenotazioneDAO;
import it.dietiestates.data.Prenotazione;
import it.dietiestates.exception.DataAccessException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SQLPrenotazioneDAO implements PrenotazioneDAO {
    private final Connection connection;

    public SQLPrenotazioneDAO(Connection dbConnection) {
        this.connection = dbConnection;
    }

    @Override
    public boolean insert(Prenotazione prenotazione) throws DataAccessException {
        String query = "INSERT INTO est.Prenotazione (dataInizio, dataFine, isAccettata, idAnnuncio, email) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setTimestamp(1, prenotazione.getDataInizio());
            statement.setTimestamp(2, prenotazione.getDataFine());
            statement.setBoolean(3, prenotazione.isAccettata());
            statement.setInt(4, prenotazione.getIdAnnuncio());
            statement.setString(5, prenotazione.getEmailCliente());

            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
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
}
