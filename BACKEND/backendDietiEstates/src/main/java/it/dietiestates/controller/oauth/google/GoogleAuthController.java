package it.dietiestates.controller.oauth.google;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.net.URI;

@Path("auth/google")
@Produces(MediaType.APPLICATION_JSON)
public class GoogleAuthController {
    private static final String CLIENT_ID = "your-google-client-id";
    private static final String REDIRECT_URI = "http://localhost:8080/auth/google/callback";
    private static final String AUTH_URL = "https://accounts.google.com/o/oauth2/auth";

    @GET
    public Response redirectToGoogle() {
        String url = AUTH_URL + "?response_type=code"
                + "&client_id=" + CLIENT_ID
                + "&redirect_uri=" + REDIRECT_URI
                + "&scope=openid email profile";

        return Response.seeOther(URI.create(url)).build();
    }
}

