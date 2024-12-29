package it.dietiestates.exception;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Classe base per tutte le eccezioni legate all'accesso ai dati.
 * Può essere estesa per rappresentare errori specifici, come SQL o NoSQL.
 */
public class DataAccessException extends Exception {
    private static final long serialVersionUID = 6890412775732099914L;
    private static final Logger logger = Logger.getLogger(DataAccessException.class.getName());

    public DataAccessException() {
        super("Errore generico durante l'accesso ai dati");
    }

	// Costruttore con solo il messaggio
    public DataAccessException(String message) {
        super(message);
        logger.log(Level.SEVERE, message);
    }

    // Costruttore con messaggio e causa
    public DataAccessException(String message, Throwable cause) {
        super(message, cause);
        logger.log(Level.SEVERE, message, cause);
    }
}
