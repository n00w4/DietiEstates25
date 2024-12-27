package it.dietiestates.dao.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import it.dietiestates.dao.NotificaDAO;
import it.dietiestates.data.Notifica;
import it.dietiestates.exception.DataAccessException;

public class SQLNotificaDAO implements NotificaDAO {
    private final Connection connection;

    public SQLNotificaDAO(Connection dbConnection) {
        this.connection = dbConnection;
    }

    @Override
    public boolean insert(Notifica notifica) throws DataAccessException {
        String query = "INSERT INTO est.Notifica (dataOra, email, idPrenotazione) VALUES (?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            String emailAgente = notifica.getAgente().getEmail();
            int prenotazioneID = notifica.getPrenotazione().getID();

            statement.setTimestamp(1, notifica.getDataOra());
            statement.setString(2, emailAgente);
            statement.setInt(3, prenotazioneID);

            return statement.execute();
        } catch (SQLException e) {
            throw new DataAccessException("Errore durante l'inserimento della notifica", e);
        }
    }
}
