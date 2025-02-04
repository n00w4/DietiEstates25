package it.dietiestates.dao.sql.model;

import it.dietiestates.dao.model.AnnuncioDAO;
import it.dietiestates.data.dto.RicercaAnnuncio;
import it.dietiestates.data.model.Annuncio;
import it.dietiestates.exception.DataAccessException;
import it.dietiestates.exception.ForeignKeyConstraintViolationException;
import it.dietiestates.exception.UniqueConstraintViolationException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SQLAnnuncioDAO implements AnnuncioDAO {
	private static final String COLUMN_ID = "idAnnuncio";
	private static final String COLUMN_TITOLO = "titolo";
	private static final String COLUMN_INDIRIZZO = "indirizzo";
	private static final String COLUMN_IMMAGINE = "immagine";
	private static final String COLUMN_DESCRIZIONE = "descrizione";
	private static final String COLUMN_DIMENSIONI = "dimensioni";
	private static final String COLUMN_PREZZO = "prezzo";
	private static final String COLUMN_PIANO = "piano";
	private static final String COLUMN_NUMEROSTANZE = "numeroStanze";
	private static final String COLUMN_CLASSEENGERGETICA = "classeEnergetica";
	private static final String COLUMN_ASCENSORE = "ascensore";
	private static final String COLUMN_PORTINERIA = "portineria";
	private static final String COLUMN_CLIMATIZZAZIONE = "climatizzazione";
	private static final String COLUMN_BOXAUTO = "boxAuto";
	private static final String COLUMN_TERRAZZO = "terrazzo";
	private static final String COLUMN_GIARDINO = "giardino";
	private static final String COLUMN_TIPOANNUNCIO = "tipoAnnuncio";
	private static final String COLUMN_POSIZIONE = "posizione";
	private static final String COLUMN_EMAILAGENTE = "email";
	private static final String ERROR_MESSAGE_INSERT = "Errore durante l'inserimento dell'annuncio";
	private static final String ERROR_MESSAGE_DELETE = "Errore durante la cancellazione dell'annuncio";
	private static final String ERROR_MESSAGE_SEARCH = "Errore durante la ricerca dell'annuncio";

	private final Connection connection;

	public SQLAnnuncioDAO(Connection connection) {
		this.connection = connection;
	}

	@Override
	public boolean insert(Annuncio annuncio) throws DataAccessException {
		String query = "INSERT INTO est.Annuncio (" +
				"titolo, indirizzo, immagine, descrizione, dimensioni, prezzo, piano, numeroStanze, classeEnergetica, " +
				"ascensore, portineria, climatizzazione, boxAuto, terrazzo, giardino, tipoAnnuncio, posizione, email" +
				") VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, CAST(? AS public.enum_annuncio), ST_GeomFromWKB(?, 4326), ?)";

		try (PreparedStatement statement = connection.prepareStatement(query)) {
	        statement.setString(1, annuncio.getTitolo());
	        statement.setString(2, annuncio.getIndirizzo());

			if (annuncio.getImmagine() == null) {
				statement.setString(3, "");
			} else {
				statement.setString(3, annuncio.getImmagine());
			}

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
			statement.setBytes(17, hexStringToByteArray(annuncio.getPosizione()));
			statement.setString(18, annuncio.getEmailAgente());

			return statement.executeUpdate() > 0;
	    } catch (SQLException e) {
			if ("23503".equals(e.getSQLState())) {
				throw new ForeignKeyConstraintViolationException("Nessun agente trovato per questo annuncio");
			}
			if ("23505".equals(e.getSQLState())) {
				throw new UniqueConstraintViolationException("Annuncio già inserito");
			}
			throw new DataAccessException(ERROR_MESSAGE_INSERT, e);
	    }
	}

	private byte[] hexStringToByteArray(String hex) {
		int len = hex.length();
		byte[] data = new byte[len / 2];
		for (int i = 0; i < len; i += 2) {
			data[i / 2] = (byte) ((Character.digit(hex.charAt(i), 16) << 4)
					+ Character.digit(hex.charAt(i + 1), 16));
		}
		return data;
	}


	@Override
	public boolean delete(Annuncio annuncio) throws DataAccessException {
		String query = "DELETE FROM est.Annuncio WHERE idAnnuncio = ? AND email = ?";
		try (PreparedStatement statement = connection.prepareStatement(query)) {
			statement.setInt(1, annuncio.getID());
			statement.setString(2, annuncio.getEmailAgente());

			return statement.executeUpdate() > 0;
		} catch (SQLException e) {
			throw new DataAccessException(ERROR_MESSAGE_DELETE, e);
		}
	}

	@Override
	public List<Annuncio> getAnnunciFromSearch(RicercaAnnuncio ricerca) throws DataAccessException {
		String query = "SELECT idAnnuncio, titolo, indirizzo,immagine, descrizione, dimensioni, prezzo, piano, numeroStanze, " +
				"classeEnergetica, ascensore, portineria, climatizzazione, " +
				"boxAuto, terrazzo, giardino, tipoAnnuncio, posizione, email FROM est.Annuncio " +
				"WHERE tipoAnnuncio = ?::public.enum_annuncio AND prezzo BETWEEN ? AND ? "
				+ "AND piano = ? AND numeroStanze >= ? AND classeEnergetica = ? "
				+ "AND ascensore = ? AND portineria = ? AND climatizzazione = ? "
				+ "AND terrazzo = ? AND giardino = ? AND boxAuto = ? "
				+ "AND dimensioni >= ? AND ST_DWithin(posizione, ST_SetSRID(ST_MakePoint(?, ?), 4326), 50000)";

		List<Annuncio> listaAnnunci = new ArrayList<>();
		try (PreparedStatement statement = connection.prepareStatement(query)) {
			statement.setString(1, ricerca.getTipoAnnuncio());
			statement.setFloat(2, ricerca.getPrezzoMin());
			statement.setFloat(3, ricerca.getPrezzoMax());
			statement.setString(4, ricerca.getPiano());
			statement.setInt(5, ricerca.getNumeroStanze());
			statement.setString(6, ricerca.getClasseEnergetica());
			statement.setBoolean(7, ricerca.isAscensore());
			statement.setBoolean(8, ricerca.isPortineria());
			statement.setBoolean(9, ricerca.isClimatizzazione());
			statement.setBoolean(10, ricerca.isTerrazzo());
			statement.setBoolean(11, ricerca.isGiardino());
			statement.setBoolean(12, ricerca.isBoxAuto());
			statement.setInt(13, ricerca.getDimensioni());
			statement.setDouble(14, ricerca.getLongitudine());
			statement.setDouble(15, ricerca.getLatitudine());

			elaborateListResultSet(listaAnnunci, statement);
		} catch (SQLException e) {
			throw new DataAccessException(ERROR_MESSAGE_SEARCH, e);
        }
		return listaAnnunci;
    }

	private void elaborateListResultSet(List<Annuncio> listaAnnunci, PreparedStatement statement) throws SQLException {
		try (ResultSet resultSet = statement.executeQuery()) {
			while (resultSet.next()) {
				int id = resultSet.getInt(COLUMN_ID);
				String titolo = resultSet.getString(COLUMN_TITOLO);
				String indirizzo = resultSet.getString(COLUMN_INDIRIZZO);
				String immagine = resultSet.getString(COLUMN_IMMAGINE);
				String descrizione = resultSet.getString(COLUMN_DESCRIZIONE);
				int dimensioni = resultSet.getInt(COLUMN_DIMENSIONI);
				float prezzo = resultSet.getFloat(COLUMN_PREZZO);
				String piano = resultSet.getString(COLUMN_PIANO);
				int numeroStanze = resultSet.getInt(COLUMN_NUMEROSTANZE);
				String classeEnergetica = resultSet.getString(COLUMN_CLASSEENGERGETICA);
				boolean ascensore = resultSet.getBoolean(COLUMN_ASCENSORE);
				boolean portineria = resultSet.getBoolean(COLUMN_PORTINERIA);
				boolean climatizzazione = resultSet.getBoolean(COLUMN_CLIMATIZZAZIONE);
				boolean boxAuto = resultSet.getBoolean(COLUMN_BOXAUTO);
				boolean terrazzo = resultSet.getBoolean(COLUMN_TERRAZZO);
				boolean giardino = resultSet.getBoolean(COLUMN_GIARDINO);
				String tipoAnnuncio = resultSet.getString(COLUMN_TIPOANNUNCIO);
				String posizione = resultSet.getString(COLUMN_POSIZIONE);
				String emailAgente = resultSet.getString(COLUMN_EMAILAGENTE);

				Annuncio annuncio = new Annuncio.Builder(id)
						.titolo(titolo)
						.indirizzo(indirizzo)
						.immagine(immagine)
						.descrizione(descrizione)
						.dimensioni(dimensioni)
						.prezzo(prezzo)
						.piano(piano)
						.numeroStanze(numeroStanze)
						.classeEnergetica(classeEnergetica)
						.ascensore(ascensore)
						.portineria(portineria)
						.climatizzazione(climatizzazione)
						.boxAuto(boxAuto)
						.terrazzo(terrazzo)
						.giardino(giardino)
						.tipoAnnuncio(tipoAnnuncio)
						.posizione(posizione)
						.emailAgente(emailAgente)
						.build();

				listaAnnunci.add(annuncio);
			}
		}
	}

	@Override
	public List<Annuncio> getAllAnnunci() throws DataAccessException {
		String query = "SELECT idAnnuncio, titolo, indirizzo,immagine, descrizione, dimensioni, " +
				"prezzo, piano, numeroStanze, classeEnergetica, ascensore, portineria, climatizzazione, " +
				"boxAuto, terrazzo, giardino, tipoAnnuncio, posizione, email FROM est.Annuncio";
		List<Annuncio> listaAnnunci = new ArrayList<>();

		try (PreparedStatement statement = connection.prepareStatement(query)) {
			elaborateListResultSet(listaAnnunci, statement);
		} catch (SQLException e) {
			throw new DataAccessException(ERROR_MESSAGE_SEARCH, e);
		}
        return listaAnnunci;
    }

	@Override
	public List<Annuncio> getAnnunciFromPosition(double longitude, double latitude) throws DataAccessException {
		String query = "SELECT idAnnuncio, titolo, indirizzo,immagine, descrizione, dimensioni, prezzo, piano, numeroStanze, " +
				"classeEnergetica, ascensore, portineria, climatizzazione, " +
				"boxAuto, terrazzo, giardino, tipoAnnuncio, posizione, email FROM est.Annuncio " +
				"WHERE ST_DWithin(posizione, ST_SetSRID(ST_MakePoint(?, ?), 4326), 50000)";
		List<Annuncio> listaAnnunci = new ArrayList<>();

		try (PreparedStatement statement = connection.prepareStatement(query)) {
			statement.setDouble(1, longitude);
			statement.setDouble(2, latitude);

			elaborateListResultSet(listaAnnunci, statement);
		} catch (SQLException e) {
			throw new DataAccessException(ERROR_MESSAGE_SEARCH, e);
		}
		return listaAnnunci;
	}
}
