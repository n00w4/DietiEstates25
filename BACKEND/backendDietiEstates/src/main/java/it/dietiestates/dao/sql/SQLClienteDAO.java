package it.dietiestates.dao.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import it.dietiestates.dao.ClienteDAO;
import it.dietiestates.data.Cliente;
import it.dietiestates.exception.DataAccessException;

public class SQLClienteDAO implements ClienteDAO {
	private Connection connection;
	
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

	        return statement.execute();
	    } catch (SQLException e) {
	        throw new DataAccessException("Errore durante l'inserimento del cliente", e);
	    }
	}
}
