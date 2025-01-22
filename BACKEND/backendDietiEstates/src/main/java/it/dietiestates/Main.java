package it.dietiestates;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import it.dietiestates.restconfig.RESTConfig;

import java.io.IOException;
import java.net.URI;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {
    // Base URI the Grizzly HTTP server will listen on
    public static final String BASE_URI = "http://0.0.0.0:8080/";
    // TODO: valutare se passare ad HTTPS
    // TODO: aggiungere versioning api (adesso risulterebbe controproducente per fini di testing)

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
    public static void main(String[] args) throws IOException {
        logger.setLevel(Level.INFO);

        // Start the server
        final HttpServer server = startServer();

        logger.info("Jersey app started with endpoints available at " + BASE_URI);
        logger.info("Input something in the console to stop it...");

        // Wait for user input to stop the server
        try {
            System.in.read();
        } catch (IOException e) {
            logger.severe("Error waiting for input: " + e.getMessage());
        } finally {
            server.shutdownNow(); // Gracefully stop the server
            logger.info("Server stopped.");
        }
    }
}
