package it.dietiestates.controller.oauth;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.net.URI;

public abstract class OAuthController {
    protected abstract String getClientId();

    protected abstract String getRedirectUri();

    protected abstract String getAuthUrl();

    protected abstract String getScope();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response redirect() {
        try {
            String url = getAuthUrl() + "?response_type=code"
                    + "&client_id=" + getClientId()
                    + "&redirect_uri=" + getRedirectUri()
                    + "&scope=" + getScope();

            return Response.seeOther(URI.create(url)).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Errore nella generazione dell'URL di reindirizzamento: " + e.getMessage()).build();
        }
    }
}

