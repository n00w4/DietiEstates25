package it.dietiestates.dao.sql.model;

import it.dietiestates.dao.model.GestoreDAO;
import it.dietiestates.data.dto.ChangeAdminPwdForm;
import it.dietiestates.data.model.Gestore;
import it.dietiestates.exception.DataAccessException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SQLGestoreDAO implements GestoreDAO {
    private final Connection connection;

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

            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DataAccessException("Errore durante l'inserimento del gestore", e);
        }
    }

    @Override
    public boolean updateAdminPassword(ChangeAdminPwdForm form) throws DataAccessException {
        String query = "UPDATE est.Gestore SET passwordAdmin=? WHERE email=? AND passwordAdmin=? AND partitaIVA=?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, form.getNewPassword());
            statement.setString(2, form.getEmail());
            statement.setString(3, form.getOldPassword());
            statement.setString(4, form.getPartitaIVA());

            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DataAccessException("Errore durante l'update della password gestore", e);
        }
    }
}

