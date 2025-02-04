package it.dietiestates.controller;

import it.dietiestates.dao.model.UtenteDAO;
import it.dietiestates.dao.sql.model.SQLUtenteDAO;
import it.dietiestates.data.dto.Credentials;
import it.dietiestates.data.dto.ErrorApiResponse;
import it.dietiestates.data.dto.TokenResponse;
import it.dietiestates.data.model.Utente;
import it.dietiestates.database.PgSQL;
import it.dietiestates.exception.DataAccessException;
import it.dietiestates.filter.JWTService;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.sql.SQLException;
import java.util.logging.Logger;

@Path("auth")
public class AuthController {
    private final UtenteDAO utenteDAO;
    private static final Logger logger = Logger.getLogger(AuthController.class.getName());
    
    public AuthController() throws SQLException {
        this.utenteDAO = new SQLUtenteDAO(PgSQL.getConnection());
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(Credentials credentials) {
        try {
            Utente utente = utenteDAO.autenticaUtente(credentials.getEmail(), credentials.getPassword());
            if (utente != null) {
                String token = JWTService.generateToken(utente.getNome(), utente.getCognome(), utente.getEmail(), utente.getTipo(), utente.getPartitaIVA(), utente.getNomeAgenzia());
                logger.info(() -> "Utente ha effettuato login con token: " + token);
                return Response.ok(new TokenResponse(token)).build();
            } else {
                ErrorApiResponse unauthorizedResponse = new ErrorApiResponse("Credenziali non valide");
                return Response.status(Response.Status.UNAUTHORIZED).entity(unauthorizedResponse).build();
            }
        } catch (DataAccessException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getApiResponse()).build();
        }
    }
}

