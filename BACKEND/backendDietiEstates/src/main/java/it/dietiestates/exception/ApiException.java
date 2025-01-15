package it.dietiestates.exception;

import it.dietiestates.dto.ApiResponse;
import it.dietiestates.dto.ErrorApiResponse;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Classe base per tutte le eccezioni dell'API.
 * Contiene dettagli standard per la risposta delle API.
 */
public class ApiException extends Exception {
    private static final long serialVersionUID = 1L;

    private final transient ErrorApiResponse apiResponse;
    private static final Logger logger = Logger.getLogger(ApiException.class.getName());

    public ApiException(String message) {
        super(message);
        logger.log(Level.SEVERE, message);
        this.apiResponse = new ErrorApiResponse(message);
    }

    public ApiException(String message, Throwable cause) {
        super(message, cause);
        logger.log(Level.SEVERE, message, cause);
        this.apiResponse = new ErrorApiResponse(message);
    }

    public ApiResponse getApiResponse() {
        return apiResponse;
    }
}



