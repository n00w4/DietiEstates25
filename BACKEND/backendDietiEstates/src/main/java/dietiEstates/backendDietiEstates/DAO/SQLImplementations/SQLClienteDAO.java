package dietiEstates.backendDietiEstates.DAO.SQLImplementations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import dietiEstates.backendDietiEstates.DAO.ClienteDAO;
import dietiEstates.backendDietiEstates.Data.Cliente;
import dietiEstates.backendDietiEstates.Exception.DataAccessException;

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
