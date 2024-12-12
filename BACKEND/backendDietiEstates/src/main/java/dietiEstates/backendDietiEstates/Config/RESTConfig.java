package dietiEstates.backendDietiEstates.Config;

import jakarta.ws.rs.ApplicationPath;
import dietiEstates.backendDietiEstates.Filter.JWTAuthFilter;
import org.glassfish.jersey.server.ResourceConfig;

@ApplicationPath("/api") // Indica il prefisso degli endpoint REST
public class RESTConfig extends ResourceConfig {
    
    public RESTConfig() {
        // Registra i filtri, provider e risorse
        packages("dietiEstates.backendDietiEstates.Resources");
        register(JWTAuthFilter.class); // Registra il filtro per autenticazione JWT
    }
}

