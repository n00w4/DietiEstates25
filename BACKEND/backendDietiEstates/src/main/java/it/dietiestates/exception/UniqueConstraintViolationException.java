package it.dietiestates.exception;

public class UniqueConstraintViolationException extends DataAccessException {
    public UniqueConstraintViolationException(String message) {
        super(message);
    }
    public UniqueConstraintViolationException(String message, Throwable cause) {
        super(message, cause);
    }
}
