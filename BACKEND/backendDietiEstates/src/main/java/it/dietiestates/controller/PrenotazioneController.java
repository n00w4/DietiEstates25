package it.dietiestates.controller;

import it.dietiestates.dao.dto.PrenotazioneConInfoDAO;
import it.dietiestates.dao.model.AgenteDAO;
import it.dietiestates.dao.model.PrenotazioneDAO;
import it.dietiestates.dao.sql.dto.SQLPrenotazioneConInfoDAO;
import it.dietiestates.dao.sql.model.SQLAgenteDAO;
import it.dietiestates.dao.sql.model.SQLPrenotazioneDAO;
import it.dietiestates.data.dto.PrenotazioneConInfo;
import it.dietiestates.data.dto.SuccessApiResponse;
import it.dietiestates.data.model.Prenotazione;
import it.dietiestates.database.PgSQL;
import it.dietiestates.exception.DataAccessException;
import it.dietiestates.exception.ForeignKeyConstraintViolationException;
import it.dietiestates.exception.OverlappingBookingException;
import it.dietiestates.exception.ValidBookingException;
import it.dietiestates.utils.EmailService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

@Path("prenotazione")
public class PrenotazioneController {
    private final PrenotazioneDAO prenotazioneDAO;
    private final PrenotazioneConInfoDAO prenotazioneConInfoDAO;
    private final AgenteDAO agenteDAO;
    private static final Logger logger = Logger.getLogger(PrenotazioneController.class.getName());

    public PrenotazioneController() throws SQLException {
        this.prenotazioneDAO = new SQLPrenotazioneDAO(PgSQL.getConnection());
        this.prenotazioneConInfoDAO = new SQLPrenotazioneConInfoDAO(PgSQL.getConnection());
        this.agenteDAO = new SQLAgenteDAO(PgSQL.getConnection());
    }

    @Path("insert")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response insertPrenotazione(Prenotazione prenotazione) {
        try {
            prenotazioneDAO.insert(prenotazione);
            String message = "Prenotazione inserita con successo";
            logger.info(message);

            String[] emailData = EmailService.generateEmailContentAgente(prenotazione);
            String emailSubject = emailData[0];
            String emailContent = emailData[1];

            String emailAgente = agenteDAO.findEmailByIdAnnuncio(prenotazione.getIdAnnuncio());

            EmailService.sendEmail(emailAgente, emailSubject, emailContent);

            SuccessApiResponse successResponse = new SuccessApiResponse(message);
            return Response.status(Response.Status.CREATED).entity(successResponse).build();
        } catch (OverlappingBookingException | ValidBookingException e) {
            return Response.status(Response.Status.CONFLICT).entity(e.getApiResponse()).build();
        } catch (ForeignKeyConstraintViolationException e) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getApiResponse()).build();
        } catch (DataAccessException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getApiResponse()).build();
        }
    }

    @Path("update-status")
    @PATCH
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateStatusPrenotazione(Prenotazione prenotazione) {
        try {
            if (prenotazioneDAO.updateStatusPrenotazione(prenotazione)) {
                String message = "Stato della prenotazione aggiornato con successo";
                logger.info(message);
                SuccessApiResponse successResponse = new SuccessApiResponse(message);
                return Response.ok(successResponse).build();
            }
        } catch (DataAccessException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getApiResponse()).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @Path("get-prenotazioni-accettate")
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPrenotazioniAccettate(@QueryParam("emailAgente") String emailAgente) {
        List<PrenotazioneConInfo> listaPrenotazioni;

        try {
            listaPrenotazioni = prenotazioneConInfoDAO.getPrenotazioniAccettate(emailAgente);
            if (listaPrenotazioni.equals(Collections.emptyList())) {
                logger.info("Richiesta per prenotazioni agente non trovate");
                return Response.status(Response.Status.NOT_FOUND).build();
            }
        } catch (DataAccessException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getApiResponse()).build();
        }
        return Response.ok(listaPrenotazioni).build();
    }

    @Path("get-prenotazioni-accettate-cliente")
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPrenotazioniAccettateCliente(@QueryParam("emailCliente") String emailCliente) {
        List<PrenotazioneConInfo> listaPrenotazioni;

        try {
            listaPrenotazioni = prenotazioneConInfoDAO.getPrenotazioniAccettateCliente(emailCliente);
            if (listaPrenotazioni.equals(Collections.emptyList())) {
                logger.info("Richiesta per prenotazioni cliente non trovate");
                return Response.status(Response.Status.NOT_FOUND).build();
            }
        } catch (DataAccessException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getApiResponse()).build();
        }
        return Response.ok(listaPrenotazioni).build();
    }
}
