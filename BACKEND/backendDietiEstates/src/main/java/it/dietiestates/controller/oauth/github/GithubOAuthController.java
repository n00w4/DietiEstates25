package it.dietiestates.controller.oauth.github;

import it.dietiestates.controller.oauth.OAuthController;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("auth/github")
@Produces(MediaType.APPLICATION_JSON)
public class GithubOAuthController extends OAuthController {
    private static final String CLIENT_ID = "Ov23liQUbv2UNe6YL5dp";
    private static final String REDIRECT_URI = "http://10.0.2.2:8080/api/auth/github/callback";
    private static final String GITHUB_AUTH_URL = "https://github.com/login/oauth/authorize";
    private static final String SCOPE = "user";

    @Override
    protected String getClientId() {
        return CLIENT_ID;
    }

    @Override
    protected String getRedirectUri() {
        return REDIRECT_URI;
    }

    @Override
    protected String getAuthUrl() {
        return GITHUB_AUTH_URL;
    }

    @Override
    protected String getScope() {
        return SCOPE;
    }
}



