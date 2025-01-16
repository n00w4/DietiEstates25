package it.dietiestates.dao.sql;

import it.dietiestates.dao.AmministratoreDAO;
import it.dietiestates.data.Amministratore;
import it.dietiestates.exception.DataAccessException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SQLAmministratoreDAO implements AmministratoreDAO {
	private final Connection connection;
	
	public SQLAmministratoreDAO(Connection dbConnection) {
		this.connection = dbConnection;
	}
	
	@Override
	public boolean insert(Amministratore amministratore) throws DataAccessException {
		String query = "INSERT INTO est.Amministratore VALUES (?. ?, ?, ?, ?)";
		try (PreparedStatement statement = connection.prepareStatement(query)) {
			statement.setString(1, amministratore.getNome());
			statement.setString(2, amministratore.getCognome());
			statement.setString(3, amministratore.getEmail());
			statement.setString(4, amministratore.getPassword());
			statement.setString(5, amministratore.getPartitaIVA());

			return statement.executeUpdate() > 0;
		} catch (SQLException e) {
			throw new DataAccessException("Errore durante l'inserimento dell'amministratore", e);
		}
	}
}
