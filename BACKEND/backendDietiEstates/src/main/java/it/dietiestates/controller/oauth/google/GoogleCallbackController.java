package it.dietiestates.controller.oauth.google;

import it.dietiestates.dto.ErrorApiResponse;
import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.Form;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.io.StringReader;
import java.util.logging.Level;
import java.util.logging.Logger;

@Path("auth/google/callback")
@Produces(MediaType.APPLICATION_JSON)
public class GoogleCallbackController {
    private static final Logger logger = Logger.getLogger(GoogleCallbackController.class.getName());
    private static final String CLIENT_ID = "112156935328-gd61v5j6q70h3idigvpn4v5cgnbi0id1.apps.googleusercontent.com";
    private static final String REDIRECT_URI = "http://localhost:8080/api/auth/google/callback";
    private static final String CLIENT_SECRET = System.getenv("GOOGLE_CLIENT_SECRET");
    private static final String TOKEN_URL = "https://oauth2.googleapis.com/token";

    @GET
    public Response handleGoogleCallback(@QueryParam("code") String code) {
        try (Client client = ClientBuilder.newClient()) {
            WebTarget target = client.target(TOKEN_URL);
            Form form = new Form()
                    .param("client_id", CLIENT_ID)
                    .param("client_secret", CLIENT_SECRET)
                    .param("code", code)
                    .param("grant_type", "authorization_code")
                    .param("redirect_uri", REDIRECT_URI);

            try (Response tokenResponse = target.request(MediaType.APPLICATION_JSON)
                    .post(Entity.form(form))) {

                String responseBody = tokenResponse.readEntity(String.class);
                logger.info(() -> "Token exchange response status: " + tokenResponse.getStatus());
                logger.info(() -> "Token exchange response body: " + responseBody);

                if (tokenResponse.getStatus() != 200) {
                    return Response.status(Response.Status.BAD_REQUEST)
                            .entity(new ErrorApiResponse("Errore nel token exchange: " + responseBody))
                            .build();
                }

                String accessToken = extractAccessToken(responseBody);
                if (accessToken == null) {
                    logger.severe(() -> "Access token mancante nella risposta JSON: " + responseBody);
                    return Response.status(Response.Status.BAD_REQUEST)
                            .entity(new ErrorApiResponse("Access token mancante"))
                            .build();
                }

                return fetchGoogleUser(accessToken);
            }
        } catch (Exception e) {
            logger.log(Level.SEVERE, e, () -> "Errore durante la gestione del callback: " + e.getMessage());
            ErrorApiResponse errorApiResponse = new ErrorApiResponse(e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(errorApiResponse).build();
        }
    }

    private String extractAccessToken(String responseBody) {
        try (JsonReader reader = Json.createReader(new StringReader(responseBody))) {
            JsonObject jsonResponse = reader.readObject();
            return jsonResponse.getString("access_token", null);
        } catch (Exception e) {
            logger.log(Level.SEVERE, e, () -> "Errore durante l'estrazione dell'access token: " + responseBody);
            return null;
        }
    }

    private Response fetchGoogleUser(String accessToken) {
        String userInfoUrl = "https://www.googleapis.com/oauth2/v2/userinfo";
        try (Client client = ClientBuilder.newClient()) {
            WebTarget target = client.target(userInfoUrl);

            try (Response userResponse = target.request(MediaType.APPLICATION_JSON)
                    .header("Authorization", "Bearer " + accessToken)
                    .get()) {

                if (userResponse.getStatus() != 200) {
                    ErrorApiResponse errorApiResponse = new ErrorApiResponse("Errore nel recupero dell'utente");
                    return Response.status(Response.Status.BAD_REQUEST).entity(errorApiResponse).build();
                }

                String userInfo = userResponse.readEntity(String.class);
                return Response.ok(userInfo).build();
            }
        } catch (Exception e) {
            logger.log(Level.SEVERE, e, () -> "Errore durante il recupero delle informazioni utente: " + e.getMessage());
            ErrorApiResponse errorApiResponse = new ErrorApiResponse(e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(errorApiResponse).build();
        }
    }
}
