package it.dietiestates.controller.oauth.google;

import it.dietiestates.controller.oauth.OAuthController;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("auth/google")
@Produces(MediaType.APPLICATION_JSON)
public class GoogleOAuthController extends OAuthController {
    private static final String CLIENT_ID = "112156935328-gd61v5j6q70h3idigvpn4v5cgnbi0id1.apps.googleusercontent.com";
    private static final String REDIRECT_URI = "http://localhost:8080/api/auth/google/callback";
    private static final String GOOGLE_AUTH_URL = "https://accounts.google.com/o/oauth2/auth";
    private static final String SCOPE = "openid+email+profile";

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
        return GOOGLE_AUTH_URL;
    }

    @Override
    protected String getScope() {
        return SCOPE;
    }
}

