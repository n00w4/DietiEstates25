package it.dietiestates.controller.oauth.google;

import it.dietiestates.controller.oauth.OAuthCallbackController;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("auth/google/callback")
@Produces(MediaType.APPLICATION_JSON)
public class GoogleOAuthCallbackController extends OAuthCallbackController {
    private static final String CLIENT_ID = "112156935328-gd61v5j6q70h3idigvpn4v5cgnbi0id1.apps.googleusercontent.com";
    private static final String CLIENT_SECRET = System.getenv("GOOGLE_CLIENT_SECRET");
    private static final String TOKEN_URL = "https://oauth2.googleapis.com/token";
    private static final String USER_INFO_URL = "https://www.googleapis.com/oauth2/v2/userinfo";
    private static final String REDIRECT_URI = "http://localhost:8080/api/auth/google/callback";

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

