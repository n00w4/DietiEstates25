package it.dietiestates;

import it.dietiestates.restconfig.RESTConfig;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import java.net.URI;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {
    // Base URI the Grizzly HTTP server will listen on
    public static final String BASE_URI = "http://0.0.0.0:8080/";

    // Creazione di un logger
    private static final Logger logger = Logger.getLogger(Main.class.getName());

    /**
     * Starts Grizzly HTTP server exposing JAX-RS resources defined in this application.
     * @return Grizzly HTTP server.
     */
    public static HttpServer startServer() {
        // Use the RESTConfig class to configure resources and filters
        final ResourceConfig rc = new RESTConfig();

        // Create and start a new instance of Grizzly HTTP server
        return GrizzlyHttpServerFactory.createHttpServer(URI.create(BASE_URI), rc);
    }

    /**
     * Main method.
     * @param args Command line arguments
     */
    public static void main(String[] args) {
        logger.setLevel(Level.INFO);

        // Start the server
        startServer();

        logger.info("Jersey app started with endpoints available at " + BASE_URI);
        logger.info("Input something in the console to stop it...");

        // Wait for user input to stop the server
        try {
            Thread.currentThread().join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
