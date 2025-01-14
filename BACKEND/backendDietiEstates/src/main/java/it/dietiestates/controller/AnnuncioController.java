package it.dietiestates.controller;

import it.dietiestates.dao.AnnuncioDAO;
import it.dietiestates.dao.sql.SQLAnnuncioDAO;
import it.dietiestates.data.Annuncio;
import it.dietiestates.database.PgSQL;
import it.dietiestates.dto.RicercaAnnuncio;
import it.dietiestates.exception.DataAccessException;
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

    @Path("getAll")
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
                logger.info("Richiesta di annunci non trovata");
                return Response.status(Response.Status.NOT_FOUND).build();
            }
        } catch (DataAccessException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
        logger.info("Richiesta annunci effettuata con successo");
        return Response.ok(listaAnnunci).build();
    }
}
