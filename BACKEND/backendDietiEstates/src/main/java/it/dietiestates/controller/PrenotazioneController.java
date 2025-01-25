package it.dietiestates.controller;

import it.dietiestates.dao.PrenotazioneDAO;
import it.dietiestates.dao.sql.SQLPrenotazioneDAO;
import it.dietiestates.data.Prenotazione;
import it.dietiestates.data.dto.SuccessApiResponse;
import it.dietiestates.database.PgSQL;
import it.dietiestates.exception.DataAccessException;
import it.dietiestates.exception.ForeignKeyConstraintViolationException;
import it.dietiestates.exception.OverlappingBookingException;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
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
            logger.info("Prenotazione inserita con successo");
            SuccessApiResponse successResponse = new SuccessApiResponse("Prenotazione inserita con successo");
            return Response.status(Response.Status.CREATED).entity(successResponse).build();
        } catch (OverlappingBookingException e) {
            return Response.status(Response.Status.CONFLICT).entity(e.getApiResponse()).build();
        } catch (ForeignKeyConstraintViolationException e) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getApiResponse()).build();
        } catch (DataAccessException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getApiResponse()).build();
        }
    }
}
