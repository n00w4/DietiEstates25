package it.dietiestates.exception;

public class OverlappingBookingException extends DataAccessException {
    public OverlappingBookingException(String message) {
        super(message);
    }

    public OverlappingBookingException(String message, Throwable cause) {
        super(message, cause);
    }
}
