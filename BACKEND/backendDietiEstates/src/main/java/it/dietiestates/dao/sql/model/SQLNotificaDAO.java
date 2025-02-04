package it.dietiestates.dao.sql.model;

import it.dietiestates.dao.model.NotificaDAO;
import it.dietiestates.data.model.Notifica;
import it.dietiestates.exception.DataAccessException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SQLNotificaDAO implements NotificaDAO {
    private static final String COLUMN_ID = "idNotifica";
    private static final String COLUMN_DATA_ORA = "dataOra";
    private static final String COLUMN_ID_PRENOTAZIONE = "idPrenotazione";

    private final Connection connection;

    public SQLNotificaDAO(Connection dbConnection) {
        this.connection = dbConnection;
    }

    @Override
    public boolean insert(Notifica notifica) throws DataAccessException {
        String query = "INSERT INTO est.Notifica (dataOra, email, idPrenotazione) VALUES (?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setTimestamp(1, notifica.getDataOra());
            statement.setString(2, notifica.getEmailAgente());
            statement.setInt(3, notifica.getIdPrenotazione());

            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DataAccessException("Errore durante l'inserimento della notifica", e);
        }
    }

    @Override
    public List<Notifica> getAllNotifiche(String emailAgente) throws DataAccessException {
        String query = "SELECT idNotifica, dataOra, email, idPrenotazione FROM est.Notifica WHERE email = ?";
        List<Notifica> listaNotifiche = new ArrayList<>();

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    int id = resultSet.getInt(COLUMN_ID);
                    Timestamp dataOra = resultSet.getTimestamp(COLUMN_DATA_ORA);
                    int idPrenotazione = resultSet.getInt(COLUMN_ID_PRENOTAZIONE);

                    Notifica notifica = new Notifica(id, dataOra, emailAgente, idPrenotazione);

                    listaNotifiche.add(notifica);
                }
            }
        } catch (SQLException e) {
            throw new DataAccessException("Errore durante la ricerca delle notifiche", e);
        }
        return listaNotifiche;
    }
}
