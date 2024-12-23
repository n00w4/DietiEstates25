package it.dietiestates.restconfig;

import jakarta.ws.rs.ApplicationPath;
import org.glassfish.jersey.server.ResourceConfig;

@ApplicationPath("api")
public class RESTConfig extends ResourceConfig {
    public RESTConfig() {
        packages("dietiEstates.backendDietiEstates");
    }
}

