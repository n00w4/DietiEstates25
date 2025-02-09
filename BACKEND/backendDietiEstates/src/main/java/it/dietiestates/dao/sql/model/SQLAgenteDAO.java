package it.dietiestates.dao.sql.model;

import it.dietiestates.dao.model.AgenteDAO;
import it.dietiestates.data.model.Agente;
import it.dietiestates.exception.DataAccessException;
import it.dietiestates.exception.UniqueConstraintViolationException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SQLAgenteDAO implements AgenteDAO {
	private final Connection connection;

	public SQLAgenteDAO(Connection dbConnection) {
		this.connection = dbConnection;
	}

	@Override
	public boolean insert(Agente agente) throws DataAccessException {
		String query = "INSERT INTO est.Agente VALUES (?, ?, ?, ?, ?)";
		try (PreparedStatement statement = connection.prepareStatement(query)) {
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

	@Override
	public String findEmailByIdAnnuncio(int idAnnuncio) throws DataAccessException {
		String query = "SELECT email FROM est.Annuncio WHERE idAnnuncio = ?";
		try (PreparedStatement statement = connection.prepareStatement(query)) {
			statement.setInt(1, idAnnuncio);
			try (ResultSet resultSet = statement.executeQuery()) {
				if (resultSet.next()) {
					return resultSet.getString("email");
				}
			}
		} catch (SQLException e) {
			throw new DataAccessException("Errore durante il recupero dell'email dell'agente", e);
		}
		return null;
	}
}