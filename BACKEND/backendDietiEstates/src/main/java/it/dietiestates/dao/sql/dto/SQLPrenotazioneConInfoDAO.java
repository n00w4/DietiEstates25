package it.dietiestates.dao.sql.dto;

import it.dietiestates.dao.dto.PrenotazioneConInfoDAO;
import it.dietiestates.data.dto.PrenotazioneConInfo;
import it.dietiestates.data.model.Annuncio;
import it.dietiestates.data.model.Prenotazione;
import it.dietiestates.exception.DataAccessException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class SQLPrenotazioneConInfoDAO implements PrenotazioneConInfoDAO {
    private final Connection connection;

    public SQLPrenotazioneConInfoDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<PrenotazioneConInfo> getPrenotazioniAccettate(String emailAgente) throws DataAccessException {
        String query = "SELECT idPrenotazione, dataInizio, dataFine, isAccettata, emailCliente, idAnnuncio, " +
                "titoloAnnuncio, indirizzo, immagine, descrizione, dimensioni, prezzo, piano, numeroStanze, " +
                "classeEnergetica, ascensore, portineria, climatizzazione, boxAuto, terrazzo, giardino, tipoAnnuncio, " +
                "posizione, emailAgente FROM est.PrenotazioneConInfo WHERE isAccettata = TRUE AND emailAgente = ?";

        return getPrenotazioniByQuery(query, emailAgente);
    }

    @Override
    public List<PrenotazioneConInfo> getPrenotazioniAccettateCliente(String emailCliente) throws DataAccessException {
        String query = "SELECT idPrenotazione, dataInizio, dataFine, isAccettata, emailCliente, idAnnuncio, " +
                "titoloAnnuncio, indirizzo, immagine, descrizione, dimensioni, prezzo, piano, numeroStanze, " +
                "classeEnergetica, ascensore, portineria, climatizzazione, boxAuto, terrazzo, giardino, tipoAnnuncio, " +
                "posizione, emailAgente FROM est.PrenotazioneConInfo WHERE isAccettata = TRUE AND emailCliente = ?";

        return getPrenotazioniByQuery(query, emailCliente);
    }

    private List<PrenotazioneConInfo> getPrenotazioniByQuery(String query, String email) throws DataAccessException {
        List<PrenotazioneConInfo> listaPrenotazioni = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, email);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    listaPrenotazioni.add(parsePrenotazioneConInfo(resultSet));
                }
            }
        } catch (SQLException e) {
            throw new DataAccessException("Errore durante la ricerca delle prenotazioni accettate", e);
        }

        return listaPrenotazioni;
    }

    private PrenotazioneConInfo parsePrenotazioneConInfo(ResultSet resultSet) throws SQLException {
        int idAnnuncio = resultSet.getInt("idAnnuncio");
        String titolo = resultSet.getString("titoloAnnuncio");
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
        String emailAgente = resultSet.getString("emailAgente");

        Annuncio annuncio = new Annuncio.Builder(idAnnuncio)
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

        int idPrenotazione = resultSet.getInt("idPrenotazione");
        LocalDateTime dataInizio = resultSet.getTimestamp("dataInizio").toLocalDateTime();
        LocalDateTime dataFine = resultSet.getTimestamp("dataFine").toLocalDateTime();
        Boolean isAccettata = resultSet.getBoolean("isAccettata");
        String emailCliente = resultSet.getString("emailCliente");

        Prenotazione prenotazione = new Prenotazione(
                idPrenotazione,
                dataInizio,
                dataFine,
                isAccettata,
                idAnnuncio,
                emailCliente
        );

        return new PrenotazioneConInfo(prenotazione, annuncio);
    }
}
