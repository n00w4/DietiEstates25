package it.dietiestates.exception;

public class ForeignKeyConstraintViolationException extends DataAccessException {
    public ForeignKeyConstraintViolationException(String message) {
        super(message);
    }

    public ForeignKeyConstraintViolationException(String message, Throwable cause) {
        super(message, cause);
    }
}
