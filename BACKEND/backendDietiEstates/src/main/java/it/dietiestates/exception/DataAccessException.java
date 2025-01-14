package it.dietiestates.exception;

/**
 * Classe base per tutte le eccezioni legate all'accesso ai dati.
 * Può essere estesa per rappresentare errori specifici.
 */
public class DataAccessException extends ApiException {
    public DataAccessException(String message) {
        super(message);
    }
    public DataAccessException(String message, Throwable cause) {
        super(message, cause);
    }
}
