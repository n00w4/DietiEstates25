package it.dietiestates.controller.oauth.github;

import it.dietiestates.controller.oauth.OAuthCallbackController;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("auth/github/callback")
@Produces(MediaType.APPLICATION_JSON)
public class GithubOAuthCallbackController extends OAuthCallbackController {
    private static final String CLIENT_ID = "Ov23liQUbv2UNe6YL5dp";
    private static final String CLIENT_SECRET = System.getenv("GITHUB_CLIENT_SECRET");
    private static final String TOKEN_URL = "https://github.com/login/oauth/access_token";
    private static final String USER_INFO_URL = "https://api.github.com/user";
    private static final String REDIRECT_URI = "http://localhost:8080/api/auth/github/callback";

    @Override
    protected String getTokenUrl() {
        return TOKEN_URL;
    }

    @Override
    protected String getClientId() {
        return CLIENT_ID;
    }

    @Override
    protected String getClientSecret() {
        return CLIENT_SECRET;
    }

    @Override
    protected String getUserInfoUrl() {
        return USER_INFO_URL;
    }

    @Override
    protected String getRedirectUri() {
        return REDIRECT_URI;
    }
}

