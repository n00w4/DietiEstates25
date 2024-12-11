package dietiEstates.backendDietiEstates.DAO.SQLImplementations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import dietiEstates.backendDietiEstates.DAO.AgenteDAO;
import dietiEstates.backendDietiEstates.Data.Agente;
import dietiEstates.backendDietiEstates.Exception.DataAccessException;

public class SQLAgenteDAO implements AgenteDAO {
	private Connection connection;
	
	public SQLAgenteDAO(Connection dbConnection) {
		this.connection = dbConnection;
	}
	
	@Override
	public boolean insert(Agente agente) throws DataAccessException {		
		try {
			String sql = "INSERT INTO est.Agente VALUES (?. ?, ?, ?, ?)";
			PreparedStatement statement = connection.prepareStatement(sql);
			
			String partitaIVA = agente.getAgenzia().getPartitaIVA();
			statement.setString(1, agente.getNome());
			statement.setString(2, agente.getCognome());
			statement.setString(3, agente.getEmail());
			statement.setString(4, agente.getPassword());
			statement.setString(5, partitaIVA);
			
			return statement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

}
