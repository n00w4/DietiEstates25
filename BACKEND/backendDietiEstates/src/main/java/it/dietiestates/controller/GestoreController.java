package it.dietiestates.controller;

import it.dietiestates.dao.GestoreDAO;
import it.dietiestates.dao.UtenteDAO;
import it.dietiestates.dao.sql.SQLGestoreDAO;
import it.dietiestates.dao.sql.SQLUtenteDAO;
import it.dietiestates.data.dto.AddUtenteForm;
import it.dietiestates.data.dto.ChangeAdminPwdForm;
import it.dietiestates.data.dto.ErrorApiResponse;
import it.dietiestates.data.dto.SuccessApiResponse;
import it.dietiestates.database.PgSQL;
import it.dietiestates.exception.DataAccessException;
import it.dietiestates.exception.UniqueConstraintViolationException;
import it.dietiestates.filter.RequireJWTAuthentication;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.sql.SQLException;
import java.util.logging.Logger;

@Path("gestore")
public class GestoreController {
    private final GestoreDAO gestoreDAO;
    private final UtenteDAO utenteDAO;
    private static final Logger logger = Logger.getLogger(GestoreController.class.getName());

    public GestoreController() throws SQLException {
        this.gestoreDAO = new SQLGestoreDAO(PgSQL.getConnection());
        this.utenteDAO = new SQLUtenteDAO(PgSQL.getConnection());
    }

    @Path("updateAdminPassword")
    @RequireJWTAuthentication
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateAdminPassword(ChangeAdminPwdForm form) {
        try {
            if (gestoreDAO.updateAdminPassword(form)) {
                logger.info("Richiesta di cambio password di amministrazione avvenuta con successo");
                SuccessApiResponse successResponse = new SuccessApiResponse("Password cambiata con successo");
                return Response.ok(successResponse).build();
            }
        } catch (DataAccessException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
        logger.info("Richiesta di cambio password di amministrazione errata");
        ErrorApiResponse errorResponse = new ErrorApiResponse("Errore durante il cambio password: controlla i dati inseriti e riprova");
        return Response.status(Response.Status.BAD_REQUEST).entity(errorResponse).build();
    }

    @Path("addUtente")
    @RequireJWTAuthentication
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addAgente(AddUtenteForm form) {
        try {
            if (utenteDAO.insertUtente(form)) {
                logger.info("Richiesta di aggiunta agente avvenuta con successo");
                SuccessApiResponse successResponse = new SuccessApiResponse("Agente aggiunto con successo");
                return Response.status(Response.Status.CREATED).entity(successResponse).build();
            }
        } catch (UniqueConstraintViolationException e) {
            return Response.status(Response.Status.CONFLICT).entity(e.getApiResponse()).build();
        } catch (DataAccessException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
        ErrorApiResponse errorResponse = new ErrorApiResponse("Errore durante l'aggiunta dell'agente: controlla i dati inseriti e riprova");
        return Response.status(Response.Status.BAD_REQUEST).entity(errorResponse).build();
    }
}

