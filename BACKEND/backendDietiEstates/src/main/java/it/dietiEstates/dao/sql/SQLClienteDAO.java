package it.dietiEstates.dao.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import it.dietiEstates.dao.ClienteDAO;
import it.dietiEstates.data.Cliente;
import it.dietiEstates.exception.DataAccessException;

public class SQLClienteDAO implements ClienteDAO {
	private Connection connection;
	
	public SQLClienteDAO(Connection dbConnection) {
		this.connection = dbConnection;
	}
	
	@Override
	public boolean insert(Cliente cliente) throws DataAccessException {
	    try {
	        String sql = "INSERT INTO est.Cliente VALUES (?, ?, ?, ?)";
	        PreparedStatement statement = connection.prepareStatement(sql);
	        
	        statement.setString(1, cliente.getNome());
	        statement.setString(2, cliente.getCognome());
	        statement.setString(3, cliente.getEmail());
	        statement.setString(4, cliente.getPassword());

	        return statement.execute();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return false;
	}
}
