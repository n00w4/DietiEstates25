package it.dietiestates.controller.oauth.github;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.net.URI;

@Path("auth/github")
@Produces(MediaType.APPLICATION_JSON)
public class GithubAuthController {
    private static final String CLIENT_ID = "Ov23liQUbv2UNe6YL5dp";
    private static final String REDIRECT_URI = "http://localhost:8080/api/auth/github/callback";
    private static final String GITHUB_AUTH_URL = "https://github.com/login/oauth/authorize";

    @GET
    public Response redirectToGitHub() {
        String authUrl = GITHUB_AUTH_URL +
                "?client_id=" + CLIENT_ID +
                "&redirect_uri=" + REDIRECT_URI +
                "&scope=user";
        return Response.seeOther(URI.create(authUrl)).build();
    }
}


