package it.dietiestates.controller.oauth;

import it.dietiestates.data.dto.ErrorApiResponse;
import it.dietiestates.data.dto.SuccessApiResponse;
import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import jakarta.ws.rs.GET;
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

public abstract class OAuthCallbackController {
    private static final Logger logger = Logger.getLogger(OAuthCallbackController.class.getName());

    protected abstract String getTokenUrl();

    protected abstract String getClientId();

    protected abstract String getClientSecret();

    protected abstract String getUserInfoUrl();

    protected abstract String getRedirectUri();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response handleCallback(@QueryParam("code") String code) {
        try (Client client = ClientBuilder.newClient()) {
            // Costruzione della richiesta HTTP per ottenere il token
            WebTarget target = client.target(getTokenUrl());
            Form form = new Form()
                    .param("client_id", getClientId())
                    .param("client_secret", getClientSecret())
                    .param("code", code)
                    .param("grant_type", "authorization_code")
                    .param("redirect_uri", getRedirectUri());

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
                return fetchUserInfo(accessToken);
            }
        } catch (Exception e) {
            logger.log(Level.SEVERE, e.getMessage(), e);
            ErrorApiResponse errorResponse = new ErrorApiResponse(e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(errorResponse).build();
        }
    }

    private Response fetchUserInfo(String accessToken) {
        try (Client client = ClientBuilder.newClient()) {
            WebTarget target = client.target(getUserInfoUrl());

            try (Response userResponse = target.request(MediaType.APPLICATION_JSON)
                    .header("Authorization", "Bearer " + accessToken)
                    .get()) {

                if (userResponse.getStatus() != 200) {
                    ErrorApiResponse errorApiResponse = new ErrorApiResponse("Errore nel recupero dell'utente");
                    return Response.status(Response.Status.BAD_REQUEST).entity(errorApiResponse).build();
                }

                String userInfo = userResponse.readEntity(String.class);
                SuccessApiResponse successResponse = new SuccessApiResponse(userInfo);
                return Response.ok(successResponse).build();
            }
        } catch (Exception e) {
            logger.log(Level.SEVERE, e.getMessage(), e);
            ErrorApiResponse errorResponse = new ErrorApiResponse(e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(errorResponse).build();
        }
    }
}
