package it.dietiEstates.dao.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import it.dietiEstates.dao.NotificaDAO;
import it.dietiEstates.data.Notifica;
import it.dietiEstates.exception.DataAccessException;

public class SQLNotificaDAO implements NotificaDAO {
    private Connection connection;

    public SQLNotificaDAO(Connection dbConnection) {
        this.connection = dbConnection;
    }

    @Override
    public boolean insert(Notifica notifica) throws DataAccessException {
        try {
            String sql = "INSERT INTO est.Notifica (dataOra, email, idPrenotazione) VALUES (?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            String emailAgente = notifica.getAgente().getEmail();
            int prenotazioneID = notifica.getPrenotazione().getID();

            statement.setTimestamp(1, notifica.getDataOra());
            statement.setString(2, emailAgente);
            statement.setInt(3, prenotazioneID);

            return statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}

