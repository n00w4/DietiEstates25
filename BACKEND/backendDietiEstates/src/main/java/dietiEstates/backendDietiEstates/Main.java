package dietiEstates.backendDietiEstates;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import java.io.IOException;
import java.net.URI;

public class Main {
    // Base URI the Grizzly HTTP server will listen on
    public static final String BASE_URI = "http://localhost:8080/api/";

    /**
     * Starts Grizzly HTTP server exposing JAX-RS resources defined in this application.
     * @return Grizzly HTTP server.
     */
    public static HttpServer startServer() {
        // Use the RESTConfig class to configure resources and filters
        final ResourceConfig rc = new dietiEstates.backendDietiEstates.Config.RESTConfig();

        // Create and start a new instance of Grizzly HTTP server
        return GrizzlyHttpServerFactory.createHttpServer(URI.create(BASE_URI), rc);
    }

    /**
     * Main method.
     * @param args Command line arguments
     * @throws IOException If an I/O error occurs
     */
    public static void main(String[] args) throws IOException {
        // Start the server
        final HttpServer server = startServer();
        System.out.println(String.format(
                "Jersey app started with endpoints available at %s%nHit Ctrl-C to stop it...",
                BASE_URI));

        // Wait for user input to stop the server
        try {
            System.in.read();
        } catch (IOException e) {
            System.err.println("Error waiting for input: " + e.getMessage());
        } finally {
            server.shutdownNow(); // Gracefully stop the server
            System.out.println("Server stopped.");
        }
    }
}



