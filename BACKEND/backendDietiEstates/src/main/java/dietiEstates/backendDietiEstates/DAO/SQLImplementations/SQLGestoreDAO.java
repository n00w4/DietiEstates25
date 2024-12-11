package dietiEstates.backendDietiEstates.DAO.SQLImplementations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import dietiEstates.backendDietiEstates.DAO.GestoreDAO;
import dietiEstates.backendDietiEstates.Data.Gestore;
import dietiEstates.backendDietiEstates.Exception.DataAccessException;

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
            String partitaIVA = gestore.getAgenzia().getPartitaIVA();
            
            statement.setString(1, gestore.getNome());
            statement.setString(2, gestore.getCognome());
            statement.setString(3, gestore.getEmail());
            statement.setString(4, gestore.getPassword());
            statement.setString(5, gestore.getPasswordAdmin());
            statement.setString(6, partitaIVA);

            return statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}

