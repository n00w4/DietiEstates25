package it.dietiEstates.dao.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import it.dietiEstates.dao.PrenotazioneDAO;
import it.dietiEstates.data.Prenotazione;
import it.dietiEstates.exception.DataAccessException;

public class SQLPrenotazioneDAO implements PrenotazioneDAO {
    private Connection connection;

    public SQLPrenotazioneDAO(Connection dbConnection) {
        this.connection = dbConnection;
    }

    @Override
    public boolean insert(Prenotazione prenotazione) throws DataAccessException {
        try {
            String sql = "INSERT INTO est.Prenotazione (dataInizio, dataFine, isAccettata, idAnnuncio, email) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            int idAnnuncio = prenotazione.getID();
            String emailCliente = prenotazione.getCliente().getEmail();

            statement.setTimestamp(1, prenotazione.getDataInizio());
            statement.setTimestamp(2, prenotazione.getDataFine());
            statement.setBoolean(3, prenotazione.isAccettata());
            statement.setInt(4, idAnnuncio);
            statement.setString(5, emailCliente);

            return statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
