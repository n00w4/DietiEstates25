package it.dietiestates.dao.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import it.dietiestates.dao.AnnuncioDAO;
import it.dietiestates.data.Annuncio;
import it.dietiestates.exception.DataAccessException;

public class SQLAnnuncioDAO implements AnnuncioDAO {
	private Connection connection;
	
	public SQLAnnuncioDAO(Connection dbConnection) {
		this.connection = dbConnection;
	}
	
	@Override
	public boolean insert(Annuncio annuncio) throws DataAccessException {
		String query = "INSERT INTO est.Annuncio VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		try (PreparedStatement statement = connection.prepareStatement(query)) {
	        String emailAgente = annuncio.getAgente().getEmail();
	        
	        statement.setString(1, annuncio.getTitolo());
	        statement.setString(2, annuncio.getIndirizzo());
	        statement.setString(3, annuncio.getImmagine());
	        statement.setString(4, annuncio.getDescrizione());
	        statement.setInt(5, annuncio.getDimensioni());
	        statement.setFloat(6, annuncio.getPrezzo());
	        statement.setString(7, annuncio.getPiano());
	        statement.setInt(8, annuncio.getNumeroStanze());
	        statement.setString(9, annuncio.getClasseEnergetica());
	        statement.setBoolean(10, annuncio.isAscensore());
	        statement.setBoolean(11, annuncio.isPortineria());
	        statement.setBoolean(12, annuncio.isClimatizzazione());
	        statement.setBoolean(13, annuncio.isBoxAuto());
	        statement.setBoolean(14, annuncio.isTerrazzo());
	        statement.setBoolean(15, annuncio.isGiardino());
	        statement.setString(16, annuncio.getTipoAnnuncio());
	        statement.setString(17, emailAgente);

	        return statement.execute();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return false;
	}

	@Override
	public boolean delete(Annuncio annuncio) throws DataAccessException {
		String query = "DELETE FROM est.Annuncio WHERE idAnnuncio = ? AND email = ?";
		try (PreparedStatement statement = connection.prepareStatement(query)) {
			String emailAgente = annuncio.getAgente().getEmail();

			statement.setInt(1, annuncio.getID());
			statement.setString(2, emailAgente);

			return statement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
}
