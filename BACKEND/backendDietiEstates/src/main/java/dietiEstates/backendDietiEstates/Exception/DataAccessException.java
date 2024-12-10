package dietiEstates.backendDietiEstates.Exception;

/**
 * Classe base per tutte le eccezioni legate all'accesso ai dati.
 * Può essere estesa per rappresentare errori specifici, come SQL o NoSQL.
 */
public class DataAccessException extends Exception {

    // Costruttore con solo il messaggio
    public DataAccessException(String message) {
        super(message);
    }

    // Costruttore con messaggio e causa
    public DataAccessException(String message, Throwable cause) {
        super(message, cause);
    }
}
