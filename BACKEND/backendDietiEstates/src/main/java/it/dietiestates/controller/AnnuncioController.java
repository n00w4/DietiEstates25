package it.dietiestates.controller;

import it.dietiestates.dao.AnnuncioDAO;
import it.dietiestates.dao.sql.SQLAnnuncioDAO;
import it.dietiestates.data.Annuncio;
import it.dietiestates.data.RicercaAnnuncio;
import it.dietiestates.database.PgSQL;
import it.dietiestates.exception.DataAccessException;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

@Path("annunci")
public class AnnuncioController {
    private final AnnuncioDAO annuncioDAO;

    public AnnuncioController() throws SQLException {
        this.annuncioDAO = new SQLAnnuncioDAO(PgSQL.getConnection());
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAnnunciFromSearch(RicercaAnnuncio ricerca) {
        List<Annuncio> listaAnnunci = null;
        try {
            listaAnnunci = annuncioDAO.getAnnunciFromSearch(ricerca);
            if (listaAnnunci.equals(Collections.emptyList())) {
                return Response.status(Response.Status.NOT_FOUND).build();
            }
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        return Response.ok(listaAnnunci).build();
    }
}
