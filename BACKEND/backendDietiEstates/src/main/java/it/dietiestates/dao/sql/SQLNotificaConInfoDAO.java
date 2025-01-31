package it.dietiestates.dao.sql;

import it.dietiestates.dao.NotificaConInfoDAO;
import it.dietiestates.data.Annuncio;
import it.dietiestates.data.Notifica;
import it.dietiestates.data.Prenotazione;
import it.dietiestates.data.dto.NotificaConInfo;
import it.dietiestates.exception.DataAccessException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SQLNotificaConInfoDAO implements NotificaConInfoDAO {
    private static final String COLUMN_ID_NOTIFICA = "idNotifica";
    private static final String COLUMN_DATA_ORA = "dataOraNotifica";
    private static final String COLUMN_ID_PRENOTAZIONE = "idPrenotazione";
    private static final String COLUMN_DATA_INIZIO = "dataInizio";
    private static final String COLUMN_DATA_FINE = "dataFine";
    private static final String COLUMN_IS_ACCETTATA = "isAccettata";
    private static final String COLUMN_ID_ANNUNCIO = "idAnnuncio";
    private static final String COLUMN_EMAIL_CLIENTE = "emailCliente";
    private static final String COLUMN_TITOLO = "titoloAnnuncio";
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

    private final Connection connection;

    public SQLNotificaConInfoDAO(Connection dbConnection) {
        this.connection = dbConnection;
    }

    @Override
    public List<NotificaConInfo> getAll(String emailAgente) throws DataAccessException {
        String query = "SELECT idNotifica, dataOraNotifica, idPrenotazione, dataInizio, dataFine, " +
                "isAccettata, idAnnuncio, emailCliente, titoloAnnuncio, indirizzo, immagine, descrizione, "
                + "dimensioni, prezzo, piano, numeroStanze, classeEnergetica, ascensore, portineria, "
                + "climatizzazione, boxAuto, terrazzo, giardino, tipoAnnuncio, posizione, emailAgente " +
                "FROM est.NotificaConInfo";

        List<NotificaConInfo> listaNotifiche = new ArrayList<>();

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    // Creazione oggetto Notifica
                    int id = resultSet.getInt(COLUMN_ID_NOTIFICA);
                    Timestamp dataOra = resultSet.getTimestamp(COLUMN_DATA_ORA);
                    int idPrenotazione = resultSet.getInt(COLUMN_ID_PRENOTAZIONE);

                    Notifica notifica = new Notifica(id, dataOra, emailAgente, idPrenotazione);

                    // Creazione oggetto Prenotazione
                    Timestamp dataInizio = resultSet.getTimestamp(COLUMN_DATA_INIZIO);
                    Timestamp dataFine = resultSet.getTimestamp(COLUMN_DATA_FINE);
                    boolean isAccettata = resultSet.getBoolean(COLUMN_IS_ACCETTATA);
                    int idAnnuncio = resultSet.getInt(COLUMN_ID_ANNUNCIO);
                    String emailCliente = resultSet.getString(COLUMN_EMAIL_CLIENTE);

                    Prenotazione prenotazione = new Prenotazione(idPrenotazione, dataInizio, dataFine, isAccettata, idAnnuncio, emailCliente);

                    // Creazione oggetto Annuncio
                    Annuncio annuncio = new Annuncio.Builder(idAnnuncio)
                            .titolo(resultSet.getString(COLUMN_TITOLO))
                            .indirizzo(resultSet.getString(COLUMN_INDIRIZZO))
                            .immagine(resultSet.getString(COLUMN_IMMAGINE))
                            .descrizione(resultSet.getString(COLUMN_DESCRIZIONE))
                            .dimensioni(resultSet.getInt(COLUMN_DIMENSIONI))
                            .prezzo(resultSet.getFloat(COLUMN_PREZZO))
                            .piano(resultSet.getString(COLUMN_PIANO))
                            .numeroStanze(resultSet.getInt(COLUMN_NUMEROSTANZE))
                            .classeEnergetica(resultSet.getString(COLUMN_CLASSEENGERGETICA))
                            .ascensore(resultSet.getBoolean(COLUMN_ASCENSORE))
                            .portineria(resultSet.getBoolean(COLUMN_PORTINERIA))
                            .climatizzazione(resultSet.getBoolean(COLUMN_CLIMATIZZAZIONE))
                            .boxAuto(resultSet.getBoolean(COLUMN_BOXAUTO))
                            .terrazzo(resultSet.getBoolean(COLUMN_TERRAZZO))
                            .giardino(resultSet.getBoolean(COLUMN_GIARDINO))
                            .tipoAnnuncio(resultSet.getString(COLUMN_TIPOANNUNCIO))
                            .posizione(resultSet.getString(COLUMN_POSIZIONE))
                            .emailAgente(emailAgente)
                            .build();

                    NotificaConInfo notificaConInfo = new NotificaConInfo(notifica, prenotazione, annuncio);

                    listaNotifiche.add(notificaConInfo);
                }
            }
        } catch (SQLException e) {
            throw new DataAccessException("Errore durante la ricerca delle notifiche", e);
        }
        return listaNotifiche;
    }
}
