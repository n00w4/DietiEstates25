package it.dietiEstates.dao.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import it.dietiEstates.dao.GestoreDAO;
import it.dietiEstates.data.Gestore;
import it.dietiEstates.exception.DataAccessException;

public class SQLGestoreDAO implements GestoreDAO {
    private Connection connection;

    public SQLGestoreDAO(Connection dbConnection) {
        this.connection = dbConnection;
    }

    @Override
    public boolean insert(Gestore gestore) throws DataAccessException {
        try {
            String sql = "INSERT INTO est.Gestore VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            
            statement.setString(1, gestore.getNome());
            statement.setString(2, gestore.getCognome());
            statement.setString(3, gestore.getEmail());
            statement.setString(4, gestore.getPassword());
            statement.setString(5, gestore.getPasswordAdmin());
            statement.setString(6, gestore.getPartitaIVA());

            return statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}

