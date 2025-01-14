package it.dietiestates.controller.oauth.github;

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

@Path("auth/github/callback")
@Produces(MediaType.APPLICATION_JSON)
public class GithubCallbackController {
    private static final Logger logger = Logger.getLogger(GithubCallbackController.class.getName());
    private static final String CLIENT_ID = "Ov23liQUbv2UNe6YL5dp";
    private static final String CLIENT_SECRET = System.getenv("GITHUB_CLIENT_SECRET");
    private static final String TOKEN_URL = "https://github.com/login/oauth/access_token";

    @GET
    public Response handleGitHubCallback(@QueryParam("code") String code) {
        try (Client client = ClientBuilder.newClient()) {
            // Costruzione della richiesta HTTP per ottenere il token
            WebTarget target = client.target(TOKEN_URL);
            Form form = new Form()
                    .param("client_id", CLIENT_ID)
                    .param("client_secret", CLIENT_SECRET)
                    .param("code", code);

            try (Response tokenResponse = target.request(MediaType.APPLICATION_JSON)
                    .post(Entity.form(form))) {

                if (tokenResponse.getStatus() != 200) {
                    ErrorApiResponse errorApiResponse = new ErrorApiResponse("Errore nel token exchange");
                    return Response.status(Response.Status.BAD_REQUEST).entity(errorApiResponse).build();
                }

                String responseBody = tokenResponse.readEntity(String.class);
                JsonObject jsonResponse;
                try (JsonReader reader = Json.createReader(new StringReader(responseBody))) {
                    jsonResponse = reader.readObject();
                }

                if (!jsonResponse.containsKey("access_token")) {
                    ErrorApiResponse errorApiResponse = new ErrorApiResponse("Access token non disponibile");
                    return Response.status(Response.Status.BAD_REQUEST).entity(errorApiResponse).build();
                }

                String accessToken = jsonResponse.getString("access_token");
                return fetchGitHubUser(accessToken);
            }
        } catch (Exception e) {
            logger.log(Level.SEVERE, e.getMessage(), e);
            ErrorApiResponse errorResponse = new ErrorApiResponse(e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(errorResponse).build();
        }
    }

    private Response fetchGitHubUser(String accessToken) {
        String userUrl = "https://api.github.com/user";
        try (Client client = ClientBuilder.newClient()) {
            WebTarget target = client.target(userUrl);

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
            logger.log(Level.SEVERE, e.getMessage(), e);
            ErrorApiResponse errorResponse = new ErrorApiResponse(e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(errorResponse).build();
        }
    }
}

