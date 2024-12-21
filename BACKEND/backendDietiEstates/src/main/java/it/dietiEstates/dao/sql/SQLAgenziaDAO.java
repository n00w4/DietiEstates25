package it.dietiEstates.dao.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import it.dietiEstates.dao.AgenziaDAO;
import it.dietiEstates.data.Agenzia;
import it.dietiEstates.exception.DataAccessException;

public class SQLAgenziaDAO implements AgenziaDAO {
	private Connection connection;
	
	public SQLAgenziaDAO(Connection dbConnection) {
		this.connection = dbConnection;
	}
	
	@Override
	public boolean insert(Agenzia agenzia) throws DataAccessException {		
		try {
			String sql = "INSERT INTO est.Agenzia VALUES (?. ?)";
			PreparedStatement statement = connection.prepareStatement(sql);
			
			statement.setString(1, agenzia.getNomeAgenzia());
			statement.setString(2, agenzia.getPartitaIVA());
			
			return statement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
}
