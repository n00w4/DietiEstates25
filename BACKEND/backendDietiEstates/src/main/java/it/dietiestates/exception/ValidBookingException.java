package it.dietiestates.exception;

public class ValidBookingException extends DataAccessException {
    public ValidBookingException(String message) {
        super(message);
    }

    public ValidBookingException(String message, Throwable cause) {
        super(message, cause);
    }
}
