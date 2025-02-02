package it.dietiestates.controller;

import it.dietiestates.dao.AnnuncioDAO;
import it.dietiestates.dao.sql.SQLAnnuncioDAO;
import it.dietiestates.data.Annuncio;
import it.dietiestates.data.dto.ErrorApiResponse;
import it.dietiestates.data.dto.RicercaAnnuncio;
import it.dietiestates.data.dto.SuccessApiResponse;
import it.dietiestates.database.PgSQL;
import it.dietiestates.exception.DataAccessException;
import it.dietiestates.exception.ForeignKeyConstraintViolationException;
import it.dietiestates.exception.UniqueConstraintViolationException;
import it.dietiestates.filter.RequireJWTAuthentication;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

@Path("annunci")
public class AnnuncioController {
    private final AnnuncioDAO annuncioDAO;
    private static final Logger logger = Logger.getLogger(AnnuncioController.class.getName());


    public AnnuncioController() throws SQLException {
        this.annuncioDAO = new SQLAnnuncioDAO(PgSQL.getConnection());
    }

    @Path("get-all")
    @RequireJWTAuthentication
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllAnnunci() {
        List<Annuncio> listaAnnunci;
        try {
            listaAnnunci = annuncioDAO.getAllAnnunci();
            if (listaAnnunci.equals(Collections.emptyList())) {
                logger.info("Richiesta di tutti gli annunci non trovata");
                return Response.status(Response.Status.NOT_FOUND).build();
            }
        } catch (DataAccessException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
        logger.info("Richiesta di tutti gli annunci effettuata con successo");
        return Response.ok(listaAnnunci).build();
    }

    @Path("search")
    @RequireJWTAuthentication
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAnnunciFromSearch(RicercaAnnuncio ricerca) {
        List<Annuncio> listaAnnunci;
        try {
            listaAnnunci = annuncioDAO.getAnnunciFromSearch(ricerca);
            if (listaAnnunci.equals(Collections.emptyList())) {
                logger.info("Richiesta di annunci con filtri non trovata");
                return Response.status(Response.Status.NOT_FOUND).build();
            }
        } catch (DataAccessException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
        logger.info("Richiesta annunci con filtri effettuata con successo");
        return Response.ok(listaAnnunci).build();
    }

    @Path("search")
    @RequireJWTAuthentication
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAnnunciFromSearchPosition(@QueryParam("longitude") double longitude, @QueryParam("latitude") double latitude) {
        List<Annuncio> listaAnnunci;
        try {
            listaAnnunci = annuncioDAO.getAnnunciFromPosition(longitude, latitude);
            if (listaAnnunci.equals(Collections.emptyList())) {
                logger.info("Richiesta di annunci per posizione non trovata");
                return Response.status(Response.Status.NOT_FOUND).build();
            }
        } catch (DataAccessException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
        logger.info("Richiesta annunci per posizione effettuata con successo");
        return Response.ok(listaAnnunci).build();
    }

    @Path("insert")
    @RequireJWTAuthentication
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response insertAnnuncio(Annuncio annuncio) {
        try {
            if (annuncioDAO.insert(annuncio)) {
                logger.info("Richiesta di aggiunta annuncio con successo");
                SuccessApiResponse successResponse = new SuccessApiResponse("Annuncio aggiunto con successo");
                return Response.status(Response.Status.CREATED).entity(successResponse).build();
            }
        } catch (ForeignKeyConstraintViolationException | UniqueConstraintViolationException e) {
            return Response.status(Response.Status.CONFLICT).entity(e.getApiResponse()).build();
        } catch (DataAccessException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getApiResponse()).build();
        }
        ErrorApiResponse errorApiResponse = new ErrorApiResponse("Errore durante l'inserimento del annuncio: controlla i dati inseriti e riprova");
        return Response.status(Response.Status.BAD_REQUEST).entity(errorApiResponse).build();
    }
}
