package it.dietiestates.controller;

import java.sql.SQLException;

import it.dietiestates.dao.sql.SQLUtenteDAO;
import it.dietiestates.data.Utente;
import it.dietiestates.database.PgSQL;
import it.dietiestates.services.JWTService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("auth")
public class AuthController {
    private final SQLUtenteDAO utenteDAO;
    
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
                String token = JWTService.generateToken(utente.getEmail());
                return Response.ok(new TokenResponse(token)).build();
            } else {
                return Response.status(Response.Status.UNAUTHORIZED).entity("Credenziali non valide").build();
            }
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Errore del server").build();
        }
    }

    public static class Credentials {
        private String email;
        private String password;
        
		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
		}
		public String getPassword() {
			return password;
		}
		public void setPassword(String password) {
			this.password = password;
		}
    }

    public static class TokenResponse {
        private String token;
        
        public TokenResponse(String token) {
            this.token = token;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }
    }
}

