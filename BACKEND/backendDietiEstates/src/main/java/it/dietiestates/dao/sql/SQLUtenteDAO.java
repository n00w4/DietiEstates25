package it.dietiestates.dao.sql;

import it.dietiestates.dao.UtenteDAO;
import it.dietiestates.data.Utente;
import it.dietiestates.data.factory.UtenteFactory;
import it.dietiestates.exception.DataAccessException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SQLUtenteDAO implements UtenteDAO {
	private final Connection connection;

	public SQLUtenteDAO(Connection connection) {
		this.connection = connection;
	}

	@Override
	public Utente autenticaUtente(String email, String password) throws DataAccessException {
		String query = "SELECT tipo_utente, nome, cognome, email, password, passwordAdmin, partitaIVA "
				+ "FROM est.utenti_unificati "
				+ "WHERE email = ? AND password = ?";

		try (PreparedStatement statement = connection.prepareStatement(query)) {
			statement.setString(1, email);
			statement.setString(2, password);

			try (ResultSet resultSet = statement.executeQuery()) {
				if (resultSet.next()) {
					String tipoUtente = resultSet.getString("tipo_utente");
					String nome = resultSet.getString("nome");
					String cognome = resultSet.getString("cognome");
					String emailUtente = resultSet.getString("email");
					String passwordUtente = resultSet.getString("password");
					String passwordAdmin = resultSet.getString("passwordAdmin");
					String partitaIVA = resultSet.getString("partitaIVA");

					return UtenteFactory.creaUtente(tipoUtente, nome, cognome, emailUtente, passwordUtente, passwordAdmin, partitaIVA);
				}
			}
		} catch (SQLException e) {
			throw new DataAccessException("Errore durante l'autenticazione dell'utente", e);
		}
		return null;
	}
}
