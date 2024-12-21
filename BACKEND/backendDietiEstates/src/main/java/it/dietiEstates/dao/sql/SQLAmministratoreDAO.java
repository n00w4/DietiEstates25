package it.dietiEstates.dao.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import it.dietiEstates.dao.AmministratoreDAO;
import it.dietiEstates.data.Amministratore;
import it.dietiEstates.exception.DataAccessException;

public class SQLAmministratoreDAO implements AmministratoreDAO {
	private Connection connection;
	
	public SQLAmministratoreDAO(Connection dbConnection) {
		this.connection = dbConnection;
	}
	
	@Override
	public boolean insert(Amministratore amministratore) throws DataAccessException {		
		try {
			String sql = "INSERT INTO est.Amministratore VALUES (?. ?, ?, ?, ?)";
			PreparedStatement statement = connection.prepareStatement(sql);
			
			statement.setString(1, amministratore.getNome());
			statement.setString(2, amministratore.getCognome());
			statement.setString(3, amministratore.getEmail());
			statement.setString(4, amministratore.getPassword());
			statement.setString(5, amministratore.getPartitaIVA());
			
			return statement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
}
