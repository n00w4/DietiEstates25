package it.dietiestates.dao.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import it.dietiestates.dao.UtenteDAO;
import it.dietiestates.data.Utente;
import it.dietiestates.exception.DataAccessException;
import it.dietiestates.utils.UtenteFactory;

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

			try (ResultSet rs = statement.executeQuery()) {
				if (rs.next()) {
					String tipoUtente = rs.getString("tipo_utente");
					String nome = rs.getString("nome");
					String cognome = rs.getString("cognome");
					String emailUtente = rs.getString("email");
					String passwordUtente = rs.getString("password");
					String passwordAdmin = rs.getString("passwordAdmin");
					String partitaIVA = rs.getString("partitaIVA");

					return UtenteFactory.creaUtente(tipoUtente, nome, cognome, emailUtente, passwordUtente, passwordAdmin, partitaIVA);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DataAccessException("Errore durante l'autenticazione dell'utente", e);
		}
		return null;
	}
}
