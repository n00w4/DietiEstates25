package dietiEstates.backendDietiEstates.DAO.SQLImplementations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import dietiEstates.backendDietiEstates.DAO.NotificaDAO;
import dietiEstates.backendDietiEstates.Data.Notifica;
import dietiEstates.backendDietiEstates.Exception.DataAccessException;

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

