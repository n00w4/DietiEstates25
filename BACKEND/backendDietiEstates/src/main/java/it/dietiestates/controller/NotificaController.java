package it.dietiestates.controller;

import it.dietiestates.dao.NotificaConInfoDAO;
import it.dietiestates.dao.sql.SQLNotificaConInfoDAO;
import it.dietiestates.data.dto.NotificaConInfo;
import it.dietiestates.database.PgSQL;
import it.dietiestates.exception.DataAccessException;
import it.dietiestates.filter.RequireJWTAuthentication;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

@Path("notifiche")
public class NotificaController {
    private final NotificaConInfoDAO notificaConInfoDAO;
    private static final Logger logger = Logger.getLogger(NotificaController.class.getName());

    public NotificaController() throws SQLException {
        this.notificaConInfoDAO = new SQLNotificaConInfoDAO(PgSQL.getConnection());
    }

    @Path("getAll")
    @RequireJWTAuthentication
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll(@QueryParam("emailAgente") String emailAgente) {
        List<NotificaConInfo> listaNotifica;
        try {
            listaNotifica = notificaConInfoDAO.getAll(emailAgente);
            if (listaNotifica.equals(Collections.emptyList())) {
                logger.info("Richiesta di tutte le notifiche non trovata");
                return Response.status(Response.Status.NOT_FOUND).build();
            }
        } catch (DataAccessException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
        logger.info("Richiesta di tutte le notifiche effettuata con successo");
        return Response.ok(listaNotifica).build();
    }
}
