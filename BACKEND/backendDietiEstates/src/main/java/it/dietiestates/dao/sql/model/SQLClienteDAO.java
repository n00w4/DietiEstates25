package it.dietiestates.dao.sql.model;

import it.dietiestates.dao.model.ClienteDAO;
import it.dietiestates.data.model.Cliente;
import it.dietiestates.exception.DataAccessException;
import it.dietiestates.exception.UniqueConstraintViolationException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SQLClienteDAO implements ClienteDAO {
	private final Connection connection;
	
	public SQLClienteDAO(Connection dbConnection) {
		this.connection = dbConnection;
	}
	
	@Override
	public boolean insert(Cliente cliente) throws DataAccessException {
		String query = "INSERT INTO est.Cliente VALUES (?, ?, ?, ?)";
		try (PreparedStatement statement = connection.prepareStatement(query)) {
	        statement.setString(1, cliente.getNome());
	        statement.setString(2, cliente.getCognome());
	        statement.setString(3, cliente.getEmail());
	        statement.setString(4, cliente.getPassword());

			return statement.executeUpdate() > 0;
	    } catch (SQLException e) {
			if ("23505".equals(e.getSQLState())) {
				throw new UniqueConstraintViolationException("Email già registrata");
			}
			throw new DataAccessException("Errore durante l'inserimento del cliente", e);
		}
	}
}
