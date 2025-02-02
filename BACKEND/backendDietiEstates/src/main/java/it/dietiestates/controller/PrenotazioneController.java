package it.dietiestates.controller;

import it.dietiestates.dao.PrenotazioneDAO;
import it.dietiestates.dao.sql.SQLPrenotazioneDAO;
import it.dietiestates.data.Prenotazione;
import it.dietiestates.data.dto.SuccessApiResponse;
import it.dietiestates.database.PgSQL;
import it.dietiestates.exception.DataAccessException;
import it.dietiestates.exception.ForeignKeyConstraintViolationException;
import it.dietiestates.exception.OverlappingBookingException;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.sql.SQLException;
import java.util.logging.Logger;

@Path("prenotazione")
public class PrenotazioneController {
    private final PrenotazioneDAO prenotazioneDAO;
    private static final Logger logger = Logger.getLogger(PrenotazioneController.class.getName());

    public PrenotazioneController() throws SQLException {
        this.prenotazioneDAO = new SQLPrenotazioneDAO(PgSQL.getConnection());
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
            SuccessApiResponse successResponse = new SuccessApiResponse(message);
            return Response.status(Response.Status.CREATED).entity(successResponse).build();
        } catch (OverlappingBookingException e) {
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
}
