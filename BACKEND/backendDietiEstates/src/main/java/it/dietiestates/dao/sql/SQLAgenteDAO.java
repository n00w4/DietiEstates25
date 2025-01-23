package it.dietiestates.dao.sql;

import it.dietiestates.dao.AgenteDAO;
import it.dietiestates.data.Agente;
import it.dietiestates.exception.DataAccessException;
import it.dietiestates.exception.UniqueConstraintViolationException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SQLAgenteDAO implements AgenteDAO {
	private final Connection connection;
	
	public SQLAgenteDAO(Connection dbConnection) {
		this.connection = dbConnection;
	}
	
	@Override
	public boolean insert(Agente agente) throws DataAccessException {
		String sql = "INSERT INTO est.Agente VALUES (?, ?, ?, ?, ?)";
		try (PreparedStatement statement = connection.prepareStatement(sql)) {
			statement.setString(1, agente.getNome());
			statement.setString(2, agente.getCognome());
			statement.setString(3, agente.getEmail());
			statement.setString(4, agente.getPassword());
			statement.setString(5, agente.getPartitaIVA());

			return statement.executeUpdate() > 0;
		} catch (SQLException e) {
			if ("23505".equals(e.getSQLState())) {
				throw new UniqueConstraintViolationException("Agente già registrato");
			}
			throw new DataAccessException("Errore durante l'inserimento dell'agente", e);
		}
	}

}
