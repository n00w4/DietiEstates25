package it.dietiestates.dao.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import it.dietiestates.dao.GestoreDAO;
import it.dietiestates.data.Gestore;
import it.dietiestates.exception.DataAccessException;

public class SQLGestoreDAO implements GestoreDAO {
    private Connection connection;

    public SQLGestoreDAO(Connection dbConnection) {
        this.connection = dbConnection;
    }

    @Override
    public boolean insert(Gestore gestore) throws DataAccessException {
        String query = "INSERT INTO est.Gestore VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, gestore.getNome());
            statement.setString(2, gestore.getCognome());
            statement.setString(3, gestore.getEmail());
            statement.setString(4, gestore.getPassword());
            statement.setString(5, gestore.getPasswordAdmin());
            statement.setString(6, gestore.getPartitaIVA());

            return statement.execute();
        } catch (SQLException e) {
            throw new DataAccessException("Errore durante l'inserimento del gestore", e);
        }
    }
}

