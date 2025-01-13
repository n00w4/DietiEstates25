package it.dietiestates.dao.sql;

import it.dietiestates.dao.AnnuncioDAO;
import it.dietiestates.data.Annuncio;
import it.dietiestates.data.RicercaAnnuncio;
import it.dietiestates.exception.DataAccessException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SQLAnnuncioDAO implements AnnuncioDAO {
	private final Connection connection;

	public SQLAnnuncioDAO(Connection connection) {
		this.connection = connection;
	}

	@Override
	public boolean insert(Annuncio annuncio) throws DataAccessException {
		String query = "INSERT INTO est.Annuncio VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		try (PreparedStatement statement = connection.prepareStatement(query)) {
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
	        statement.setString(17, annuncio.getEmailAgente());

			return statement.executeUpdate() > 0;
	    } catch (SQLException e) {
			throw new DataAccessException("Errore durante l'inserimento dell'annuncio", e);
	    }
	}

	@Override
	public boolean delete(Annuncio annuncio) throws DataAccessException {
		String query = "DELETE FROM est.Annuncio WHERE idAnnuncio = ? AND email = ?";
		try (PreparedStatement statement = connection.prepareStatement(query)) {
			statement.setInt(1, annuncio.getID());
			statement.setString(2, annuncio.getEmailAgente());

			return statement.executeUpdate() > 0;
		} catch (SQLException e) {
			throw new DataAccessException("Errore durante la cancellazione dell'annuncio", e);
		}
	}

	@Override
	public List<Annuncio> getAnnunciFromSearch(RicercaAnnuncio ricerca) throws DataAccessException {
		String query = "SELECT * FROM est.Annuncio WHERE tipoAnnuncio = ?::public.enum_annuncio AND prezzo BETWEEN ? AND ? "
				+ "AND piano = ? AND numeroStanze >= ? AND classeEnergetica = ? "
				+ "AND ascensore = ? AND portineria = ? AND climatizzazione = ? "
				+ "AND terrazzo = ? AND giardino = ? AND boxAuto = ? "
				+ "AND ST_DWithin(posizione, ST_SetSRID(ST_MakePoint(?, ?), 4326), 50000)";

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
			statement.setDouble(13, ricerca.getLongitudine());
			statement.setDouble(14, ricerca.getLatitudine());

			try (ResultSet resultSet = statement.executeQuery()) {
				while (resultSet.next()) {
					int id = resultSet.getInt("idAnnuncio");
					String titolo = resultSet.getString("titolo");
					String indirizzo = resultSet.getString("indirizzo");
					String immagine = resultSet.getString("immagine");
					String descrizione = resultSet.getString("descrizione");
					int dimensioni = resultSet.getInt("dimensioni");
					float prezzo = resultSet.getFloat("prezzo");
					String piano = resultSet.getString("piano");
					int numeroStanze = resultSet.getInt("numeroStanze");
					String classeEnergetica = resultSet.getString("classeEnergetica");
					boolean ascensore = resultSet.getBoolean("ascensore");
					boolean portineria = resultSet.getBoolean("portineria");
					boolean climatizzazione = resultSet.getBoolean("climatizzazione");
					boolean boxAuto = resultSet.getBoolean("boxAuto");
					boolean terrazzo = resultSet.getBoolean("terrazzo");
					boolean giardino = resultSet.getBoolean("giardino");
					String tipoAnnuncio = resultSet.getString("tipoAnnuncio");
					String posizione = resultSet.getString("posizione");
					String emailAgente = resultSet.getString("email");

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
		} catch (SQLException e) {
			throw new DataAccessException("Errore durante la ricerca dell'annuncio", e);
        }
		return listaAnnunci;
    }

	@Override
	public List<Annuncio> getAllAnnunci() throws DataAccessException {
		String query = "SELECT * FROM est.Annuncio";
		List<Annuncio> listaAnnunci = new ArrayList<>();

		try (PreparedStatement statement = connection.prepareStatement(query)) {
			try (ResultSet resultSet = statement.executeQuery()) {
				while (resultSet.next()) {
					int id = resultSet.getInt("idAnnuncio");
					String titolo = resultSet.getString("titolo");
					String indirizzo = resultSet.getString("indirizzo");
					String immagine = resultSet.getString("immagine");
					String descrizione = resultSet.getString("descrizione");
					int dimensioni = resultSet.getInt("dimensioni");
					float prezzo = resultSet.getFloat("prezzo");
					String piano = resultSet.getString("piano");
					int numeroStanze = resultSet.getInt("numeroStanze");
					String classeEnergetica = resultSet.getString("classeEnergetica");
					boolean ascensore = resultSet.getBoolean("ascensore");
					boolean portineria = resultSet.getBoolean("portineria");
					boolean climatizzazione = resultSet.getBoolean("climatizzazione");
					boolean boxAuto = resultSet.getBoolean("boxAuto");
					boolean terrazzo = resultSet.getBoolean("terrazzo");
					boolean giardino = resultSet.getBoolean("giardino");
					String tipoAnnuncio = resultSet.getString("tipoAnnuncio");
					String posizione = resultSet.getString("posizione");
					String emailAgente = resultSet.getString("email");

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
		} catch (SQLException e) {
			throw new DataAccessException("Errore durante la ricerca dell'annuncio", e);
		}
        return listaAnnunci;
    }
}
