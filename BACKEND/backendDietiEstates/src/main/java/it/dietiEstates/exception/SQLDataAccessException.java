package it.dietiEstates.exception;

public class SQLDataAccessException extends DataAccessException {
	private static final long serialVersionUID = -7738528352596875780L;

	public SQLDataAccessException(String message) {
        super(message);
    }

    public SQLDataAccessException(String message, Throwable cause) {
        super(message, cause);
    }
}
