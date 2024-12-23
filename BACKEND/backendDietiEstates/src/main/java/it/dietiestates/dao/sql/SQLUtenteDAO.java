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
	private Connection connection;
	
	public SQLUtenteDAO(Connection connection) {
        this.connection = connection;
    }

	@Override
	public Utente autenticaUtente(String email, String password) throws DataAccessException {
		String query = "SELECT tipo_utente, nome, cognome, email, password, passwordAdmin, partitaIVA "
				+ "FROM est.utenti_unificati "
				+ "WHERE email = ? AND password = ?";
		
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, email);
			statement.setString(2, password);
			ResultSet rs = statement.executeQuery();
			if (rs.next()) {
				String tipoUtente = rs.getString("tipo_utente");
				String nome = rs.getString("nome");
				String cognome = rs.getString("cognome");
				String emailUtente = rs.getString("email");
				String passwordUtente = rs.getString("password");
				String passwordAdmin = rs.getString("passwordAdmin");
				String partitaIVA = rs.getString("partitaIVA");
				
				Utente utente = UtenteFactory.creaUtente(tipoUtente, nome, cognome, emailUtente, passwordUtente, passwordAdmin, partitaIVA);
				return utente;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
