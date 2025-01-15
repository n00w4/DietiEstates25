package it.dietiestates.controller.oauth.google;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.net.URI;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Path("auth/google")
@Produces(MediaType.APPLICATION_JSON)
public class GoogleAuthController {
    private static final String CLIENT_ID = "112156935328-gd61v5j6q70h3idigvpn4v5cgnbi0id1.apps.googleusercontent.com";
    private static final String REDIRECT_URI = "http://localhost:8080/api/auth/google/callback";
    private static final String AUTH_URL = "https://accounts.google.com/o/oauth2/auth";

    @GET
    public Response redirectToGoogle() {
        try {
            String encodedRedirectUri = URLEncoder.encode(REDIRECT_URI, StandardCharsets.UTF_8.toString());
            String encodedScope = URLEncoder.encode("openid email profile", StandardCharsets.UTF_8.toString());

            String url = AUTH_URL + "?response_type=code"
                    + "&client_id=" + CLIENT_ID
                    + "&redirect_uri=" + encodedRedirectUri
                    + "&scope=" + encodedScope;

            return Response.seeOther(URI.create(url)).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Errore nella generazione dell'URL di reindirizzamento: " + e.getMessage()).build();
        }
    }
}
