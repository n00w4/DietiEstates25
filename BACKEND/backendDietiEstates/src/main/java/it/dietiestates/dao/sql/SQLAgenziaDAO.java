package it.dietiestates.dao.sql;

import it.dietiestates.dao.AgenziaDAO;
import it.dietiestates.data.Agenzia;
import it.dietiestates.exception.DataAccessException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SQLAgenziaDAO implements AgenziaDAO {
	private final Connection connection;
	
	public SQLAgenziaDAO(Connection dbConnection) {
		this.connection = dbConnection;
	}
	
	@Override
	public boolean insert(Agenzia agenzia) throws DataAccessException {
		String query = "INSERT INTO est.Agenzia VALUES (?, ?)";
		try (PreparedStatement statement = connection.prepareStatement(query)) {
			statement.setString(1, agenzia.getNomeAgenzia());
			statement.setString(2, agenzia.getPartitaIVA());

			return statement.executeUpdate() > 0;
		} catch (SQLException e) {
			throw new DataAccessException("Errore durante l'inserimento dell'agenzia", e);
		}
	}
}
